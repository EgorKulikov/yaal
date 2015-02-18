package net.egork;

public class AliceGame {
    public long findMinimumValue(long x, long y) {
        long sum = x + y;
        long moves = Math.round(Math.sqrt(x + y));
        while (moves * moves < sum) {
            moves++;
        }
        while (moves * moves > sum) {
            moves--;
        }
        if (moves * moves != sum) {
            return -1;
        }
        if (x == 2 || y == 2) {
            return -1;
        }
        int answer = 0;
        for (int i = (int) (2 * moves - 1); i > 0; i -= 2) {
            if (x >= i && x - i != 2) {
                x -= i;
                answer++;
            }
        }
        if (x != 0) {
            throw new RuntimeException();
        }
		return answer;
    }
}
