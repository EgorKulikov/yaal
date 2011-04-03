import java.util.InputMismatchException;
import java.math.BigInteger;
import java.io.*;

/**
 * @author Egor Kulikov (egor@egork.net)
 * Actual solution is at the bottom
 */
public class Main {
	public static void main(String[] args) {
		InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(InputReader in, PrintWriter out) {
		Solver solver = new TheHeartFlu();
		int i = 1;
		while (true) {
			solver.solve(i++, in, out);
			if (in.isFinished())
				break;
		}
	}
}

class MainChecker {
	public static String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return new TheHeartFluChecker().check(input, expectedOutput, actualOutput);
	}
}

abstract class InputReader {
	private boolean finished = false;

	public abstract int read();

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public long readLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuffer res = new StringBuffer();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	private boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	private String readLine0() {
		StringBuffer buf = new StringBuffer();
		int c = read();
		while (c != '\n' && c != -1) {
			if (c != '\r')
				buf.appendCodePoint(c);
			c = read();
		}
		return buf.toString();
	}

	public String readLine() {
		String s = readLine0();
		while (s.trim().length() == 0)
			s = readLine0();
		return s;
	}

	public String readLine(boolean ignoreEmptyLines) {
		if (ignoreEmptyLines)
			return readLine();
		else
			return readLine0();
	}

	public BigInteger readBigInteger() {
		try {
			return new BigInteger(readString());
		} catch (NumberFormatException e) {
			throw new InputMismatchException();
		}
	}

	public char readCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}

	public double readDouble() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		double res = 0;
		while (!isSpaceChar(c) && c != '.') {
			if (c == 'e' || c == 'E')
				return res * Math.pow(10, readInt());
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		}
		if (c == '.') {
			c = read();
			double m = 1;
			while (!isSpaceChar(c)) {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, readInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				m /= 10;
				res += (c - '0') * m;
				c = read();
			}
		}
		return res * sgn;
	}

	public int[] readIntArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = readInt();
		return array;
	}

	public long[] readLongArray(int size) {
		long[] array = new long[size];
		for (int i = 0; i < size; i++)
			array[i] = readLong();
		return array;
	}

	public double[] readDoubleArray(int size) {
		double[] array = new double[size];
		for (int i = 0; i < size; i++)
			array[i] = readDouble();
		return array;
	}

	public String[] readStringArray(int size) {
		String[] array = new String[size];
		for (int i = 0; i < size; i++)
			array[i] = readString();
		return array;
	}

	public char[][] readTable(int rowCount, int columnCount) {
		char[][] table = new char[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				table[i][j] = readCharacter();
		}
		return table;
	}

	public void readIntArrays(int[]... arrays) {
		for (int i = 0; i < arrays[0].length; i++) {
			for (int j = 0; j < arrays.length; j++)
				arrays[j][i] = readInt();
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public abstract void close();
}

abstract class Checker {
	public abstract String check(InputReader input, InputReader expectedOutput, InputReader actualOutput);
	public abstract double getCertainty();

	public String tokenCheck(InputReader expectedOutput, InputReader actualOutput) {
		int index = 0;
		boolean readingResult = false;
		try {
			while (true) {
				readingResult = true;
				String resultToken = actualOutput.readString();
				readingResult = false;
				String outputToken = expectedOutput.readString();
				if (!resultToken.equals(outputToken)) {
					try {
						if (isDoubleEquals(Double.parseDouble(outputToken), Double.parseDouble(resultToken),
							getCertainty()))
						{
							index++;
							continue;
						}
					} catch (NumberFormatException ignored) {}
					return "'" + outputToken + "' expected at " + index + " but '" + resultToken + "' received";
				}
				index++;
			}
		} catch (InputMismatchException e) {
			if (readingResult) {
				try {
					expectedOutput.readString();
					return "only " + index + " tokens received";
				} catch (InputMismatchException e1) {
					return null;
				}
			} else
				return "only " + index + " tokens expected";
		}
	}

	protected static boolean isDoubleEquals(double expected, double actual, double certainty) {
		return Math.abs(expected - actual) < certainty * Math.max(Math.abs(expected), 1);
	}
}

class TheHeartFluChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}

class Exit {
	private Exit() {
	}

	public static void exit(InputReader in, PrintWriter out) {
		in.setFinished(true);
		out.close();
	}
}

class StreamInputReader extends InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar, numChars;

	public StreamInputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	@Override
	public void close() {
		try {
			stream.close();
		} catch (IOException ignored) {
		}
	}
}

class StringInputReader extends InputReader {
	private Reader stream;
	private char[] buf = new char[1024];
	private int curChar, numChars;

	public StringInputReader(String stream) {
		this.stream = new StringReader(stream);
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	@Override
	public void close() {
		try {
			stream.close();
		} catch (IOException ignored) {
		}
	}
}

interface Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out);
}

class TheHeartFlu implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String input;
		try {
			input = in.readLine();
			if ("".equals(input)) {
				Exit.exit(in, out);
				return;
			}
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
			return;
		}
		if (testNumber != 1) {
			out.println("---");
		}
		String[] inputParts = input.split("[*]");
		String name = inputParts[0];
		String age = inputParts[1];
		String[] values = inputParts[2].split(",");
		double[] ecg = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			ecg[i] = Double.parseDouble(values[i]);
		}
		int q = -1;
		int r = -1;
		int s = -1;
		for (int i = 0; i < ecg.length; i++) {
			if (ecg[i] < 0) {
				q = i;
				break;
			}
		}
		if (q != -1) {
			for (int i = q; i < ecg.length; i++) {
				if (ecg[i] > 0) {
					r = i;
					break;
				}
			}
			if (r != -1) {
				for (int i = r; i < ecg.length; i++) {
					if (ecg[i] < 0) {
						s = i;
						break;
					}
				}
			} else {
				q = -10000;
				s = 100000;
			}
		} else {
			q = -10000;
			s = 100000;
		}
		boolean passed = false;
		if (s - q <= 10 && s - q >= 6) {
			passed = true;
		}
		if (passed) {
			for (int i = q; i <= s; i++) {
				if (Math.abs(ecg[i]) > 35) {
					passed = false;
				}
			}
		}
		out.println(name);
		out.println(age);
		out.println("Triage " + (!passed ? "PASS" : "NO PASS"));
	}
}

