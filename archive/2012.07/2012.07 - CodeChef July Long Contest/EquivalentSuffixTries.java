package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.string.AbstractStringHash;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class EquivalentSuffixTries {
    static final int MOD = 42424242;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int mask = 0;
        for (int i = 0; i < s.length(); i++)
            mask |= 1 << (s.charAt(i) - 'a');
        int different = Integer.bitCount(mask);
        long answer = 1;
        for (int i = 0; i < different; i++) {
            answer *= 26 - i;
            answer %= MOD;
        }
        StringHash hash = new SimpleStringHash(s);
        int deviateEnd = ArrayUtils.maxElement(StringUtils.zAlgorithm(StringUtils.reverse(s)));
        int position = s.length() - deviateEnd - 1;
        int left = 0;
        int right = deviateEnd + 1;
        while (left < right) {
            int middle = (left + right + 1) / 2;
            long curHash = hash.hash(position, position + middle);
            boolean found = false;
            for (int i = 0; i < position; i++) {
                if (hash.hash(i, i + middle) == curHash) {
                    found = true;
                    break;
                }
            }
            if (found)
                left = middle;
            else
                right = middle - 1;
        }
//        deviateEnd = Math.min(deviateEnd, deviateEnd + 1 - left);
        boolean[] cantStartWith = new boolean[26];
        if (left > 0) {
            long curHash = hash.hash(position, position + left);
            for (int i = 0; i < position; i++) {
                if (curHash == hash.hash(i, i + left))
                    cantStartWith[s.charAt(i + left) - 'a'] = true;
            }
        }
        int shouldBeSame = Math.max(0, left - 1);
        long targetHash = hash.hash(s.length() - deviateEnd, s.length() - deviateEnd + shouldBeSame);
        Set<Long> variants = new HashSet<Long>();
        Set<Long> badVariants = new HashSet<Long>();
        for (int i = 0; i < s.length() - deviateEnd; i++) {
            int delta = s.length() - deviateEnd - i;
            int repeats = deviateEnd / delta;
            int remainder = deviateEnd % delta;
            long repeatHash = hash.hash(i, i + delta);
            long remainderHash = hash.hash(i, i + remainder);
            long smallPower = power(AbstractStringHash.MULTIPLIER, delta);
            long curPower = 1;
            long curHash = 0;
            for (int j = 0; j < repeats; j++) {
                curHash += curPower * repeatHash;
                curPower *= smallPower;
            }
            curHash += remainderHash * curPower;
            int sameRepeats = shouldBeSame / delta;
            int sameRemainder = shouldBeSame % delta;
            long sameRemainderHash = hash.hash(i, i + sameRemainder);
            curPower = 1;
            long sameCurHash = 0;
            for (int j = 0; j < sameRepeats; j++) {
                sameCurHash += curPower * repeatHash;
                curPower *= smallPower;
            }
            sameCurHash += sameRemainderHash * curPower;
            int start = s.charAt(i + shouldBeSame % delta) - 'a';
            if (targetHash != sameCurHash || cantStartWith[start])
                continue;
            variants.add(curHash);
            if (i != 0 && s.charAt(i - 1) == s.charAt(s.length() - deviateEnd - 1))
                badVariants.add(curHash);
        }
        answer *= (variants.size() - badVariants.size());
        answer %= MOD;
        out.printLine(answer);
	}

    private long power(long base, int exponent) {
        if (exponent == 0)
            return 1;
        long result = power(base, exponent >> 1);
        result *= result;
        if ((exponent & 1) != 0)
            result *= base;
        return result;
    }
}
