package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Tennison {
	int toWin;
	double pSun;
	double pRain;
	double[] pInitial = {0, Double.NaN, 1};
	double pMore;
	double pPlus;
	double pLess;
	double pMinus;

	double[][][][][] result;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		toWin = in.readInt();
		pSun = in.readDouble();
		pRain = in.readDouble();
		pInitial[1] = in.readDouble();
		pPlus = in.readDouble();
		pMore = in.readDouble();
		pMinus = in.readDouble();
		pLess = in.readDouble();
		result = new double[3][toWin][toWin][][];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < toWin; j++) {
				for (int k = 0; k < toWin; k++)
					result[i][j][k] = new double[j + 1][k + 1];
			}
		}
		ArrayUtils.fill(result, -1);
		out.printFormat("Case #%d: %.6f\n", testNumber, go(1, 0, 0, 0, 0));
    }

	private double go(int type, int won, int lost, int deltaPlus, int deltaMinus) {
		if (won == toWin)
			return 1;
		if (lost == toWin)
			return 0;
		if (result[type][won][lost][deltaPlus][deltaMinus] != -1)
			return result[type][won][lost][deltaPlus][deltaMinus];
		double curSun = pInitial[type] + deltaPlus * pPlus - deltaMinus * pMinus;
		if (curSun > 1)
			return result[type][won][lost][deltaPlus][deltaMinus] = go(2, won, lost, 0, 0);
		if (curSun < 0)
			return result[type][won][lost][deltaPlus][deltaMinus] = go(0, won, lost, 0, 0);
		return result[type][won][lost][deltaPlus][deltaMinus] = (curSun * pSun + (1 - curSun) * pRain) *
			(pMore * go(type, won + 1, lost, deltaPlus + 1, deltaMinus) +
			(1 - pMore) * go(type, won + 1, lost, deltaPlus, deltaMinus)) +
			(curSun * (1 - pSun) + (1 - curSun) * (1 - pRain)) *
			(pLess * go(type, won, lost + 1, deltaPlus, deltaMinus + 1) +
			(1 - pLess) * go(type, won, lost + 1, deltaPlus, deltaMinus));
	}
}
