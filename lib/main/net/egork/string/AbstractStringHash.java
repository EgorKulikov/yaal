package net.egork.string;

import net.egork.numbers.IntegerUtils;

import java.util.Random;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public abstract class AbstractStringHash implements StringHash {
    public static final long MULTIPLIER;
    protected static final long FIRST_REVERSE_MULTIPLIER;
    protected static final long SECOND_REVERSE_MULTIPLIER;
    public static final long FIRST_MOD;
    public static final long SECOND_MOD;

    static {
        Random random = new Random(System.currentTimeMillis());
        FIRST_MOD = IntegerUtils.nextPrime((long) (1e9 + random.nextInt((int) 1e9)));
        SECOND_MOD = IntegerUtils.nextPrime((long) (1e9 + random.nextInt((int) 1e9)));
		MULTIPLIER = random.nextInt((int) 1e9 - 257) + 257;
        FIRST_REVERSE_MULTIPLIER = IntegerUtils.reverse(MULTIPLIER, FIRST_MOD);
        SECOND_REVERSE_MULTIPLIER = IntegerUtils.reverse(MULTIPLIER, SECOND_MOD);
    }

    public long hash(int from) {
		return hash(from, length());
	}
}
