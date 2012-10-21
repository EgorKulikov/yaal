package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedGem {
	static class Segment {
		double start;
		double end;

		Segment(double start, double end) {
			this.start = start;
			this.end = end;
		}
	}

	static class Event implements Comparable<Event> {
		double when;
		int delta;

		Event(double when, int delta) {
			this.when = when;
			this.delta = delta;
		}

		public int compareTo(Event o) {
			return Double.compare(when, o.when);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int rPurple = in.readInt();
		int xRed = in.readInt();
		int yRed = in.readInt();
		int rRed = in.readInt();
		if (n == 0) throw new UnknownError();
		Segment[] covers = new Segment[n];
		for (int i = 0; i < n; ++i) {
			int xOrange = in.readInt();
			int yOrange = in.readInt();
			int rOrange = in.readInt();
			covers[i] = findCover(rPurple, xRed, yRed, rRed, xOrange, yOrange, rOrange);
			//System.out.println(covers[i].start + " " + covers[i].end);
		}
		int numCovers = 0;
		for (Segment s : covers) if (s.end < s.start) ++numCovers;
		double curTime = -Math.PI;
		List<Event> events = new ArrayList<Event>();
		for (Segment s : covers) {
			events.add(new Event(s.start, 1));
			events.add(new Event(s.end, -1));
		}
		Collections.sort(events);
		double res = 0;
		for (Event e : events) {
			if (numCovers == 0)
				res += (e.when - curTime);
			numCovers += e.delta;
			curTime = e.when;
		}
		if (numCovers == 0)
			res += Math.PI - curTime;
		out.printLine(String.format("%.4f", Math.max(0.0, Math.min(1.0, res / (2 * Math.PI)))));
	}

	private Segment findCover(int rPurple, int xRed, int yRed, int rRed, int xOrange, int yOrange, int rOrange) {
		double koefRed = rOrange / (double) (rRed + rOrange);
		double koefOrange = rRed / (double) (rRed + rOrange);
		double distance = Math.sqrt((xRed - xOrange) * (xRed - xOrange) + (yRed - yOrange) * (yRed - yOrange));
		double xCenter = (xRed * koefRed + xOrange * koefOrange);
		double yCenter = (yRed * koefRed + yOrange * koefOrange);
		double sin = rOrange / (distance * koefRed);
		if (sin > 1.0) sin = 1.0;
		double alpha = Math.asin(sin);
		double midAlpha = Math.atan2(yOrange - yRed, xOrange - xRed);
		double startAlpha = midAlpha - alpha;
		double endAlpha = midAlpha + alpha;
		Segment s = new Segment(findIntersection(xCenter, yCenter, startAlpha, rPurple), findIntersection(xCenter, yCenter, endAlpha, rPurple));
		return s;
	}

	private double findIntersection(double xs, double ys, double alpha, int r) {
		double dx = Math.cos(alpha);
		double dy = Math.sin(alpha);
		double a = 1.0;
		double b = 2 * dx * xs + 2 * dy * ys;
		double c = xs * xs + ys * ys - r * r;
		double d = b * b - 4 * a * c;
		if (d <= 1e-8) throw new RuntimeException();
		double tm = (-b + Math.sqrt(d)) / (2 * a);
		double tx = xs + tm * dx;
		double ty = ys + tm * dy;
		return Math.atan2(ty, tx);
	}
}
