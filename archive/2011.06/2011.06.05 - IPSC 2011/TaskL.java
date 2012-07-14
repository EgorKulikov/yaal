import net.egork.utils.Solver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class TaskL implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		try {
			InputStream firstStream = new FileInputStream(in.readString());
			InputStream secondStream = new FileInputStream(in.readString());
			OutputStream output = new FileOutputStream("output.txt");
			while (firstStream.available() != 0) {
				output.write(firstStream.read() ^ secondStream.read());
			}
			output.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

