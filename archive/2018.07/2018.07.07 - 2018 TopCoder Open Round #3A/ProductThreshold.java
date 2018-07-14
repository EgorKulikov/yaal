package net.egork;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.util.Arrays.copyOf;

public class ProductThreshold {
    public long subarrayCount(int N, int limit, int[] Aprefix, int spread, int offset) {
        int[] a = copyOf(Aprefix, N);
        int seed = abs(a[Aprefix.length - 1]);
        for (int i = Aprefix.length; i < N; i++) {
            seed = (seed * 1103515245 + 12345) & MAX_VALUE;
            a[i] = (seed % spread) + offset;
        }
        long answer = 0;
        int[] positive = new int[N];
        int[] negative = new int[N];
        int posStart = 0;
        int posEnd = 0;
        int negStart = 0;
        int negEnd = 0;
        int bigPositive = 0;
        int bigNegative = 0;
        int zero = 0;
        for (int i = 0; i < N; i++) {
            if (a[i] == 0) {
                zero += bigPositive + bigNegative + posEnd - posStart + negEnd - negStart + 1;
                bigPositive = bigNegative = posStart = posEnd = negStart = negEnd = 0;
            } else {
                if (a[i] < 0) {
                    int[] temp = positive;
                    positive = negative;
                    negative = temp;
                    int t = posStart;
                    posStart = negStart;
                    negStart = t;
                    t = posEnd;
                    posEnd = negEnd;
                    negEnd = t;
                    t = bigPositive;
                    bigPositive = bigNegative;
                    bigNegative = t;
                    a[i] = -a[i];
                    negative[negEnd++] = limit;
                } else {
                    positive[posEnd++] = limit;
                }
                if (a[i] != 1) {
                    while (posStart < posEnd && positive[posStart] < a[i]) {
                        posStart++;
                        bigPositive++;
                    }
                    while (negStart < negEnd && negative[negStart] < a[i]) {
                        negStart++;
                        bigNegative++;
                    }
                    for (int j = posStart; j < posEnd; j++) {
                        positive[j] /= a[i];
                    }
                    for (int j = negStart; j < negEnd; j++) {
                        negative[j] /= a[i];
                    }
                }
            }
            answer += zero + bigNegative + negEnd - negStart + posEnd - posStart;
        }
        return answer;
    }
}
