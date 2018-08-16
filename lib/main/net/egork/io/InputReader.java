package net.egork.io;

import net.egork.generated.collections.pair.IntIntPair;
import net.egork.generated.collections.pair.LongLongPair;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.InputMismatchException;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class InputReader {
    private boolean finished = false;

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public double[] readDoubleArray(int size) {
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = readDouble();
        }
        return array;
    }

    public String[] readStringArray(int size) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = readString();
        }
        return array;
    }

    public char[] readCharArray(int size) {
        char[] array = new char[size];
        for (int i = 0; i < size; i++) {
            array[i] = readCharacter();
        }
        return array;
    }

    public IntIntPair[] readIntPairArray(int size) {
        IntIntPair[] result = new IntIntPair[size];
        for (int i = 0; i < size; i++) {
            result[i] = readIntPair();
        }
        return result;
    }

    public LongLongPair[] readLongPairArray(int size) {
        LongLongPair[] result = new LongLongPair[size];
        for (int i = 0; i < size; i++) {
            result[i] = readLongPair();
        }
        return result;
    }

    public void readIntArrays(int[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++) {
                arrays[j][i] = readInt();
            }
        }
    }

    public void readLongArrays(long[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++) {
                arrays[j][i] = readLong();
            }
        }
    }

    public void readDoubleArrays(double[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++) {
                arrays[j][i] = readDouble();
            }
        }
    }

    public char[][] readTable(int rowCount, int columnCount) {
        char[][] table = new char[rowCount][];
        for (int i = 0; i < rowCount; i++) {
            table[i] = this.readCharArray(columnCount);
        }
        return table;
    }

    public int[][] readIntTable(int rowCount, int columnCount) {
        int[][] table = new int[rowCount][];
        for (int i = 0; i < rowCount; i++) {
            table[i] = readIntArray(columnCount);
        }
        return table;
    }

    public double[][] readDoubleTable(int rowCount, int columnCount) {
        double[][] table = new double[rowCount][];
        for (int i = 0; i < rowCount; i++) {
            table[i] = this.readDoubleArray(columnCount);
        }
        return table;
    }

    public long[][] readLongTable(int rowCount, int columnCount) {
        long[][] table = new long[rowCount][];
        for (int i = 0; i < rowCount; i++) {
            table[i] = readLongArray(columnCount);
        }
        return table;
    }

    public String[][] readStringTable(int rowCount, int columnCount) {
        String[][] table = new String[rowCount][];
        for (int i = 0; i < rowCount; i++) {
            table[i] = this.readStringArray(columnCount);
        }
        return table;
    }

    public String readText() {
        StringBuilder result = new StringBuilder();
        while (true) {
            int character = read();
            if (character == '\r') {
                continue;
            }
            if (character == -1) {
                break;
            }
            result.append((char) character);
        }
        return result.toString();
    }

    public void readStringArrays(String[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++) {
                arrays[j][i] = readString();
            }
        }
    }

    public long[] readLongArray(int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = readLong();
        }
        return array;
    }

    public int[] readIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = readInt();
        }
        return array;
    }

    public LongLongPair readLongPair() {
        long first = readLong();
        long second = readLong();
        return new LongLongPair(first, second);
    }

    public IntIntPair readIntPair() {
        int first = readInt();
        int second = readInt();
        return new IntIntPair(first, second);
    }

    public int read() {
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar++];
    }

    public int peek() {
        if (numChars == -1) {
            return -1;
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                return -1;
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar];
    }

    public int peekNonWhitespace() {
        while (isWhitespace(peek())) {
            read();
        }
        return peek();
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long readLong() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String readString() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            if (Character.isValidCodePoint(c)) {
                res.appendCodePoint(c);
            }
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public boolean isSpaceChar(int c) {
        if (filter != null) {
            return filter.isSpaceChar(c);
        }
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    private String readLine0() {
        StringBuilder buf = new StringBuilder();
        int c = read();
        while (c != '\n' && c != -1) {
            if (c != '\r') {
                buf.appendCodePoint(c);
            }
            c = read();
        }
        return buf.toString();
    }

    public String readLine() {
        String s = readLine0();
        while (s.trim().length() == 0) {
            s = readLine0();
        }
        return s;
    }

    public String readLine(boolean ignoreEmptyLines) {
        if (ignoreEmptyLines) {
            return readLine();
        } else {
            return readLine0();
        }
    }

    public BigInteger readBigInteger() {
        try {
            return new BigInteger(readString());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    public char readCharacter() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        return (char) c;
    }

    public double readDouble() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E') {
                return res * Math.pow(10, readInt());
            }
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }

    public boolean isExhausted() {
        int value;
        while (isSpaceChar(value = peek()) && value != -1) {
            read();
        }
        return value == -1;
    }

    public String next() {
        return readString();
    }

    public SpaceCharFilter getFilter() {
        return filter;
    }

    public void setFilter(SpaceCharFilter filter) {
        this.filter = filter;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}
