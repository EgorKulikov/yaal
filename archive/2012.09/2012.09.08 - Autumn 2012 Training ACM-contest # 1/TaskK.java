package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	int[] taken;
	int[] r;
	private int mod;
	int[][] delta;

	long[] result = {5273912160L, 2636956080L, 1757970720L, 1289178528L, 1054782432L, 878985360L, 753415832L, 644589264L, 585990240L, 527391216L, 479446560L, 429725712L, 405685448L, 376707578L, 351594144L, 321307008L, 310230257L, 292995120L, 277574373L, 234396096L, 251139060L, 239723280L, 229300548L, 214862856L, 175797072L, 202842720L, 195330060L, 184168434L, 181859040L, 175797072L, 170126208L, 160646022L, 159815400L, 155115116L, 150683238L, 143241888L, 142538364L, 138787185L, 135228824L, 117198048L, 128633890L, 125569528L, 122649120L, 117198048L, 117198048L, 114650280L, 112210876L, 107102130L, 107630784L, 58599024L, 103410054L, 99167700L, 99507781L, 97664982L, 95889312L, 92084220L, 92524757L, 90929492L, 89388327L, 78132264L, 86457550L, 85063091L, 83712908L, 80304306L, 81137030L, 79907808L, 78715039L, 75834076L, 76433587L, 75341452L, 74280450L, 71620944L, 72246308L, 71269316L, 58599024L, 67851499L, 68492396L, 67614432L, 66758392L, 58599024L, 65110020L, 64316945L, 63541122L, 61389722L, 62046052L, 61324560L, 60619680L, 58599024L, 59257446L, 58599024L, 57955064L, 56051256L, 56708738L, 56105454L, 55514870L, 53548572L, 54370308L, 53815328L, 53273400L, 0L};

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		mod = in.readInt();
		if (count == 5) {
			out.printLine("Case #" + testNumber + ":", result[mod - 1]);
			return;
		}
		long answer = solve(count, mod);
		out.printLine("Case #" + testNumber + ":", answer);
	}

	private long solve(int count, int mod) {
		this.mod = mod;
		long answer = 0;
		r = new int[100 * mod + 1];
		for (int i = 0; i < 100 * mod; i++)
			r[i] = i % mod;
		if (count == 1) {
			for (int i = 10; i < 100; i++) {
				if (r[i] == 0)
					answer++;
			}
		} else {
			int[] quantity = new int[mod];
			int[][] differentQuantity = new int[100][mod];
			delta = new int[100][100];
			for (int i = 10; i < 100; i++) {
				for (int j = 10; j < 100; j++) {
					if (i == j)
						continue;
					int remainder = r[mod - r[r[100] * i + j]];
					quantity[remainder]++;
					differentQuantity[i][remainder]++;
					differentQuantity[j][remainder]++;
					delta[i][j] = remainder;
				}
			}
			taken = new int[count - 2];
			answer = go(count - 2, 0, 10000 % mod, 0, quantity, differentQuantity);
		}
		return answer;
	}

	public static void main(String[] args) {
		TaskK task = new TaskK();
		for (int j = 1; j <= 100; j++) {
			System.out.print(task.solve(5, j) + "L, ");
		}
	}

	private long go(int maxStep, int step, int multiplier, int remainder, int[] quantity, int[][] differentQuantity) {
		if (step == maxStep) {
			long answer = quantity[remainder];
			for (int i : taken)
				answer -= differentQuantity[i][remainder];
			for (int i = 0; i < taken.length; i++) {
				for (int j = 0; j < taken.length; j++) {
					if (i != j && delta[taken[i]][taken[j]] == remainder)
						answer++;
				}
			}
			return answer;
		}
		long answer = 0;
		for (int i = 10; i < 100; i++) {
			boolean good = true;
			for (int j = 0; j < step; j++) {
				if (taken[j] == i)
					good = false;
			}
			if (!good)
				continue;
			taken[step] = i;
			answer += go(maxStep, step + 1, r[multiplier * 100], r[remainder + multiplier * i], quantity, differentQuantity);
		}
		return answer;
	}
}
