import net.egork.geometry.GeometryUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int bottleCount = in.readInt();
		int volume = in.readInt();
		int friendCount = in.readInt();
		if (bottleCount < friendCount && bottleCount % (friendCount - bottleCount) != 0) {
			out.println("NO");
			return;
		}
		out.println("YES");
		int bottleIndex = 1;
		double remainingVolume = volume;
		double perFriend = (double)volume * bottleCount / friendCount;
		for (int i = 0; i < friendCount; i++) {
			double friendVolume = perFriend;
			boolean first = true;
			while (friendVolume > GeometryUtils.epsilon) {
				if (first)
					first = false;
				else
					out.print(" ");
				double currentVolume = Math.min(friendVolume, remainingVolume);
				friendVolume -= currentVolume;
				remainingVolume -= currentVolume;
				out.printf("%d %.10f", bottleIndex, currentVolume);
				if (remainingVolume < GeometryUtils.epsilon) {
					bottleIndex++;
					remainingVolume = volume;
				}
			}
			out.println();
		}
	}
}

