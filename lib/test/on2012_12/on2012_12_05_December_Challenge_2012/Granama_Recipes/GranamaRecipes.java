package on2012_12.on2012_12_05_December_Challenge_2012.Granama_Recipes;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GranamaRecipes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] first = in.readString().toCharArray();
        char[] second = in.readString().toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        if (Arrays.equals(first, second)) {
            out.printLine("YES");
            return;
        }
        first = ArrayUtils.unique(first);
        second = ArrayUtils.unique(second);
        if (Arrays.equals(first, second))
            out.printLine("NO");
        else
            out.printLine("YES");
    }
}
