package net.egork.plugin;

import com.intellij.openapi.ui.VerticalFlowLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TaskConfigurationDialog extends JDialog {
	private TaskConfiguration configuration;
	private TaskConfiguration returnedConfiguration = null;
	private JComboBox predefinedConfigurations;
	private JTextField taskID;
	private JComboBox testType;
	private JComboBox inputType;
	private JTextField customInputFileName;
	private JComboBox outputType;
	private JTextField customOutputFileName;

	private TaskConfigurationDialog(TaskConfiguration configuration) {
		super((Frame)null, true);
		if (configuration == null)
			configuration = TaskConfiguration.CODE_FORCES;
		this.configuration = configuration;
		predefinedConfigurations = new JComboBox(new Object[]{
			TaskConfiguration.CODE_FORCES,
			TaskConfiguration.CODE_CHEF,
			TaskConfiguration.OPEN_CUP
		});
		predefinedConfigurations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyConfiguration((TaskConfiguration) predefinedConfigurations.getSelectedItem());
			}
		});
		taskID = new JTextField();
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskConfigurationDialog.this.configuration = constructConfiguration();
				customInputFileName.setEnabled(TaskConfigurationDialog.this.configuration.getInputType() ==
					TaskConfiguration.InputType.FILE_CUSTOM);
				customOutputFileName.setEnabled(TaskConfigurationDialog.this.configuration.getOutputType() ==
					TaskConfiguration.InputType.FILE_CUSTOM);
			}
		};
		taskID.addActionListener(actionListener);
		testType = new JComboBox(TaskConfiguration.TestType.values());
		testType.addActionListener(actionListener);
		inputType = new JComboBox(TaskConfiguration.InputType.values());
		inputType.addActionListener(actionListener);
		customInputFileName = new JTextField();
		customInputFileName.addActionListener(actionListener);
		outputType = new JComboBox(TaskConfiguration.InputType.values());
		outputType.addActionListener(actionListener);
		customOutputFileName = new JTextField();
		customOutputFileName.addActionListener(actionListener);
		JPanel panel = new JPanel(new VerticalFlowLayout());
		panel.add(predefinedConfigurations);
		panel.add(taskID);
		panel.add(testType);
		panel.add(inputType);
		panel.add(customInputFileName);
		panel.add(outputType);
		panel.add(customOutputFileName);
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JButton ok = new JButton("OK");
		buttonPanel.add(ok, BorderLayout.WEST);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnedConfiguration = constructConfiguration();
				setVisible(false);
			}
		});
		JButton cancel = new JButton("Cancel");
		buttonPanel.add(cancel, BorderLayout.EAST);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnedConfiguration = null;
				setVisible(false);
			}
		});
		panel.add(buttonPanel);
		setContentPane(panel);
		pack();
		applyConfiguration(configuration);
		getRootPane().setDefaultButton(ok);
	}

	@Override
	public void setVisible(boolean b) {
		if (b) {
			taskID.requestFocusInWindow();
			taskID.setSelectionStart(0);
			taskID.setSelectionEnd(taskID.getText().length());
		}
		super.setVisible(b);
	}

	public static TaskConfiguration editConfiguration(Component component) {
		TaskConfigurationDialog dialog = new TaskConfigurationDialog(ConfigurationHolder.getInstance().getLastConfiguration());
		dialog.setLocation(Util.getLocation(component));
		dialog.setVisible(true);
		return dialog.returnedConfiguration;
	}

	private void applyConfiguration(TaskConfiguration current) {
		taskID.setText(current.getTaskID());
		testType.setSelectedItem(current.getTestType());
		inputType.setSelectedItem(current.getInputType());
		customInputFileName.setText(current.getCustomInputFilename());
		outputType.setSelectedItem(current.getOutputType());
		customOutputFileName.setText(current.getCustomOutputFilename());
		configuration = current;
		customInputFileName.setEnabled(configuration.getInputType() == TaskConfiguration.InputType.FILE_CUSTOM);
		customOutputFileName.setEnabled(
			configuration.getOutputType() == TaskConfiguration.InputType.FILE_CUSTOM);
	}

	private TaskConfiguration constructConfiguration() {
		return new TaskConfiguration(taskID.getText(), (TaskConfiguration.TestType) testType.getSelectedItem(),
			(TaskConfiguration.InputType) inputType.getSelectedItem(), customInputFileName.getText(),
			(TaskConfiguration.InputType) outputType.getSelectedItem(), customOutputFileName.getText());
	}
}
