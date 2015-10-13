package on2015_10.on2015_10_06_October_Challenge_2015.CyclicShiftsInPermutation;



import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;

public class CyclicShiftsInPermutation {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        /*for (int i = 5; i <= 5; i++) {
            for (int j = 3; j < 4; j++) {
                int[] start = ArrayUtils.createOrder(i);
                Set<IntList> set = new HashSet<>();
                Queue<int[]> queue = new ArrayDeque<>();
                set.add(new IntArray(start));
                queue.add(start);
                while (!queue.isEmpty()) {
                    int[] p = queue.poll();
                    for (int k = 0; k <= i - j; k++) {
                        int[] cand = new int[i];
                        for (int l = 0; l < k; l++) {
                            cand[l] = p[l];
                        }
                        for (int l = k + j; l < i; l++) {
                            cand[l] = p[l];
                        }
                        cand[k] = p[k + j - 1];
                        for (int l = 1; l < j; l++) {
                            cand[k + l] = p[k + l - 1];
                        }
                        IntArray key = new IntArray(cand);
                        if (!set.contains(key)) {
                            set.add(key);
                            queue.add(cand);
                        }
                    }
                }
                System.err.println(i + " " + j + " " + set.size());
                for (IntList list : set) {
                    System.err.println(list.toString() + " " + ArrayUtils.getOddity(list.toArray()));
                }
            }
        }*/
        int n = in.readInt();
        int k = in.readInt();
        int[] p = IOUtils.readIntArray(in, n);
        int[] q = IOUtils.readIntArray(in, n);
        MiscUtils.decreaseByOne(p, q);
        if (k < n) {
            if (k % 2 == 1 && ArrayUtils.getOddity(p) != ArrayUtils.getOddity(q)) {
                out.printLine(-1);
                return;
            }
            long answer = getAnswer(n, q);
            if (k % 2 == 1) {
                answer /= 2;
            } else {
                answer %= MOD;
            }
            answer++;
            answer %= MOD;
            out.printLine(answer);
        } else {
            for (int i = 0; i < n; i++) {
                if (q[0] == p[i]) {
                    boolean good = true;
                    for (int j = 0; j < n; j++) {
                        if (q[j] != p[(i + j) % n]) {
                            good = false;
                            break;
                        }
                    }
                    if (good) {
                        out.printLine(q[0] + 1);
                    } else {
                        out.printLine(-1);
                    }
                    return;
                }
            }
        }
    }

    protected long getAnswer(int n, int[] q) {
        long answer = 0;
        NavigableSet<Integer> was = new TreapSet<>();
        long[] factorials = IntegerUtils.generateFactorial(n, MOD * 2);
        for (int i : q) {
			n--;
			int add = i - was.headSet(i).size();
			answer += factorials[n] * add;
			answer %= MOD * 2;
			was.add(i);
		}
        return answer;
    }
}
