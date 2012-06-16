package net.egork.string;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public interface StringHash {
	long MULTIPLIER = 43;

	long hash(int from, int to);

	long hash(int from);

	int length();
}
