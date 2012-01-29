package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int cnt4 = in.readInt();
		int cnt7 = in.readInt();
		int cnt47 = in.readInt();
		int cnt74 = in.readInt();
		if (cnt4 == cnt47 && cnt4 == cnt74 && cnt7 > cnt4) {
			for (int i = 0; i < cnt74; i++)
				out.print(74);
			for (int i = cnt74; i < cnt7; i++)
				out.print(7);
			out.printLine();
			return;
		}
		if (Math.abs(cnt47 - cnt74) > 1 || Math.max(cnt47, cnt74) > Math.min(cnt4, cnt7) ||
			cnt4 == cnt7 && cnt4 == cnt47 && cnt4 == cnt74)
		{
			out.printLine(-1);
			return;
		}
		if (cnt74 > cnt47) {
			out.print(7);
			cnt7--;
			cnt74--;
		}
		int front4 = cnt4 - cnt47;
		if (cnt47 == cnt74)
			front4--;
		for (int i = 0; i < front4; i++) {
			out.print(4);
			cnt4--;
		}
		for (int i = 0; i < cnt47; i++) {
			out.print(47);
			cnt4--;
			cnt7--;
			if (i != 0)
				cnt74--;
		}
		for (int i = 0; i < cnt7; i++)
			out.print(7);
		if (cnt74 != 0)
			out.print(4);
		out.printLine();
	}
}
