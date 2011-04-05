package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class DeleteTask extends AnAction {
	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(Util.isCurrentProjectAlgo(e) && ConfigurationHolder.getInstance().getCurrentTask() != null);
	}


	public void actionPerformed(AnActionEvent e) {
		TaskConfiguration configuration = ConfigurationHolder.getInstance().getCurrentTask();
		if (configuration == null)
			return;
		String taskID = configuration.getTaskID();
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + taskID + "?", "", JOptionPane.YES_NO_OPTION);
		if (result != JOptionPane.YES_OPTION)
			return;
		Util.removeFile("main/" + taskID + ".java");
		Util.removeFile("main/" + taskID + ".task");
		Util.removeFile("main/" + taskID + "Checker.java");
		if (configuration.isTopCoder())
			Util.removeFile("topcoder/" + taskID + ".java");
	}
}
