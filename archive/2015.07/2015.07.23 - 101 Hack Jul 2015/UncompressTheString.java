package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class UncompressTheString {
    static class State {
        State parent;
        long[] qty = new long[26];
        String suffix;
        int[][] letters;
        long length;
        int level;

        public State(State parent, String suffix, int multiplier) {
            this.parent = parent;
            this.suffix = suffix;
            if (parent != null) {
                for (int i = 0; i < 26; i++) {
                    qty[i] = parent.qty[i] * multiplier;
                }
            }
            letters = new int[26][suffix.length() + 1];
            for (int i = 0; i < suffix.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    letters[j][i + 1] = letters[j][i];
                }
                letters[suffix.charAt(i) - 'a'][i + 1]++;
                qty[suffix.charAt(i) - 'a']++;
            }
            level = parent == null ? 0 : parent.level + 1;
            if (multiplier == 0 || parent == null) {
                length = suffix.length();
                return;
            }
            if ((Long.MAX_VALUE - suffix.length()) / multiplier >= (parent.length)) {
                length = (parent.length) * multiplier + suffix.length();
            } else {
                length = Long.MAX_VALUE;
            }
        }

        public long query(int letter, long from, long to) {
            if (from >= length - suffix.length()) {
                return letters[letter][((int) (to - (length - suffix.length()))) + 1] - letters[letter][((int) (from - (length - suffix.length())))];
            }
            long add = 0;
            if (to >= length - suffix.length()) {
                add = letters[letter][((int) (to - (length - suffix.length()))) + 1];
                to = length - suffix.length() - 1;
            }
            long fDiv = from / parent.length;
            long tDiv = to / parent.length;
            if (fDiv == tDiv) {
                return add + parent.query(letter, from % parent.length, to % parent.length);
            }
            add += (tDiv - fDiv - 1) * parent.qty[letter];
            if (from == 0) {
                return add + parent.qty[letter] + parent.query(letter, 0, to % parent.length);
            }
            if (to == length - suffix.length() - 1) {
                return add + parent.qty[letter] + parent.query(letter, from % parent.length, parent.length - 1);
            }
            return add + parent.query(letter, from % parent.length, parent.length - 1) + parent.query(letter, 0, to % parent.length);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        State current = new State(null, "", 0);
        StringBuilder part = new StringBuilder();
        int multiplier = 0;
        for (int i = 0; i < s.length() && current.length != Long.MAX_VALUE; i++) {
            if (Character.isDigit(s.charAt(i))) {
                if (s.charAt(i) != '1') {
                    current = new State(current, part.toString(), multiplier);
                    multiplier = s.charAt(i) - '0';
                    part = new StringBuilder();
                }
            } else {
                part.append(s.charAt(i));
            }
        }
        if (current.length != Long.MAX_VALUE) {
            current = new State(current, part.toString(), multiplier);
        }
        System.err.println(current.level);
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            if (i % 1000 == 0) {
                System.err.println(i);
            }
            int letter = in.readCharacter() - 'a';
            long from = in.readLong() - 1;
            long to = in.readLong() - 1;
            out.printLine(current.query(letter, from, to));
        }
    }
}
