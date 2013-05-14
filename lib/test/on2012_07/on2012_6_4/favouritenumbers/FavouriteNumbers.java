package on2012_07.on2012_6_4.favouritenumbers;



import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class FavouriteNumbers {
    Set<String> terminal = new HashSet<String>();
    private Map<String, String[]> graph;
    long[] tens = IntegerUtils.generatePowers(10, 19, Long.MAX_VALUE);
    Map<Pair<String, Integer>, Long> result = new HashMap<Pair<String, Integer>, Long>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long left = in.readLong();
        long right = in.readLong();
        long index = in.readLong();
        int count = in.readInt();
        String[] favorite = IOUtils.readStringArray(in, count);
        terminal = new HashSet<String>();
        Set<String> states = new HashSet<String>();
        for (String s : favorite) {
            for (int i = 0; i <= s.length(); i++)
                states.add(s.substring(0, i));
        }
        for (String s : states) {
            for (String good : favorite) {
                if (s.endsWith(good)) {
                    terminal.add(s);
                    break;
                }
            }
        }
        graph = new HashMap<String, String[]>();
        for (String s : states) {
            String[] moves = new String[10];
            for (int i = 0; i < 10; i++) {
                String ss = s + i;
                for (int j = 0; j <= ss.length(); j++) {
                    if (states.contains(ss.substring(j))) {
                        moves[i] = ss.substring(j);
                        break;
                    }
                }
            }
            graph.put(s, moves);
        }
        index += count(left - 1);
        if (count(right) < index) {
            out.printLine("no such number");
            return;
        }
        while (right > left) {
            long middle = (left + right) >> 1;
            if (count(middle) < index)
                left = middle + 1;
            else
                right = middle;
        }
        out.printLine(left);
	}

    private long count(long left) {
        return count("", Long.toString(left));
    }

    private long count(String state, String suffix) {
        if (terminal.contains(state))
            return Long.parseLong("0" + suffix) + 1;
        if (suffix.length() == 0)
            return 0;
        long result = 0;
        for (int i = 0; i < suffix.charAt(0) - '0'; i++)
            result += count(graph.get(state)[i], suffix.length() - 1);
        return result + count(graph.get(state)[suffix.charAt(0) - '0'], suffix.substring(1));
    }

    private long count(String state, int remaining) {
        Pair<String, Integer> key = Pair.makePair(state, remaining);
        if (result.containsKey(key))
            return result.get(key);
        if (terminal.contains(state)) {
            result.put(key, tens[remaining]);
            return tens[remaining];
        }
        if (remaining == 0) {
            result.put(key, 0L);
            return 0;
        }
        long count = 0;
        for (int i = 0; i < 10; i++)
            count += count(graph.get(state)[i], remaining - 1);
        result.put(key, count);
        return count;
    }
}
