package net.egork.timus;

import net.egork.collections.CPPMap;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task1446 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int studentCount = in.readInt();
		Map<String, List<String>> map = new CPPMap<String, List<String>>(new Factory<List<String>>() {
			public List<String> create() {
				return new ArrayList<String>();
			}
		});
		for (int i = 0; i < studentCount; i++) {
			String studentName = in.readLine();
			String faculty = in.readString();
			map.get(faculty).add(studentName);
		}
		String[] faculties = {"Slytherin", "Hufflepuff", "Gryffindor", "Ravenclaw"};
		boolean isFirst = true;
		for (String faculty : faculties) {
			if (isFirst)
				isFirst = false;
			else
				out.println();
			out.println(faculty + ":");
			for (String student : map.get(faculty))
				out.println(student);
		}
	}
}

