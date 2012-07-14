import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int hatCount = in.readInt();
		int tShirtCount = in.readInt();
		int pantCount = in.readInt();
		int socksCount = in.readInt();
		int shoesCount = in.readInt();
		if (hatCount == 0)
			throw new UnknownError();
		out.printLine(hatCount * tShirtCount * pantCount * socksCount * socksCount * shoesCount * shoesCount);
	}
}
