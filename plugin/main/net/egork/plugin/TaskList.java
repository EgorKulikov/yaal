package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

import java.awt.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TaskList extends AnAction {
	private final Map<Presentation, AnActionEvent> presentations = new HashMap<Presentation, AnActionEvent>();
	private final Set<Component> components = new HashSet<Component>();

	@Override
	public boolean displayTextInToolbar() {
		return true;
	}

	@Override
	public void update(AnActionEvent e) {
		TaskConfiguration currentTask = ConfigurationHolder.getInstance().getCurrentTask();
		presentations.put(e.getPresentation(), e);
		if (e.getInputEvent() != null && e.getInputEvent().getComponent() != null) {
			components.add(e.getInputEvent().getComponent());
		}
		update(currentTask);
	}

	@Override
	public void actionPerformed(AnActionEvent e) {
		TaskConfiguration selected = SelectTaskDialog.selectTask(e.getInputEvent().getComponent());
		ConfigurationHolder.getInstance().setCurrentTask(selected);
		components.add(e.getInputEvent().getComponent());
	}

	@Override
	public boolean isTransparentUpdate() {
		return true;
	}

	public void update(TaskConfiguration currentTask) {
		String taskName = currentTask == null ? "No task" : currentTask.getTaskID().length() <= 13 ?
			currentTask.getTaskID() : currentTask.getTaskID().substring(0, 10) + "...";
		for (Presentation presentation : presentations.keySet()) {
			presentation.setText("");
			presentation.setText(taskName);
			presentation.setEnabled(Util.isCurrentProjectAlgo(presentations.get(presentation)) && currentTask != null);
		}
		for (Component component : components) {
			if (component.isDisplayable())
				component.repaint();
			else
				components.remove(component);
		}
	}
}
