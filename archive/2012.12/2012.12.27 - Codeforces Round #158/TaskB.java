package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.map.Counter;
import net.egork.datetime.Date;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String prophecy = in.readString();
        Counter<String> counter = new Counter<String>();
        for (int i = 0; i <= prophecy.length() - 10; i++) {
            String current = prophecy.substring(i, i + 10);
            boolean good = true;
            for (int j = 0; j < 10; j++) {
                if (current.charAt(j) == '-') {
                    if (j != 2 && j != 5) {
                        good = false;
                        break;
                    }
                } else if (j == 2 || j == 5) {
                    good = false;
                    break;
                }
            }
            if (!good)
                continue;
            Date date = Date.parse(current, "DD-MM-YYYY");
            if (date.isValid() && date.year >= 2013 && date.year <= 2015)
                counter.add(current);
        }
        long max = CollectionUtils.maxElement(counter.values());
        for (String s : counter.keySet()) {
            if (counter.get(s) == max) {
                out.printLine(s);
                return;
            }
        }
        throw new RuntimeException();
    }
}
