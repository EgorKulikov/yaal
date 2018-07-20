package on2018_07.on2018_07_20_2018_TopCoder_Open_Algorithm.Alchemy;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;

import static java.util.Arrays.fill;
import static net.egork.numbers.IntegerUtils.gcd;

public class Alchemy {
    public String leadToGold(int[] leadOutput, int[] goldOutput, long goal) {
        int n = leadOutput.length;
        IntList zeroes = new IntArrayList();
        for (int i = 0; i < n; i++) {
            if (leadOutput[i] == 0) {
                zeroes.add(goldOutput[i]);
            }
        }
        if (zeroes.isEmpty()) {
            return "Impossible";
        }
        zeroes.sort();
        zeroes = zeroes.unique();
        IntList shifts = new IntArrayList();
        IntList currentShifts = new IntArrayList();
        currentShifts.add(0);
        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < n; j++) {
                if (leadOutput[j] == i) {
                    for (int k : currentShifts) {
                        shifts.add(goldOutput[j] + k);
                    }
                }
            }
            IntList nextShifts = new IntArrayList();
            for (int j : currentShifts) {
                for (int k : zeroes) {
                    nextShifts.add(j + k);
                }
            }
            nextShifts.sort();
            nextShifts = nextShifts.unique();
            currentShifts = nextShifts;
        }
        shifts.sort();
        shifts = shifts.unique();
        if (!shifts.isEmpty() && shifts.get(0) == 0) {
            shifts = shifts.subList(1, shifts.size());
        }
        if (shifts.isEmpty()) {
            return (goal <= Integer.MAX_VALUE && zeroes.contains((int) goal)) ? "Possible" : "Impossible";
        }
        int size = shifts.get(0);
        long[] minimal = new long[size];
        fill(minimal, Long.MAX_VALUE / 2);
        for (int i : zeroes) {
            minimal[i % minimal.length] = Math.min(minimal[i % minimal.length], i);
        }
        for (int i = 1; i < shifts.size(); i++) {
            int delta = shifts.get(i);
            int g = gcd(delta, size);
            for (int j = 0; j < g; j++) {
                int k = j;
                for (int l = 0; l < 2; l++) {
                    do {
                        int nk = (k + delta) % size;
                        minimal[nk] = Math.min(minimal[nk], minimal[k] + delta);
                        k = nk;
                    } while (k != j);
                }
            }
        }
        return minimal[(int) (goal % size)] <= goal ? "Possible" : "Impossible";
    }
}
