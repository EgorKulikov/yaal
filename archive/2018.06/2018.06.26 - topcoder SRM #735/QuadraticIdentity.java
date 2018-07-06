package net.egork;

import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.numbers.IntegerUtils;

import java.math.BigInteger;

import static net.egork.numbers.IntegerUtils.gcd;

public class QuadraticIdentity {
    public long[] getFixedPoints(long m) {
        LongList divisors = IntegerUtils.getDivisors(m);
        LongList answer = new LongArrayList();
        for (long x : divisors) {
            long y = m / x;
            if (gcd(x, y) != 1) {
                continue;
            }
            long a = findCommon(0, y, 1, x);
            answer.add(a);
        }
        answer.sort();
        while (answer.size() > 500) {
            LongList result = new LongArrayList();
            for (int i = 0; i < answer.size(); i += 2) {
                result.add(answer.get(i));
            }
            answer = result;
        }
        return answer.toArray();
    }

    public static long findCommon(long aRemainder, long aMod, long bRemainder, long bMod) {
        BigInteger aReverse = BigInteger.valueOf(aMod).modInverse(BigInteger.valueOf(bMod));
        BigInteger bReverse = BigInteger.valueOf(bMod).modInverse(BigInteger.valueOf(aMod));
        BigInteger mod = BigInteger.valueOf(aMod * bMod);
        return bReverse.multiply(BigInteger.valueOf(aRemainder)).mod(mod).multiply(BigInteger.valueOf(bMod)).add(
                aReverse.multiply(BigInteger.valueOf(bRemainder)).mod(mod).multiply(BigInteger.valueOf(aMod))
        ).mod(mod).longValue();
    }
}
