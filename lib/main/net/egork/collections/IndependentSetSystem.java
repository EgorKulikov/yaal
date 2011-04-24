package net.egork.collections;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class IndependentSetSystem {
	private final int[] color;
	private int setCount;
	private Listener listener;

	public IndependentSetSystem(int size) {
		color = new int[size];
		for (int i = 0; i < size; i++)
			color[i] = i;
		setCount = size;
	}

	public IndependentSetSystem(IndependentSetSystem other) {
		color = other.color.clone();
		setCount = other.setCount;
	}

	public boolean join(int a, int b) {
		a = get(a);
		b = get(b);
		if (a == b)
			return false;
		setCount--;
		color[b] = a;
		if (listener != null)
			listener.joined(b, a);
		return true;
	}

	public int get(int a) {
		if (color[a] == a)
			return a;
		return color[a] = get(color[a]);
	}

	public int getSetCount() {
		return setCount;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public static interface Listener {
		public void joined(int joinedRoot, int root);
	}
}
