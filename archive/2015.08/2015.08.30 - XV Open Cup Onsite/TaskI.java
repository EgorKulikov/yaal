package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int bigHeight = in.readInt();
		int bigWidth = in.readInt();
		int smallHeight = in.readInt();
		int smallWidth = in.readInt();
		boolean[][] big = new boolean[bigHeight][bigWidth];
		boolean[][] small = new boolean[smallHeight][smallWidth];
		for (int row = 0; row < bigHeight; ++row) {
			String s = in.readString();
			for (int col = 0; col < bigWidth; ++col) {
				big[row][col] = s.charAt(col) == 'x';
			}
		}
		int[] sr = new int[smallHeight * smallWidth];
		int[] sc = new int[smallHeight * smallWidth];
		int cnt = 0;
		for (int row = 0; row < smallHeight; ++row) {
			String s = in.readString();
			for (int col = 0; col < smallWidth; ++col) {
				small[row][col] = s.charAt(col) == 'x';
				if (small[row][col]) {
					sr[cnt] = row;
					sc[cnt] = col;
					++cnt;
				}
			}
		}
		for (int row = 0; row < bigHeight; ++row) {
			for (int col = 0; col < bigWidth; ++col) if (big[row][col]) {
				int dr = row - sr[0];
				int dc = col - sc[0];
				for (int i = 0; i < cnt; ++i) {
					int nr = sr[i] + dr;
					int nc = sc[i] + dc;
					if (nr < 0 || nr >= bigHeight || nc < 0 || nc >= bigWidth || !big[nr][nc]) {
						out.printLine("NIE");
						return;
					}
					big[nr][nc] = false;
				}
			}
		}
		out.printLine("TAK");
	}
}
