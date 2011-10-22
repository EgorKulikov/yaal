package net.egork.utils;

import java.io.PrintWriter;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Exit {
	private Exit() {
	}

	public static void exit(net.egork.utils.io.old.InputReader in, PrintWriter out) {
		in.setFinished(true);
		in.close();
		out.close();
	}
}
