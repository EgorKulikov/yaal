package net.egork.plugin;

import com.intellij.openapi.vfs.VirtualFile;
import net.egork.utils.test.Test;

import java.io.Serializable;
import java.util.*;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TaskConfiguration implements Serializable {
	private static final long serialVersionUID = 239L;

	public static final TaskConfiguration CODE_FORCES = new TaskConfiguration("Codeforces", "Task", TestType.SINGLE, InputType.STDIN);
	public static final TaskConfiguration CODE_CHEF = new TaskConfiguration("CodeChef", "", TestType.MULTI_NUMBER, InputType.STDIN);
	public static final TaskConfiguration OPEN_CUP = new TaskConfiguration("Open Cup", " ", TestType.SINGLE, InputType.FILE_TASK_ID);

	private final String taskID;
	private final TestType testType;
	private final InputType inputType;
	private final InputType outputType;
	private final String customInputFilename;
	private final String customOutputFilename;
	private final Collection<Test> tests;
	private transient String configurationName = null;

	private TaskConfiguration(String configurationName, String taskID, TestType testType, InputType inputType) {
		this(taskID, testType, inputType, inputType);
		this.configurationName = configurationName;
	}

	public TaskConfiguration(String taskID, TestType testType, InputType inputType, InputType outputType) {
		this(taskID, testType, inputType, "input.txt", outputType, "output.txt");
	}

	public TaskConfiguration(String taskID, TestType testType, InputType inputType, String customInputFilename,
		InputType outputType, String customOutputFilename)
	{
		this(taskID, testType, inputType, customInputFilename, outputType, customOutputFilename,
			Collections.<Test>emptySet());
	}

	public TaskConfiguration(String taskID, TestType testType, InputType inputType, String customInputFilename,
		InputType outputType, String customOutputFilename, Collection<Test> tests)
	{
		this.taskID = taskID;
		this.testType = testType;
		this.inputType = inputType;
		this.outputType = outputType;
		this.customInputFilename = customInputFilename;
		this.customOutputFilename = customOutputFilename;
		this.tests = tests;
	}

	public String getTaskID() {
		return taskID;
	}

	public TestType getTestType() {
		return testType;
	}

	public InputType getInputType() {
		return inputType;
	}

	public InputType getOutputType() {
		return outputType;
	}

	public String getCustomInputFilename() {
		return customInputFilename;
	}

	public String getCustomOutputFilename() {
		return customOutputFilename;
	}

	public Collection<Test> getTests() {
		return tests;
	}

	public boolean isTopCoder() {
		return false;
	}

	public String[] generateFullSource(String solverFileName, String checkerFileName) {
		String source = Util.loadSourceFile(solverFileName);
		String checkerSource = Util.loadSourceFile(checkerFileName);
		if (source == null || checkerSource == null)
			return null;
		source = mandatoryImports() + source;
		StringBuilder additionalCode = new StringBuilder();
		additionalCode.append(generateMainClass());
		Set<String> imports = new HashSet<String>();
		String fullSource = inlineImports(additionalCode.toString(), imports, source).toString();
		String checkerImport = "import net.egork.utils.io.stringinputreader.StringInputReader;\n";
		String checkerFullSource = inlineImports(generateMainChecker(), imports, checkerImport + checkerSource).toString();
		return new String[]{fullSource, checkerFullSource};
	}

	public String generateMainChecker() {
		StringBuilder checker = new StringBuilder();
		checker.append("\nclass MainChecker {\n");
		checker.append("\tpublic static String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {\n");
		checker.append("\t\treturn new ").append(taskID).append("Checker().check(input, expectedOutput, actualOutput);\n");
		checker.append("\t}\n");
		checker.append("\n");
		checker.append("\tpublic static Collection<Test> generateTests() {\n");
		checker.append("\t\treturn new ").append(taskID).append("Checker().generateTests();\n");
		checker.append("\t}\n");
		checker.append("}\n\n");
		return checker.toString();
	}

	public String mandatoryImports() {
		StringBuilder mandatoryImports = new StringBuilder();
		mandatoryImports.append("import net.egork.utils.Exit;\n");
		mandatoryImports.append("import net.egork.utils.io.StreamInputReader;\n");
		mandatoryImports.append("import java.io.*;\n");
		mandatoryImports.append("import net.egork.utils.io.InputReader;\n");
		mandatoryImports.append("import net.egork.utils.Solver;\n");
		return mandatoryImports.toString();
	}

	public String generateMainRunEnvironment() {
		return "import net.egork.utils.test.Test;\n" + "import java.util.Collection;\n" + mandatoryImports() + generateMainClass() + generateMainChecker();
	}

	protected StringBuilder inlineImports(String additionalCode, Set<String> imports, String...sources) {
		List<String> fullSource = new ArrayList<String>();
		for (String source : sources)
			parse(source, fullSource, imports);
		StringBuilder result = new StringBuilder();
		for (String imported : imports) {
			if (!imported.startsWith("net.egork.utils.") && !Util.isLibraryClass(imported))
				result.append("import ").append(imported).append(";\n");
		}
		result.append("\n");
		result.append("/**\n");
		result.append(" * Generated by Contest helper plug-in\n");
		result.append(" * Actual solution is at the bottom\n");
		result.append(" */\n");
		result.append(additionalCode);
		for (String line : fullSource)
			result.append(line).append("\n");
		return result;
	}

	private void parse(String source, List<String> fullSource, Set<String> imports) {
		String[] lines = source.split("\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith("import")) {
				String importedClass = lines[i].substring(7, lines[i].length() - 1);
				if (!imports.contains(importedClass)) {
					if (importedClass.startsWith("java.io.") && !importedClass.substring(8).contains(".") && importedClass.length() != 9)
						continue;
					String packagePath = null;
					String packageName = importedClass.substring(0, importedClass.lastIndexOf('.'));
					if (importedClass.startsWith("net.egork.utils."))
						packagePath = "utils/main/" + packageName.replace('.', '/');
					else if (Util.isLibraryClass(importedClass))
						packagePath = "lib/main/" + packageName.replace('.', '/');
					else
						imports.add(importedClass);
					if (packagePath == null)
						continue;
					VirtualFile directory = Util.getFile(packagePath);
					if (directory == null)
						continue;
					for (VirtualFile child : directory.getChildren()) {
						String extension = child.getExtension();
						if (extension != null && extension.equals("java")) {
							String fullyQualifiedName = packageName + "." + child.getNameWithoutExtension();
							if (imports.contains(fullyQualifiedName))
								continue;
							imports.add(fullyQualifiedName);
							parse(Util.loadSourceFile(packagePath + "/" + child.getName()), fullSource, imports);
						}
					}
				}
			} else if (lines[i].startsWith("public ")) {
				if (isTopCoder() && lines[i].startsWith("public class " + taskID))
					fullSource.add(lines[i]);
				else
					fullSource.add(lines[i].substring(7));
				fullSource.addAll(Arrays.asList(lines).subList(i + 1, lines.length));
				fullSource.add("");
				return;
			}
		}
	}

	public String generateTests() {
		StringBuilder result = new StringBuilder();
		result.append("import java.io.*;\n");
		result.append("public class Tests {\n");
		result.append("\tpublic static final Test[] TESTS = {\n");
		for (Test test : tests) {
			result.append("\t\tnew Test(\"").append(escape(test.getInput())).append("\", \"").
				append(escape(test.getExpectedOutput())).append("\"),\n");
		}
		result.append("\t};\n");
		result.append("}\n");
		result = inlineImports("", new HashSet<String>(), result.toString());
		return result.toString();
	}

	public String generateMainTests() {
		StringBuilder result = new StringBuilder();
		result.append("import java.io.*;\n");
		result.append("import net.egork.utils.test.Test;\n");
		result.append("public class Tests {\n");
		result.append("\tpublic static final Test[] TESTS = {\n");
		for (Test test : tests) {
			result.append("\t\tnew Test(\"").append(escape(test.getInput())).append("\", \"").
				append(escape(test.getExpectedOutput())).append("\"),\n");
		}
		result.append("\t};\n");
		result.append("}\n");
		return result.toString();
	}

	private static String escape(String input) {
		return input.replaceAll("\\\\", "\\\\").replaceAll("\n", "\\\\n").replaceAll("\t", "\\\\t").replaceAll("\"", "\\\\\"");
	}

	public String generateMainClass() {
		StringBuilder mainClass = new StringBuilder();
		mainClass.append("public class Main {\n");
		mainClass.append("\tpublic static void main(String[] args) {\n");
		String inputFileName = getInputFileName();
		String outputFileName = getOutputFileName();
		if (inputFileName != null || outputFileName != null) {
			mainClass.append("\t\tInputReader in;\n");
			mainClass.append("\t\tPrintWriter out;\n");
			mainClass.append("\t\ttry {\n");
			if (inputFileName != null)
				mainClass.append("\t\t\tin = new StreamInputReader(new FileInputStream(\"").append(inputFileName).append("\"));\n");
			else
				mainClass.append("\t\t\tin = new StreamInputReader(System.in);\n");
			if (outputFileName != null)
				mainClass.append("\t\t\tout = new PrintWriter(new PrintStream(new FileOutputStream(\"").append(outputFileName).append("\")));\n");
			else
				mainClass.append("\t\t\tout = new PrintWriter(System.out);\n");
			mainClass.append("\t\t} catch (Exception e) {\n");
			mainClass.append("\t\t\tthrow new RuntimeException(e);\n");
			mainClass.append("\t\t}\n");
		} else {
			mainClass.append("\t\tInputReader in = new StreamInputReader(System.in);\n");
			mainClass.append("\t\tPrintWriter out = new PrintWriter(System.out);\n");
		}
		mainClass.append("\t\trun(in, out);\n");
		mainClass.append("\t}\n\n");
		mainClass.append("\tpublic static void run(InputReader in, PrintWriter out) {\n");
		mainClass.append(generateTestCode());
		mainClass.append("\t}\n");
		mainClass.append("}\n\n");
		return mainClass.toString();
	}

	public String generateMainStub() {
		StringBuilder mainClass = new StringBuilder();
		mainClass.append(mandatoryImports());
		mainClass.append("import java.util.Collection;\n");
		mainClass.append("import java.util.Collections;\n");
		mainClass.append("import net.egork.utils.test.Test;\n");
		mainClass.append("public class Main {\n");
		mainClass.append("\tpublic static void main(String[] args) {\n");
		mainClass.append("\t}\n\n");
		mainClass.append("\tpublic static void run(InputReader in, PrintWriter out) {\n");
		mainClass.append("\t}\n");
		mainClass.append("}\n\n");
		mainClass.append("\nclass MainChecker {\n");
		mainClass.append("\tpublic static String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {\n");
		mainClass.append("\t\treturn null;\n");
		mainClass.append("\t}\n");
		mainClass.append("\n");
		mainClass.append("\tpublic static Collection<Test> generateTests() {\n");
		mainClass.append("\t\treturn Collections.emptyList();\n");
		mainClass.append("\t}\n");
		mainClass.append("}\n\n");
		return mainClass.toString();
	}

	public String generateChecker() {
		return "import net.egork.utils.checker.Checker;\n" +
			"import net.egork.utils.io.InputReader;\n" +
			"import java.util.Collections;\n" +
			"import java.util.Collection;\n" +
			"import net.egork.utils.test.Test;\n" +
			"\n" +
			"public class " + taskID + "Checker extends Checker {\n" +
			"\t@Override\n" +
			"\tpublic String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {\n" +
			"\t\treturn tokenCheck(expectedOutput, actualOutput);\n" +
			"\t}\n" +
			"\n" +
			"\t@Override\n" +
			"\tpublic double getCertainty() {\n" +
			"\t\treturn 0;\n" +
			"\t}\n" +
			"\n" +
			"\t@Override\n" +
			"\tpublic Collection<Test> generateTests() {\n" +
			"\t\treturn Collections.<Test>emptyList();\n" +
			"\t}\n" +
			"}\n";
	}

	public String generateCodeStub() {
		StringBuilder codeStub = new StringBuilder();
		codeStub.append("import net.egork.utils.Solver;\n");
		codeStub.append("import net.egork.utils.io.InputReader;\n");
		codeStub.append("\n");
		codeStub.append("import java.io.PrintWriter;\n");
		codeStub.append("\n");
		codeStub.append("public class ").append(taskID).append(" implements Solver {\n");
		codeStub.append("\tpublic void solve(int testNumber, InputReader in, PrintWriter out) {\n");
		codeStub.append("\t}\n");
		codeStub.append("}\n");
		return codeStub.toString();
	}

	private String generateTestCode() {
		StringBuilder testCode = new StringBuilder();
		testCode.append("\t\tSolver solver = new ").append(taskID).append("();\n");
		if (testType == TestType.SINGLE) {
			testCode.append("\t\tsolver.solve(1, in, out);\n");
			testCode.append("\t\tExit.exit(in, out);\n");
		} else if (testType == TestType.MULTI_NUMBER) {
			testCode.append("\t\tint testCount = in.readInt();\n");
			testCode.append("\t\tfor (int i = 1; i <= testCount; i++)\n");
			testCode.append("\t\t\tsolver.solve(i, in, out);\n");
			testCode.append("\t\tExit.exit(in, out);\n");
		} else {
			testCode.append("\t\tint i = 1;\n");
			testCode.append("\t\twhile (true) {\n");
			testCode.append("\t\t\tsolver.solve(i++, in, out);\n");
			testCode.append("\t\t\tif (in.isFinished())\n");
			testCode.append("\t\t\t\tbreak;\n");
			testCode.append("\t\t}\n");
		}
		return testCode.toString();
	}

	private String getInputFileName() {
		if (inputType == InputType.STDIN)
			return null;
		if (inputType == InputType.FILE_TASK_ID)
			return taskID.toLowerCase() + ".in";
		return customInputFilename;
	}

	private String getOutputFileName() {
		if (outputType == InputType.STDIN)
			return null;
		if (outputType == InputType.FILE_TASK_ID)
			return taskID.toLowerCase() + ".out";
		return customOutputFilename;
	}

	public TaskConfiguration setTests(List<Test> tests) {
		return new TaskConfiguration(taskID, testType, inputType, customInputFilename, outputType, customOutputFilename,
			tests);
	}

	public void generateUnitTest(String sourceCode, String checkerCode) {
		int id = Math.abs((sourceCode + checkerCode).replaceAll("[\n\r ]", "").hashCode());
		String packageName = "generated.test" + id;
		String testCode = "import net.egork.utils.io.stringinputreader.StringInputReader;\n" +
			"import net.egork.utils.test.Test;\n" +
			"import org.junit.Assert;\n" +
			"\n" +
			"import java.io.PrintWriter;\n" +
			"import java.io.StringWriter;\n" +
			"import java.util.Arrays;\n" +
			"import java.util.Collection;\n" +
			"import java.util.Locale;\n" +
			"\n" +
			"public class Test" + id + " {\n" +
			"\t@org.junit.Test\n" +
			"\tpublic void test() throws Exception {\n" +
			"\t\tLocale.setDefault(Locale.US);\n" +
			"\t\tCollection<Test> tests = Arrays.asList(Tests.TESTS);\n" +
			"\t\ttests.addAll(MainChecker.generateTests());\n" +
			"\t\tfor (Test test : tests) {\n" +
			"\t\t\tStringWriter output = new StringWriter();\n" +
			"\t\t\ttry {\n" +
			"\t\t\t\tMain.run(new StringInputReader(test.getInput()), new PrintWriter(output));\n" +
			"\t\t\t\tString verdict = MainChecker.check(new StringInputReader(test.getInput()),\n" +
			"\t\t\t\t\tnew StringInputReader(test.getExpectedOutput()), new StringInputReader(output.toString()));\n" +
			"\t\t\t\tif (verdict != null) {\n" +
			"\t\t\t\t\tSystem.err.println(\"Failed with wrong answer: \" + verdict);\n" +
			"\t\t\t\t\tSystem.err.println(\"Input:\");\n" +
			"\t\t\t\t\tSystem.err.println(test.getInput());\n" +
			"\t\t\t\t\tSystem.err.println(\"Expected output:\");\n" +
			"\t\t\t\t\tSystem.err.println(test.getExpectedOutput());\n" +
			"\t\t\t\t\tSystem.err.println(\"Actual output:\");\n" +
			"\t\t\t\t\tSystem.err.println(output.toString());\n" +
			"\t\t\t\t\tAssert.fail();\n" +
			"\t\t\t\t}\n" +
			"\t\t\t} catch (Throwable e) {\n" +
			"\t\t\t\tSystem.err.println(\"Failed with runtime error\");\n" +
			"\t\t\t\tSystem.err.println(\"Input:\");\n" +
			"\t\t\t\tSystem.err.println(test.getInput());\n" +
			"\t\t\t\tSystem.err.println(\"Expected output:\");\n" +
			"\t\t\t\tSystem.err.println(test.getExpectedOutput());\n" +
			"\t\t\t\tSystem.err.println(\"Exception:\");\n" +
			"\t\t\t\te.printStackTrace();\n" +
			"\t\t\t\tAssert.fail();\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"}";
		String path = "lib/test/generated/test" + id;
		Util.saveSourceFile(path, "Test" + id + ".java", addPackage(packageName, testCode));
		Util.saveSourceFile(path, "Main.java", addPackage(packageName, generateMainRunEnvironment()));
		Util.saveSourceFile(path, "Tests.java", addPackage(packageName, generateMainTests()));
		Util.saveSourceFile(path, taskID + ".java", addPackage(packageName, sourceCode));
		Util.saveSourceFile(path, taskID + "Checker.java", addPackage(packageName, checkerCode));
	}

	private String addPackage(String packageName, String code) {
		return "package " + packageName + ";\n\n" + code;
	}

	public static enum TestType {
		SINGLE,
		MULTI_NUMBER,
		MULTI_EOF
	}

	public static enum InputType {
		STDIN,
		FILE_TASK_ID,
		FILE_CUSTOM
	}

	@Override
	public String toString() {
		if (configurationName != null)
			return configurationName;
		return taskID;
	}
}
