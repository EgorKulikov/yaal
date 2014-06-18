package on2014_06.on2014_06_June_Challenge_2014.Forgot_Password;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ForgotPassword {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] from = new char[count];
		char[] to = new char[count];
		for (int i = 0; i < count; i++) {
			from[i] = in.readCharacter();
			to[i] = in.readCharacter();
		}
		char[] number = in.readString().toCharArray();
		int[] map = ArrayUtils.createOrder(128);
		for (int i = 0; i < count; i++)
			map[from[i]] = to[i];
		for (int i = 0; i < number.length; i++)
			number[i] = (char) map[number[i]];
		int start = 0;
		int end = number.length - 1;
		if (ArrayUtils.count(number, '.') != 0) {
			while (end > start && number[end] == '0')
				end--;
		}
		if (end >= start && number[end] == '.')
			end--;
		while (start < end && number[start] == '0')
			start++;
		if (start <= end)
			out.printLine(new String(number, start, end - start + 1));
		else
			out.printLine(0);
    }
}
