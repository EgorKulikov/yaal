package net.egork;

import java.util.ArrayList;
import java.util.List;

public class TurtleSpy {
	public double maxDistance(String[] commands) {
		double totalForward = 0;
		double totalBackward = 0;
		List<Integer> turns = new ArrayList<Integer>();
		for (String command : commands) {
			String type = command.split(" ")[0];
			int value = Integer.parseInt(command.split(" ")[1]);
			if (type.equals("forward"))
				totalForward += value;
			else if (type.equals("backward"))
				totalBackward += value;
			else if (type.equals("left"))
				turns.add(value);
			else
				turns.add(360 - value);
		}
		boolean[][] possibleTurns = new boolean[turns.size() + 1][360];
		possibleTurns[0][0] = true;
		for (int i = 0; i < turns.size(); i++) {
			int turn = turns.get(i);
			for (int j = 0; j < 360; j++) {
				if (possibleTurns[i][j]) {
					possibleTurns[i + 1][j] = true;
					possibleTurns[i + 1][(j + turn) % 360] = true;
				}
			}
		}
		double answer = 0;
		for (int i = 0; i < 360; i++) {
			if (!possibleTurns[turns.size()][i])
				continue;
			double angle = i * Math.PI / 180;
			answer = Math.max(answer, Math.sqrt(totalForward * totalForward + totalBackward * totalBackward -
				2 * totalForward * totalBackward * Math.cos(angle)));
		}
		return answer;
	}

}

