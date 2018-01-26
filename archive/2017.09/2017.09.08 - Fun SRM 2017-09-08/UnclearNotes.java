package net.egork;

public class UnclearNotes {
    public String isMatch(String S, String T) {
        S = S.replace('o', '0').replace('1', 'l').replace('m', 'n');
        T = T.replace('o', '0').replace('1', 'l').replace('m', 'n');
        return S.equals(T) ? "Possible" : "Impossible";
    }
}
