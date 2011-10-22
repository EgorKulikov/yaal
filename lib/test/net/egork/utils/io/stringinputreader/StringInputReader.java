package net.egork.utils.io.stringinputreader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.InputMismatchException;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class StringInputReader extends net.egork.utils.io.old.InputReader {
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
