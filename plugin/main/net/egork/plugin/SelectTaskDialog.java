package net.egork.plugin;

import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SelectTaskDialog extends JDialog {
	private TaskConfiguration selectedTask;

	private SelectTaskDialog() {
		super((Frame)null, true);
		selectedTask = ConfigurationHolder.getInstance().getCurrentTask();
		final TaskConfiguration[] tasks = ConfigurationHolder.getInstance().getTasks();
		final JBList taskList = new JBList(tasks);
		taskList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = taskList.locationToIndex(e.getPoint());
				if (index >= 0 && index < tasks.length) {
					selectedTask = tasks[index];
					setVisible(false);
				}
			}
		});
		setContentPane(taskList);
		pack();
		setLocation(700, 70);
	}

	public static TaskConfiguration selectTask() {
		SelectTaskDialog dialog = new SelectTaskDialog();
		dialog.setVisible(true);
		return dialog.selectedTask;
	}
}
