package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.map.Counter;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static net.egork.misc.MiscUtils.isValidCell;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int d1 = in.readInt();
        int d2 = in.readInt();
        List<IntIntPair> answer = solve(n, d1, d2);
        for (IntIntPair result : answer) {
            out.printLine(result.first, result.second);
        }
    }

    List<IntIntPair> solve(int n, int d1, int d2) {
        List<IntIntPair> answer = new ArrayList<>();
        if (d1 % 4 != 0 && d2 % 4 != 0) {
            for (int i = 0; i < 2 * n; i += 2) {
                for (int j = 0; j < 2 * n; j += 2) {
                    answer.add(new IntIntPair(i, j));
                }
            }
            return answer;
        }
        if (d1 % 4 == 0 && d2 % 4 == 0) {
            List<IntIntPair> call = solve((n + 1) / 2, d1 / 4, d2 / 4);
            for (IntIntPair pair : call) {
                if (pair.first < n && pair.second < n) {
                    if (answer.size() < n * n) {
                        answer.add(new IntIntPair(2 * pair.first, 2 * pair.second));
                    }
                    if (answer.size() < n * n) {
                        answer.add(new IntIntPair(2 * pair.first, 2 * pair.second + 1));
                    }
                    if (answer.size() < n * n) {
                        answer.add(new IntIntPair(2 * pair.first + 1, 2 * pair.second));
                    }
                    if (answer.size() < n * n) {
                        answer.add(new IntIntPair(2 * pair.first + 1, 2 * pair.second + 1));
                    }
                }
            }
            return answer;
        }
        if (d2 % 4 == 0) {
            return solve(n, d2, d1);
        }
        List<IntIntPair> call = solve((n + 1) / 2, d1 / 4);
        for (IntIntPair pair : call) {
            if (pair.first < n && pair.second < n) {
                if (answer.size() < n * n) {
                    answer.add(new IntIntPair(2 * pair.first, 2 * pair.second));
                }
                if (answer.size() < n * n && d2 % 2 == 0) {
                    answer.add(new IntIntPair(2 * pair.first, 2 * pair.second + 1));
                }
                if (answer.size() < n * n && d2 % 2 == 1) {
                    answer.add(new IntIntPair(2 * pair.first + 1, 2 * pair.second + 1));
                }
            }
        }
        return answer;
    }

    private List<IntIntPair> solve(int n, int d) {
        List<IntIntPair> answer = new ArrayList<>();
        if (d % 4 == 0) {
            List<IntIntPair> call = solve((n + 1) / 2, d / 4);
            for (IntIntPair pair : call) {
                if (pair.first < n && pair.second < n) {
                    answer.add(new IntIntPair(2 * pair.first, 2 * pair.second));
                    answer.add(new IntIntPair(2 * pair.first, 2 * pair.second + 1));
                    answer.add(new IntIntPair(2 * pair.first + 1, 2 * pair.second));
                    answer.add(new IntIntPair(2 * pair.first + 1, 2 * pair.second + 1));
                }
            }
            return answer;
        }
        if (d % 2 == 0) {
            for (int i = 0; i < 2 * n; i += 2) {
                for (int j = 0; j < 2 * n; j++) {
                    answer.add(new IntIntPair(i, j));
                }
            }
            return answer;
        }
        for (int i = 0; i < 2 * n; i++) {
            for (int j = i % 2; j < 2 * n; j += 2) {
                answer.add(new IntIntPair(i, j));
            }
        }
        return answer;
    }
}
