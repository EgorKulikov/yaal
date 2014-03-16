package net.egork;

public class PowerOfThree {
    public String ableToGet(int x, int y) {
		if (x == 0 && y == 0)
			return "Possible";
		int rx = x % 3;
		if  (rx < 0)
			rx += 3;
		int ry = y % 3;
		if (ry < 0)
			ry += 3;
		if (rx != 0 && ry != 0 || rx == 0 && ry == 0)
			return "Impossible";
		if (rx == 1)
			x--;
		if (rx == 2)
			x++;
		if (ry == 1)
			y--;
		if (ry == 2)
			y++;
		return ableToGet(x / 3, y / 3);
    }
}
