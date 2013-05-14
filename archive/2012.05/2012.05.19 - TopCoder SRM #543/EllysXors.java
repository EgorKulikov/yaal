package net.egork;

public class EllysXors {
	public long getXor(long L, long R) {
		return getXor(R) ^ getXor(L - 1);
	}

	private long getXor(long to) {
		if (to % 2 == 0)
			return to ^ getXor(to - 1);
		return (to + 1) / 2 % 2;
	}


}

