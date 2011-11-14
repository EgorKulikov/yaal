package net.egork.collections.sequence;

import java.util.AbstractList;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class StringWrapper extends AbstractList<Character> {
	private final CharSequence string;

	public static List<Character> wrap(CharSequence string) {
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
