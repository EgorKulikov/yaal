package net.egork;

import net.egork.collections.Pair;
import net.egork.geometry.Circle;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class EllysDeathStars {
	public double getMax(String[] stars, String[] ships) {
		int starCount = stars.length;
		int shipCount = ships.length;
		int[] x = new int[starCount];
		int[] y = new int[starCount];
		for (int i = 0; i < starCount; i++) {
			x[i] = Integer.parseInt(stars[i].split(" ")[0]);
			y[i] = Integer.parseInt(stars[i].split(" ")[1]);
		}
		int[] sx = new int[shipCount];
		int[] sy = new int[shipCount];
		int[] ex = new int[shipCount];
		int[] ey = new int[shipCount];
		int[] speed = new int[shipCount];
		int[] range = new int[shipCount];
		int[] energy = new int[shipCount];
		for (int i = 0; i < shipCount; i++) {
			sx[i] = Integer.parseInt(ships[i].split(" ")[0]);
			sy[i] = Integer.parseInt(ships[i].split(" ")[1]);
			ex[i] = Integer.parseInt(ships[i].split(" ")[2]);
			ey[i] = Integer.parseInt(ships[i].split(" ")[3]);
			speed[i] = Integer.parseInt(ships[i].split(" ")[4]);
			range[i] = Integer.parseInt(ships[i].split(" ")[5]);
			energy[i] = Integer.parseInt(ships[i].split(" ")[6]);
		}
		double[] present = new double[shipCount];
		Set<Double> times = new TreeSet<Double>();
		for (int i = 0; i < shipCount; i++) {
			if (sx[i] == ex[i] && sy[i] == ey[i])
				continue;
			Segment curShip = new Segment(new Point(sx[i], sy[i]), new Point(ex[i], ey[i]));
			for (int j = 0; j < starCount; j++) {
				Circle curStar = new Circle(new Point(x[j], y[j]), range[i]);
				Point[] intersection = curShip.intersect(curStar);
				for (Point point : intersection)
					times.add(point.distance(curShip.a) / speed[i]);
			}
			times.add(0d);
			times.add(present[i] = curShip.length() / speed[i]);
		}
		Double[] tt = times.toArray(new Double[times.size()]);
		Graph<Object> graph = new Graph<Object>();
		long multiplier = (long) 1e14;
		Set<Pair<Integer, Integer>> allStars = new HashSet<Pair<Integer, Integer>>();
		for (int i = 0; i < shipCount; i++) {
			if (sx[i] == ex[i] && sy[i] == ey[i])
				continue;
			graph.add(new FlowEdge<Object>("source", i, energy[i] * multiplier));
			double vx = (ex[i] - sx[i]) / present[i];
			double vy = (ey[i] - sy[i]) / present[i];
			for (int j = 0; j < starCount; j++) {
				Circle curStar = new Circle(new Point(x[j], y[j]), range[i]);
				for (int k = 1; k < tt.length; k++) {
					double curTime = (tt[k] + tt[k - 1]) / 2;
					if (curTime > present[i])
						continue;
					if (curStar.contains(new Point(sx[i] + vx * curTime, sy[i] + vy * curTime))) {
						Pair<Integer, Integer> star = Pair.makePair(j, k);
						graph.add(new FlowEdge<Object>(i, star, Long.MAX_VALUE / 2));
						allStars.add(star);
					}
				}
			}
		}
		for (Pair<Integer, Integer> star : allStars) {
			graph.add(new FlowEdge<Object>(star, "sink", (long) ((tt[star.second] - tt[star.second - 1]) * multiplier)));
		}
		long answer = GraphAlgorithms.dinic(graph, "source", "sink");
		return answer / 1e14;
	}


}

