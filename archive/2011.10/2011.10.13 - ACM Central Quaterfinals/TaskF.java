import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[][] table = IOUtils.readIntTable(in, 3, 3);
		if (isMagic(table)) {
			out.println(0);
			return;
		}
		Map<Long, Integer> answer = new HashMap<Long, Integer>();
		Map<Long, Long> last = new HashMap<Long, Long>();
		long code = encode(table);
		answer.put(code, 0);
		int[][] next = new int[3][3];
		Queue<Long> queue = new ArrayDeque<Long>();
		queue.add(code);
		long result = -1;
		main:
		while (!queue.isEmpty()) {
			code = queue.poll();
			decode(table, code);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					shift(table, next, i, j);
					long nextCode = encode(next);
					if (!answer.containsKey(nextCode)) {
						answer.put(nextCode, answer.get(code) + 1);
						last.put(nextCode, (code << 4) + (i << 2) + j);
						queue.add(nextCode);
						if (isMagic(next)) {
							result = nextCode;
							break main;
						}
					}
				}
			}
		}
		if (result == -1)
			throw new RuntimeException();
		out.println(answer.get(result));
		List<String> sequence = new ArrayList<String>(answer.get(result));
		char[] direction = {'D', 'U', 'L', 'R'};
		while (last.get(result) != null) {
			String current = "";
			result = last.get(result);
			current = ((result & 3) + 1) + current;
			result >>= 2;
			current = direction[((int) (result & 3))] + current;
			result >>= 2;
			sequence.add(current);
		}
		Collections.reverse(sequence);
		for (String move : sequence)
			out.println(move);
	}

	private boolean isMagic(int[][] table) {
		int sum;
		for (int i = 0; i < 3; i++) {
			sum = 0;
			for (int j = 0; j < 3; j++)
				sum += table[i][j];
			if (sum != 15)
				return false;
			sum = 0;
			for (int j = 0; j < 3; j++)
				sum += table[j][i];
			if (sum != 15)
				return false;
		}
		sum = 0;
		for (int j = 0; j < 3; j++)
			sum += table[j][j];
		if (sum != 15)
			return false;
		sum = 0;
		for (int j = 0; j < 3; j++)
			sum += table[j][2 - j];
		return sum == 15;
	}

	private long encode(int[][] table) {
		long result = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result <<= 4;
				result += table[i][j];
			}
		}
		return result;
	}

	private void decode(int[][] table, long code) {
		for (int i = 2; i >= 0; i--) {
			for (int j = 2; j >= 0; j--) {
				table[i][j] = (int) (code & 15);
				code >>= 4;
			}
		}
	}

	private void shift(int[][] table, int[][] next, int direction, int index) {
		for (int i = 0; i < 3; i++)
			System.arraycopy(table[i], 0, next[i], 0, 3);
		if (direction == 0) {
			for (int i = 0; i < 3; i++)
				next[i][index] = table[(i + 2) % 3][index];
		} else if (direction == 1) {
			for (int i = 0; i < 3; i++)
				next[i][index] = table[(i + 1) % 3][index];
		} else if (direction == 2) {
			for (int i = 0; i < 3; i++)
				next[index][i] = table[index][(i + 1) % 3];
		} else {
			for (int i = 0; i < 3; i++)
				next[index][i] = table[index][(i + 2) % 3];
		}
	}
}

