package on2013_01.on2013_01_25_ACM_ICPC_Amritapuri_onsite_regionals_2012___Replay.The_Mirror_of_Galadriel;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheMirrorOfGaladriel {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		if (s.equals(StringUtils.reverse(s)))
			out.printLine("YES");
		else
			out.printLine("NO");
    }
}
