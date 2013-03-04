package net.egork;

import net.egork.datetime.Date;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Facepalm {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String compressed = in.readString();
		List<String> answer = new ArrayList<String>();
		Date last = new Date(2100, 1, 1);
		for (Date date = new Date(2000, 1, 1); !date.equals(last); date = date.next()) {
			String current = date.toString("ddmmyy").replace("0", "");
			if (current.equals(compressed))
				answer.add(date.toString("dd.mm.yy"));
		}
		out.printLine(answer.size());
		for (String date : answer)
			out.printLine(date);
	}
}
