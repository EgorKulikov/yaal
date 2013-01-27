package on2013_01.on2013_01_21_Single_Round_Match_567.StringGame;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;

import java.util.Arrays;

public class StringGame {
	public int[] getWinningStrings(String[] S) {
        int count = S.length;
        char[][] words = new char[count][];
        for (int i = 0; i < count; i++)
            words[i] = S[i].toCharArray();
        int[][] qty = new int[count][26];
        for (int i = 0; i < count; i++) {
            for (char c : words[i])
                qty[i][c - 'a']++;
        }
        IntList answer = new IntArrayList();
        for (int i = 0; i < count; i++) {
            boolean[] can = new boolean[count];
            Arrays.fill(can, true);
            can[i] = false;
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < 26; k++) {
                    boolean valid = true;
                    boolean kills = false;
                    for (int l = 0; l < count; l++) {
                        if (can[l] && qty[l][k] > qty[i][k]) {
                            valid = false;
                            break;
                        }
                        if (can[l] && qty[l][k] < qty[i][k])
                            kills = true;
                    }
                    if (valid && kills) {
                        for (int l = 0; l < count; l++) {
                            if (qty[l][k] < qty[i][k])
                                can[l] = false;
                        }
                        break;
                    }
                }
            }
            boolean good = true;
            for (int j = 0; j < count; j++) {
                if (can[j])
                    good = false;
            }
            if (good)
                answer.add(i);
        }
        return answer.toArray();
	}
}
