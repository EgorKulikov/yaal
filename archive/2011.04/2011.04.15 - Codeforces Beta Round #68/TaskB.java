package April2011.CodeforcesBetaRound68;

import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int carCount = in.readInt();
		int cheaterCar = in.readInt() - 1;
		int controllerCar = in.readInt() - 1;
		int controllerDirection = "to tail".equals(in.readLine()) ? 1 : -1;
		boolean[] possibleCheaterLocations = new boolean[carCount];
		possibleCheaterLocations[cheaterCar] = true;
		char[] types = in.readString().toCharArray();
		for (int i = 0; i < types.length; i++) {
			boolean[] nextPossibleLocations = new boolean[carCount];
			if (controllerCar == 0)
				controllerDirection = 1;
			else if (controllerCar == carCount - 1)
				controllerDirection = -1;
			int nextControllerCar = controllerCar + controllerDirection;
			if (types[i] == '1') {
				Arrays.fill(nextPossibleLocations, true);
				nextPossibleLocations[nextControllerCar] = false;
			} else {
				boolean caught = true;
				for (int j = 0; j < carCount; j++) {
					if (j != controllerCar && j != nextControllerCar && (possibleCheaterLocations[j] || j > 0 && possibleCheaterLocations[j - 1] || j < carCount - 1 && possibleCheaterLocations[j + 1])) {
						nextPossibleLocations[j] = true;
						caught = false;
					}
				}
				if (caught) {
					out.println("Controller " + (i + 1));
					return;
				}
			}
			possibleCheaterLocations = nextPossibleLocations;
			controllerCar = nextControllerCar;
		}
		out.println("Stowaway");
	}
}

