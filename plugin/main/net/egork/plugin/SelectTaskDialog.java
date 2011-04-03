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

	private SelectTaskDialog() {
		super(null, ModalityType.DOCUMENT_MODAL);
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
//		setLocation(700, 70);
	}

	public static TaskConfiguration selectTask(Component component) {
		SelectTaskDialog dialog = new SelectTaskDialog();
		Point location = new Point(component.getX(), component.getY() + component.getHeight());
		SwingUtilities.convertPointToScreen(location, component);
		location.x -= component.getLocation().x;
		location.y -= component.getLocation().y;
		dialog.setLocation(location);
		dialog.setVisible(true);
		return dialog.selectedTask;
	}
}
