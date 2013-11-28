package on2013_11.on2013_11_22_Facebook_Hacker_Cup_2014_Qualification_Round.Basketball_Game;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class BasketballGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int time = in.readInt();
		int on = in.readInt();
		String[] names = new String[count];
		int[] percent = new int[count];
		int[] height = new int[count];
		for (int i = 0; i < count; i++) {
			names[i] = in.readString();
			percent[i] = in.readInt();
			height[i] = in.readInt();
		}
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, ArrayUtils.compareBy(percent, height));
		ArrayUtils.reverse(order);
		boolean[] onCourt = new boolean[count];
		int[] played = new int[count];
		Arrays.fill(onCourt, 0, 2 * on, true);
		for (int i = 0; i < time; i++) {
			for (int j = 0; j < count; j++) {
				if (onCourt[j])
					played[j]++;
			}
			for (int j = 0; j < 2; j++) {
				if (count <= 2 * on + j)
					continue;
				int maxPlayed = -1;
				int toOff = -1;
				int end = count - 1;
				if ((end & 1) != j)
					end--;
				for (int k = end; k >= 0; k -= 2) {
					if (onCourt[k] && played[k] > maxPlayed) {
						maxPlayed = played[k];
						toOff = k;
					}
				}
				int minPlayed = Integer.MAX_VALUE;
				int toOn = -1;
				for (int k = j; k < count; k += 2) {
					if (!onCourt[k] && played[k] < minPlayed) {
						minPlayed = played[k];
						toOn = k;
					}
				}
				onCourt[toOff] = false;
				onCourt[toOn] = true;
			}
		}
		List<String> answer = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			if (onCourt[i])
				answer.add(names[order[i]]);
		}
		Collections.sort(answer);
		out.print("Case #" + testNumber + ": ");
		out.printLine(answer.toArray());
	}
}
