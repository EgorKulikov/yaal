package on2016_09.on2016_09_13_Codeforces_Round__371__Div__1_.A___Sonya_and_Queries;



import net.egork.collections.map.Counter;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.parseInt;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Counter<Integer> counter = new Counter<>();
        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            char type = in.readCharacter();
            if (type == '+') {
                counter.add(convert(in.readString()));
            } else if (type == '-') {
                counter.add(convert(in.readString()), -1);
            } else {
                out.printLine(counter.get(parseInt(in.readString(), 2)));
            }
        }
    }

    private int convert(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            answer *= 2;
            int digit = s.charAt(i) - '0';
            answer += digit % 2;
        }
        return answer;
    }
}
