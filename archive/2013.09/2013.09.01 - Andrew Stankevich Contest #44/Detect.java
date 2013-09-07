package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Detect {
	static String big = "import java.io.OutputStreamWriter;\n" +
		"import java.io.BufferedWriter;\n" +
		"import java.util.Comparator;\n" +
		"import java.io.OutputStream;\n" +
		"import java.io.PrintWriter;\n" +
		"import java.io.Writer;\n" +
		"import java.util.List;\n" +
		"import java.io.IOException;\n" +
		"import java.util.Arrays;\n" +
		"import java.util.InputMismatchException;\n" +
		"import java.util.ArrayList;\n" +
		"import java.io.FileOutputStream;\n" +
		"import java.io.FileInputStream;\n" +
		"import java.util.NoSuchElementException;\n" +
		"import java.math.BigInteger;\n" +
		"import java.io.InputStream;\n" +
		"\n" +
		"/**\n" +
		" * Built using CHelper plug-in\n" +
		" * Actual solution is at the top\n" +
		" * @author Egor Kulikov (egor@egork.net)\n" +
		" */\n" +
		"public class Main {\n" +
		"\tpublic static void main(String[] args) {\n" +
		"\t\tInputStream inputStream;\n" +
		"\t\ttry {\n" +
		"\t\t\tinputStream = new FileInputStream(\"intelligent.in\");\n" +
		"\t\t} catch (IOException e) {\n" +
		"\t\t\tthrow new RuntimeException(e);\n" +
		"\t\t}\n" +
		"\t\tOutputStream outputStream;\n" +
		"\t\ttry {\n" +
		"\t\t\toutputStream = new FileOutputStream(\"intelligent.out\");\n" +
		"\t\t} catch (IOException e) {\n" +
		"\t\t\tthrow new RuntimeException(e);\n" +
		"\t\t}\n" +
		"\t\tInputReader in = new InputReader(inputStream);\n" +
		"\t\tOutputWriter out = new OutputWriter(outputStream);\n" +
		"\t\tIntelligent solver = new Intelligent();\n" +
		"\t\ttry {\n" +
		"\t\t\tint testNumber = 1;\n" +
		"\t\t\twhile (true)\n" +
		"\t\t\t\tsolver.solve(testNumber++, in, out);\n" +
		"\t\t} catch (UnknownError e) {\n" +
		"\t\t\tout.close();\n" +
		"\t\t}\n" +
		"\t}\n" +
		"}\n" +
		"\n" +
		"class Intelligent {\n" +
		"    public void solve(int testNumber, InputReader in, OutputWriter out) {\n" +
		"\t\tint count = in.readInt();\n" +
		"\t\tif (count == 0)\n" +
		"\t\t\tthrow new UnknownError();\n" +
		"\t\tfinal long[] toStudy = new long[count];\n" +
		"\t\tlong[] day = new long[count];\n" +
		"\t\tIOUtils.readLongArrays(in, day, toStudy);\n" +
		"\t\tint tripCount = in.readInt();\n" +
		"\t\tlong[] start = new long[tripCount];\n" +
		"\t\tlong[] end = new long[tripCount];\n" +
		"\t\tIOUtils.readLongArrays(in, start, end);\n" +
		"\t\tint[] tripOrder = ArrayUtils.order(start);\n" +
		"\t\tint[] examOrder = ArrayUtils.order(day);\n" +
		"\t\tint j = 0;\n" +
		"\t\tlong decrease = 0;\n" +
		"\t\tfor (int i : examOrder) {\n" +
		"\t\t\twhile (j < tripCount && start[tripOrder[j]] < day[i]) {\n" +
		"\t\t\t\tdecrease += end[tripOrder[j]] - start[tripOrder[j]] + 1;\n" +
		"\t\t\t\tj++;\n" +
		"\t\t\t}\n" +
		"\t\t\tday[i] -= decrease;\n" +
		"\t\t}\n" +
		"\t\tHeap heap = new Heap(new IntComparator() {\n" +
		"\t\t\tpublic int compare(int first, int second) {\n" +
		"\t\t\t\treturn IntegerUtils.longCompare(toStudy[second], toStudy[first]);\n" +
		"\t\t\t}\n" +
		"\t\t}, count);\n" +
		"\t\tlong totalTime = 0;\n" +
		"\t\tfor (int i : examOrder) {\n" +
		"\t\t\tif (totalTime + toStudy[i] + 1 <= day[i]) {\n" +
		"\t\t\t\theap.add(i);\n" +
		"\t\t\t\ttotalTime += toStudy[i] + 1;\n" +
		"\t\t\t} else if (!heap.isEmpty() && toStudy[i] < toStudy[heap.peek()]) {\n" +
		"\t\t\t\ttotalTime -= toStudy[heap.poll()] + 1;\n" +
		"\t\t\t\ttotalTime += toStudy[i] + 1;\n" +
		"\t\t\t\theap.add(i);\n" +
		"\t\t\t}\n" +
		"\t\t}\n" +
		"\t\tout.printLine(heap.getSize());\n" +
		"\t\tint[] answer = new int[heap.getSize()];\n" +
		"\t\tfor (int i = 0; i < answer.length; i++)\n" +
		"\t\t\tanswer[i] = heap.poll() + 1;\n" +
		"\t\tArrays.sort(answer);\n" +
		"\t\tout.printLine(answer);\n" +
		"    }\n" +
		"}\n" +
		"\n" +
		"class InputReader {\n" +
		"\n" +
		"\tprivate InputStream stream;\n" +
		"\tprivate byte[] buf = new byte[1024];\n" +
		"\tprivate int curChar;\n" +
		"\tprivate int numChars;\n" +
		"\tprivate SpaceCharFilter filter;\n" +
		"\n" +
		"\tpublic InputReader(InputStream stream) {\n" +
		"\t\tthis.stream = stream;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int read() {\n" +
		"\t\tif (numChars == -1)\n" +
		"\t\t\tthrow new InputMismatchException();\n" +
		"\t\tif (curChar >= numChars) {\n" +
		"\t\t\tcurChar = 0;\n" +
		"\t\t\ttry {\n" +
		"\t\t\t\tnumChars = stream.read(buf);\n" +
		"\t\t\t} catch (IOException e) {\n" +
		"\t\t\t\tthrow new InputMismatchException();\n" +
		"\t\t\t}\n" +
		"\t\t\tif (numChars <= 0)\n" +
		"\t\t\t\treturn -1;\n" +
		"\t\t}\n" +
		"\t\treturn buf[curChar++];\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int readInt() {\n" +
		"\t\tint c = read();\n" +
		"\t\twhile (isSpaceChar(c))\n" +
		"\t\t\tc = read();\n" +
		"\t\tint sgn = 1;\n" +
		"\t\tif (c == '-') {\n" +
		"\t\t\tsgn = -1;\n" +
		"\t\t\tc = read();\n" +
		"\t\t}\n" +
		"\t\tint res = 0;\n" +
		"\t\tdo {\n" +
		"\t\t\tif (c < '0' || c > '9')\n" +
		"\t\t\t\tthrow new InputMismatchException();\n" +
		"\t\t\tres *= 10;\n" +
		"\t\t\tres += c - '0';\n" +
		"\t\t\tc = read();\n" +
		"\t\t} while (!isSpaceChar(c));\n" +
		"\t\treturn res * sgn;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic long readLong() {\n" +
		"\t\tint c = read();\n" +
		"\t\twhile (isSpaceChar(c))\n" +
		"\t\t\tc = read();\n" +
		"\t\tint sgn = 1;\n" +
		"\t\tif (c == '-') {\n" +
		"\t\t\tsgn = -1;\n" +
		"\t\t\tc = read();\n" +
		"\t\t}\n" +
		"\t\tlong res = 0;\n" +
		"\t\tdo {\n" +
		"\t\t\tif (c < '0' || c > '9')\n" +
		"\t\t\t\tthrow new InputMismatchException();\n" +
		"\t\t\tres *= 10;\n" +
		"\t\t\tres += c - '0';\n" +
		"\t\t\tc = read();\n" +
		"\t\t} while (!isSpaceChar(c));\n" +
		"\t\treturn res * sgn;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic boolean isSpaceChar(int c) {\n" +
		"\t\tif (filter != null)\n" +
		"\t\t\treturn filter.isSpaceChar(c);\n" +
		"\t\treturn isWhitespace(c);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic static boolean isWhitespace(int c) {\n" +
		"\t\treturn c == ' ' || c == '\\n' || c == '\\r' || c == '\\t' || c == -1;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic interface SpaceCharFilter {\n" +
		"\t\tpublic boolean isSpaceChar(int ch);\n" +
		"\t}\n" +
		"}\n" +
		"\n" +
		"class OutputWriter {\n" +
		"\tprivate final PrintWriter writer;\n" +
		"\n" +
		"\tpublic OutputWriter(OutputStream outputStream) {\n" +
		"\t\twriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));\n" +
		"\t}\n" +
		"\n" +
		"\tpublic OutputWriter(Writer writer) {\n" +
		"\t\tthis.writer = new PrintWriter(writer);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic void print(Object...objects) {\n" +
		"\t\tfor (int i = 0; i < objects.length; i++) {\n" +
		"\t\t\tif (i != 0)\n" +
		"\t\t\t\twriter.print(' ');\n" +
		"\t\t\twriter.print(objects[i]);\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"    public void print(int[] array) {\n" +
		"        for (int i = 0; i < array.length; i++) {\n" +
		"            if (i != 0)\n" +
		"                writer.print(' ');\n" +
		"            writer.print(array[i]);\n" +
		"        }\n" +
		"    }\n" +
		"\n" +
		"    public void printLine(int[] array) {\n" +
		"        print(array);\n" +
		"        writer.println();\n" +
		"    }\n" +
		"\n" +
		"    public void printLine(Object...objects) {\n" +
		"\t\tprint(objects);\n" +
		"\t\twriter.println();\n" +
		"\t}\n" +
		"\n" +
		"\tpublic void close() {\n" +
		"\t\twriter.close();\n" +
		"\t}\n" +
		"\n" +
		"\t}\n" +
		"\n" +
		"class IOUtils {\n" +
		"\n" +
		"\tpublic static void readLongArrays(InputReader in, long[]... arrays) {\n" +
		"\t\tfor (int i = 0; i < arrays[0].length; i++) {\n" +
		"\t\t\tfor (int j = 0; j < arrays.length; j++)\n" +
		"\t\t\t\tarrays[j][i] = in.readLong();\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\t}\n" +
		"\n" +
		"class ArrayUtils {\n" +
		"\n" +
		"\tpublic static int[] createOrder(int size) {\n" +
		"\t\tint[] order = new int[size];\n" +
		"\t\tfor (int i = 0; i < size; i++)\n" +
		"\t\t\torder[i] = i;\n" +
		"\t\treturn order;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic static int[] sort(int[] array, IntComparator comparator) {\n" +
		"\t\treturn sort(array, 0, array.length, comparator);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic static int[] sort(int[] array, int from, int to, IntComparator comparator) {\n" +
		"\t\tif (from == 0 && to == array.length)\n" +
		"\t\t\tnew IntArray(array).inPlaceSort(comparator);\n" +
		"\t\telse\n" +
		"\t\t\tnew IntArray(array).subList(from, to).inPlaceSort(comparator);\n" +
		"\t\treturn array;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic static int[] order(final long[] array) {\n" +
		"\t\treturn sort(createOrder(array.length), new IntComparator() {\n" +
		"\t\t\tpublic int compare(int first, int second) {\n" +
		"\t\t\t\tif (array[first] < array[second])\n" +
		"\t\t\t\t\treturn -1;\n" +
		"\t\t\t\tif (array[first] > array[second])\n" +
		"\t\t\t\t\treturn 1;\n" +
		"\t\t\t\treturn 0;\n" +
		"\t\t\t}\n" +
		"\t\t});\n" +
		"\t}\n" +
		"\n" +
		"\t}\n" +
		"\n" +
		"class Heap {\n" +
		"\tprivate IntComparator comparator;\n" +
		"\tprivate int size = 0;\n" +
		"\tprivate int[] elements;\n" +
		"\tprivate int[] at;\n" +
		"\n" +
		"\tpublic Heap(int maxElement) {\n" +
		"\t\tthis(10, maxElement);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic Heap(IntComparator comparator, int maxElement) {\n" +
		"\t\tthis(10, comparator, maxElement);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic Heap(int capacity, int maxElement) {\n" +
		"\t\tthis(capacity, IntComparator.DEFAULT, maxElement);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic Heap(int capacity, IntComparator comparator, int maxElement) {\n" +
		"\t\tthis.comparator = comparator;\n" +
		"\t\telements = new int[capacity];\n" +
		"\t\tat = new int[maxElement];\n" +
		"\t\tArrays.fill(at, -1);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int getSize() {\n" +
		"\t\treturn size;\n" +
		"\t}\n" +
		"\n" +
		"    public boolean isEmpty() {\n" +
		"\t\treturn size == 0;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int add(int element) {\n" +
		"\t\tensureCapacity(size + 1);\n" +
		"\t\telements[size] = element;\n" +
		"\t\tat[element] = size;\n" +
		"\t\tshiftUp(size++);\n" +
		"\t\treturn at[element];\n" +
		"\t}\n" +
		"\n" +
		"\tpublic void shiftUp(int index) {\n" +
		"//\t\tif (index < 0 || index >= size)\n" +
		"//\t\t\tthrow new IllegalArgumentException();\n" +
		"\t\tint value = elements[index];\n" +
		"\t\twhile (index != 0) {\n" +
		"\t\t\tint parent = (index - 1) >>> 1;\n" +
		"\t\t\tint parentValue = elements[parent];\n" +
		"\t\t\tif (comparator.compare(parentValue, value) <= 0) {\n" +
		"\t\t\t\telements[index] = value;\n" +
		"\t\t\t\tat[value] = index;\n" +
		"\t\t\t\treturn;\n" +
		"\t\t\t}\n" +
		"\t\t\telements[index] = parentValue;\n" +
		"\t\t\tat[parentValue] = index;\n" +
		"\t\t\tindex = parent;\n" +
		"\t\t}\n" +
		"\t\telements[0] = value;\n" +
		"\t\tat[value] = 0;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic void shiftDown(int index) {\n" +
		"\t\tif (index < 0 || index >= size)\n" +
		"\t\t\tthrow new IllegalArgumentException();\n" +
		"\t\twhile (true) {\n" +
		"\t\t\tint child = (index << 1) + 1;\n" +
		"\t\t\tif (child >= size)\n" +
		"\t\t\t\treturn;\n" +
		"\t\t\tif (child + 1 < size && comparator.compare(elements[child], elements[child + 1]) > 0)\n" +
		"\t\t\t\tchild++;\n" +
		"\t\t\tif (comparator.compare(elements[index], elements[child]) <= 0)\n" +
		"\t\t\t\treturn;\n" +
		"\t\t\tswap(index, child);\n" +
		"\t\t\tindex = child;\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\tprivate void swap(int first, int second) {\n" +
		"\t\tint temp = elements[first];\n" +
		"\t\telements[first] = elements[second];\n" +
		"\t\telements[second] = temp;\n" +
		"\t\tat[elements[first]] = first;\n" +
		"\t\tat[elements[second]] = second;\n" +
		"\t}\n" +
		"\n" +
		"\tprivate void ensureCapacity(int size) {\n" +
		"\t\tif (elements.length < size) {\n" +
		"\t\t\tint[] oldElements = elements;\n" +
		"\t\t\telements = new int[Math.max(2 * elements.length, size)];\n" +
		"\t\t\tSystem.arraycopy(oldElements, 0, elements, 0, this.size);\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int peek() {\n" +
		"\t\tif (isEmpty())\n" +
		"\t\t\tthrow new IndexOutOfBoundsException();\n" +
		"\t\treturn elements[0];\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int poll() {\n" +
		"\t\tif (isEmpty())\n" +
		"\t\t\tthrow new IndexOutOfBoundsException();\n" +
		"\t\tint result = elements[0];\n" +
		"\t\tat[result] = -1;\n" +
		"\t\tif (size == 1) {\n" +
		"\t\t\tsize = 0;\n" +
		"\t\t\treturn result;\n" +
		"\t\t}\n" +
		"\t\telements[0] = elements[--size];\n" +
		"\t\tat[elements[0]] = 0;\n" +
		"\t\tshiftDown(0);\n" +
		"\t\treturn result;\n" +
		"\t}\n" +
		"\n" +
		"    }\n" +
		"\n" +
		"interface IntComparator {\n" +
		"    public static final IntComparator DEFAULT = new IntComparator() {\n" +
		"        public int compare(int first, int second) {\n" +
		"            if (first < second)\n" +
		"                return -1;\n" +
		"            if (first > second)\n" +
		"                return 1;\n" +
		"            return 0;\n" +
		"        }\n" +
		"    };\n" +
		"\n" +
		"\tpublic int compare(int first, int second);\n" +
		"}\n" +
		"\n" +
		"class IntegerUtils {\n" +
		"\n" +
		"    public static int longCompare(long a, long b) {\n" +
		"\t\tif (a < b)\n" +
		"\t\t\treturn -1;\n" +
		"\t\tif (a > b)\n" +
		"\t\t\treturn 1;\n" +
		"\t\treturn 0;\n" +
		"\t}\n" +
		"\n" +
		"\t}\n" +
		"\n" +
		"abstract class IntCollection {\n" +
		"\tpublic abstract IntIterator iterator();\n" +
		"\tpublic abstract int size();\n" +
		"\n" +
		"\t}\n" +
		"\n" +
		"interface IntIterator {\n" +
		"\tpublic int value() throws NoSuchElementException;\n" +
		"\t/*\n" +
		"\t * @throws NoSuchElementException only if iterator already invalid\n" +
		"\t */\n" +
		"\tpublic void advance() throws NoSuchElementException;\n" +
		"\tpublic boolean isValid();\n" +
		"}\n" +
		"\n" +
		"abstract class IntList extends IntCollection implements Comparable<IntList> {\n" +
		"\tprivate static final int INSERTION_THRESHOLD = 16;\n" +
		"\n" +
		"\tpublic abstract int get(int index);\n" +
		"\tpublic abstract void set(int index, int value);\n" +
		"\n" +
		"\tpublic IntIterator iterator() {\n" +
		"\t\treturn new IntIterator() {\n" +
		"\t\t\tprivate int size = size();\n" +
		"\t\t\tprivate int index = 0;\n" +
		"\n" +
		"\t\t\tpublic int value() throws NoSuchElementException {\n" +
		"\t\t\t\tif (!isValid())\n" +
		"\t\t\t\t\tthrow new NoSuchElementException();\n" +
		"\t\t\t\treturn get(index);\n" +
		"\t\t\t}\n" +
		"\n" +
		"\t\t\tpublic void advance() throws NoSuchElementException {\n" +
		"\t\t\t\tif (!isValid())\n" +
		"\t\t\t\t\tthrow new NoSuchElementException();\n" +
		"\t\t\t\tindex++;\n" +
		"\t\t\t}\n" +
		"\n" +
		"\t\t\tpublic boolean isValid() {\n" +
		"\t\t\t\treturn index < size;\n" +
		"\t\t\t}\n" +
		"\t\t};\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntList subList(final int from, final int to) {\n" +
		"\t\treturn new SubList(from, to);\n" +
		"\t}\n" +
		"\n" +
		"\tprivate void swap(int first, int second) {\n" +
		"\t\tif (first == second)\n" +
		"\t\t\treturn;\n" +
		"\t\tint temp = get(first);\n" +
		"\t\tset(first, get(second));\n" +
		"\t\tset(second, temp);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntSortedList inPlaceSort(IntComparator comparator) {\n" +
		"\t\tquickSort(0, size() - 1, (Integer.bitCount(Integer.highestOneBit(size()) - 1) * 5) >> 1, comparator);\n" +
		"\t\treturn new IntSortedArray(this, comparator);\n" +
		"\t}\n" +
		"\n" +
		"\tprivate void quickSort(int from, int to, int remaining, IntComparator comparator) {\n" +
		"\t\tif (to - from < INSERTION_THRESHOLD) {\n" +
		"\t\t\tinsertionSort(from, to, comparator);\n" +
		"\t\t\treturn;\n" +
		"\t\t}\n" +
		"\t\tif (remaining == 0) {\n" +
		"\t\t\theapSort(from, to, comparator);\n" +
		"\t\t\treturn;\n" +
		"\t\t}\n" +
		"\t\tremaining--;\n" +
		"\t\tint pivotIndex = (from + to) >> 1;\n" +
		"\t\tint pivot = get(pivotIndex);\n" +
		"\t\tswap(pivotIndex, to);\n" +
		"\t\tint storeIndex = from;\n" +
		"\t\tint equalIndex = to;\n" +
		"\t\tfor (int i = from; i < equalIndex; i++) {\n" +
		"\t\t\tint value = comparator.compare(get(i), pivot);\n" +
		"\t\t\tif (value < 0)\n" +
		"\t\t\t\tswap(storeIndex++, i);\n" +
		"\t\t\telse if (value == 0)\n" +
		"\t\t\t\tswap(--equalIndex, i--);\n" +
		"\t\t}\n" +
		"\t\tquickSort(from, storeIndex - 1, remaining, comparator);\n" +
		"\t\tfor (int i = equalIndex; i <= to; i++)\n" +
		"\t\t\tswap(storeIndex++, i);\n" +
		"\t\tquickSort(storeIndex, to, remaining, comparator);\n" +
		"\t}\n" +
		"\n" +
		"\tprivate void heapSort(int from, int to, IntComparator comparator) {\n" +
		"\t\tfor (int i = (to + from - 1) >> 1; i >= from; i--)\n" +
		"\t\t\tsiftDown(i, to, comparator, from);\n" +
		"\t\tfor (int i = to; i > from; i--) {\n" +
		"\t\t\tswap(from, i);\n" +
		"\t\t\tsiftDown(from, i - 1, comparator, from);\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\tprivate void siftDown(int start, int end, IntComparator comparator, int delta) {\n" +
		"\t\tint value = get(start);\n" +
		"\t\twhile (true) {\n" +
		"\t\t\tint child = ((start - delta) << 1) + 1 + delta;\n" +
		"\t\t\tif (child > end)\n" +
		"\t\t\t\treturn;\n" +
		"\t\t\tint childValue = get(child);\n" +
		"\t\t\tif (child + 1 <= end) {\n" +
		"\t\t\t\tint otherValue = get(child + 1);\n" +
		"\t\t\t\tif (comparator.compare(otherValue, childValue) > 0) {\n" +
		"\t\t\t\t\tchild++;\n" +
		"\t\t\t\t\tchildValue = otherValue;\n" +
		"\t\t\t\t}\n" +
		"\t\t\t}\n" +
		"\t\t\tif (comparator.compare(value, childValue) >= 0)\n" +
		"\t\t\t\treturn;\n" +
		"\t\t\tswap(start, child);\n" +
		"\t\t\tstart = child;\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\tprivate void insertionSort(int from, int to, IntComparator comparator) {\n" +
		"\t\tfor (int i = from + 1; i <= to; i++) {\n" +
		"\t\t\tint value = get(i);\n" +
		"\t\t\tfor (int j = i - 1; j >= from; j--) {\n" +
		"\t\t\t\tif (comparator.compare(get(j), value) <= 0)\n" +
		"\t\t\t\t\tbreak;\n" +
		"\t\t\t\tswap(j, j + 1);\n" +
		"\t\t\t}\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int hashCode() {\n" +
		"\t\tint hashCode = 1;\n" +
		"\t\tfor (IntIterator i = iterator(); i.isValid(); i.advance())\n" +
		"\t\t\thashCode = 31 * hashCode + i.value();\n" +
		"\t\treturn hashCode;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic boolean equals(Object obj) {\n" +
		"\t\tif (!(obj instanceof IntList))\n" +
		"\t\t\treturn false;\n" +
		"\t\tIntList list = (IntList)obj;\n" +
		"\t\tif (list.size() != size())\n" +
		"\t\t\treturn false;\n" +
		"\t\tIntIterator i = iterator();\n" +
		"\t\tIntIterator j = list.iterator();\n" +
		"\t\twhile (i.isValid()) {\n" +
		"\t\t\tif (i.value() != j.value())\n" +
		"\t\t\t\treturn false;\n" +
		"\t\t\ti.advance();\n" +
		"\t\t\tj.advance();\n" +
		"\t\t}\n" +
		"\t\treturn true;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int compareTo(IntList o) {\n" +
		"\t\tIntIterator i = iterator();\n" +
		"\t\tIntIterator j = o.iterator();\n" +
		"\t\twhile (true) {\n" +
		"\t\t\tif (i.isValid()) {\n" +
		"\t\t\t\tif (j.isValid()) {\n" +
		"\t\t\t\t\tif (i.value() != j.value()) {\n" +
		"\t\t\t\t\t\tif (i.value() < j.value())\n" +
		"\t\t\t\t\t\t\treturn -1;\n" +
		"\t\t\t\t\t\telse\n" +
		"\t\t\t\t\t\t\treturn 1;\n" +
		"\t\t\t\t\t}\n" +
		"\t\t\t\t} else\n" +
		"\t\t\t\t\treturn 1;\n" +
		"\t\t\t} else {\n" +
		"\t\t\t\tif (j.isValid())\n" +
		"\t\t\t\t\treturn -1;\n" +
		"\t\t\t\telse\n" +
		"\t\t\t\t\treturn 0;\n" +
		"\t\t\t}\n" +
		"\t\t\ti.advance();\n" +
		"\t\t\tj.advance();\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\tprivate class SubList extends IntList {\n" +
		"        private final int to;\n" +
		"        private final int from;\n" +
		"        private int size;\n" +
		"\n" +
		"        public SubList(int from, int to) {\n" +
		"            this.to = to;\n" +
		"            this.from = from;\n" +
		"            size = to - from;\n" +
		"        }\n" +
		"\n" +
		"        public int get(int index) {\n" +
		"            if (index < 0 || index >= size)\n" +
		"                throw new IndexOutOfBoundsException();\n" +
		"            return IntList.this.get(index + from);\n" +
		"        }\n" +
		"\n" +
		"        public void set(int index, int value) {\n" +
		"            if (index < 0 || index >= size)\n" +
		"                throw new IndexOutOfBoundsException();\n" +
		"            IntList.this.set(index + from, value);\n" +
		"        }\n" +
		"\n" +
		"        public int size() {\n" +
		"            return size;\n" +
		"        }\n" +
		"\n" +
		"        }\n" +
		"}\n" +
		"\n" +
		"abstract class IntSortedList extends IntList {\n" +
		"\tprotected final IntComparator comparator;\n" +
		"\n" +
		"\tprotected IntSortedList(IntComparator comparator) {\n" +
		"\t\tthis.comparator = comparator;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic void set(int index, int value) {\n" +
		"\t\tthrow new UnsupportedOperationException();\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntSortedList inPlaceSort(IntComparator comparator) {\n" +
		"\t\tif (comparator == this.comparator)\n" +
		"\t\t\treturn this;\n" +
		"\t\tthrow new UnsupportedOperationException();\n" +
		"\t}\n" +
		"\n" +
		"\tprotected void ensureSorted() {\n" +
		"\t\tint size = size();\n" +
		"\t\tif (size == 0)\n" +
		"\t\t\treturn;\n" +
		"\t\tint last = get(0);\n" +
		"\t\tfor (int i = 1; i < size; i++) {\n" +
		"\t\t\tint current = get(i);\n" +
		"\t\t\tif (comparator.compare(last, current) > 0)\n" +
		"\t\t\t\tthrow new IllegalArgumentException();\n" +
		"\t\t\tlast = current;\n" +
		"\t\t}\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntSortedList subList(final int from, final int to) {\n" +
		"\t\treturn new IntSortedList(comparator) {\n" +
		"\t\t\tprivate int size = to - from;\n" +
		"\n" +
		"\t\t\t@Override\n" +
		"\t\t\tpublic int get(int index) {\n" +
		"\t\t\t\tif (index < 0 || index >= size)\n" +
		"\t\t\t\t\tthrow new IndexOutOfBoundsException();\n" +
		"\t\t\t\treturn IntSortedList.this.get(index + from);\n" +
		"\t\t\t}\n" +
		"\n" +
		"\t\t\t@Override\n" +
		"\t\t\tpublic int size() {\n" +
		"\t\t\t\treturn size;\n" +
		"\t\t\t}\n" +
		"\t\t};\n" +
		"\t}\n" +
		"}\n" +
		"\n" +
		"class IntArray extends IntList {\n" +
		"\tprivate final int[] array;\n" +
		"\n" +
		"\tpublic IntArray(int[] array) {\n" +
		"\t\tthis.array = array;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntArray(IntCollection collection) {\n" +
		"\t\tarray = new int[collection.size()];\n" +
		"\t\tint i = 0;\n" +
		"\t\tfor (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())\n" +
		"\t\t\tarray[i++] = iterator.value();\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int get(int index) {\n" +
		"\t\treturn array[index];\n" +
		"\t}\n" +
		"\n" +
		"\tpublic void set(int index, int value) {\n" +
		"\t\tarray[index] = value;\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int size() {\n" +
		"\t\treturn array.length;\n" +
		"\t}\n" +
		"\n" +
		"\t}\n" +
		"\n" +
		"class IntSortedArray extends IntSortedList {\n" +
		"\tprivate final int[] array;\n" +
		"\n" +
		"\tpublic IntSortedArray(int[] array) {\n" +
		"\t\tthis(array, IntComparator.DEFAULT);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntSortedArray(IntCollection collection) {\n" +
		"\t\tthis(collection, IntComparator.DEFAULT);\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntSortedArray(int[] array, IntComparator comparator) {\n" +
		"\t\tsuper(comparator);\n" +
		"\t\tthis.array = array;\n" +
		"\t\tensureSorted();\n" +
		"\t}\n" +
		"\n" +
		"\tpublic IntSortedArray(IntCollection collection, IntComparator comparator) {\n" +
		"\t\tsuper(comparator);\n" +
		"\t\tarray = new int[collection.size()];\n" +
		"\t\tint i = 0;\n" +
		"\t\tfor (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())\n" +
		"\t\t\tarray[i++] = iterator.value();\n" +
		"\t\tensureSorted();\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int get(int index) {\n" +
		"\t\treturn array[index];\n" +
		"\t}\n" +
		"\n" +
		"\tpublic int size() {\n" +
		"\t\treturn array.length;\n" +
		"\t}\n" +
		"}\n" +
		"\n";

	static Random random = new Random(5122473154315L);

	static String shuffleRandom(String s) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; ++i) {
			int j = i + random.nextInt(arr.length - i);
			char t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
		}
		return new String(arr);
	}

	static String shuffleBlock(String s) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length / 4; ++i) {
			int j = i + random.nextInt(arr.length / 4 - i);
			for (int k =0 ; k < 4; ++k) {
				char t = arr[i * 4 + k];
				arr[i * 4 + k] = arr[j * 4 + k];
				arr[j * 4 + k] = t;
			}
			for (int k =0 ; k < 4; ++k) {
				j = k + random.nextInt(4 - k);
				char t = arr[i * 4 + k];
				arr[i * 4 + k] = arr[i * 4 + j];
				arr[i * 4 + j] = t;
			}
		}
		for (int i = 0; i < arr.length / 10; ++i) {
			int a = random.nextInt(arr.length);
			int b = random.nextInt(arr.length);
			char t = arr[a];
			arr[a] = arr[b];
			arr[b] = t;
		}
		return new String(arr);
	}

	/*static {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < big.length(); ++i) {
			char ch = big.charAt(i);
			b.append(ch <= ' ' ? ' ' : ch);
		}
		while (b.length() % 4 != 0) b.append(' ');
		String s = b.toString();
		boolean[][] ok = new boolean[256][256];
		for (boolean[] x : ok) Arrays.fill(x, true);
		final int[][] totalDelta = new int[256][256];
		for (int att = 0; att < 100; ++att) {
			int[][] delta = new int[256][256];
			String s1 = shuffleRandom(s);
			for (int i = 0; i < s1.length() / 4; ++i)
				for (int j= 0; j < 4; ++j)
					for (int k = j + 1; k < 4; ++k) {
						++delta[s1.charAt(i * 4 + j)][s1.charAt(i * 4 + k)];
						++delta[s1.charAt(i * 4 + k)][s1.charAt(i * 4 + j)];
					}
			s1 = shuffleBlock(s);
			for (int i = 0; i < s1.length() / 4; ++i)
				for (int j= 0; j < 4; ++j)
					for (int k = j + 1; k < 4; ++k) {
						--delta[s1.charAt(i * 4 + j)][s1.charAt(i * 4 + k)];
						--delta[s1.charAt(i * 4 + k)][s1.charAt(i * 4 + j)];
					}
			for (int a = 0; a < 256; ++a)
				for (int aa = 0; aa < 256; ++aa) {
					if (delta[a][aa] < 0 && totalDelta[a][aa] > 0 || delta[a][aa] > 0 && totalDelta[a][aa] < 0) {
						ok[a][aa] = false;
						ok[aa][a] = false;
					}
					totalDelta[a][aa] += delta[a][aa];
				}
    	}
		Integer[] perm = new Integer[65536];
		for (int i = 0; i < perm.length; ++i) perm[i] = i;
		Arrays.sort(perm, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int u1 = o1 / 256;
				int v1 = o1 % 256;
				int u2 = o2 / 256;
				int v2 = o2 % 256;
				return Math.abs(totalDelta[u2][v2]) - Math.abs(totalDelta[u1][v1]);
			}
		});
		for (int i = 0; i < 100; ++i) {
			int u = perm[i] / 256;
			int v = perm[i] % 256;
			System.out.println("handle(" + u + ", " + v + ", " + totalDelta[u][v] + ");");
		}
	}*/

	int[][][] sd;
	int[] scores;


    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		sd = new int[n][256][256];
		for (int t = 0; t < n; ++t) {
			int[][] delta = sd[t];
			String s1 = in.readLine();
			for (int i = 0; i < s1.length() / 4; ++i)
				for (int j= 0; j < 4; ++j)
					for (int k = j + 1; k < 4; ++k) {
						++delta[s1.charAt(i * 4 + j)][s1.charAt(i * 4 + k)];
						++delta[s1.charAt(i * 4 + k)][s1.charAt(i * 4 + j)];
					}
		}
		scores = new int[n];
		handle(32, 32, -149102);
		handle(32, 116, 25084);
		handle(116, 32, 25084);
		handle(32, 101, 23037);
		handle(101, 32, 23037);
		handle(32, 97, 22588);
		handle(97, 32, 22588);
		handle(32, 114, 19729);
		handle(114, 32, 19729);
		handle(32, 59, -19111);
		handle(59, 32, -19111);
		handle(32, 125, -17500);
		handle(125, 32, -17500);
		handle(97, 114, -17073);
		handle(114, 97, -17073);
		handle(32, 123, -13941);
		handle(123, 32, -13941);
		handle(32, 111, 13898);
		handle(111, 32, 13898);
		handle(32, 108, 12305);
		handle(108, 32, 12305);
		handle(114, 116, -10985);
		handle(116, 114, -10985);
		handle(110, 116, -10733);
		handle(116, 110, -10733);
		handle(32, 110, 10697);
		handle(110, 32, 10697);
		handle(116, 116, 10546);
		handle(105, 110, -10352);
		handle(110, 105, -10352);
		handle(105, 105, 9898);
		handle(32, 61, -9851);
		handle(61, 32, -9851);
		handle(32, 105, 9665);
		handle(105, 32, 9665);
		handle(32, 40, 9096);
		handle(40, 32, 9096);
		handle(32, 109, 8447);
		handle(109, 32, 8447);
		handle(32, 117, 8346);
		handle(117, 32, 8346);
		handle(105, 116, -7833);
		handle(116, 105, -7833);
		handle(111, 114, -7499);
		handle(114, 111, -7499);
		handle(32, 115, 6952);
		handle(115, 32, 6952);
		handle(101, 116, -6776);
		handle(116, 101, -6776);
		handle(101, 108, -6759);
		handle(108, 101, -6759);
		handle(32, 41, -6564);
		handle(41, 32, -6564);
		handle(101, 114, -6547);
		handle(114, 101, -6547);
		handle(111, 116, -6326);
		handle(116, 111, -6326);
		handle(32, 46, 6218);
		handle(46, 32, 6218);
		handle(32, 112, 6172);
		handle(112, 32, 6172);
		handle(110, 110, 5786);
		handle(32, 100, 5657);
		handle(100, 32, 5657);
		handle(41, 59, -5649);
		handle(59, 41, -5649);
		handle(73, 110, -5545);
		handle(110, 73, -5545);
		handle(40, 41, -5413);
		handle(41, 40, -5413);
		handle(32, 99, 5341);
		handle(99, 32, 5341);
		handle(109, 111, -5183);
		handle(111, 109, -5183);
		handle(112, 117, -5029);
		handle(117, 112, -5029);
		handle(97, 112, -4896);
		handle(112, 97, -4896);
		handle(101, 109, -4884);
		handle(109, 101, -4884);
		handle(100, 101, -4175);
		handle(101, 100, -4175);
		handle(101, 111, 4163);
		handle(111, 101, 4163);
		handle(108, 114, 4150);
		handle(114, 108, 4150);
		handle(105, 108, -4145);
		handle(108, 105, -4145);
		handle(97, 118, -4129);
		handle(118, 97, -4129);
		handle(40, 105, -4032);
		handle(105, 40, -4032);
		handle(97, 105, 3918);
		handle(105, 97, 3918);
		handle(109, 112, -3903);
		handle(112, 109, -3903);
		handle(115, 116, -3849);
		handle(116, 115, -3849);
		handle(97, 110, 3829);
		handle(110, 97, 3829);
		Integer[] perm = new Integer[n];
		for (int i = 0; i < n; ++i) perm[i] = i;
		Arrays.sort(perm, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return scores[o1] - scores[o2];
			}
		});
		String[] answer = new String[n];
		for (int i = 0; i < n / 2; ++i) answer[perm[i]] = "block";
		for (int i = n / 2; i < n; ++i) answer[perm[i]] = "random";
		for (String x : answer) out.printLine(x);
    }

	private void handle(int a, int b, int val) {
		if (val > 0) val = 1; else val = -1;
		for (int i = 0; i < scores.length; ++i) scores[i] += sd[i][a][b] * val;
	}
}
