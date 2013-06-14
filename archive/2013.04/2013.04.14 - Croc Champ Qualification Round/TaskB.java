package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		StringBuilder currentToken = new StringBuilder();
		boolean untilQuote;
		while (!in.isExhausted()) {
			if (in.peek() == '"') {
				untilQuote = true;
				in.read();
			} else
				untilQuote = false;
			while (in.peek() != -1 && (untilQuote && in.peek() != '"' || !untilQuote && !Character.isWhitespace(in.peek())))
				currentToken.appendCodePoint(in.read());
			if (untilQuote)
				in.read();
			out.printLine("<" + currentToken + ">");
			currentToken = new StringBuilder();
		}
    }
}
