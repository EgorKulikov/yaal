package net.egork.collections.sequence;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class StringWrapper extends AbstractSequence<Character> {
	private final CharSequence string;

	public static Sequence<Character> wrap(CharSequence string) {
		return new StringWrapper(string);
	}

	private StringWrapper(CharSequence string) {
		this.string = string;
	}

	public int size() {
		return string.length();
	}

	public Character get(int index) {
		return string.charAt(index);
	}
}
