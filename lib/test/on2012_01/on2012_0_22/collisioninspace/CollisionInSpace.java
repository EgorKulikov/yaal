package on2012_01.on2012_0_22.collisioninspace;



import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CollisionInSpace {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] char2Dir = new int[256];
		char2Dir['R'] = 0;
		char2Dir['D'] = 1;
		char2Dir['L'] = 2;
		char2Dir['U'] = 3;
		int xEarth = in.readInt();
		int yEarth = in.readInt();
		int dirEarth = char2Dir[in.readCharacter()];
		int count = in.readInt();
		int[] x = new int[count + 1];
		int[] y = new int[count + 1];
		int[] dir = new int[count + 1];
		x[0] = xEarth * 2;
		y[0] = yEarth * 2;
		dir[0] = dirEarth;
		for (int i = 1; i <= count; i++) {
			x[i] = in.readInt() * 2;
			y[i] = in.readInt() * 2;
			dir[i] = char2Dir[in.readCharacter()];
		}
		for (int i = 0; i < 400; i++) {
			for (int j = 0; j <= count; j++) {
				x[j] += MiscUtils.DX4[dir[j]];
				y[j] += MiscUtils.DY4[dir[j]];
				if (j != 0 && x[j] == x[0] && y[j] == y[0]) {
					out.printFormat("%.1f\n", (i + 1) / 2.);
					return;
				}
			}
		}
		out.printLine("SAFE");
	}
}
