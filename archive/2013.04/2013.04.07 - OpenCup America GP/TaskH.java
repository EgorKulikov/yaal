package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskH {
	int[] varId;
	char[] whichVar;
	int[][] varArr;
	int lastVar;
	String curToken;

	void nextToken(InputReader in) {
		if (in.isExhausted()) {
			curToken = "$";
		} else {
			curToken = in.readString();
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		varId = new int[26];
		whichVar = new char[20];
		Arrays.fill(varId, -1);
		lastVar = 0;
		int[] possibleMap = new int[(1 << 20) >> 5];
		Arrays.fill(possibleMap, -1);
		varArr = new int[20][(1 << 20) >> 5];
		for (int i = 0; i < 20; ++i)
			for (int j = 0; j < (1 << 20); ++j)
				if ((j & (1 << i)) != 0)
					varArr[i][j >> 5] |= 1 << (j & 31);
		nextToken(in);
		parseStatementList(possibleMap, in, out);
		if (!curToken.equals("$")) throw new RuntimeException();
    }

	private void parseStatementList(int[] possibleMap, InputReader in, OutputWriter out) {
		while (true) {
			if (curToken.equals("if")) {
				nextToken(in);
				int[] expr = parseExpression(in);
				if (!curToken.equals("then")) throw new RuntimeException();
				nextToken(in);
				int[] newMap = new int[possibleMap.length];
				for (int i = 0; i < possibleMap.length; ++i)
					newMap[i] = possibleMap[i] & expr[i];
				parseStatementList(newMap, in, out);
				if (curToken.equals("else")) {
					nextToken(in);
					for (int i = 0; i < possibleMap.length; ++i)
						newMap[i] = possibleMap[i] & ~expr[i];
					parseStatementList(newMap, in, out);
				}
				if (!curToken.equals("fi")) throw new RuntimeException();
				nextToken(in);
			} else if (curToken.equals("checkpoint")) {
				nextToken(in);
				analyzeCheckpoint(possibleMap, out);
			} else {
				break;
			}
		}
	}

	private void analyzeCheckpoint(int[] possibleMap, OutputWriter out) {
		int canZeroMask = 0;
		int canOneMask = 0;
		for (int i = 0; i < possibleMap.length; ++i) {
			if (possibleMap[i] != 0) {
				canOneMask |= (i << 5);
				canZeroMask |= (((1 << 15) - 1) - i) << 5;
				for (int j = 0; j < 32; ++j)
					if ((possibleMap[i] & (1 << j)) != 0) {
						canOneMask |= j;
						canZeroMask |= 31 - j;
					}
			}
		}
		if (canOneMask == 0 && canZeroMask == 0) {
			out.printLine(">unreachable");
			return;
		}
		char[] output = new char[26];
		out.print(">");
		for (int k = 0; k < lastVar; ++k)
			if ((canZeroMask & (1 << k)) == 0) {
				output[whichVar[k] - 'a'] = Character.toUpperCase(whichVar[k]);
			} else if ((canOneMask & (1 << k)) == 0) {
				output[whichVar[k] - 'a'] = whichVar[k];
			}
		for (int k = 0; k < 26; ++k) if (output[k] != 0)
			out.print(output[k]);
		out.printLine();
	}

	String expr;
	int exprPos;

	private int[] parseExpression(InputReader in) {
		expr = curToken + "$";
		exprPos = 0;
		nextToken(in);
		int[] ret = parseExpr();
		if (expr.charAt(exprPos) != '$') throw new RuntimeException();
		return ret;
	}

	private int[] parseExpr() {
		int[] left = parseInsideOr();
		while (expr.charAt(exprPos) == '|') {
			++exprPos;
			int[] right = parseInsideOr();
			for (int i = 0; i < left.length; ++i) {
				left[i] |= right[i];
			}
		}
		return left;
	}

	private int[] parseInsideOr() {
		int[] left = parseInsideAnd();
		while (expr.charAt(exprPos) == '&') {
			++exprPos;
			int[] right = parseInsideAnd();
			for (int i = 0; i < left.length; ++i) {
				left[i] &= right[i];
			}
		}
		return left;
	}

	private int[] parseInsideAnd() {
		char ch = expr.charAt(exprPos);
		switch (ch) {
			case '~': {
				++exprPos;
				int[] left = parseInsideAnd();
				for (int i = 0; i < left.length; ++i) left[i] = ~left[i];
				return left;
			}
			case '(': {
				++exprPos;
				int[] left = parseExpr();
				if (expr.charAt(exprPos) != ')') throw new RuntimeException();
				++exprPos;
				return left;
			}
			default:
				if (ch < 'A' || ch > 'Z') throw new RuntimeException();
				ch = Character.toLowerCase(ch);
				++exprPos;
				if (varId[ch - 'a'] < 0) {
					varId[ch - 'a'] = lastVar;
					whichVar[lastVar] = ch;
					++lastVar;
				}
				return varArr[varId[ch - 'a']].clone();
		}
	}
}
