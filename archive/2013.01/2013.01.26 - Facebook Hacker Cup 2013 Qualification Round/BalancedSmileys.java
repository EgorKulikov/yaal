package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class BalancedSmileys {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] message = in.readLine().toCharArray();
		Set<Integer> current = new EHashSet<Integer>();
		current.add(0);
		for (int i = 0; i < message.length; i++) {
			if (message[i] != '(' && message[i] != ')')
				continue;
			boolean couldBeSame = i > 0 && message[i - 1] == ':';
			int delta = message[i] == '(' ? 1 : -1;
			Set<Integer> next = new EHashSet<Integer>();
			for (int j : current) {
				for (int k = (couldBeSame ? 0 : 1); k < 2; k++) {
					int l = j + delta * k;
					if (l >= 0)
						next.add(l);
				}
			}
			current = next;
		}
    	out.printLine("Case #" + testNumber + ":", current.contains(0) ? "YES" : "NO");
    }
}
