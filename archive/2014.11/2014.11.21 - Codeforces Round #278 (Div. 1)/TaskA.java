package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int hp = in.readInt();
		int attack = in.readInt();
		int defence = in.readInt();
		int monsterHP = in.readInt();
		int monsterAttack = in.readInt();
		int monsterDefence = in.readInt();
		int hpCost = in.readInt();
		int attackCost = in.readInt();
		int defenceCost = in.readInt();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= 200; i++) {
			for (int j = 0; j <= 200; j++) {
				int actualAttack = attack + i;
				if (actualAttack <= monsterDefence) {
					continue;
				}
				int actualDefence = defence + j;
				int damageTaken = 0;
				int health = monsterHP;
				while (health > 0) {
					health -= actualAttack - monsterDefence;
					damageTaken += Math.max(0, monsterAttack - actualDefence);
				}
				answer = Math.min(answer, i * attackCost + j * defenceCost + Math.max(damageTaken + 1 - hp, 0) * hpCost);
			}
		}
		out.printLine(answer);
    }
}
