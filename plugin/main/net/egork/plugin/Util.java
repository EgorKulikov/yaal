package net.egork.plugin;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import net.egork.utils.io.streaminputreader.StreamInputReader;

import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.InputMismatchException;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Util {
	public static Project getProject() {
		for (Project project : ProjectManager.getInstance().getOpenProjects()) {
			if (project.getName().contains("algo"))
				return project;
		}
		return null;
	}

	public static boolean isCurrentProjectAlgo(AnActionEvent event) {
		Project project = event.getData(DataKeys.PROJECT);
		return project != null && project.getProjectFilePath().contains("algo");
	}

	public static InputStream getInputStream(String path) {
		Project project = getProject();
		if (project == null)
			return null;
		VirtualFile baseDirectory = project.getBaseDir();
		if (baseDirectory == null)
			return null;
		VirtualFile file = baseDirectory.findFileByRelativePath(path);
		if (file == null)
			return null;
		try {
			return file.getInputStream();
		} catch (IOException e) {
			return null;
		}
	}

	public static void removeFile(final String path) {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			public void run() {
				Project project = getProject();
				if (project == null)
					return;
				VirtualFile baseDirectory = project.getBaseDir();
				if (baseDirectory == null)
					return;
				VirtualFile file = baseDirectory.findFileByRelativePath(path);
				if (file == null)
					return;
				try {
					file.delete(this);
				} catch (IOException ignored) {
				}
			}
		});
	}

	public static Point getLocation(Component component) {
		Point location = new Point(component.getX(), component.getY() + component.getHeight());
		SwingUtilities.convertPointToScreen(location, component);
		location.x -= component.getLocation().x;
		location.y -= component.getLocation().y;
		return location;
	}

	public static OutputStream getOutputStream(String path, String filename) {
		Project project = getProject();
		if (project == null)
			return null;
		VirtualFile baseDirectory = project.getBaseDir();
		if (baseDirectory == null)
			return null;
		String[] splitPath = path.split("/");
		VirtualFile directory = baseDirectory;
		for (String directoryName : splitPath) {
			VirtualFile next = directory.findChild(directoryName);
			if (next == null) {
				try {
					next = directory.createChildDirectory(filename, directoryName);
				} catch (IOException e) {
					return null;
				}
			}
			if (next == null)
				return null;
			directory = next;
		}
		VirtualFile file;
		try {
			file = directory.findOrCreateChildData(filename, filename);
			if (file == null)
				return null;
			return file.getOutputStream(filename);
		} catch (IOException e) {
			return null;
		}
	}

	public static String loadSourceFile(String path) {
		InputStream inputStream = getInputStream(path);
		if (inputStream == null)
			return null;
		StreamInputReader reader = new StreamInputReader(inputStream);
		StringBuilder source = new StringBuilder();
		try {
			//noinspection InfiniteLoopStatement
			while (true)
				source.append(reader.readLine(false)).append("\n");
		} catch (InputMismatchException e) {
			reader.close();
			return source.toString();
		}
	}

	public static void saveSourceFile(final String path, final String filename, final String source) {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			public void run() {
				try {
					OutputStream outputStream = getOutputStream(path, filename);
					if (outputStream == null)
						return;
					for (char c : source.toCharArray())
						outputStream.write(c);
					outputStream.close();
				} catch (IOException ignored) {
				}
			}
		});
	}

	public static TaskConfiguration loadConfiguration(String path) {
		InputStream inputStream = getInputStream(path);
		if (inputStream == null)
			return null;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			TaskConfiguration result = (TaskConfiguration) objectInputStream.readObject();
			objectInputStream.close();
			return result;
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public static void saveConfiguration(final String path, final String filename, final TaskConfiguration configuration) {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			public void run() {
				try {
					OutputStream outputStream = getOutputStream(path, filename);
					if (outputStream == null)
						return;
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
					objectOutputStream.writeObject(configuration);
					objectOutputStream.close();
				} catch (IOException ignored) {
				}
			}
		});
	}
}
