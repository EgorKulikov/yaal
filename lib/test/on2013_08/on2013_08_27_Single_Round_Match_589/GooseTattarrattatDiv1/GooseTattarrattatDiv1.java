package on2013_08.on2013_08_27_Single_Round_Match_589.GooseTattarrattatDiv1;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.misc.ArrayUtils;

public class GooseTattarrattatDiv1 {
    public int getmin(String S) {
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(26);
		for (int i = 0, j = S.length() - 1; i < j; i++, j--)
			setSystem.join(S.charAt(i) - 'a', S.charAt(j) - 'a');
		int[] max = new int[26];
		int[] qty = new int[26];
		for (int i = 0; i < S.length(); i++)
			qty[S.charAt(i) - 'a']++;
		for (int i = 0; i < 26; i++)
			max[setSystem.get(i)] = Math.max(max[setSystem.get(i)], qty[i]);
		return (int) (S.length() - ArrayUtils.sumArray(max));
    }
}
