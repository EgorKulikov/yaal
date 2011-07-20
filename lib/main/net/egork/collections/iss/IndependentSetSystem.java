package net.egork.collections.iss;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface IndependentSetSystem {
	public boolean join(int first, int second);

	public int get(int index);

	public int getSetCount();

	public void setListener(Listener listener);

	public static interface Listener {
		public void joined(int joinedRoot, int root);
	}
}
