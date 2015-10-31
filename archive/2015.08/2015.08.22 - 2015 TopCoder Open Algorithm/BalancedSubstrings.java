package net.egork;

public class BalancedSubstrings {
    public int countSubstrings(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            int leftSum = 0;
            int leftQty = 0;
            int rightSum = 0;
            int rightQty = 0;
            int at = i;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1' && j > at) {
                    rightSum += j - at;
                    rightQty++;
                }
                while (leftSum < rightSum) {
                    leftSum += leftQty;
                    rightSum -= rightQty;
                    if (s.charAt(at) == '1') {
                        leftSum++;
                        leftQty++;
                    }
                    if (s.charAt(++at) == '1') {
                        rightQty--;
                    }
                }
                if (leftSum == rightSum) {
                    answer++;
                }
            }
        }
		return answer;
    }
}
