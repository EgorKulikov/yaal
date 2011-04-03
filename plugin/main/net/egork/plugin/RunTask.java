package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class RunTask extends AnAction {
	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(Util.getProject() != null && ConfigurationHolder.getInstance().getCurrentTask() != null);
	}

	public void actionPerformed(final AnActionEvent e) {
		TaskConfiguration configuration = ConfigurationHolder.getInstance().getCurrentTask();
		String taskID = configuration.getTaskID();
		String mainSource = configuration.generateFullSource("main/" + taskID + ".java", "main/" + taskID + "Checker.java");
		if (mainSource == null)
			return;
		Util.saveSourceFile("test", "Main.java", mainSource);
		Util.saveSourceFile("main", "Main.java", configuration.generateMainRunEnvironment());
		Util.saveSourceFile("test", "Tests.java", configuration.generateTests());
		Util.saveSourceFile("main", "Tests.java", configuration.generateTests());
	}
}
