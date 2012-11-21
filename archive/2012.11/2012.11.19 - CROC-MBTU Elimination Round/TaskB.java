package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String address = in.readString();
		int index = address.lastIndexOf("ru");
		String domain = address.substring(0, index);
		String context = address.substring(index + 2);
		String protocol;
		if (domain.startsWith("http"))
			protocol = "http";
		else
			protocol = "ftp";
		domain = domain.substring(protocol.length());
		out.print(protocol + "://" + domain + ".ru");
		if (!context.isEmpty())
			out.print("/" + context);
		out.printLine();
	}
}
