package on2012_12.on2012_12_09_Codeforces_Round__155.B___Jury_Size;



import net.egork.collections.CollectionUtils;
import net.egork.collections.map.Counter;
import net.egork.datetime.Date;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] month = new int[count];
		int[] day = new int[count];
		int[] people = new int[count];
		int[] time = new int[count];
		IOUtils.readIntArrays(in, month, day, people, time);
		Counter<Integer> counter = new Counter<Integer>();
		for (int i = 0; i < count; i++) {
			Date date = new Date(2013, month[i], day[i]);
			int asInt = date.asInt();
			for (int j = asInt - time[i]; j < asInt; j++) {
				counter.add(j, people[i]);
			}
		}
		out.printLine(CollectionUtils.maxElement(counter.values()));
	}
}
