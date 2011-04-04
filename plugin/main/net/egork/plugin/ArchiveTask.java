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
		Calendar currentDate = Calendar.getInstance();
		String packageName = "net.egork.y" + currentDate.get(Calendar.YEAR) + ".m" +
			(currentDate.get(Calendar.MONTH) + 1) + ".d" + currentDate.get(Calendar.DAY_OF_MONTH);
		sourceCode = "package " + packageName + ";\n\n" + sourceCode;
		String filePath = "archive/" + packageName.replace('.', '/');
		Util.saveSourceFile(filePath, taskID + ".java", sourceCode);
		Util.saveConfiguration(filePath, taskID + ".task", configuration);
		Util.removeFile("main/" + taskID + ".java");
		Util.removeFile("main/" + taskID + ".task");
		Util.removeFile("main/" + taskID + "Checker.java");
	}
}
