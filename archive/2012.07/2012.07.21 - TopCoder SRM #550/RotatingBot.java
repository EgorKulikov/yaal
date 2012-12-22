package net.egork;

public class RotatingBot {
	public int minArea(int[] moves) {
		if (moves.length == 1)
			return moves[0] + 1;
		if (moves.length == 2)
			return (moves[0] + 1) * (moves[1] + 1);
		if (moves.length == 3)
			return (Math.max(moves[0], moves[2]) + 1) * (moves[1] + 1);
		if (moves[2] < moves[0])
			return -1;
		if (moves[2] == moves[0]) {
			for (int i = 3; i < moves.length; i++) {
				if (moves[i] >= moves[i - 2])
					return -1;
				if (moves[i] != moves[i - 2] - 1 && i != moves.length - 1)
					return -1;
			}
			return (moves[0] + 1) * (moves[1] + 1);
		}
		if (moves.length == 4)
			return (Math.max(moves[0], moves[2]) + 1) * (Math.max(moves[1], moves[3]) + 1);
		if (moves[1] > moves[3])
			return -1;
		if (moves[1] == moves[3]) {
			if (moves[4] + moves[0] + 1 > moves[2])
				return -1;
			if (moves[4] + moves[0] + 1 != moves[2] && moves.length > 5)
				return -1;
			for (int i = 5; i < moves.length; i++) {
				if (moves[i] >= moves[i - 2])
					return -1;
				if (moves[i] != moves[i - 2] - 1 && i != moves.length - 1)
					return -1;
			}
			return (moves[2] + 1) * (moves[1] + 1);
		}
		if (moves[4] > moves[2])
			return -1;
		if (moves[4] != moves[2] && moves.length > 5)
			return -1;
		if (moves.length > 5) {
			if (moves[1] + moves[5] + 1 > moves[3])
				return -1;
			if (moves[1] + moves[5] + 1 != moves[3] && moves.length > 6)
				return -1;
			for (int i = 6; i < moves.length; i++) {
				if (moves[i] >= moves[i - 2])
					return -1;
				if (moves[i] != moves[i - 2] - 1 && i != moves.length - 1)
					return -1;
			}
		}
		return (moves[2] + 1) * (moves[3] + 1);
	}


}

