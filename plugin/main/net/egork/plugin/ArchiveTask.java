package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.Calendar;

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
		String packageName = "unsorted";
		if (sourceCode != null)
			sourceCode = "package " + packageName + ";\n\n" + sourceCode;
		if (checkerCode != null)
			checkerCode = "package " + packageName + ";\n\n" + checkerCode;
		String filePath = "archive/" + packageName.replace('.', '/');
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
		}
	}
}
