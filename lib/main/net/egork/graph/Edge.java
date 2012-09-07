package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Edge<V> {
	public V getSource();
	public V getDestination();
	public int getSourceID();
	public int getDestinationID();
	public long getWeight();
	public long getCapacity();
	public long getFlow();
	public void pushFlow(long flow);
	public int getTransposedID();
	public Edge<V> getTransposedEdge();
	public int getReverseID();
	public Edge<V> getReverseEdge();
	public int getID();
}
