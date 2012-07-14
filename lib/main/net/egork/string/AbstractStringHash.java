package net.egork.string;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public abstract class AbstractStringHash implements StringHash {
	public long hash(int from) {
		return hash(from, length());
	}
}
