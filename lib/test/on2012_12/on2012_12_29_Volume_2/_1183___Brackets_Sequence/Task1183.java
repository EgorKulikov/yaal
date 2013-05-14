package on2012_12.on2012_12_29_Volume_2._1183___Brackets_Sequence;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1183 {
    int[][] answer;
    int[][] position;
    char[] sequence;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            out.printLine();
            return;
        }
        sequence = in.readString().toCharArray();
        int count = sequence.length;
        answer = new int[count][count];
        position = new int[count][count];
        ArrayUtils.fill(answer, -1);
        go(0, count - 1);
        out.printLine(build(0, count - 1));
    }

    private String build(int from, int to) {
        if (from > to)
            return "";
        if (position[from][to] == from) {
            if (sequence[from] == '(' || sequence[from] == ')')
                return "()" + build(from + 1, to);
            return "[]" + build(from + 1, to);
        }
        return sequence[from] + build(from + 1, position[from][to] - 1) + sequence[position[from][to]] +
                build(position[from][to] + 1, to);
    }

    private int go(int from, int to) {
        if (from > to)
            return 0;
        if (answer[from][to] != -1)
            return answer[from][to];
        answer[from][to] = go(from + 1, to) + 2;
        position[from][to] = from;
        char closing;
        if (sequence[from] == '(')
            closing = ')';
        else if (sequence[from] == '[')
            closing = ']';
        else
            return answer[from][to];
        for (int i = from + 1; i <= to; i++) {
            if (sequence[i] == closing) {
                int current = go(from + 1, i - 1) + go(i + 1, to) + 2;
                if (current < answer[from][to]) {
                    answer[from][to] = current;
                    position[from][to] = i;
                }
            }
        }
        return answer[from][to];
    }
}
