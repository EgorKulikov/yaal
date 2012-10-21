package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        double side = Math.sin(3 * Math.PI / 10) * 20;
        out.printLine(4 * count + 1);
        out.printLine(0, 0);
        for (int i = 1; i <= count; i++)
            out.printLine(side * i, 0);
        double height = Math.cos(3 * Math.PI / 10) * 10;
        for (int i = 0; i < count; i++)
            out.printLine(side * i + side / 2, height);
        double otherHeight = -Math.sin(2 * Math.PI / 5) * 10;
        double shift = Math.cos(2 * Math.PI / 5) * 10;
        for (int i = 0; i < count; i++)
            out.printLine(side * i + shift, otherHeight);
        for (int i = 0; i < count; i++)
            out.printLine(side * (i + 1) - shift, otherHeight);
        for (int i = 0; i < count; i++)
            out.printLine(i + 1, 2 * count + 2 + i, 3 * count + 2 + i, 2 + i, count + 2 + i);
        for (int i = 0; i <= count; i++)
            out.print(count - i + 1, "");
        for (int i = 0; i < count; i++)
            out.print(3 * count + 2 + i, count + 2 + i, 2 * count + 2 + i, 2 + i, "");
        out.printLine();
	}
}
