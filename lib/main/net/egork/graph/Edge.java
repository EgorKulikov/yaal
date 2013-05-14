package net.egork.graph;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Edge {
	public int getSource();
	public int getDestination();
	public long getWeight();
	public long getCapacity();
	public long getFlow();
	public void pushFlow(long flow);
	public boolean getFlag(int bit);
	public void setFlag(int bit);
	public void removeFlag(int bit);
	public int getTransposedID();
	public Edge getTransposedEdge();
	public int getReverseID();
	public Edge getReverseEdge();
	public int getID();
	public void remove();
	public void restore();
}
