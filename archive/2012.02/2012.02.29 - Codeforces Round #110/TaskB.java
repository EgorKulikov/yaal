package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int countTruth = in.readInt();
		boolean[] killed = new boolean[count];
		int[] who = new int[count];
		for (int i = 0; i < count; i++) {
			killed[i] = in.readCharacter() == '+';
			who[i] = in.readInt() - 1;
		}
		int base = 0;
		int[] deltaIfKilled = new int[count];
		for (int i = 0; i < count; i++) {
			if (killed[i])
				deltaIfKilled[who[i]]++;
			else {
				deltaIfKilled[who[i]]--;
				base++;
			}
		}
		boolean[] canKill = new boolean[count];
		for (int i = 0; i < count; i++)
			canKill[i] = base + deltaIfKilled[i] == countTruth;
		int countCanKill = CollectionUtils.count(Array.wrap(canKill), true);
		for (int i = 0; i < count; i++) {
			if (killed[i]) {
				if (canKill[who[i]] && countCanKill == 1)
					out.printLine("Truth");
				else if (canKill[who[i]])
					out.printLine("Not defined");
				else
					out.printLine("Lie");
			} else {
				if (canKill[who[i]] && countCanKill == 1)
					out.printLine("Lie");
				else if (canKill[who[i]])
					out.printLine("Not defined");
				else
					out.printLine("Truth");
			}
		}
	}
}
