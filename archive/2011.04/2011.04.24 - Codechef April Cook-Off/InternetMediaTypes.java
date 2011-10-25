import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Map;

public class InternetMediaTypes implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int typeCount = in.readInt();
		int queryCount = in.readInt();
		Map<String, String> types = new CPPMap<String, String>(new Factory<String>() {
			public String create() {
				return "unknown";
			}
		});
		for (int i = 0; i < typeCount; i++) {
			String extension = in.readString();
			String type = in.readString();
			types.put(extension, type);
		}
		for (int i = 0; i < queryCount; i++) {
			String filename = in.readString();
			int index = filename.lastIndexOf('.');
			if (index == -1)
				index = filename.length() - 1;
			out.println(types.get(filename.substring(index + 1)));
		}
	}
}

