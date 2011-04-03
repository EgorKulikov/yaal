package net.egork.plugin;

import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import net.egork.utils.test.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TestEditorDialog extends JDialog {
	private TaskConfiguration configuration;
	private List<Test> tests;
	private JTextArea input;
	private JTextArea output;
	private JBList testList;
	private int currentTest = -1;

	private TestEditorDialog() {
		super((Frame)null, true);
		configuration = ConfigurationHolder.getInstance().getCurrentTask();
		tests = new ArrayList<Test>(configuration.getTests());
		testList = new JBList(tests.toArray());
		testList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = testList.locationToIndex(e.getPoint());
				if (index >= 0 && index < tests.size()) {
					saveState();
					input.setText(tests.get(index).getInput());
					output.setText(tests.get(index).getExpectedOutput());
					currentTest = index;
					input.setEnabled(true);
					output.setEditable(true);
				}
			}
		});

		input = new JTextArea();
		output = new JTextArea();
		JBScrollPane scrollTestList = new JBScrollPane(testList, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JBScrollPane scrollInput = new JBScrollPane(input);
		JBScrollPane scrollOutput = new JBScrollPane(output);
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveState();
				configuration = configuration.setTests(tests);
				setVisible(false);
			}
		});
		JButton newTest = new JButton("New");
		newTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveState();
				tests.add(new Test("", ""));
				testList.setListData(tests.toArray());
				testList.setSelectedIndex(tests.size() - 1);
				input.setText("");
				output.setText("");
				currentTest = tests.size() - 1;
				input.setEnabled(true);
				output.setEditable(true);
			}
		});
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentTest == -1)
					return;
				tests.remove(currentTest);
				testList.setListData(tests.toArray());
				input.setText("");
				output.setText("");
				currentTest = -1;
				input.setEnabled(false);
				output.setEditable(false);
			}
		});
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		buttonPanel.add(save);
		buttonPanel.add(newTest);
		buttonPanel.add(remove);
		JPanel left = new JPanel(new BorderLayout(5, 5));
		left.add(scrollTestList, BorderLayout.CENTER);
		left.add(buttonPanel, BorderLayout.SOUTH);
		JPanel right = new JPanel(new GridLayout(2, 1, 5, 5)) {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(300, 300);
			}
		};
		right.add(scrollInput);
		right.add(scrollOutput);
		JPanel main = new JPanel(new BorderLayout(5, 5));
		main.add(left, BorderLayout.WEST);
		main.add(right, BorderLayout.CENTER);
		setContentPane(main);
		setLocation(200, 200);
		pack();
	}

	private void saveState() {
		if (currentTest == -1)
			return;
		tests.set(currentTest, new Test(input.getText(), output.getText()));
		testList.setListData(tests.toArray());
	}

	public static TaskConfiguration editTests() {
		TestEditorDialog dialog = new TestEditorDialog();
		dialog.setVisible(true);
		return dialog.configuration;
	}
}
