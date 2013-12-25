package on2013_05.on2013_05_19_May_Cook_off_2013.Subtraction_Game_1;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SubtractionGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i : numbers)
			answer = IntegerUtils.gcd(answer, i);
		out.printLine(answer);
    }
}
