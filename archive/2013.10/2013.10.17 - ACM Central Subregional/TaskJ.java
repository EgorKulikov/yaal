package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.geometry.*;
import net.egork.geometry.Vector;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstCount = in.readInt();
		final Point[] first = new Point[firstCount];
		for (int i = 0; i < firstCount; i++)
			first[i] = Point.readPoint(in);
		final int secondCount = in.readInt();
		final Point[] second = new Point[secondCount];
		for (int i = 0; i < secondCount; i++)
			second[i] = Point.readPoint(in);
		counter(first);
		counter(second);
		Segment[] firstSides = new Polygon(first).sides();
		Segment[] secondSides = new Polygon(second).sides();
		List<Intersection>[] firstIntersections = new List[firstCount];
		List<Intersection>[] secondIntersections = new List[secondCount];
		for (int i = 0; i < firstCount; i++)
			firstIntersections[i] = new ArrayList<Intersection>();
		for (int i = 0; i < secondCount; i++)
			secondIntersections[i] = new ArrayList<Intersection>();
		for (int i = 0; i < firstCount; i++) {
			for (int j = 0; j < secondCount; j++) {
				Point point = firstSides[i].intersect(secondSides[j], false);
				if (point == null)
					continue;
				Intersection intersection = new Intersection();
				intersection.point = point;
				intersection.firstSide = i;
				intersection.secondSide = j;
				firstIntersections[i].add(intersection);
				secondIntersections[j].add(intersection);
			}
		}
		for (int i = 0; i < firstCount; i++) {
			final int finalI = i;
			Collections.sort(firstIntersections[i], new Comparator<Intersection>() {
				public int compare(Intersection o1, Intersection o2) {
					return Double.compare(first[finalI].distance(o1.point), first[finalI].distance(o2.point));
				}
			});
			for (int j = 0; j < firstIntersections[i].size(); j++)
				firstIntersections[i].get(j).firstIndex = j;
		}
		for (int i = 0; i < secondCount; i++) {
			final int finalI = i;
			Collections.sort(secondIntersections[i], new Comparator<Intersection>() {
				public int compare(Intersection o1, Intersection o2) {
					return Double.compare(second[finalI].distance(o1.point), second[finalI].distance(o2.point));
				}
			});
			for (int j = 0; j < secondIntersections[i].size(); j++)
				secondIntersections[i].get(j).secondIndex = j;
		}
		Set<Intersection> processed = new EHashSet<Intersection>();
		int answer = 0;
		for (int i = 0; i < firstCount; i++) {
			for (Intersection intersection : firstIntersections[i]) {
				if (processed.contains(intersection))
					continue;
				boolean onSecond;
				int sideIndex;
				int index;
				if (firstSides[i].line().value(secondSides[intersection.secondSide].b) > 0) {
					onSecond = true;
					sideIndex = intersection.secondSide;
					index = intersection.secondIndex;
				} else {
					onSecond = false;
					sideIndex = i;
					index = intersection.firstIndex;
				}
				answer++;
				while (!processed.contains(intersection)) {
					processed.add(intersection);
					index++;
					List<Intersection>[] current = onSecond ? secondIntersections : firstIntersections;
					while (index == current[sideIndex].size()) {
						index = 0;
						sideIndex++;
						if (sideIndex == current.length)
							sideIndex = 0;
					}
					intersection = current[sideIndex].get(index);
					onSecond = !onSecond;
					if (onSecond) {
						sideIndex = intersection.secondSide;
						index = intersection.secondIndex;
					} else {
						sideIndex = intersection.firstSide;
						index = intersection.firstIndex;
					}
				}
			}
		}
		if (answer == 0) {
			if (new Polygon(first).contains(second[0]) || new Polygon(second).contains(first[0]))
				answer = 1;
		}
		out.printLine(answer);
    }

	private void counter(Point[] points) {
		double angle = 0;
		for (int i = 0; i < points.length; i++) {
			Point next = points[(i + 1) % points.length];
			Point nextNext = points[(i + 2) % points.length];
			double delta = new Vector(next, nextNext).angle() - new Vector(points[i], next).angle();
			angle += GeometryUtils.canonicalAngle(delta);
		}
		if (angle > 0)
			ArrayUtils.reverse(points);
	}

	static class Intersection {
		Point point;
		int firstSide;
		int firstIndex;
		int secondSide;
		int secondIndex;
	}
}
