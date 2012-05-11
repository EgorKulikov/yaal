package net.egork.graph;

import java.util.*;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Graph<V> {
	private Map<V, Edge<V>> firstEdge = new HashMap<V, Edge<V>>();
	private Map<Edge<V>, Edge<V>> nextEdge = new HashMap<Edge<V>, Edge<V>>();
	private Map<Edge<V>, Edge<V>> lastEdge = new HashMap<Edge<V>, Edge<V>>();

	public Iterable<Edge<V>> getIncident(final V vertex) {
		return new Iterable<Edge<V>>() {
			public Iterator<Edge<V>> iterator() {
				return new EdgeIterator(vertex);
			}
		};
	}

	public void add(Edge<V> edge) {
		addImpl(edge);
		edge = edge.getReverseEdge();
		if (edge != null)
			addImpl(edge);
	}

	private void addImpl(Edge<V> edge) {
		V source = edge.getSource();
		if (firstEdge.containsKey(source)) {
			Edge<V> last = firstEdge.get(source);
			lastEdge.put(last, edge);
			nextEdge.put(edge, last);
		}
		firstEdge.put(source, edge);
	}

	public Collection<V> vertices() {
		return Collections.unmodifiableCollection(firstEdge.keySet());
	}

	public void remove(Edge<V> edge) {
		removeImpl(edge);
		edge = edge.getReverseEdge();
		if (edge != null)
			removeImpl(edge);
	}

	private void removeImpl(Edge<V> edge) {
		V source = edge.getSource();
		if (firstEdge.containsKey(source)) {
			Edge<V> next = nextEdge.get(edge);
			Edge<V> last = lastEdge.get(edge);
			if (last == null) {
				if (next == null)
					firstEdge.remove(source);
				else {
					firstEdge.put(source, next);
					lastEdge.remove(next);
				}
			} else if (next == null)
				nextEdge.remove(last);
			else {
				nextEdge.put(last, next);
				lastEdge.put(next, last);
			}
			nextEdge.remove(edge);
			lastEdge.remove(edge);
		}
	}

	public void remove(V vertex) {
		for (Edge<V> edge : getIncident(vertex)) {
			nextEdge.remove(edge);
			lastEdge.remove(edge);
		}
		firstEdge.remove(vertex);
	}

	private class EdgeIterator implements Iterator<Edge<V>> {
		private Edge<V> current, last;

		public EdgeIterator(V vertex) {
			current = firstEdge.get(vertex);
		}

		public boolean hasNext() {
			return current != null;
		}

		public Edge<V> next() {
			if (!hasNext())
				throw new NoSuchElementException();
			last = current;
			current = nextEdge.get(current);
			return last;
		}

		public void remove() {
			if (last == null)
				throw new IllegalStateException();
			Graph.this.remove(last);
			last = null;
		}
	}
}
