package net.egork;

public class OnlySanta {
    public String solve(String S) {
        String has = "SAT";
        int at = 0;
        for (int i = 0; i < S.length(); i++) {
            if (at < 3 && has.charAt(at) == S.charAt(i)) {
                at++;
            }
        }
        if (at != 3) {
            return S + "SANTA";
        }
        at = 0;
        for (int i = 0; i < S.length(); i++) {
            if (at == 2) {
                return S.substring(0, i) + "N" + S.substring(i) + "A";
            }
            if (at < 3 && has.charAt(at) == S.charAt(i)) {
                at++;
            }
        }
        throw new RuntimeException();
    }
}
