package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String place = in.readString();
		int row = Integer.parseInt(place.substring(0, place.length() - 1));
		char inRow = place.charAt(place.length() - 1);
		if (row <= 2) {
			if (inRow == 'A' || inRow == 'D')
				out.println("window");
			else
				out.println("aisle");
		} else if (row <= 20) {
			if (inRow == 'A' || inRow == 'F')
				out.println("window");
			else
				out.println("aisle");
		} else {
			if (inRow == 'A' || inRow == 'K')
				out.println("window");
			else if (inRow == 'C' || inRow == 'D' || inRow == 'G' || inRow == 'H')
				out.println("aisle");
			else
				out.println("neither");
		}
	}
}
