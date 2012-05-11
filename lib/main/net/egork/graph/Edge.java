package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Edge<V> {
	public V getSource();
	public V getDestination();
	public long getWeight();
	public long getCapacity();
	public long getFlow();
	public void pushFlow(long flow);
	public Edge<V> getTransposedEdge();
	public Edge<V> getReverseEdge();
}
