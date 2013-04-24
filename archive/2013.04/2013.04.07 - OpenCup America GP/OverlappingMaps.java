package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class OverlappingMaps {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int w = in.readInt();
		int h = in.readInt();
		int x = in.readInt();
		int y = in.readInt();
		int s = in.readInt();
		int r = in.readInt();
		if (s == 0) throw new UnknownError();
		double vx = Math.cos(r / 180.0 * Math.PI) * s / 100.0;
		double vy = Math.sin(r / 180.0 * Math.PI) * s / 100.0;
		double ux = Math.cos((r + 90) / 180.0 * Math.PI) * s / 100.0;
		double uy = Math.sin((r + 90) / 180.0 * Math.PI) * s / 100.0;
		double cx = 0;
		double cy = 0;
		for (int i = 0; i < 100000; ++i) {
			double nx = x + vx * cx + ux * cy;
			double ny = y + vy * cx + uy * cy;
			cx = nx;
			cy = ny;
		}
		out.printLine(String.format("%.10f %.10f", cx, cy));
    }
}
