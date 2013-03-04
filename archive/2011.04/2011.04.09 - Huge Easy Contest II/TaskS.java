package April2011.UVaHugeEasyContestII;

import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.*;

public class TaskS implements Solver {
	public void solve(int testNumber, final InputReader in, PrintWriter out) {
		int programCount = in.readInt();
		int studentCount = in.readInt();
		String[] programName = new String[programCount];
		int[] spacesAvailable = new int[programCount];
		Map<Integer, Integer> programID = new HashMap<Integer, Integer>();
		for (int i = 0; i < programCount; i++) {
			programName[i] = in.readLine();
			programID.put(in.readInt(), i);
			spacesAvailable[i] = in.readInt();
		}
		String[] studentName = new String[studentCount];
		final int[] studentID = new int[studentCount];
		int[][] program = new int[studentCount][];
		final double[][] mark = new double[studentCount][];
		final double[] significantMark = new double[studentCount];
		final int[] index = new int[studentCount];
		for (int i = 0; i < studentCount; i++) {
			studentName[i] = in.readLine();
			int count = in.readInt();
			studentID[i] = in.readInt();
			program[i] = new int[count];
			mark[i] = new double[count];
			for (int j = 0; j < count; j++) {
				program[i][j] = programID.get(in.readInt());
				mark[i][j] = in.readDouble();
			}
		}
		final Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = Double.compare(mark[o1][index[o1]], mark[o2][index[o2]]);
				if (value != 0)
					return value;
				return studentID[o1] - studentID[o2];
			}
		};
		@SuppressWarnings({"unchecked"})
		PriorityQueue<Integer>[] admitted = new PriorityQueue[programCount];
		for (int i = 0; i < programCount; i++)
			admitted[i] = new PriorityQueue<Integer>(spacesAvailable[i] + 1, comparator);
		Queue<Integer> students = new ArrayDeque<Integer>();
		for (int i = 0; i < studentCount; i++)
			students.add(i);
		while (!students.isEmpty()) {
			int current = students.poll();
			if (index[current] == program[current].length)
				continue;
			int id = program[current][index[current]];
			significantMark[current] = mark[current][index[current]];
			admitted[id].add(current);
			if (admitted[id].size() > spacesAvailable[id]) {
				int next = admitted[id].poll();
				index[next]++;
				students.add(next);
			}
		}
		Comparator<Integer> outputComparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = Double.compare(significantMark[o1], significantMark[o2]);
				if (value != 0)
					return -value;
				return studentID[o2] - studentID[o1];
			}
		};
		for (int i = 0; i < programCount; i++) {
			out.println(programName[i]);
			out.println(admitted[i].size());
			List<Integer> currentAdmitted = new ArrayList<Integer>(admitted[i]);
			Collections.sort(currentAdmitted, outputComparator);
			for (int j = 0; j < currentAdmitted.size(); j++) {
				out.println((j + 1) + ". " + studentName[currentAdmitted.get(j)]);
			}
		}
	}
}

