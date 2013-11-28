package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Burnout {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] time = IOUtils.readIntArray(in, count);
		int respawnCost = 0;
		for (int i : time)
			respawnCost += (i / 30 + 1) * 10;
		int fragCost = 0;
		for (int i : time)
			fragCost += (i / 60 + 1) * 15;
		if (respawnCost < fragCost)
			out.printLine("Case", testNumber + ":", "Respawn", respawnCost);
		else if (respawnCost > fragCost)
			out.printLine("Case", testNumber + ":", "Frag", fragCost);
		else
			out.printLine("Case", testNumber + ":", "Respawn Frag", fragCost);
    }
}
