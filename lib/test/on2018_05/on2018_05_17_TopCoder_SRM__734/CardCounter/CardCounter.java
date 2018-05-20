package on2018_05.on2018_05_17_TopCoder_SRM__734.CardCounter;



import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static java.lang.Math.max;
import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.sumArray;

public class CardCounter {
    int[] value;
    int dealer;
    double[] winPlayer;
    double[][][][] winDealer;

    public double winningChance(int[] deck, int dealer, int[] player) {
        this.dealer = dealer;
        value = new int[(int) sumArray(deck)];
        int at = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < deck[i]; j++) {
                value[at++] = i + 1;
            }
        }
        winPlayer = new double[1 << value.length];
        fill(winPlayer, NaN);
        winDealer = new double[2][22][3][];
        return solve(0, player[0] + player[1], player[0] == 1 || player[1] == 1);
    }

    private double solve(int mask, int score, boolean soft) {
        if (!isNaN(winPlayer[mask])) {
            return winPlayer[mask];
        }
        if (score > 21) {
            return winPlayer[mask] = 0;
        }
        double stand = solveDealer(mask, getScore(score, soft), dealer, dealer == 1);
        double hit = 0;
        int by = 0;
        for (int i = 0; i < value.length; i++) {
            if ((mask >> i & 1) == 0) {
                hit += solve(mask + (1 << i), score + value[i], soft || value[i] == 1);
                by++;
            }
        }
        hit /= by;
        return winPlayer[mask] = max(stand, hit);
    }

    private double solveDealer(int mask, int player, int dealer, boolean dealerSoft) {
        if (dealer > 21) {
            return 1;
        }
        if (winDealer[dealerSoft ? 1 : 0][player][dealer / 10] == null) {
            winDealer[dealerSoft ? 1 : 0][player][dealer / 10] = new double[1 << value.length];
            fill(winDealer[dealerSoft ? 1 : 0][player][dealer / 10], NaN);
        }
        if (!isNaN(winDealer[dealerSoft ? 1 : 0][player][dealer / 10][mask])) {
            return winDealer[dealerSoft ? 1 : 0][player][dealer / 10][mask];
        }
        int score = getScore(dealer, dealerSoft);
        if (score >= 17) {
            if (player > score) {
                return winDealer[dealerSoft ? 1 : 0][player][dealer / 10][mask] = 1;
            } else {
                return winDealer[dealerSoft ? 1 : 0][player][dealer / 10][mask] = 0;
            }
        }
        double result = 0;
        double by = 0;
        for (int i = 0; i < value.length; i++) {
            if ((mask >> i & 1) == 0) {
                result += solveDealer(mask + (1 << i), player, dealer + value[i], dealerSoft || value[i] == 1);
                by++;
            }
        }
        return winDealer[dealerSoft ? 1 : 0][player][dealer / 10][mask] = result / by;
    }

    private int getScore(int score, boolean soft) {
        if (score <= 11 && soft) {
            return score + 10;
        }
        return score;
    }
}
