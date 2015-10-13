package net.egork;

public class LuckySum {
    public long construct(String note) {
        long answer = Long.MAX_VALUE;
        for (int i = 1; i <= note.length(); i++) {
            for (int j = Math.max(i, note.length() - 1); j <= note.length(); j++) {
                answer = Math.min(answer, go(0, note.length() - 1, note.length() - i, note.length() - j, note, 0, 1));
            }
        }
		return answer == Long.MAX_VALUE ? -1 : answer;
    }

    private long go(int remainder, int current, int stopFirst, int stopSecond, String note, long number, long ten) {
        if (current == -1) {
            if (remainder == 0) {
                return number;
            } else {
                return Long.MAX_VALUE;
            }
        }
        if (stopSecond > current) {
            if (matches(remainder, note, current)) {
                return go(0, current - 1, stopFirst, stopSecond, note, number + ten * remainder, ten * 10);
            }
            return Long.MAX_VALUE;
        } else if (stopFirst > current) {
            int variant = remainder + 4;
            long answer = Long.MAX_VALUE;
            if (matches(variant % 10, note, current)) {
                answer = Math.min(answer, go(variant / 10, current - 1, stopFirst, stopSecond, note, number + ten * (variant % 10), ten * 10));
            }
            variant = remainder + 7;
            if (matches(variant % 10, note, current)) {
                answer = Math.min(answer, go(variant / 10, current - 1, stopFirst, stopSecond, note, number + ten * (variant % 10), ten * 10));
            }
            return answer;
        } else {
            int variant = remainder + 8;
            long answer = Long.MAX_VALUE;
            if (matches(variant % 10, note, current)) {
                answer = Math.min(answer, go(variant / 10, current - 1, stopFirst, stopSecond, note, number + ten * (variant % 10), ten * 10));
            }
            variant = remainder + 11;
            if (matches(variant % 10, note, current)) {
                answer = Math.min(answer, go(variant / 10, current - 1, stopFirst, stopSecond, note, number + ten * (variant % 10), ten * 10));
            }
            variant = remainder + 14;
            if (matches(variant % 10, note, current)) {
                answer = Math.min(answer, go(variant / 10, current - 1, stopFirst, stopSecond, note, number + ten * (variant % 10), ten * 10));
            }
            return answer;
        }
    }

    private boolean matches(int variant, String note, int current) {
        if (current == 0 && variant == 0) {
            return false;
        }
        if (note.charAt(current) == '?') {
            return true;
        }
        return note.charAt(current) == variant + '0';
    }
}
