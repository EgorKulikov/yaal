package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int length = in.readInt();
        char[] startState = in.readString().toCharArray();
        int startPosition = in.readInt() - 1;
        char[] endState = in.readString().toCharArray();
        int endPosition = in.readInt() - 1;
        out.printLine("M1");
        for (int i = 0; i < 10; i++)
            out.printLine("<");
        out.printLine("E");
        for (int i = 0; i < 27; i++) {
            int first = i % 3;
            int second = i / 3 % 3;
            int third = i / 9;
            out.printLine("M" + (100 + i));
            if (first == 1)
                out.printLine("v");
            else if (first == 2)
                out.printLine("x");
            out.printLine(">");
            if (second == 1)
                out.printLine("v");
            else if (second == 2)
                out.printLine("x");
            out.printLine(">");
            if (third == 1)
                out.printLine("v");
            else if (third == 2)
                out.printLine("x");
            out.printLine(">");
            out.printLine("E");
        }
        int position = startPosition;
        while (position >= 10) {
            out.printLine("#1");
            position -= 10;
        }
        while (position > 0) {
            out.printLine("<");
            position--;
        }
        for (int i = 0; i + 3 < length; i += 3) {
            int code = 0;
            for (int j = 2; j >= 0; j--) {
                code *= 3;
                if (startState[j + i] == '.' && endState[j + i] == '-')
                    code++;
                else if (startState[j + i] == '-' && endState[j + i] == '.')
                    code += 2;
            }
            out.printLine("#" + (100 + code));
        }
        int end = (length - 1) - ((length - 1) % 3);
        for (int i = end; i < length; i++) {
            if (startState[i] == '.' && endState[i] == '-')
                out.printLine("v");
            else if (startState[i] == '-' && endState[i] == '.')
                out.printLine("x");
            if (i != length - 1)
                out.printLine(">");
        }
        position = length - 1;
        while (position - endPosition >= 10) {
            out.printLine("#1");
            position -= 10;
        }
        while (position > endPosition) {
            out.printLine("<");
            position--;
        }
        out.printLine("s");
	}
}
