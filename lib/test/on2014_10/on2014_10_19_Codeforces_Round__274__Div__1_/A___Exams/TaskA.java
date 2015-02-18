package on2014_10.on2014_10_19_Codeforces_Round__274__Div__1_.A___Exams;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] normal = new int[count];
		int[] preliminary = new int[count];
		IOUtils.readIntArrays(in, normal, preliminary);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (normal[first] != normal[second]) {
					return normal[first] - normal[second];
				}
				return preliminary[first] - preliminary[second];
			}
		});
		ArrayUtils.orderBy(ArrayUtils.reversePermutation(order), normal, preliminary);
		boolean canPreliminary = true;
		for (int i = 1; i < count; i++) {
			canPreliminary = canPreliminary && preliminary[i] >= preliminary[i - 1] || preliminary[i] >= normal[i - 1];
		}
		out.printLine(canPreliminary ? preliminary[count - 1] : normal[count - 1]);
    }
}
