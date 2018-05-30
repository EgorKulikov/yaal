package net.egork.numbers;

import java.util.Objects;

import static java.lang.Character.isDigit;
import static java.lang.Math.max;
import static java.util.Arrays.copyOf;

/**
 * @author egor@egork.net
 */
public class LongInteger implements Cloneable, Comparable<LongInteger> {
    private static final int MOD = 1000;
    private static final int MULTIPLY_THRESHOLD = 50;

    public static final LongInteger ZERO = new LongInteger("0");
    public static final LongInteger ONE = new LongInteger("1");

    private int length;
    private int[] digits;
    private int sign;

    private LongInteger(int length, int[] digits, int sign) {
        this.length = length;
        this.digits = digits;
        this.sign = sign;
    }

    public LongInteger(String s) {
        if (s.isEmpty()) {
            throw new NumberFormatException();
        }
        int from = 0;
        int to = s.length();
        if (s.charAt(0) == '-') {
            sign = -1;
            from = 1;
        } else {
            sign = 1;
            if (s.charAt(0) == '+') {
                from = 1;
            }
        }
        if (from == to) {
            throw new NumberFormatException();
        }
        length = (to - from + 2) / 3;
        digits = new int[length];
        for (int i = 0; i < length; i++) {
            int start = max(to - 3, from);
            for (int j = start; j < to; j++) {
                digits[i] *= 10;
                char c = s.charAt(j);
                if (!isDigit(c)) {
                    throw new NumberFormatException();
                }
                digits[i] += c - '0';
            }
            to = start;
        }
        if (length == 1 && digits[0] == 0) {
            sign = 0;
        }
    }

    public LongInteger multiply(long number) {
        LongInteger result = clone();
        result.multiplyBy(number);
        return result;
    }

    public void multiplyBy(long number) {
        long add = 0;
        for (int i = 0; i < length; i++) {
            add += number * digits[i];
            digits[i] = (int) (add % MOD);
            add /= MOD;
        }
        while (add != 0) {
            ensureLength(length + 1);
            digits[length++] = (int) (add % MOD);
            add /= MOD;
        }
    }

    public LongInteger multiply(LongInteger number) {
        if (Math.min(length, number.length) <= MULTIPLY_THRESHOLD) {
            return straightforwardMultiply(this, number);
        } else {
            return fftMultiply(this, number);
        }
    }

    private static LongInteger fftMultiply(LongInteger a, LongInteger b) {
        long[] result = FastFourierTransform.multiply(a.digits, b.digits, a.length, b.length);
        long add = 0;
        LongInteger product = new LongInteger(0, new int[result.length], a.sign * b.sign);
        for (int i = 0; i < result.length; i++) {
            add += result[i];
            if (add != 0) {
                product.length = i + 1;
            }
            product.digits[i] = (int) (add % MOD);
            add /= MOD;
        }
        while (add > 0) {
            product.ensureLength(product.length + 1);
            product.digits[product.length++] = (int) (add % MOD);
            add /= MOD;
        }
        return product;
    }

    private static LongInteger straightforwardMultiply(LongInteger a, LongInteger b) {
        if (a.sign == 0 || b.sign == 0) {
            return ZERO;
        }
        LongInteger product = new LongInteger(a.length + b.length - 1, new int[a.length + b.length], a.sign * b.sign);
        long add = 0;
        for (int i = 0; i < a.length + b.length - 1; i++) {
            for (int j = Math.max(0, i - b.length + 1); j < a.length && j <= i; j++) {
                add += a.digits[j] * b.digits[i - j];
            }
            product.digits[i] = (int) (add % MOD);
            add /= MOD;
        }
        while (add > 0) {
            product.ensureLength(product.length + 1);
            product.digits[product.length++] = (int) (add % MOD);
            add /= MOD;
        }
        return product;
    }

    private void ensureLength(int length) {
        if (digits.length < length) {
            digits = copyOf(digits, max(2 * digits.length, length));
        }
    }

    public static int absCompare(LongInteger a, LongInteger b) {
        if (a.length != b.length) {
            return Integer.compare(a.length, b.length);
        }
        for (int i = a.length - 1; i >= 0; i--) {
            if (a.digits[i] != b.digits[i]) {
                return Integer.compare(a.digits[i], b.digits[i]);
            }
        }
        return 0;
    }

    public static int compare(LongInteger a, LongInteger b) {
        if (a.sign != b.sign) {
            return Integer.compare(a.sign, b.sign);
        }
        return absCompare(a, b);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public LongInteger clone() {
        return new LongInteger(length, digits.clone(), sign);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LongInteger that = (LongInteger) o;
        return length == that.length &&
                sign == that.sign &&
                equals(digits, that.digits, length);
    }

    private static boolean equals(int[] a, int[] b, int length) {
        for (int i = 0; i < length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(length, sign);
        result = 31 * result + hashCode(digits, length);
        return result;
    }

    private static int hashCode(int[] digits, int length) {
        int result = 1;
        for (int i = 0; i < length; i++) {
            result = 31 * result + digits[i];
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (sign < 0) {
            result.append('-');
        }
        result.append(digits[length - 1]);
        for (int i = length - 2; i >= 0; i--) {
            int current = digits[i];
//            if (current < 1000) {
//                result.append('0');
                if (current < 100) {
                    result.append('0');
                    if (current < 10) {
                        result.append('0');
                    }
//                }
            }
            result.append(current);
        }
        return result.toString();
    }

    public LongInteger power(int exponent) {
        if (exponent == 0) {
            return ONE;
        }
        if ((exponent & 1) == 0) {
            return power(exponent >> 1).square();
        }
        return power(exponent - 1).multiply(this);
    }

    public LongInteger square() {
        return multiply(this);
    }

    @Override
    public int compareTo(LongInteger o) {
        return compare(this, o);
    }
}
