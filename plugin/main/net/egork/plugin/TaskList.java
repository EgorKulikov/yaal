package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TaskList extends AnAction {
	@Override
	public boolean displayTextInToolbar() {
		return true;
	}

	@Override
	public void update(AnActionEvent e) {
		final TaskConfiguration currentTask = ConfigurationHolder.getInstance().getCurrentTask();
		e.getPresentation().setText(currentTask == null ? "No task" : currentTask.getTaskID());
		e.getPresentation().setEnabled(Util.getProject() != null && ConfigurationHolder.getInstance().getCurrentTask() != null);
	}

	@Override
	public void actionPerformed(AnActionEvent anActionEvent) {
		TaskConfiguration selected = SelectTaskDialog.selectTask();
		ConfigurationHolder.getInstance().setCurrentTask(selected);
	}
}
