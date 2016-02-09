package net.egork.generated.collections.list;

import net.egork.generated.collections.comparator.CharComparator;
import net.egork.generated.collections.comparator.DoubleComparator;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.comparator.LongComparator;

public class Sorter {
    private static final int INSERTION_THRESHOLD = 16;

    private Sorter() {
    }

    public static void sort(DoubleList list, DoubleComparator comparator) {
        quickSort(list, 0, list.size() - 1, (Integer.bitCount(Integer.highestOneBit(list.size()) - 1) * 5) >> 1,
                comparator);
    }

    private static void quickSort(DoubleList list, int from, int to, int remaining, DoubleComparator comparator) {
        if (to - from < INSERTION_THRESHOLD) {
            insertionSort(list, from, to, comparator);
            return;
        }
        if (remaining == 0) {
            heapSort(list, from, to, comparator);
            return;
        }
        remaining--;
        int pivotIndex = (from + to) >> 1;
        double pivot = list.get(pivotIndex);
        list.swap(pivotIndex, to);
        int storeIndex = from;
        int equalIndex = to;
        for (int i = from; i < equalIndex; i++) {
            int value = comparator.compare(list.get(i), pivot);
            if (value < 0) {
                list.swap(storeIndex++, i);
            } else if (value == 0) {
                list.swap(--equalIndex, i--);
            }
        }
        quickSort(list, from, storeIndex - 1, remaining, comparator);
        for (int i = equalIndex; i <= to; i++) {
            list.swap(storeIndex++, i);
        }
        quickSort(list, storeIndex, to, remaining, comparator);
    }

    private static void heapSort(DoubleList list, int from, int to, DoubleComparator comparator) {
        for (int i = (to + from - 1) >> 1; i >= from; i--) {
            siftDown(list, i, to, comparator, from);
        }
        for (int i = to; i > from; i--) {
            list.swap(from, i);
            siftDown(list, from, i - 1, comparator, from);
        }
    }

    private static void siftDown(DoubleList list, int start, int end, DoubleComparator comparator, int delta) {
        double value = list.get(start);
        while (true) {
            int child = ((start - delta) << 1) + 1 + delta;
            if (child > end) {
                return;
            }
            double childValue = list.get(child);
            if (child + 1 <= end) {
                double otherValue = list.get(child + 1);
                if (comparator.compare(otherValue, childValue) > 0) {
                    child++;
                    childValue = otherValue;
                }
            }
            if (comparator.compare(value, childValue) >= 0) {
                return;
            }
            list.swap(start, child);
            start = child;
        }
    }

    private static void insertionSort(DoubleList list, int from, int to, DoubleComparator comparator) {
        for (int i = from + 1; i <= to; i++) {
            double value = list.get(i);
            for (int j = i - 1; j >= from; j--) {
                if (comparator.compare(list.get(j), value) <= 0) {
                    break;
                }
                list.swap(j, j + 1);
            }
        }
    }

    public static void sort(IntList list, IntComparator comparator) {
        quickSort(list, 0, list.size() - 1, (Integer.bitCount(Integer.highestOneBit(list.size()) - 1) * 5) >> 1,
                comparator);
    }

    private static void quickSort(IntList list, int from, int to, int remaining, IntComparator comparator) {
        if (to - from < INSERTION_THRESHOLD) {
            insertionSort(list, from, to, comparator);
            return;
        }
        if (remaining == 0) {
            heapSort(list, from, to, comparator);
            return;
        }
        remaining--;
        int pivotIndex = (from + to) >> 1;
        int pivot = list.get(pivotIndex);
        list.swap(pivotIndex, to);
        int storeIndex = from;
        int equalIndex = to;
        for (int i = from; i < equalIndex; i++) {
            int value = comparator.compare(list.get(i), pivot);
            if (value < 0) {
                list.swap(storeIndex++, i);
            } else if (value == 0) {
                list.swap(--equalIndex, i--);
            }
        }
        quickSort(list, from, storeIndex - 1, remaining, comparator);
        for (int i = equalIndex; i <= to; i++) {
            list.swap(storeIndex++, i);
        }
        quickSort(list, storeIndex, to, remaining, comparator);
    }

    private static void heapSort(IntList list, int from, int to, IntComparator comparator) {
        for (int i = (to + from - 1) >> 1; i >= from; i--) {
            siftDown(list, i, to, comparator, from);
        }
        for (int i = to; i > from; i--) {
            list.swap(from, i);
            siftDown(list, from, i - 1, comparator, from);
        }
    }

    private static void siftDown(IntList list, int start, int end, IntComparator comparator, int delta) {
        int value = list.get(start);
        while (true) {
            int child = ((start - delta) << 1) + 1 + delta;
            if (child > end) {
                return;
            }
            int childValue = list.get(child);
            if (child + 1 <= end) {
                int otherValue = list.get(child + 1);
                if (comparator.compare(otherValue, childValue) > 0) {
                    child++;
                    childValue = otherValue;
                }
            }
            if (comparator.compare(value, childValue) >= 0) {
                return;
            }
            list.swap(start, child);
            start = child;
        }
    }

    private static void insertionSort(IntList list, int from, int to, IntComparator comparator) {
        for (int i = from + 1; i <= to; i++) {
            int value = list.get(i);
            for (int j = i - 1; j >= from; j--) {
                if (comparator.compare(list.get(j), value) <= 0) {
                    break;
                }
                list.swap(j, j + 1);
            }
        }
    }

    public static void sort(LongList list, LongComparator comparator) {
        quickSort(list, 0, list.size() - 1, (Integer.bitCount(Integer.highestOneBit(list.size()) - 1) * 5) >> 1,
                comparator);
    }

    private static void quickSort(LongList list, int from, int to, int remaining, LongComparator comparator) {
        if (to - from < INSERTION_THRESHOLD) {
            insertionSort(list, from, to, comparator);
            return;
        }
        if (remaining == 0) {
            heapSort(list, from, to, comparator);
            return;
        }
        remaining--;
        int pivotIndex = (from + to) >> 1;
        long pivot = list.get(pivotIndex);
        list.swap(pivotIndex, to);
        int storeIndex = from;
        int equalIndex = to;
        for (int i = from; i < equalIndex; i++) {
            int value = comparator.compare(list.get(i), pivot);
            if (value < 0) {
                list.swap(storeIndex++, i);
            } else if (value == 0) {
                list.swap(--equalIndex, i--);
            }
        }
        quickSort(list, from, storeIndex - 1, remaining, comparator);
        for (int i = equalIndex; i <= to; i++) {
            list.swap(storeIndex++, i);
        }
        quickSort(list, storeIndex, to, remaining, comparator);
    }

    private static void heapSort(LongList list, int from, int to, LongComparator comparator) {
        for (int i = (to + from - 1) >> 1; i >= from; i--) {
            siftDown(list, i, to, comparator, from);
        }
        for (int i = to; i > from; i--) {
            list.swap(from, i);
            siftDown(list, from, i - 1, comparator, from);
        }
    }

    private static void siftDown(LongList list, int start, int end, LongComparator comparator, int delta) {
        long value = list.get(start);
        while (true) {
            int child = ((start - delta) << 1) + 1 + delta;
            if (child > end) {
                return;
            }
            long childValue = list.get(child);
            if (child + 1 <= end) {
                long otherValue = list.get(child + 1);
                if (comparator.compare(otherValue, childValue) > 0) {
                    child++;
                    childValue = otherValue;
                }
            }
            if (comparator.compare(value, childValue) >= 0) {
                return;
            }
            list.swap(start, child);
            start = child;
        }
    }

    private static void insertionSort(LongList list, int from, int to, LongComparator comparator) {
        for (int i = from + 1; i <= to; i++) {
            long value = list.get(i);
            for (int j = i - 1; j >= from; j--) {
                if (comparator.compare(list.get(j), value) <= 0) {
                    break;
                }
                list.swap(j, j + 1);
            }
        }
    }

    public static void sort(CharList list, CharComparator comparator) {
        quickSort(list, 0, list.size() - 1, (Integer.bitCount(Integer.highestOneBit(list.size()) - 1) * 5) >> 1,
                comparator);
    }

    private static void quickSort(CharList list, int from, int to, int remaining, CharComparator comparator) {
        if (to - from < INSERTION_THRESHOLD) {
            insertionSort(list, from, to, comparator);
            return;
        }
        if (remaining == 0) {
            heapSort(list, from, to, comparator);
            return;
        }
        remaining--;
        int pivotIndex = (from + to) >> 1;
        char pivot = list.get(pivotIndex);
        list.swap(pivotIndex, to);
        int storeIndex = from;
        int equalIndex = to;
        for (int i = from; i < equalIndex; i++) {
            int value = comparator.compare(list.get(i), pivot);
            if (value < 0) {
                list.swap(storeIndex++, i);
            } else if (value == 0) {
                list.swap(--equalIndex, i--);
            }
        }
        quickSort(list, from, storeIndex - 1, remaining, comparator);
        for (int i = equalIndex; i <= to; i++) {
            list.swap(storeIndex++, i);
        }
        quickSort(list, storeIndex, to, remaining, comparator);
    }

    private static void heapSort(CharList list, int from, int to, CharComparator comparator) {
        for (int i = (to + from - 1) >> 1; i >= from; i--) {
            siftDown(list, i, to, comparator, from);
        }
        for (int i = to; i > from; i--) {
            list.swap(from, i);
            siftDown(list, from, i - 1, comparator, from);
        }
    }

    private static void siftDown(CharList list, int start, int end, CharComparator comparator, int delta) {
        char value = list.get(start);
        while (true) {
            int child = ((start - delta) << 1) + 1 + delta;
            if (child > end) {
                return;
            }
            char childValue = list.get(child);
            if (child + 1 <= end) {
                char otherValue = list.get(child + 1);
                if (comparator.compare(otherValue, childValue) > 0) {
                    child++;
                    childValue = otherValue;
                }
            }
            if (comparator.compare(value, childValue) >= 0) {
                return;
            }
            list.swap(start, child);
            start = child;
        }
    }

    private static void insertionSort(CharList list, int from, int to, CharComparator comparator) {
        for (int i = from + 1; i <= to; i++) {
            char value = list.get(i);
            for (int j = i - 1; j >= from; j--) {
                if (comparator.compare(list.get(j), value) <= 0) {
                    break;
                }
                list.swap(j, j + 1);
            }
        }
    }
}