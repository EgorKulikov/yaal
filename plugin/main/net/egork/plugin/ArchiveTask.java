package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ArchiveTask extends AnAction {
	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(Util.isCurrentProjectAlgo(e) && ConfigurationHolder.getInstance().getCurrentTask() != null);
	}

	public void actionPerformed(AnActionEvent e) {
		TaskConfiguration configuration = ConfigurationHolder.getInstance().getCurrentTask();
		if (configuration == null)
			return;
		String taskID = configuration.getTaskID();
		String sourceCode = Util.loadSourceFile("main/" + taskID + ".java");
		String checkerCode = Util.loadSourceFile("main/" + taskID + "Checker.java");
		String filePath = "archive/unsorted";
		if (sourceCode != null)
			Util.saveSourceFile(filePath, taskID + ".java", sourceCode);
		if (checkerCode != null)
			Util.saveSourceFile(filePath, taskID + "Checker.java", checkerCode);
		Util.saveConfiguration(filePath, taskID + ".task", configuration);
		Util.removeFile("main/" + taskID + ".java");
		Util.removeFile("main/" + taskID + ".task");
		Util.removeFile("main/" + taskID + "Checker.java");
		if (configuration.isTopCoder()) {
			Util.removeFile("topcoder/" + taskID + ".java");
			((TopCoderConfiguration)configuration).commentMainIfNeeded();
		} else {
			Util.saveSourceFile("main", "Main.java", configuration.generateMainStub());
			configuration.generateUnitTest(sourceCode, checkerCode);
		}
	}
}
