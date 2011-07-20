package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class RestoreTask extends AnAction {
	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(Util.isCurrentProjectAlgo(e));
	}
	
	public void actionPerformed(AnActionEvent e) {
		List<TaskConfiguration> tasks = Util.loadTasks("archive/unsorted");
		if (tasks.isEmpty())
			return;
		Collections.sort(tasks, new Comparator<TaskConfiguration>() {
			public int compare(TaskConfiguration o1, TaskConfiguration o2) {
				return o1.getTaskID().compareTo(o2.getTaskID());
			}
		});
		TaskConfiguration toRestore = SelectTaskDialog.selectTaskToRestore(e.getInputEvent().getComponent(),
			tasks.toArray(new TaskConfiguration[tasks.size()]));
		if (toRestore == null)
			return;
		String taskID = toRestore.getTaskID();
		String sourceCode = Util.loadSourceFile("archive/unsorted/" + taskID + ".java");
		String checkerCode = Util.loadSourceFile("archive/unsorted/" + taskID + "Checker.java");
		if (sourceCode != null) {
			Util.saveSourceFile("main", taskID + ".java", sourceCode);
			Util.removeFile("archive/unsorted/" + taskID + ".java");
		}
		if (checkerCode != null) {
			Util.saveSourceFile("main", taskID + "Checker.java", checkerCode);
			Util.removeFile("archive/unsorted/" + taskID + "Checker.java");
		}
		Util.saveConfiguration("main", taskID + ".task", toRestore);
		Util.removeFile("archive/unsorted/" + taskID + ".task");
		if (!toRestore.isTopCoder()) {
			int id = Math.abs((sourceCode + checkerCode).replaceAll("[\n\r ]", "").hashCode());
			String path = "lib/test/generated/test" + id;
			VirtualFile testDirectory = Util.getFile(path);
			if (testDirectory == null)
				return;
			for (VirtualFile file : testDirectory.getChildren())
				Util.removeFile(path + "/" + file.getName());
			Util.removeFile(path);
		}
	}
}
