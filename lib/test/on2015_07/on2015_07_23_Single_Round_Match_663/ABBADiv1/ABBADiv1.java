package on2015_07.on2015_07_23_Single_Round_Match_663.ABBADiv1;



import net.egork.string.StringUtils;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class ABBADiv1 {
    public String canObtain(String initial, String target) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(target);
        Set<String> set = new HashSet<>();
        set.add(target);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(initial)) {
                return "Possible";
            }
            if (current.endsWith("A")) {
                String next = current.substring(0, current.length() - 1);
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
            if (current.startsWith("B")) {
                String next = StringUtils.reverse(current.substring(1));
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
		return "Impossible";
    }
}
