package net.egork.collections.sequence;

import net.egork.misc.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.filter.Filter;
import net.egork.collections.function.Function;
import net.egork.collections.set.TreapSet;
import net.egork.misc.Factory;
import net.egork.misc.MiscUtils;

import java.util.*;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ListUtils {
	public static<T> int find(List<T> sequence, T value) {
		int size = sequence.size();
		for (int i = 0; i < size; i++) {
			if (MiscUtils.equals(sequence.get(i), value))
				return i;
		}
		return size;
	}

	public static <T> int find(List<T> sequence, Filter<T> filter) {
		int size = sequence.size();
		for (int i = 0; i < size; i++) {
			if (filter.accept(sequence.get(i)))
				return i;
		}
		return size;
	}

	public static<T extends Comparable<T>> int minIndex(List<T> sequence) {
		return find(sequence, CollectionUtils.minElement(sequence));
	}

	public static<T extends Comparable<T>> int maxIndex(List<T> sequence) {
		return find(sequence, CollectionUtils.maxElement(sequence));
	}

	public static<T> int minIndex(List<T> sequence, Comparator<T> comparator) {
		return find(sequence, CollectionUtils.minElement(sequence, comparator));
	}

	public static<T> int maxIndex(List<T> sequence, Comparator<T> comparator) {
		return find(sequence, CollectionUtils.maxElement(sequence, comparator));
	}

	public static<T> void fill(List<T> sequence, T value) {
		for (int i = 0; i < sequence.size(); i++)
			sequence.set(i, value);
	}

	public static<T> void fill(List<T> sequence, Factory<T> factory) {
		for (int i = 0; i < sequence.size(); i++)
			sequence.set(i, factory.create());
	}

	public static<T extends Comparable<T>> long countUnorderedPairs(final List<T> sequence) {
		long result = 0;
		NavigableSet<T> set = new TreapSet<T>();
		for (T element : sequence) {
			result += set.tailSet(element).size();
			set.add(element);
		}
		return result;
	}

	public static<T extends Comparable<T>> boolean nextPermutation(List<T> sequence) {
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
				Collections.sort(sequence.subList(i + 1, sequence.size()));
				return true;
			}
		}
		return false;
	}

	public static<T> boolean isSubSequence(List<T> sequence, List<T> sample) {
		int index = 0;
		for (T element : sequence) {
			if (element.equals(sample.get(index))) {
				if (++index == sample.size())
					return true;
			}
		}
		return false;
	}

	public static Integer[] order(final List<? extends Comparable<?>>...sequences) {
		return ArrayUtils.order(sequences[0].size(), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				for (List<? extends Comparable> sequence : sequences) {
					//noinspection unchecked
					int value = sequence.get(o1).compareTo(sequence.get(o2));
					if (value != 0)
						return value;
				}
				return 0;
			}
		});
	}

	public static<T> Integer[] order(final List<T> sequence, final Comparator<T> comparator) {
		return ArrayUtils.order(sequence.size(), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return comparator.compare(sequence.get(o1), sequence.get(o2));
			}
		});
	}

	public static<T> List<T> transform(List<T> sequence, Function<T, T> function) {
		for (int i = 0; i < sequence.size(); i++)
			sequence.set(i, function.value(sequence.get(i)));
		return sequence;
	}

	public static List<Integer> increment(List<Integer> path) {
		return transform(path, new Function<Integer, Integer>() {
			public Integer value(Integer argument) {
				return argument + 1;
			}
		});
	}

	public static<T> boolean contains(List<T> sequence, T value) {
		return find(sequence, value) != -1;
	}

	public static<T> boolean contains(List<T> sequence, Filter<T> filter) {
		return find(sequence, filter) != -1;
	}

	public static<T> List<T> filter(List<T> sequence, Filter<Pair<T, Integer>> filter) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < sequence.size(); i++) {
			T element = sequence.get(i);
			if (filter.accept(Pair.makePair(element, i)))
				result.add(element);
		}
		return result;
	}

	public static <T> int findLast(List<T> sequence, T value) {
		int size = sequence.size();
		for (int i = size - 1; i >= 0; i--) {
			if (MiscUtils.equals(sequence.get(i), value))
				return i;
		}
		return -1;
	}

	public static <T> int findLast(List<T> sequence, Filter<T> filter) {
		int size = sequence.size();
		for (int i = size - 1; i >= 0; i--) {
			if (filter.accept(sequence.get(i)))
				return i;
		}
		return -1;
	}
}
