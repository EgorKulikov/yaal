package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Mafia {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] map = IOUtils.readTable(in, size, size);
		while (true) {
			char[] word = in.readString().toCharArray();
			if (word.length == 1 && word[0] == '0')
				return;
			int si = -1;
			int sj = -1;
			int fi = -1;
			int fj = -1;
			for (int i = 0; i < size && si == -1; i++) {
				for (int j = 0; j < size && si == -1; j++) {
					for (int k = 0; k < 8 && si == -1; k++) {
						int di = MiscUtils.DX8[k];
						int dj = MiscUtils.DY8[k];
						if (!MiscUtils.isValidCell(i + di * (word.length - 1), j + dj * (word.length - 1), size, size))
							continue;
						boolean found = true;
						for (int l = 0; l < word.length && found; l++) {
							if (map[i + di * l][j + dj * l] != word[l])
								found = false;
						}
						if (found) {
							si = i + 1;
							sj = j + 1;
							fi = i + di * (word.length - 1) + 1;
							fj = j + dj * (word.length - 1) + 1;
						}
					}
				}
			}
			if (si == -1)
				out.printLine("Not found");
			else
				out.printLine(si + "," + sj, fi + "," + fj);
		}
    }
}
