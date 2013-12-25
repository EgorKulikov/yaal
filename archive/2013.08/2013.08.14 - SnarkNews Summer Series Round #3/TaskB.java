package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long x = 0;
		long y = 0;
		int dir = 3;
		int speed = 0;
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			String command = in.readString();
			if ("Fwrd".equals(command)) {
				if (speed == 0)
					speed = 1;
			} else if ("Back".equals(command)) {
				if (speed == 0)
					speed = -1;
			} else if ("More".equals(command)) {
				if (speed > 0 && speed < 5)
					speed++;
			} else if ("Less".equals(command)) {
				if (speed > 0)
					speed--;
			} else if ("Stop".equals(command))
				speed = 0;
			else if ("Rght".equals(command)) {
				if (speed == 0)
					dir = (dir + 1) & 3;
			} else if ("Left".equals(command)) {
				if (speed == 0)
					dir = (dir + 3) & 3;
			}
			x += MiscUtils.DX4[dir] * speed;
			y += MiscUtils.DY4[dir] * speed;
		}
		out.printLine(x * 100, y * 100);
    }
}
