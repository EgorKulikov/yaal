package net.egork.plugin;

import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SelectTaskDialog extends JDialog {
	private TaskConfiguration selectedTask;

	private SelectTaskDialog(final TaskConfiguration[] tasks, TaskConfiguration defaultTask) {
		super(null, ModalityType.DOCUMENT_MODAL);
		selectedTask = defaultTask;
		final JBList taskList = new JBList(tasks);
		taskList.setSelectedValue(selectedTask, true);
		taskList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = taskList.locationToIndex(e.getPoint());
				if (index >= 0 && index < tasks.length) {
					selectedTask = tasks[index];
					setVisible(false);
				} else
					setVisible(false);
			}
		});
		taskList.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int index = taskList.locationToIndex(e.getPoint());
				if (index >= 0 && index < tasks.length) {
					taskList.setSelectedIndex(index);
				}
			}
		});
		taskList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
                setVisible(false);
			}
		});
		setUndecorated(true);
		setContentPane(taskList);
		pack();
	}

	public static TaskConfiguration selectTask(Component component) {
		SelectTaskDialog dialog = new SelectTaskDialog(ConfigurationHolder.getInstance().getTasks(), ConfigurationHolder.getInstance().getCurrentTask());
		dialog.setLocation(Util.getLocation(component));
		dialog.setVisible(true);
		return dialog.selectedTask;
	}

	public static TaskConfiguration selectTaskToRestore(Component component, TaskConfiguration[] tasks) {
		SelectTaskDialog dialog = new SelectTaskDialog(tasks, null);
		dialog.setLocation(Util.getLocation(component));
		dialog.setVisible(true);
		return dialog.selectedTask;
	}
}
