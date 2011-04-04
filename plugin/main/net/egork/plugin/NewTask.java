package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class NewTask extends AnAction {
	public void actionPerformed(final AnActionEvent event) {
		TaskConfiguration returnedConfiguration = TaskConfigurationDialog.editConfiguration(event.getInputEvent().getComponent());
		if (returnedConfiguration == null)
			return;
		ConfigurationHolder.getInstance().setLastConfiguration(returnedConfiguration);
		createSourceStub(returnedConfiguration);
	}

	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(Util.isCurrentProjectAlgo(e));
	}

	private void createSourceStub(final TaskConfiguration configuration) {
		String taskID = configuration.getTaskID();
		Util.saveSourceFile("main", taskID + ".java", configuration.generateCodeStub());
		Util.saveSourceFile("main", taskID + "Checker.java", configuration.generateChecker());
		Util.saveConfiguration("main", taskID + ".task", configuration);
	}
}
