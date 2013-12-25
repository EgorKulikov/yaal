package on2013_03.on2013_03_04_Codeforces_Round__171.A___The_Point_on_the_Spiral;


import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int[][] map = new int[201][201];
		ArrayUtils.fill(map, -1);
		map[100][100] = 0;
		int cx = 101;
		int cy = 100;
		int dx = 1;
		int dy = 0;
		int cur = 0;
		while (true) {
			map[cx][cy] = cur;
			if (cx == 200 && cy == 0)
				break;
			int sx = -dy;
			int sy = dx;
			if (map[cx + sx][cy + sy] == -1) {
				dx = sx;
				dy = sy;
				cur++;
			}
			cx += dx;
			cy += dy;
		}
		out.printLine(map[x + 100][y + 100]);
    }
}
