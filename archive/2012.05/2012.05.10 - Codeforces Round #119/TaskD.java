package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long green = in.readInt();
		long red = in.readInt();
		long period = green + red;
		long[] lengths = IOUtils.readLongArray(in, count + 1);
		for (int i = 1; i <= count; i++)
			lengths[i] += lengths[i - 1];
		NavigableSet<Vertex> vertices = new TreeSet<Vertex>();
		vertices.add(new GreenVertex(0));
		for (int i = count - 1; i >= 0; i--) {
			long start = (period - lengths[i] % period) % period;
			if (start + green <= period) {
				Vertex fake = new GreenVertex(start);
				if (vertices.contains(fake))
					remove(vertices.headSet(fake, false));
				else {
					Vertex last = vertices.headSet(fake, false).last();
					remove(vertices.headSet(fake, false));
					last.setStart(start);
					vertices.add(last);
				}
				long additionalWait = vertices.headSet(fake, true).last().totalWait(start);
				remove(vertices.tailSet(new GreenVertex(start + green), true));
				if (start + green < period)
					vertices.add(new RedVertex(start + green, start + period, additionalWait));
				if (start != 0)
					vertices.add(new RedVertex(0, start, additionalWait));
			} else {
				Vertex fake = new GreenVertex(start);
				if (vertices.contains(fake))
					remove(vertices.subSet(new GreenVertex(start - red), true, fake, false));
				else {
					Vertex last = vertices.headSet(fake, false).last();
					remove(vertices.subSet(new GreenVertex(start - red), true, fake, false));
					if (last.start() >= start - red) {
						last.setStart(start);
						vertices.add(last);
					} else {
						last = last.clone();
						last.setStart(start);
						vertices.add(last);
					}
				}
				vertices.add(new RedVertex(start - red, start, vertices.headSet(fake, true).last().totalWait(start)));
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			long time = in.readInt();
			out.printLine(vertices.headSet(new GreenVertex(time % period), true).last().totalWait(time % period) + lengths[count] + time);
		}
	}

	private void remove(NavigableSet<Vertex> vertices) {
		vertices.clear();
	}

	abstract static class Vertex implements Comparable<Vertex> {
		long start;

		protected Vertex(long start) {
			this.start = start;
		}

		abstract long totalWait(long atTime);
		public abstract Vertex clone();

		long start() {
			return start;
		}

		void setStart(long newStart) {
			start = newStart;
		}

		public int compareTo(Vertex o) {
			return IntegerUtils.longCompare(start, o.start());
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Vertex)) return false;

			Vertex vertex = (Vertex) o;

			if (start != vertex.start) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return (int) (start ^ (start >>> 32));
		}
	}

	static class RedVertex extends Vertex {
		final long waitTill;
		final long additionalWait;

		RedVertex(long start, long waitTill, long additionalWait) {
			super(start);
			this.waitTill = waitTill;
			this.additionalWait = additionalWait;
		}

		public long totalWait(long atTime) {
			if (waitTill < atTime)
				throw new RuntimeException();
			return waitTill - atTime + additionalWait;
		}

		public Vertex clone() {
			return new RedVertex(start, waitTill, additionalWait);
		}

	}

	static class GreenVertex extends Vertex {
		GreenVertex(long start) {
			super(start);
		}

		public long totalWait(long atTime) {
			return 0;
		}

		public Vertex clone() {
			return new GreenVertex(start);
		}
	}
}
