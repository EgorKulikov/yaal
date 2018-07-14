package net.egork;

public class LeftToRightGame {
    public String whoWins(int length, int divisor) {
        if (divisor == 1) {
            return "Bob";
        }
        if (length % 2 == 1) {
            return "Alice";
        }
        if (divisor <= 11) {
            return "Bob";
        }
        return "Alice";
    }
}
