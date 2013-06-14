package net.egork;

import net.egork.collections.map.EHashMap;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;

public class Binary {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int length = in.readInt();
        int count = in.readInt();
        String sample = in.readString();
        Map<Long, Integer> map = new EHashMap<Long, Integer>();
        StringHash hash = new SimpleStringHash(sample);
        for (int i = 0; i <= count; i++)
            map.put(hash.hash(0, i), i);
        int[][] moves = new int[count + 1][2];
        moves[count][0] = moves[count][1] = count;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 2; j++) {
                String current = sample.substring(0, i) + j;
                StringHash currentHash = new SimpleStringHash(current);
                for (int k = 0; k <= current.length(); k++) {
                    if (map.containsKey(currentHash.hash(k))) {
                        moves[i][j] = map.get(currentHash.hash(k));
                        break;
                    }
                }
            }
        }
        int[] answer = new int[count + 1];
        answer[0] = 1;
        int[] next = new int[count + 1];
        for (int i = 0; i < length; i++) {
            Arrays.fill(next, 0);
            for (int j = 0; j <= count; j++) {
                next[moves[j][0]] += answer[j];
                next[moves[j][1]] += answer[j];
            }
            for (int j = 0; j <= count; j++)
                next[j] %= 10000;
            int[] temp = next;
            next = answer;
            answer = temp;
        }
        out.printLine(answer[count]);
	}
}
