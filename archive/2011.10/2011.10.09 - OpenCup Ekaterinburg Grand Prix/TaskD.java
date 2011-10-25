import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskD implements Solver {
	ArrayList<Long> xs = new ArrayList<Long>();
	ArrayList<Long> ys = new ArrayList<Long>();

	void add(long xx, long yy) {
		xs.add(xx);
		ys.add(yy);
	}

	void go(long x0, long y0, long dx, long dy, long ex, long ey, long z, long area) {
		long h = area / z;
		long m = area % z;
		add(x0, y0);
		x0 += ex * (h + 1);
		y0 += ey * (h + 1);
		add(x0, y0);
		x0 += dx * m;
		y0 += dy * m;
		add(x0, y0);
		x0 += ex * (- 1);
		y0 += ey * (- 1);
		add(x0, y0);
		x0 += dx * (z - m);
		y0 += dy * (z - m);
		add(x0, y0);
		x0 += ex * (- h);
		y0 += ey * (- h);
		add(x0, y0);
	}

	private boolean process(long w, long h, long x, long y, long d) {
		long a = h * w;
		long a2 = a / 2;
		long b = (w - x) * h;
		if (b <= a2) {
			go(x, h, 0, -1, -1, 0, h, a2 - b);
			return false;
		}
		b = (w - x) * (y + d) + h - y - d;
		if (b <= a2) {
			go(x, h, 0, -1, 1, 0, h - y - d, (w - 1 - x) * (h - y - d) - (a2 - b));
			add(x, 0);
			return false;
		}
		b = d * (w - 1 - x) + h;
		if (b <= a2) {
			add(w - 1, h);
			add(w - 1, y + d);
			add(x, y + d);
			go(x, y, 0, -1, 1, 0, y, y * (w - 1 - x) - (a2 - b));
			return false;
		}
		b = d * d + h + w - 1 - d - x;
		if (b <= a2) {
			add(w - 1, h);
			add(w - 1, y + d);
			add(x, y + d);
			add(x, y);
			go(x + d, y, 1, 0, 0, 1, w - 1 - x - d, (w - 1 - x - d) * (d - 1) - (a2 - b));
			add(w - 1, 0);
			return false;
		}
		return true;
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long w = in.readInt();
		long h = in.readInt();
		long x = in.readInt();
		long y = in.readInt();
		long d = in.readInt();
		if (process(w, h, x, y, d)) {
			out.println("Impossible");
			return;
		}
		while (ys.get(1) == h) {
			xs.remove(0);
			ys.remove(0);
		}
		while (ys.get(ys.size() - 2) == 0) {
			xs.remove(xs.size() - 1);
			ys.remove(ys.size() - 1);
		}
		for (int i = 0; i < xs.size(); i++) {
			if (i == 0 || i == xs.size() - 1) {
				continue;
			}
			long x1 = xs.get(i - 1);
			long y1 = ys.get(i - 1);
			long x2 = xs.get(i);
			long y2 = ys.get(i);
			long x3 = xs.get(i + 1);
			long y3 = ys.get(i + 1);
			long ar = x1 * y2 - x2 * y1 + x2 * y3 - x3 * y2 + x3 * y1 - x1 * y3;
			if (ar == 0) {
				xs.remove(i);
				ys.remove(i);
				i--;
			}
		}
		out.println(xs.size());
		for (int i = 0; i < xs.size(); i++) {
			out.println(xs.get(i) + " " + ys.get(i));
		}
	}


}

