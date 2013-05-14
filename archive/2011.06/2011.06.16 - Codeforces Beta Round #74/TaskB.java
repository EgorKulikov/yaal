import net.egork.utils.Solver;
import net.egork.utils.old.io.stringinputreader.StringInputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int commandCount = in.readInt();
		Map<String, Widget> map = new TreeMap<String, Widget>();
		for (int i = 0; i < commandCount; i++) {
			String command = in.readLine();
			command = command.replace('.', ' ').replace('(', ' ').replace(')', ' ').replace(',', ' ');
			net.egork.utils.old.io.old.InputReader reader = new StringInputReader(command);
			String first = reader.readString();
			if ("Widget".equals(first)) {
				String name = reader.readString();
				int width = reader.readInt();
				int height = reader.readInt();
				map.put(name, new Widget(width, height));
			} else if ("HBox".equals(first))
				map.put(reader.readString(), new Widget(Alignment.HORIZONTAL));
			else if ("VBox".equals(first))
				map.put(reader.readString(), new Widget(Alignment.VERTICAL));
			else {
				String method = reader.readString();
				if ("set_border".equals(method))
					map.get(first).border = reader.readInt();
				else if ("set_spacing".equals(method))
					map.get(first).spacing = reader.readInt();
				else
					map.get(first).children.add(map.get(reader.readString()));
			}
		}
		for (Map.Entry<String, Widget> entry : map.entrySet()) {
			entry.getValue().pack();
			out.println(entry.getKey() + " " + entry.getValue().width + " " + entry.getValue().height);
		}
	}

	private static class Widget {
		private long width = 0;
		private long height = 0;
		private Alignment alignment = Alignment.VERTICAL;
		private long border = 0;
		private long spacing = 0;
		private List<Widget> children = new ArrayList<Widget>();
		private boolean packed = false;

		private Widget(int width, int height) {
			this.width = width;
			this.height = height;
		}

		private Widget(Alignment alignment) {
			this.alignment = alignment;
		}

		public void pack() {
			if (packed)
				return;
			packed = true;
			if (children.isEmpty())
				return;
			for (Widget child : children) {
				child.pack();
				if (alignment == Alignment.VERTICAL) {
					width = Math.max(width, child.width);
					height += child.height;
				} else {
					height = Math.max(height, child.height);
					width += child.width;
				}
			}
			if (alignment == Alignment.VERTICAL)
				height += (children.size() - 1) * spacing;
			else
				width += (children.size() - 1) * spacing;
			height += 2 * border;
			width += 2 * border;
		}
	}

	private enum Alignment {
		VERTICAL, HORIZONTAL
	}
}

