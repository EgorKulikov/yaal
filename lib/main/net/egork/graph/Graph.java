package net.egork.graph;

import net.egork.collections.IntIterator;

import java.util.*;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class Graph<V> {
	protected int vertexCount;
	protected int edgeCount;

	protected Map<V, Integer> map = new HashMap<V, Integer>();

	public V[] vertices;
	public int[] firstOutbound;
	public int[] firstInbound;

	public Edge<V>[] edges;
	public int[] nextInbound;
	public int[] nextOutbound;
	public int[] from;
	public int[] to;
	public long[] weight;
	public long[] capacity;
	public int[] reverseEdge;
	public boolean[] removed;

	public Graph() {
		this(10);
	}

	public Graph(int vertexCapacity) {
		this(vertexCapacity, vertexCapacity);
	}

	public Graph(int vertexCapacity, int edgeCapacity) {
		//noinspection unchecked
		vertices = (V[]) new Object[vertexCapacity];
		firstOutbound = new int[vertexCapacity];
		firstInbound = new int[vertexCapacity];

		//noinspection unchecked
		edges = new Edge[edgeCapacity];
		from = new int[edgeCapacity];
		to = new int[edgeCapacity];
		nextInbound = new int[edgeCapacity];
		nextOutbound = new int[edgeCapacity];
		weight = new long[edgeCapacity];
		capacity = new long[edgeCapacity];
		reverseEdge = new int[edgeCapacity];
		removed = new boolean[edgeCapacity];
	}

	public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
		ensureEdgeCapacity(edgeCount + 1);
		if (firstOutbound[fromID] != -1)
			nextOutbound[edgeCount] = firstOutbound[fromID];
		else
			nextOutbound[edgeCount] = -1;
		firstOutbound[fromID] = edgeCount;
		if (firstInbound[toID] != -1)
			nextInbound[edgeCount] = nextInbound[toID];
		else
			nextInbound[edgeCount] = -1;
		firstInbound[toID] = edgeCount;
		this.from[edgeCount] = fromID;
		this.to[edgeCount] = toID;
		this.weight[edgeCount] = weight;
		this.capacity[edgeCount] = capacity;
		this.reverseEdge[edgeCount] = reverseEdge;
		edges[edgeCount] = createEdge(edgeCount);
		return edgeCount++;
	}

	protected GraphEdge createEdge(int id) {
		return new GraphEdge(id);
	}

	public Edge<V> addFlowWeightedEdge(V from, V to, long weight, long capacity) {
		int fromID = resolveOrAdd(from);
		int toID = resolveOrAdd(to);
		if (capacity == 0) {
			int result = addEdge(fromID, toID, weight, 0, -1);
			return edges[result];
		} else {
			int lastEdgeCount = edgeCount;
			addEdge(toID, fromID, -weight, 0, lastEdgeCount + entriesPerEdge());
			int result = addEdge(fromID, toID, weight, capacity, lastEdgeCount);
			return edges[result];
		}
	}

	protected int entriesPerEdge() {
		return 1;
	}

	public Edge<V> addFlowEdge(V from, V to, long capacity) {
		return addFlowWeightedEdge(from, to, 0, capacity);
	}

	public Edge<V> addWeightedEdge(V from, V to, long weight) {
		return addFlowWeightedEdge(from, to, weight, 0);
	}

	public Edge<V> addSimpleEdge(V from, V to) {
		return addWeightedEdge(from, to, 1);
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

	public int resolve(V vertex) {
		if (map.containsKey(vertex))
			return map.get(vertex);
		throw new IllegalArgumentException();
	}

	public V getVertex(int id) {
		return vertices[id];
	}

	public Edge<V> getEdge(int id) {
		return edges[id];
	}

	public void removeVertex(V vertex) {
		removeVertexByID(resolve(vertex));
	}

	public void removeVertexByID(int id) {
		for (Edge<V> edge : getInboundByID(id))
			removeEdge(edge);
		for (Edge<V> edge : getOutboundByID(id))
			removeEdge(edge);
		map.remove(vertices[id]);
		vertices[id] = null;
	}

	public void removeEdge(Edge<V> edge) {
		removeEdgeByID(edge.getID());
	}

	private void removeEdgeByID(int id) {
		removed[id] = true;
		edges[id] = null;
	}

	public Iterable<Edge<V>> getOutbound(V vertex) {
		return getOutboundByID(resolve(vertex));
	}

	public Iterable<Edge<V>> getOutboundByID(final int id) {
		return new Iterable<Edge<V>>() {
			public Iterator<Edge<V>> iterator() {
				return new EdgeIterator(id, firstOutbound, nextOutbound);
			}
		};
	}

	public Iterable<Edge<V>> getInbound(V vertex) {
		return getInboundByID(resolve(vertex));
	}

	public Iterable<Edge<V>> getInboundByID(final int id) {
		return new Iterable<Edge<V>>() {
			public Iterator<Edge<V>> iterator() {
				return new EdgeIterator(id, firstInbound, nextInbound);
			}
		};
	}

	public IntIterator getOutboundIterator(int id) {
		return new IDIterator(id, firstOutbound, nextOutbound);
	}

	public IntIterator getInboundIterator(int id) {
		return new IDIterator(id, firstInbound, nextInbound);
	}

	private int resolveOrAdd(V vertex) {
		if (map.containsKey(vertex))
			return map.get(vertex);
		ensureVertexCapacity(vertexCount + 1);
		map.put(vertex, vertexCount);
		vertices[vertexCount] = vertex;
		firstInbound[vertexCount] = firstOutbound[vertexCount] = -1;
		return vertexCount++;
	}

	protected void ensureEdgeCapacity(int size) {
		if (from.length < size) {
			int newSize = Math.max(size, 2 * from.length);
			edges = resize(edges, newSize);
			from = resize(from, newSize);
			to = resize(to, newSize);
			nextInbound = resize(nextInbound, newSize);
			nextOutbound = resize(nextOutbound, newSize);
			weight = resize(weight, newSize);
			capacity = resize(capacity, newSize);
			reverseEdge = resize(reverseEdge, newSize);
			removed = resize(removed, newSize);
		}
	}

	protected void ensureVertexCapacity(int size) {
		if (firstInbound.length < size) {
			int newSize = Math.max(size, 2 * firstInbound.length);
			vertices = resize(vertices, newSize);
			firstInbound = resize(firstInbound, newSize);
			firstOutbound = resize(firstOutbound, newSize);
		}
	}

	protected int[] resize(int[] array, int size) {
		int[] newArray = new int[size];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	protected boolean[] resize(boolean[] array, int size) {
		boolean[] newArray = new boolean[size];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	private long[] resize(long[] array, int size) {
		long[] newArray = new long[size];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	private Edge<V>[] resize(Edge<V>[] array, int size) {
		@SuppressWarnings("unchecked")
		Edge<V>[] newArray = new Edge[size];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	private V[] resize(V[] array, int size) {
		@SuppressWarnings("unchecked")
		V[] newArray = (V[]) new Object[size];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	public boolean isSparse() {
		return vertexCount == 0 || edgeCount * 20 / vertexCount <= vertexCount;
	}

	public Collection<? extends V> vertices() {
		return Arrays.asList(vertices);
	}

	public void addVertex(V vertex) {
		resolveOrAdd(vertex);
	}

	protected class GraphEdge implements Edge<V> {
		protected int id;

		protected GraphEdge(int id) {
			this.id = id;
		}

		public V getSource() {
			return vertices[from[id]];
		}

		public V getDestination() {
			return vertices[to[id]];
		}

		public int getSourceID() {
			return from[id];
		}

		public int getDestinationID() {
			return to[id];
		}

		public long getWeight() {
			return weight[id];
		}

		public long getCapacity() {
			return capacity[id];
		}

		public long getFlow() {
			return capacity[reverseEdge[id]];
		}

		public void pushFlow(long flow) {
			if (flow == 0)
				return;
			if (flow > 0) {
				if (capacity[id] < flow)
					throw new IllegalArgumentException("Not enough capacity");
			} else {
				if (capacity[reverseEdge[id]] < -flow)
					throw new IllegalArgumentException("Not enough capacity");
			}
			capacity[id] -= flow;
			capacity[reverseEdge[id]] += flow;
		}

		public int getTransposedID() {
			return -1;
		}

		public Edge<V> getTransposedEdge() {
			return null;
		}

		public int getReverseID() {
			return reverseEdge[id];
		}

		public Edge<V> getReverseEdge() {
			if (reverseEdge[id] == -1)
				return null;
			return edges[reverseEdge[id]];
		}

		public int getID() {
			return id;
		}
	}

	private class EdgeIterator implements Iterator<Edge<V>> {
		private int edgeID;
		private final int[] next;
		private int lastID = -1;

		public EdgeIterator(int id, int[] first, int[] next) {
			this.next = next;
			first[id] = edgeID = nextEdge(first[id]);
		}

		private int nextEdge(int id) {
			while (id != -1 && removed[id])
				id = next[id];
			return id;
		}

		public boolean hasNext() {
			return edgeID != -1;
		}

		public Edge<V> next() {
			if (edgeID == -1)
				throw new NoSuchElementException();
			lastID = edgeID;
			edgeID = next[lastID] = nextEdge(next[lastID]);
			return edges[lastID];
		}

		public void remove() {
			if (lastID == -1)
				throw new IllegalStateException();
			removeEdgeByID(lastID);
			lastID = -1;
		}
	}

	private class IDIterator implements IntIterator {
		private int edgeID;
		private final int[] next;
		private int lastID = -1;

		public IDIterator(int id, int[] first, int[] next) {
			this.next = next;
			edgeID = first[id];
			if (edgeID != -1 && removed[edgeID])
				first[id] = edgeID = nextEdge(first[id]);
		}

		private int nextEdge(int id) {
			while (id != -1 && removed[id])
				id = next[id];
			return id;
		}

		public boolean hasNext() {
			return edgeID != -1;
		}

		public int next() {
			if (edgeID == -1)
				throw new NoSuchElementException();
			lastID = edgeID;
			edgeID = next[lastID];
			if (edgeID != -1 && removed[edgeID])
				edgeID = next[lastID] = nextEdge(next[lastID]);
			return lastID;
		}

		public void remove() {
			if (lastID == -1)
				throw new IllegalStateException();
			removeEdgeByID(lastID);
			lastID = -1;
		}
	}
}
