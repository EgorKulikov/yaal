package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Decode {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] a = decode(in.readString());
		int[] b = decode(in.readString());
		int[] message = new int[b.length];
		message[0] = 32;
		int[] key = new int[message.length];
		for (int i = 0; i < a.length; i++) {
			key[i] = b[i] ^ message[i];
			message[i + 1] = a[i] ^ key[i];
		}
		key[a.length] = b[a.length] ^ message[a.length];
		for (int i : key) {
			if (i >= 16)
				out.print(Integer.toHexString(i).toUpperCase());
			else
				out.print("0" + Integer.toHexString(i).toUpperCase());
		}
		out.printLine();
	}

	private int[] decode(String s) {
		int[] result = new int[s.length() / 2];
		for (int i = 0; i < result.length; i++)
			result[i] = Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		return result;
	}
}
