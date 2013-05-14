package on2012_04.on2012_3_14.taskd;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	double[][][] answer;
	double[][][] sumFirst;
	double[][][] sumSecond;
	double[][][] sumBoth;
	private int[] minDamage;
	private int[] maxDamage;
	private double[] probability;
	boolean[] firstWillShoot;
	boolean[] secondWillShoot;
	double firstQuant;
	double secondQuant;
	int length;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] hp = new int[2];
		int[] delta = new int[2];
		minDamage = new int[2];
		maxDamage = new int[2];
		int[] probabilityNotHit = new int[2];
		IOUtils.readIntArrays(in, hp, delta, minDamage, maxDamage, probabilityNotHit);
		if (probabilityNotHit[0] == 100) {
			out.printLine(0);
			return;
		}
		probability = new double[2];
		for (int i = 0; i < 2; i++)
			probability[i] = (100 - probabilityNotHit[i]) / 100d;
		firstQuant = probability[0] / (maxDamage[0] - minDamage[0] + 1);
		secondQuant = probability[1] / (maxDamage[1] - minDamage[1] + 1);
		answer = new double[hp[0] + 1][hp[1] + 1][];
		sumFirst = new double[hp[0] + 1][hp[1] + 1][];
		sumSecond = new double[hp[0] + 1][hp[1] + 1][];
		sumBoth = new double[hp[0] + 1][hp[1] + 1][];
		int g = (int) IntegerUtils.gcd(delta[0], delta[1]);
		length = delta[0] / g + delta[1] / g - 1;
		firstWillShoot = new boolean[length];
		secondWillShoot = new boolean[length];
		int ff = 0;
		int ss = 0;
		for (int i = 0; i < length; i++) {
			if (ff <= ss)
				firstWillShoot[i] = true;
			if (ss <= ff)
				secondWillShoot[i] = true;
			int min = Math.min(ff, ss);
			ff -= min;
			ss -= min;
			if (ff == 0)
				ff = delta[0];
			if (ss == 0)
				ss = delta[1];
		}
		out.printLine(go(hp[0], hp[1], 0));
	}

	private double go(int firstHp, int secondHp, int shift) {
		if (secondHp <= 0)
			return 1;
		if (firstHp <= 0)
			return 0;
		if (answer[firstHp][secondHp] == null)
			init(firstHp, secondHp);
		return answer[firstHp][secondHp][shift];
	}

	int counter = 0;

	private void init(int firstHp, int secondHp) {
//		counter++;
//		if (counter % 1000 == 0)
//			System.err.println(firstHp + " " + secondHp);
		answer[firstHp][secondHp] = new double[length + 1];
		double[] curAnswer = answer[firstHp][secondHp];
		double curAns = 0;
		double curProbability = 1;
		for (int i = 0; i < length; i++) {
			if (firstWillShoot[i]) {
				if (secondWillShoot[i]) {
//					double cur = 0;
//					for (int j = minDamage[0]; j <= maxDamage[0]; j++) {
//						for (int k = minDamage[1]; k <= maxDamage[1]; k++)
//							cur += go(firstHp - k, secondHp - j, i + 1);
//							curAnswer[i] += firstQuant * secondQuant * go(firstHp - k, secondHp - j, i + 1);
//					}
//					curAnswer[i] += cur * firstQuant * secondQuant;
					curAnswer[i] += getBothSum(firstHp, secondHp, i + 1) * firstQuant * secondQuant;
//					double cur = 0;
//					int upTo = Math.min(maxDamage[1], firstHp);
//					for (int j = minDamage[1]; j <= maxDamage[1]; j++)
//						cur += getSecondSum(firstHp - j, secondHp, i + 1);
//					curAnswer[i] = firstQuant * secondQuant * cur;
//					cur = 0;
//					for (int j = minDamage[0]; j <= maxDamage[0]; j++)
//						cur += go(firstHp, secondHp - j, i + 1);
//					curAnswer[i] += cur * firstQuant * (1 - probability[1]);
					curAnswer[i] += firstQuant * getSecondSum(firstHp, secondHp, i + 1) * (1 - probability[1]);
//					cur = 0;
//					for (int j = minDamage[1]; j <= maxDamage[1]; j++)
//						cur += go(firstHp - j, secondHp, i + 1);
//						curAnswer[i] += secondQuant * go(firstHp - j, secondHp, i + 1) * (1 - probability[0]);
//					curAnswer[i] += cur * secondQuant * (1 - probability[0]);
					curAnswer[i] += secondQuant * getFirstSum(firstHp, secondHp, i + 1) * (1 - probability[0]);
					curAns += curAnswer[i] * curProbability;
					curProbability *= 1 - probability[1];
				} else {
//					double cur = 0;
//					for (int j = minDamage[0]; j <= maxDamage[0]; j++)
//						cur += go(firstHp, secondHp - j, i + 1);
//					curAnswer[i] += cur * firstQuant;
					curAnswer[i] += firstQuant * getSecondSum(firstHp, secondHp, i + 1);
					curAns += curAnswer[i] * curProbability;
				}
				curProbability *= 1 - probability[0];
			} else if (secondWillShoot[i]) {
//				double cur = 0;
//				for (int j = minDamage[1]; j <= maxDamage[1]; j++)
//					cur += go(firstHp - j, secondHp, i + 1);
//				curAnswer[i] += cur * secondQuant;
				curAnswer[i] += secondQuant * getFirstSum(firstHp, secondHp, i + 1);
				curAns += curAnswer[i] * curProbability;
				curProbability *= 1 - probability[1];
			}
		}
		curAns /= 1 - curProbability;
		curAnswer[length] = (float) curAns;
		for (int i = length - 1; i >= 0; i--) {
			if (firstWillShoot[i]) {
				if (secondWillShoot[i])
					curAnswer[i] += curAnswer[i + 1] * (1 - probability[0]) * (1 - probability[1]);
				else
					curAnswer[i] += curAnswer[i + 1] * (1 - probability[0]);
			} else if (secondWillShoot[i])
				curAnswer[i] += curAnswer[i + 1] * (1 - probability[1]);
			else
				curAnswer[i] += curAnswer[i + 1];
		}
	}

	double getFirstSum(int firstHp, int secondHp, int shift) {
		if (secondHp <= 0)
			return maxDamage[1] - minDamage[1] + 1;
		if (firstHp <= 0)
			return 0;
		if (sumFirst[firstHp][secondHp] == null)
			initFirstSum(firstHp, secondHp);
		return sumFirst[firstHp][secondHp][shift];
	}

	private void initFirstSum(int firstHp, int secondHp) {
		sumFirst[firstHp][secondHp] = new double[length + 1];
		for (int i = 0; i <= length; i++)
			sumFirst[firstHp][secondHp][i] = getFirstSum(firstHp - 1, secondHp, i) + go(firstHp - minDamage[1], secondHp, i) - go(firstHp - maxDamage[1] - 1, secondHp, i);
	}

	double getSecondSum(int firstHp, int secondHp, int shift) {
		if (secondHp <= 0)
			return maxDamage[0] - minDamage[0] + 1;
		if (firstHp <= 0)
			return Math.max(0, maxDamage[0] - secondHp + 1);
		if (sumSecond[firstHp][secondHp] == null)
			initSecondSum(firstHp, secondHp);
		return sumSecond[firstHp][secondHp][shift];
	}

	private void initSecondSum(int firstHp, int secondHp) {
		sumSecond[firstHp][secondHp] = new double[length + 1];
		for (int i = 0; i <= length; i++)
			sumSecond[firstHp][secondHp][i] = getSecondSum(firstHp, secondHp - 1, i) + go(firstHp, secondHp - minDamage[0], i) - go(firstHp, secondHp - maxDamage[0] - 1, i);
	}

	double getBothSum(int firstHp, int secondHp, int shift) {
		if (secondHp <= 0)
			return (maxDamage[0] - minDamage[0] + 1) * (maxDamage[1] - minDamage[1] + 1);
		if (firstHp <= 0)
			return Math.max(0, maxDamage[0] - secondHp + 1) * (maxDamage[1] - minDamage[1] + 1);
		if (sumBoth[firstHp][secondHp] == null)
			initBothSum(firstHp, secondHp);
		return sumBoth[firstHp][secondHp][shift];
	}

	private void initBothSum(int firstHp, int secondHp) {
		sumBoth[firstHp][secondHp] = new double[length + 1];
		for (int i = 0; i <= length; i++)
			sumBoth[firstHp][secondHp][i] = getBothSum(firstHp, secondHp - 1, i) + getFirstSum(firstHp, secondHp - minDamage[0], i) - getFirstSum(firstHp, secondHp - maxDamage[0] - 1, i);
	}
}
