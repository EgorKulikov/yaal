package net.egork;

public class KnightCircuit2 {
	public int maxSize(int w, int h) {
		if (w == 3 && h == 3)
			return 8;
		if (w >= 3 && h >= 3)
			return w * h;
		if (w == 2)
			return (h + 1) / 2;
		if (h == 2)
			return (w + 1) / 2;
		return 1;
	}
}
