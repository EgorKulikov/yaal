package on2013_09.on2013_09_18_Codeforces_Trainings_Season_1_Episode_2.TaskG;



import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (rowCount == 0 && columnCount == 0)
			throw new UnknownError();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int size = Math.max(Integer.highestOneBit(2 * rowCount - 1), Integer.highestOneBit(2 * columnCount - 1));
		map = Arrays.copyOf(map, size);
		for (int i = 0; i < rowCount; i++) {
			map[i] = Arrays.copyOf(map[i], size);
			Arrays.fill(map[i], columnCount, size, '0');
		}
		for (int i = rowCount; i < size; i++) {
			map[i] = new char[size];
			Arrays.fill(map[i], '0');
		}
		Node.init();
		Node root = Node.build(map, 0, 0, size);
		if (root.id < 2)
			Node.squadSize++;
		out.printLine(root.quadSize, Node.squadSize);
    }

	static class Node {
		static int nextID = 2;
		static List<Node> nodes = new ArrayList<Node>();
		static Map<Node, Integer> map = new EHashMap<Node, Integer>();
		static Node white = new Node();
		static Node black = new Node();
		static int squadSize;

		static void init() {
			white.id = 0;
			black.id = 1;
			white.quadSize = 1;
			black.quadSize = 1;
			Arrays.fill(black.children, 1);
			nextID = 2;
			nodes = new ArrayList<Node>();
			map = new EHashMap<Node, Integer>();
			squadSize = 0;
			nodes.add(white);
			nodes.add(black);
		}

		int quadSize;
		int id;
		int[] children = new int[4];

		static Node build(char[][] map, int row, int column, int size) {
			boolean same = true;
			for (int i = row; i < row + size && same; i++) {
				for (int j = column; j < column + size && same; j++) {
					if (map[i][j] != map[row][column])
						same = false;
				}
			}
			if (same) {
				return nodes.get(map[row][column] - '0');
			}
			Node node = new Node();
			int half = size >> 1;
			node.children[0] = build(map, row, column, half).id;
			node.children[1] = build(map, row, column + half, half).id;
			node.children[2] = build(map, row + half, column, half).id;
			node.children[3] = build(map, row + half, column + half, half).id;
			if (Node.map.containsKey(node))
				node = nodes.get(Node.map.get(node));
			else {
				squadSize++;
				node.id = nextID++;
				nodes.add(node);
				node.quadSize = 1;
				Node.map.put(node, node.id);
				for (int i : node.children) {
					node.quadSize += nodes.get(i).quadSize;
					if (i < 2)
						squadSize++;
				}
			}
			return node;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Node node = (Node) o;

			if (!Arrays.equals(children, node.children)) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(children);
		}
	}
}
