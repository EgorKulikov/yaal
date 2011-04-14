package Timus.Part4;

import net.egork.utils.io.stringinputreader.StringInputReader;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1352 implements Solver {
	private int[] answer = new int[39];

	public Task1352() {
		InputReader in = new StringInputReader("1\t\t2\n" +
			"2\t\t3\n" +
			"3\t\t5\n" +
			"4\t\t7\n" +
			"5\t\t13\n" +
			"6\t\t17\n" +
			"7\t\t19\n" +
			"8\t\t31\n" +
			"9\t\t61\n" +
			"10\t\t89\n" +
			"11\t\t107\n" +
			"12\t\t127\n" +
			"13\t\t521\n" +
			"14\t\t607\n" +
			"15\t\t1279\n" +
			"16\t\t2203\n" +
			"17\t\t2281\n" +
			"18\t\t3217\n" +
			"19\t\t4253\n" +
			"20\t\t4423\n" +
			"21\t\t9689\n" +
			"22\t\t9941\n" +
			"23\t\t11213\n" +
			"24\t\t19937\n" +
			"25\t\t21701\n" +
			"26\t\t23209\n" +
			"27\t\t44497\n" +
			"28\t\t86243\n" +
			"29\t\t110503\n" +
			"30\t\t132049\n" +
			"31\t\t216091\n" +
			"32\t\t756839\n" +
			"33\t\t859433\n" +
			"34\t\t1257787\n" +
			"35\t\t1398269\n" +
			"36\t\t2976221\n" +
			"37\t\t3021377\n" +
			"38\t\t6972593");
		for (int i = 1; i <= 38; i++)
			answer[in.readInt()] = in.readInt();
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		out.println(answer[in.readInt()]);
	}
}

