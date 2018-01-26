package on2017_07.on2017_07_08_IPSC_2017.B;





import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int c = in.readInt();
        String color = in.readString();
        IntIntPair[] answer;
        if (r <= c) {
            answer = solve(r, c, color);
        } else {
            answer = solve(c, r, color);
            if (answer != null) {
                for (int i = 0; i < answer.length; i++) {
                    answer[i] = new IntIntPair(answer[i].second, answer[i].first);
                }
            }
        }
        if (answer == null) {
            out.printLine("impossible");
        } else {
            for (int i = 0; i < answer.length; i++) {
                out.printLine(answer[i].first, answer[i].second);
            }
        }
    }

    private IntIntPair[] solve(int r, int c, String color) {
        if (r == 1 && c > 3 || r == 1 && c == 3 && color.equals("W")) {
            return null;
        }
        if (r == 5 && c == 5 && color.equals("B")) {
            IntIntPair[] answer = new IntIntPair[12];
            answer[0] = new IntIntPair(1, 2);
            answer[1] = new IntIntPair(2, 1);
            answer[2] = new IntIntPair(3, 2);
            answer[3] = new IntIntPair(4, 1);
            answer[4] = new IntIntPair(5, 2);
            answer[5] = new IntIntPair(4, 3);
            answer[6] = new IntIntPair(5, 4);
            answer[7] = new IntIntPair(4, 5);
            answer[8] = new IntIntPair(3, 4);
            answer[9] = new IntIntPair(2, 5);
            answer[10] = new IntIntPair(1, 4);
            answer[11] = new IntIntPair(2, 3);
            return answer;
        }
        if (r == 5 && c == 7 && color.equals("B")) {
            IntIntPair[] answer = new IntIntPair[17];
            answer[0] = new IntIntPair(1, 4);
            answer[1] = new IntIntPair(2, 3);
            answer[2] = new IntIntPair(1, 2);
            answer[3] = new IntIntPair(2, 1);
            answer[4] = new IntIntPair(3, 2);
            answer[5] = new IntIntPair(4, 1);
            answer[6] = new IntIntPair(5, 2);
            answer[7] = new IntIntPair(4, 3);
            answer[8] = new IntIntPair(5, 4);
            answer[9] = new IntIntPair(4, 5);
            answer[10] = new IntIntPair(3, 4);
            answer[11] = new IntIntPair(2, 5);
            answer[12] = new IntIntPair(1, 6);
            answer[13] = new IntIntPair(2, 7);
            answer[14] = new IntIntPair(3, 6);
            answer[15] = new IntIntPair(4, 7);
            answer[16] = new IntIntPair(5, 6);
            return answer;
        }
        if (r > 3 || r == 3 && c > 5) {
            return null;
        }
        if (r == 3 && c == 3) {
            if (color.equals("W")) {
                return null;
            }
            IntIntPair[] answer = new IntIntPair[4];
            answer[0] = new IntIntPair(1, 2);
            answer[1] = new IntIntPair(2, 1);
            answer[2] = new IntIntPair(3, 2);
            answer[3] = new IntIntPair(2, 3);
            return answer;
        }
        if (r == 3 && c == 4) {
            return null;
        }
        if (r == 3 && c == 5) {
            if (color.equals("W")) {
                return null;
            }
            IntIntPair[] answer = new IntIntPair[7];
            answer[0] = new IntIntPair(3, 2);
            answer[1] = new IntIntPair(2, 1);
            answer[2] = new IntIntPair(1, 2);
            answer[3] = new IntIntPair(2, 3);
            answer[4] = new IntIntPair(3, 4);
            answer[5] = new IntIntPair(2, 5);
            answer[6] = new IntIntPair(1, 4);
            return answer;
        }
        int curRow = color.equals("B") ? 2 : 1;
        int curColumn = 1;
        if (r == 1) {
            curColumn = curRow;
            curRow = 1;
        }
        IntIntPair[] answer = new IntIntPair[r * c / 2 + ((r * c % 2 == 1 && color.equals("W")) ? 1 : 0)];
        int at = 0;
        boolean endDone = false;
        while (true) {
            List<IntIntPair> all = new ArrayList<>();
            int diagonal = curRow + curColumn;
            if (diagonal > r + c || endDone && diagonal == r + c) {
                break;
            }
            for (int i = 1; i <= r; i++) {
                if (diagonal - i >= 1 && diagonal - i <= c) {
                    all.add(new IntIntPair(i, diagonal - i));
                }
            }
            int start = all.indexOf(new IntIntPair(curRow, curColumn));
            int end;
            int firstCandidate = -1;
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).first + 1 <= r && all.get(i).second + 1 <= c) {
                    firstCandidate = i;
                    break;
                }
            }
            int lastCandidate = -1;
            for (int i = all.size() - 1; i >= 0; i--) {
                if (all.get(i).first + 1 <= r && all.get(i).second + 1 <= c) {
                    lastCandidate = i;
                    break;
                }
            }
            if (all.size() == 1) {
                end = 0;
            } else if (all.size() == 2 && firstCandidate == -1) {
                end = 1 - start;
            } else if (firstCandidate == start) {
                end = lastCandidate;
            } else {
                end = firstCandidate;
            }
            answer[at++] = all.get(start);
            for (int i = 0; i < all.size(); i++) {
                if (i != start && i != end) {
                    answer[at++] = all.get(i);
                }
            }
            if (start != end) {
                answer[at++] = all.get(end);
            }
            curRow = all.get(end).first + 1;
            curColumn = all.get(end).second + 1;
            if (curRow - curColumn == r - c && !endDone && at != answer.length) {
                answer[at++] = new IntIntPair(r, c);
                endDone = true;
            }
        }
        for (int i = 0; i < answer.length - 1; i++) {
            if (answer[i].first + answer[i].second != answer[i + 1].first + answer[i + 1].second &&
                    answer[i].first - answer[i].second != answer[i + 1].first - answer[i + 1].second) {
                throw new RuntimeException();
            }
            if (answer[i].first <= 0 || answer[i].first > r || answer[i].second <= 0 || answer[i].second > c) {
                throw new RuntimeException();
            }
            for (int j = i + 1; j < answer.length; j++) {
                if (answer[i].equals(answer[j])) {
                    throw new RuntimeException();
                }
            }
        }
        return answer;
    }
}
