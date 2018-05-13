package net.egork;

public class SocialNetwork {
    public double averageFriends(String[] interests) {
        int n = interests.length;
        int total = 0;
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < interests[i].length(); j++) {
                mask[i] += 1 << (interests[i].charAt(j) - 'A');
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if ((mask[i] & mask[j]) != 0) {
                    total += 2;
                }
            }
        }
        return (double) total / n;
    }
}
