package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.collections.sequence.Array;
import net.egork.collections.CollectionUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskG implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int peopleCount = in.readInt();
		String[] names = new String[peopleCount];
		double[] up = new double[peopleCount];
		double[] down = new double[peopleCount];
		double[] rock = new double[peopleCount];
		double[] paper = new double[peopleCount];
		double[] scissors = new double[peopleCount];
		for (int i = 0; i < peopleCount; i++) {
			names[i] = in.readString();
			up[i] = in.readInt();
			down[i] = in.readInt();
			rock[i] = in.readInt();
			paper[i] = in.readInt();
			scissors[i] = in.readInt();
			double sumUpDown = up[i] + down[i];
			up[i] /= sumUpDown;
			down[i] /= sumUpDown;
			double sumRockPaperScissors = rock[i] + paper[i] + scissors[i];
			rock[i] /= sumRockPaperScissors;
			paper[i] /= sumRockPaperScissors;
			scissors[i] /= sumRockPaperScissors;
		}
		double[] expectation = new double[1 << peopleCount];
		double[] upProbability = new double[1 << peopleCount];
		double[] downProbability = new double[1 << peopleCount];
		for (int i = 0; i < (1 << peopleCount); i++) {
			upProbability[i] = 1;
			downProbability[i] = 1;
			for (int j = 0; j < peopleCount; j++) {
				if ((i >> j & 1) != 0) {
					upProbability[i] *= up[j];
					downProbability[i] *= down[j];
				}
			}
		}
		double[][] lose = new double[1 << peopleCount][peopleCount];
		for (int i = 1; i < (1 << peopleCount); i++) {
			if (Integer.bitCount(i) == 1) {
				lose[i][Integer.numberOfTrailingZeros(i)] = 1;
				continue;
			}
			if (Integer.bitCount(i) == 2) {
				int first = Integer.numberOfTrailingZeros(i);
				int second = Integer.numberOfTrailingZeros(Integer.highestOneBit(i));
				double firstWin = rock[first] * scissors[second] + scissors[first] * paper[second] + paper[first] * rock[second];
				double secondWin = rock[second] * scissors[first] + scissors[second] * paper[first] + paper[second] * rock[first];
				if (firstWin == 0 && secondWin == 0)
					expectation[i] = Double.POSITIVE_INFINITY;
				else {
					expectation[i] = 1 / (firstWin + secondWin);
					lose[i][first] = secondWin / (firstWin + secondWin);
					lose[i][second] = firstWin / (firstWin + secondWin);
				}
				continue;
			}
			double nonStay = 0;
			int j = i;
			do {
				double probability = upProbability[j] * downProbability[i - j];
				if (probability == 0) {
					j = (j - 1) & i;
					continue;
				}
				int upBitCount = Integer.bitCount(j);
				int downBitCount = Integer.bitCount(i - j);
				if (upBitCount == 0 || downBitCount == 0 || upBitCount == downBitCount) {
					j = (j - 1) & i;
					continue;
				}
				nonStay += probability;
				int nextPosition;
				if (upBitCount > downBitCount)
					nextPosition = i - j;
				else
					nextPosition = j;
				if (expectation[nextPosition] == Double.POSITIVE_INFINITY || expectation[i] == Double.POSITIVE_INFINITY)
					expectation[i] = Double.POSITIVE_INFINITY;
				else
					expectation[i] += expectation[nextPosition] * probability;
				for (int k = 0; k < peopleCount; k++)
					lose[i][k] += probability * lose[nextPosition][k];
				j = (j - 1) & i;
			} while (j != i);
			if (nonStay == 0) {
				expectation[i] = Double.POSITIVE_INFINITY;
				continue;
			}
			if (expectation[i] != Double.POSITIVE_INFINITY) {
				expectation[i]++;
				expectation[i] /= nonStay;
			}
			for (int k = 0; k < peopleCount; k++)
				lose[i][k] /= nonStay;
		}
		int all = (1 << peopleCount) - 1;
		if (expectation[all] == Double.POSITIVE_INFINITY)
			out.print("Infinity ");
		else
			out.printf("%.3f ", expectation[all]);
		double maximalProbability = CollectionUtils.maxElement(Array.wrap(lose[all]));
		out.printf("%.3f", maximalProbability * 100);
		out.println("%");
		List<String> losers = new ArrayList<String>();
		for (int i = 0; i < peopleCount; i++) {
			if (Math.abs(lose[all][i] - maximalProbability) < 1e-8)
//			if (lose[all][i] == maximalProbability)
				losers.add(names[i]);
		}
		Collections.sort(losers);
		IOUtils.printCollection(losers, out);
	}
}

