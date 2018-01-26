package net.egork;

import static java.lang.Integer.MAX_VALUE;

public class Subrectangle {
    public int minMissed(String S) {
        if (S.indexOf('#') == -1) {
            return 1;
        }
        if (S.indexOf('.') == -1) {
            return 0;
        }
        int first = S.indexOf('#');
        int last = S.lastIndexOf('#');
        if (S.substring(first, last + 1).indexOf('.') == -1) {
            return 0;
        }
        int answer = MAX_VALUE;
        for (int len = 2; len <= S.length(); len++) {
            for (int width = 1; width < len; width++) {
                int current = 0;
                int start = first % len;
                if (start + width > len) {
                    current = len - start;
                    start = 0;
                }
                int pos = start;
                for (int i = first; i <= last; i++) {
                    if (S.charAt(i) == '#') {
                        if (pos < start) {
                            current += start - pos;
                            pos = start;
                        } else if (pos >= start + width) {
                            current += start + (len - pos);
                            pos = start;
                        }
                        pos++;
                    } else {
                        if (pos >= start && pos < start + width) {
                            current += start + width - pos;
                            pos = start + width;
                        }
                        pos++;
                    }
                    if (pos >= len) {
                        pos -= len;
                    }
                }
                if (pos >= start && pos < start + width) {
                    current += start + width - pos;
                    pos = start + width;
                }
                pos += S.length() - last - 1;
                pos %= len;
                if (pos != 0) {
                    current += len - pos;
                }
                answer = Math.min(answer, current);
            }
        }
        return answer;
    }
}
