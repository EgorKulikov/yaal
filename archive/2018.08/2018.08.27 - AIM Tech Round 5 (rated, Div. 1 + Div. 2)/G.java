package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static java.util.Arrays.copyOf;

public class G {
    long[] moves = {204761410474L};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long left = 1;
        long right = 10004205361450474L;
        for (int i = 0; i < 5; i++) {
            long[] request;
            if (i == 0) {
                request = new long[2];
                request[0] = 1;
                request[1] = moves[0];
            } else {
                request = request(5 - i, left, right);
            }
            out.printLine(request);
            out.flush();
            int result = in.readInt();
            if (result == -1) {
                return;
            }
            if (result == 0) {
                right = request[1] - 1;
            } else if (result == request[0]) {
                left = request[result] + 1;
            } else {
                left = request[result] + 1;
                right = request[result + 1] - 1;
            }
        }
    }

    long bigMove(int moves) {
        long result = 0;
        for (int i = 0; i < moves; i++) {
            result *= 10001;
            result += 10000;
        }
        return result;
    }

    long[] request(int moves, long left, long right) {
        long[] request = new long[(int) (1 + min(left, 10000))];
        request[0] = request.length - 1;
        for (int i = 1; i < request.length; i++) {
            if (left > right) {
                request[0] = i - 1;
                request = copyOf(request, i);
                return request;
            }
            if (left >= 10000) {
                long next = bigMove(moves - 1) + left;
                request[i] = min(next, right);
                left = next + 1;
            } else {
                long from = left;
                long to = right;
                while (from < to) {
                    long middle = (from + to + 1) >> 1;
                    if (fit(moves - 1, left, middle - 1)) {
                        from = middle;
                    } else {
                        to = middle - 1;
                    }
                }
                request[i] = from;
                left = from + 1;
            }
        }
        if (!fit(moves - 1, left, right)) {
            return null;
        }
        return request;
    }

    private boolean fit(int moves, long left, long right) {
        if (left > right) {
            return true;
        }
        if (moves == 0) {
            return false;
        }
        long max = min(left, 10000);
        for (int i = 1; i < moves; i++) {
            max *= 10001;
            max += 10000;
        }
        if (right - left >= max) {
            return false;
        }
        if (left >= 10000) {
            return true;
        }
        int length = (int) (1 + min(left, 10000));
        for (int i = 1; i < length; i++) {
            if (left > right) {
                return true;
            }
            if (left >= 10000) {
                long next = bigMove(moves - 1) + left;
                left = next + 1;
            } else {
                long from = left;
                long to = right;
                while (from < to) {
                    long middle = (from + to + 1) >> 1;
                    if (fit(moves - 1, left, middle - 1)) {
                        from = middle;
                    } else {
                        to = middle - 1;
                    }
                }
                left = from + 1;
            }
        }
        if (!fit(moves - 1, left, right)) {
            return false;
        }
        return true;
    }
}
