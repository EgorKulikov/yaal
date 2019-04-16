package net.egork;

public class OneHandRoadPainting {
    public long fastest(int[] dStart, int[] dEnd, int paintPerBrush) {
        int remaining = 0;
        long answer = 0;
        for (int i = dStart.length - 1; i >= 0; i--) {
            int length = dEnd[i] - dStart[i];
            if (length <= remaining) {
                remaining -= length;
                continue;
            }
            length -= remaining;
            long first = dStart[i] + length;
            long last = dStart[i] + length % paintPerBrush;
            if (last == dStart[i]) {
                last += paintPerBrush;
            }
            long qty = (first - last) / paintPerBrush + 1;
            answer += qty * (last + first);
            remaining = paintPerBrush - ((int) (last - dStart[i]));
        }
        return answer;
    }
}
