package net.egork.collections.sequence;

import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.filter.Filter;
import net.egork.collections.function.Function;
import net.egork.collections.set.TreapSet;
import net.egork.misc.Factory;
import net.egork.misc.MiscUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SequenceUtils {
	public static<T> int find(Sequence<T> sequence, T value) {
		int size = sequence.size();
		for (int i = 0; i < size; i++) {
			if (MiscUtils.equals(sequence.get(i), value))
				return i;
		}
		return -1;
	}

	public static <T> int find(Sequence<T> sequence, Filter<T> filter) {
		int size = sequence.size();
		for (int i = 0; i < size; i++) {
			if (filter.accept(sequence.get(i)))
				return i;
		}
		return -1;
	}

	public static<T extends Comparable<T>> int minIndex(Sequence<T> sequence) {
		return find(sequence, CollectionUtils.minElement(sequence));
	}

	public static<T extends Comparable<T>> int maxIndex(Sequence<T> sequence) {
		return find(sequence, CollectionUtils.maxElement(sequence));
	}

	public static<T> int minIndex(Sequence<T> sequence, Comparator<T> comparator) {
		return find(sequence, CollectionUtils.minElement(sequence, comparator));
	}

	public static<T> int maxIndex(Sequence<T> sequence, Comparator<T> comparator) {
		return find(sequence, CollectionUtils.maxElement(sequence, comparator));
	}

	public static<T> void fill(WritableSequence<T> sequence, T value) {
		for (int i = 0; i < sequence.size(); i++)
			sequence.set(i, value);
	}

	public static<T> void fill(WritableSequence<T> sequence, Factory<T> factory) {
		for (int i = 0; i < sequence.size(); i++)
			sequence.set(i, factory.create());
	}

	public static<T extends Comparable<T>> long countUnorderedPairs(final Sequence<T> sequence) {
		long result = 0;
		NavigableSet<T> set = new TreapSet<T>();
		int index = 0;
		for (T element : sequence) {
			result += index - set.headSet(element).size();
			set.add(element);
		}
		return result;
	}

	public static<T extends Comparable<T>> boolean nextPermutation(WritableSequence<T> sequence) {
		for (int i = sequence.size() - 2; i >= 0; i--) {
			if (sequence.get(i).compareTo(sequence.get(i + 1)) < 0) {
				int index = i + 1;
				for (int j = i + 2; j < sequence.size(); j++) {
					if (sequence.get(i).compareTo(sequence.get(j)) >= 0)
						break;
					else
						index = j;
				}
				T temp = sequence.get(i);
				sequence.set(i, sequence.get(index));
				sequence.set(index, temp);
				sort(sequence.subSequence(i + 1));
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings({"unchecked"})
	public static<T extends Comparable<T>> WritableSequence<T> sort(WritableSequence<T> sequence) {
		return sort(sequence, null);
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	public static<T> WritableSequence<T> sort(WritableSequence<T> sequence, Comparator<? super T> comparator) {
		makeHeap(sequence, comparator);
		for (int i = sequence.size() - 1; i > 0; i--) {
			T temp = sequence.get(0);
			sequence.set(0, sequence.get(i));
			sequence.set(i, temp);
			siftDown(sequence, 0, i, comparator);
		}
		return sequence;
	}

	private static<T> WritableSequence<T> makeHeap(WritableSequence<T> sequence, Comparator<? super T> comparator) {
		int length = sequence.size();
		for (int i = length / 2 - 1; i >= 0; i--)
			siftDown(sequence, i, length, comparator);
		return sequence;
	}

	private static<T> void siftDown(WritableSequence<T> sequence, int start, int end, Comparator<? super T> comparator) {
		int root = start;
		while (2 * root + 1 < end) {
			int childIndex = 2 * root + 1;
			if (childIndex + 1 < end && compare(sequence.get(childIndex), sequence.get(childIndex + 1), comparator) < 0)
				childIndex++;
			if (compare(sequence.get(childIndex), sequence.get(root), comparator) <= 0)
				return;
			T temp = sequence.get(root);
			sequence.set(root, sequence.get(childIndex));
			sequence.set(childIndex, temp);
			root = childIndex;
		}
	}

	private static<T> int compare(T first, T second, Comparator<? super T> comparator) {
		if (comparator != null)
			return comparator.compare(first, second);
		//noinspection unchecked
		return ((Comparable<? super T>)first).compareTo(second);
	}

	public static<T> boolean isSubSequence(Sequence<T> sequence, Sequence<T> sample) {
		int index = 0;
		for (T element : sequence) {
			if (element.equals(sample.get(index))) {
				if (++index == sample.size())
					return true;
			}
		}
		return false;
	}

	public static Integer[] order(final Sequence<? extends Comparable<?>>...sequences) {
		return ArrayUtils.order(sequences[0].size(), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				for (Sequence<? extends Comparable> sequence : sequences) {
					//noinspection unchecked
					int value = sequence.get(o1).compareTo(sequence.get(o2));
					if (value != 0)
						return value;
				}
				return 0;
			}
		});
	}

	public static<T> Integer[] order(final Sequence<T> sequence, final Comparator<T> comparator) {
		return ArrayUtils.order(sequence.size(), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return comparator.compare(sequence.get(o1), sequence.get(o2));
			}
		});
	}

	public static<T> WritableSequence<T> transform(WritableSequence<T> sequence, Function<T, T> function) {
		for (int i = 0; i < sequence.size(); i++)
			sequence.set(i, function.value(sequence.get(i)));
		return sequence;
	}

	public static WritableSequence<Integer> increment(WritableSequence<Integer> path) {
		return transform(path, new Function<Integer, Integer>() {
			public Integer value(Integer argument) {
				return argument + 1;
			}
		});
	}

	public static<T> boolean contains(WritableSequence<T> sequence, T value) {
		return find(sequence, value) != -1;
	}

	public static<T> boolean contains(WritableSequence<T> sequence, Filter<T> filter) {
		return find(sequence, filter) != -1;
	}

	public static<T> List<T> filter(Sequence<T> sequence, Filter<Pair<T, Integer>> filter) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < sequence.size(); i++) {
			T element = sequence.get(i);
			if (filter.accept(Pair.makePair(element, i)))
				result.add(element);
		}
		return result;
	}

	public static <T> int findLast(Sequence<T> sequence, T value) {
		int size = sequence.size();
		for (int i = size - 1; i >= 0; i--) {
			if (MiscUtils.equals(sequence.get(i), value))
				return i;
		}
		return -1;
	}

	public static <T> int findLast(Sequence<T> sequence, Filter<T> filter) {
		int size = sequence.size();
		for (int i = size - 1; i >= 0; i--) {
			if (filter.accept(sequence.get(i)))
				return i;
		}
		return -1;
	}
}
