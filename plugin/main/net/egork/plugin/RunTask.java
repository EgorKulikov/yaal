package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class RunTask extends AnAction {
	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(Util.isCurrentProjectAlgo(e) && ConfigurationHolder.getInstance().getCurrentTask() != null);
	}

	public void actionPerformed(final AnActionEvent e) {
		TaskConfiguration configuration = ConfigurationHolder.getInstance().getCurrentTask();
		String taskID = configuration.getTaskID();
		Util.synchronizeFile("main/" + taskID + ".java");
		if (configuration.isTopCoder()) {
			TopCoderConfiguration tcConfiguration = (TopCoderConfiguration) configuration;
			String source = tcConfiguration.generateSource();
			if (source == null)
				return;
			Util.saveSourceFile("main", "TopCoder.java", tcConfiguration.generateMain());
			Util.saveSourceFile("topcoder", "TopCoder.java", tcConfiguration.generateMain());
			Util.saveSourceFile("topcoder", taskID + ".java", tcConfiguration.generateSource());
			Util.eliminateUnusedCode("topcoder/" + taskID + ".java");
			return;
		}
		String[] sources = configuration.generateFullSource("main/" + taskID + ".java", "main/" + taskID + "Checker.java");
		if (sources == null)
			return;
		Util.saveSourceFile("test", "Main.java", sources[0]);
		Util.saveSourceFile("test", "MainChecker.java", sources[1]);
		Util.saveSourceFile("main", "Main.java", configuration.generateMainRunEnvironment());
		Util.saveSourceFile("test", "Tests.java", configuration.generateTests());
		Util.saveSourceFile("main", "Tests.java", configuration.generateMainTests());
		Util.eliminateUnusedCode("test/Main.java");
	}
}
