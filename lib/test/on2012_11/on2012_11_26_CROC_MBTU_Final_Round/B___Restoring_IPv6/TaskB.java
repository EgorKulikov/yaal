package on2012_11.on2012_11_26_CROC_MBTU_Final_Round.B___Restoring_IPv6;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.StringWrapper;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String address = in.readString();
		int position = address.indexOf("::");
		if (position != -1) {
			int separators = CollectionUtils.count(StringWrapper.wrap(address), ':') - 2;
			int skipped = 6 - separators;
			if (position == 0)
				skipped++;
			if (position == address.length() - 2)
				skipped++;
			String newAddress = address.substring(0, position);
			if (position != 0)
				newAddress += ":";
			for (int i = 0; i < skipped; i++) {
				newAddress += "0";
				if (i != skipped - 1)
					newAddress += ":";
			}
			if (position != address.length() - 2)
				newAddress += ":";
			newAddress += address.substring(position + 2);
			address = newAddress;
		}
		String[] tokens = address.split(":");
		boolean first = true;
		for (String token : tokens) {
			if (first)
				first = false;
			else
				out.print(':');
			for (int i = 0; i < 4 - token.length(); i++) {
				out.print(0);
			}
			out.print(token);
		}
		out.printLine();
	}
}
