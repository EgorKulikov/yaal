package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	String[] samples = {"....x.xxxxx.xxxxx.x...x.xxxxx.xxxxx.xxxxx.......xxxxx.xxxxx.xxxxx",
	"....x.....x.....x.x...x.x.....x.........x...x...x...x.x...x.x...x",
	"....x.....x.....x.x...x.x.....x.........x...x...x...x.x...x.x...x",
	"....x.xxxxx.xxxxx.xxxxx.xxxxx.xxxxx.....x.xxxxx.xxxxx.xxxxx.x...x",
	"....x.x.........x.....x.....x.x...x.....x...x...x...x.....x.x...x",
	"....x.x.........x.....x.....x.x...x.....x...x...x...x.....x.x...x",
	"....x.xxxxx.xxxxx.....x.xxxxx.xxxxx.....x.......xxxxx.xxxxx.xxxxx"};

	String value = "1234567+890";

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[][] inp = new char[7][];
		for (int i = 0; i < 7; i++) {
			inp[i] = in.readString().toCharArray();
		}
		StringBuilder parsed = new StringBuilder();
		for (int j = 0; j < inp[0].length; j += 6) {
			parsed.append(parse(inp, j));
		}
		String[] tokens = parsed.toString().split("[+]");
		int answer = 0;
		for (String token : tokens) {
			answer += Integer.parseInt(token);
		}
		String result = Integer.toString(answer);
		for (int i = 0; i < 7; i++) {
			boolean first = true;
			for (char c : result.toCharArray()) {
				if (first) {
					first = false;
				} else {
					out.print('.');
				}
				for (int j = 0; j < 11; j++) {
					if (value.charAt(j) == c) {
						out.print(samples[i].substring(j * 6, j * 6 + 5));
					}
				}
			}
			out.printLine();
		}
	}

	private char parse(char[][] inp, int shift) {
		for (int i = 0; i <= 10; i++) {
			boolean good = true;
			for (int j = 0; good && j < 7; j++) {
				for (int k = 0; k < 5; k++) {
					if (inp[j][shift + k] != samples[j].charAt(i * 6 + k)) {
						good = false;
						break;
					}
				}
			}
			if (good) {
				return value.charAt(i);
			}
		}
		throw new RuntimeException();
	}
}
