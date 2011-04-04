package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class EditTests extends AnAction {
	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(Util.isCurrentProjectAlgo(e) && ConfigurationHolder.getInstance().getCurrentTask() != null);
	}

	public void actionPerformed(AnActionEvent e) {
		TaskConfiguration current = TestEditorDialog.editTests(e.getInputEvent().getComponent());
		Util.saveConfiguration("main", current.getTaskID() + ".task", current);
	}
}
