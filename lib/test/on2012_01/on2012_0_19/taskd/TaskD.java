package on2012_01.on2012_0_19.taskd;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        String[] buttons = {"ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        char[] corresponding = new char[256];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < buttons[i].length(); j++)
                corresponding[buttons[i].charAt(j)] = (char) ('2' + i);
        }
        char[] input = in.readString().toCharArray();
        for (int i = 0; i < input.length; i++)
            input[i] = corresponding[Character.toUpperCase(input[i])];
        if (new String(input).equals(StringUtils.reverse(new String(input))))
            out.printLine("YES");
        else
            out.printLine("NO");
	}
}
