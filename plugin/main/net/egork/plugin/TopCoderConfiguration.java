package net.egork.plugin;

import java.util.HashSet;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TopCoderConfiguration extends TaskConfiguration {
	private static final long serialVersionUID = 100500L;

	public TopCoderConfiguration(String taskID) {
		super(taskID, TestType.SINGLE, InputType.STDIN, InputType.STDIN);
	}

	@Override
	public boolean isTopCoder() {
		return true;
	}

	public String generateSource() {
		String source = Util.loadSourceFile("main/" + getTaskID() + ".java");
		if (source == null)
			return null;
		source = inlineImports("", new HashSet<String>(), source).toString();
		return source;
	}

	public String generateMain() {
		StringBuilder main = new StringBuilder();
		main.append("public class TopCoder {\n");
		main.append("\tpublic static void main(String[] args) {\n");
		main.append("\t\t").append(getTaskID()).append(".main(args);\n");
		main.append("\t}\n");
		main.append("}\n");
		return main.toString();
	}

	public void commentMainIfNeeded() {
		String mainSource = Util.loadSourceFile("main/TopCoder.java");
		if (mainSource.contains("\t" + getTaskID() + ".main")) {
			StringBuilder main = new StringBuilder();
			main.append("public class TopCoder {\n");
			main.append("\tpublic static void main(String[] args) {\n");
			main.append("\t}\n");
			main.append("}\n");
			Util.saveSourceFile("main", "TopCoder.java", main.toString());
			Util.saveSourceFile("topcoder", "TopCoder.java", main.toString());
		}
	}
}
