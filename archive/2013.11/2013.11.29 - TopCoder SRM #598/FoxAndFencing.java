package net.egork;

public class FoxAndFencing {
    public String WhoCanWin(int mov1, int mov2, int rng1, int rng2, int d) {
		if (d <= mov1 + rng1)
			return "Ciel";
		if (d <= mov2 + rng2 - mov1)
			return "Liss";
		if (mov1 == mov2)
			return "Draw";
		if (mov1 > mov2) {
			if (can(mov1, mov2, rng1, rng2))
				return "Ciel";
			else
				return "Draw";
		}
		if (can(mov2, mov1, rng2, rng1))
			return "Liss";
		else
			return "Draw";
    }

	private boolean can(int mov1, int mov2, int rng1, int rng2) {
		int strikeDistance = mov1 - mov2 + rng1;
		return strikeDistance > mov2 + rng2;
	}
}
