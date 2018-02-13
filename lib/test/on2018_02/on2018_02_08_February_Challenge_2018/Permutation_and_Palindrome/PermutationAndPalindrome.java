package on2018_02.on2018_02_08_February_Challenge_2018.Permutation_and_Palindrome;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class PermutationAndPalindrome {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int[] qty = new int[26];
        for (int i = 0; i < s.length(); i++) {
            qty[s.charAt(i) - 'a']++;
        }
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            odd += qty[i] & 1;
        }
        if (odd > 1) {
            out.printLine(-1);
            return;
        }
        IntList[] pos = new IntList[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new IntArrayList();
        }
        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - 'a'].add(i + 1);
        }
        int[] answer = new int[s.length()];
        int start = 0;
        int end = answer.length - 1;
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < qty[i]; j += 2) {
                answer[start++] = pos[i].get(j - 1);
                answer[end--] = pos[i].get(j);
            }
            if ((qty[i] & 1) == 1) {
                answer[answer.length >> 1] = pos[i].get(qty[i] - 1);
            }
        }
        out.printLine(answer);
    }
}
