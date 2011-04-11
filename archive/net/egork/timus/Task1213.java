package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task1213 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		Set<String> rooms = new HashSet<String>();
		rooms.add(in.readString());
		while (true) {
			String connection = in.readString();
			if ("#".equals(connection)) {
				out.println(rooms.size() - 1);
				return;
			}
			rooms.addAll(Arrays.asList(connection.split("-")));
		}
	}
}

