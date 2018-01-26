package on2017_09.on2017_09_08_Fun_SRM_2017_09_08.Avoid9;



import static net.egork.misc.ArrayUtils.sumArray;

public class Avoid9 {
    public int maxSizeOf9Free(int[] A) {
        int[] qty = new int[9];
        for (int i : A) {
            qty[i % 9]++;
        }
        for (int i = 0; i < 9; i += 3) {
            qty[i] = Math.min(qty[i], 2);
        }
        int[] taken = new int[9];
        int result = maxPossible(0, qty, taken);
        if (result < 3) {
            result = -1;
        }
        return result;
    }

    private int maxPossible(int current, int[] qty, int[] taken) {
        if (current == 9) {
            return (int) sumArray(taken);
        }
        taken[current] = 0;
        int answer = maxPossible(current + 1, qty, taken);
        boolean canTakeOne = true;
        boolean canTakeTwo = true;
        for (int i = 0; i < current; i++) {
            if (taken[i] == 0) {
                continue;
            }
            if ((2 * current + i) % 9 == 0) {
                canTakeTwo = false;
            }
            if (taken[i] >= 2 && (2 * i + current) % 9 == 0) {
                canTakeOne = false;
                break;
            }
            for (int j = 0; j < i; j++) {
                if (taken[j] != 0 && (i + j + current) % 9 == 0) {
                    canTakeOne = false;
                    break;
                }
            }
            if (!canTakeOne) {
                break;
            }
        }
        if (qty[current] > 0 && canTakeOne) {
            taken[current] = 1;
            answer = Math.max(answer, maxPossible(current + 1, qty, taken));
        }
        if (qty[current] > 1 && canTakeOne && canTakeTwo) {
            taken[current] = qty[current];
            answer = Math.max(answer, maxPossible(current + 1, qty, taken));
        }
        return answer;
    }
}
