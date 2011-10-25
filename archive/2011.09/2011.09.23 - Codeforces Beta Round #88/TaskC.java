import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		char[][] graph = IOUtils.readTable(in, count, count);
		Node head = new Node(null, 0);
		for (int i = 1; i < count; i++) {
			Node current = head;
			Node last = null;
			int incoming = -1;
			Node position = null;
			while (current != null) {
				if (graph[i][current.id] == '0') {
					if (incoming != -1) {
						out.println((i + 1) + " " + (incoming + 1) + " " + (current.id + 1));
						return;
					}
				} else {
					if (incoming == -1)
						position = last;
					incoming = current.id;
				}
				last = current;
				current = current.next;
			}
			if (incoming == -1)
				last.next = new Node(null, i);
			else if (position == null)
				head = new Node(head, i);
			else
				position.next = new Node(position.next, i);
		}
		out.println(-1);
	}

	private static class Node {
		private Node next;
		private final int id;

		private Node(Node next, int id) {
			this.next = next;
			this.id = id;
		}
	}
}

