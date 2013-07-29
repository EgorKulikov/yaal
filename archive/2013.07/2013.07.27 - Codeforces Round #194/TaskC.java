package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntIterator;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Map<Integer, IntSet> map = new CPPMap<Integer, IntSet>(new Factory<IntSet>() {
			public IntSet create() {
				return new SpecialSet();
			}
		});
		map = twice(map, 10, -1);
		map = twice(map, 100, -1);
		int value = in.readInt();
		map = twice(map, 10000, value);
		int count = in.readInt();
		for (int i : map.get(value).toArray()) {
			out.printFormat("%08d\n", i);
			if (--count == 0)
				break;
		}
		if (count != 0)
			throw new RuntimeException();
    }

	private Map<Integer, IntSet> twice(Map<Integer, IntSet> counter, int ten, int exact) {
		for (int i = 0; i < ten; i++)
			counter.get(i).add(i);
		Map<Integer, IntSet> result = new CPPMap<Integer, IntSet>(new Factory<IntSet>() {
			public IntSet create() {
				return new SpecialSet();
			}
		});
		for (Map.Entry<Integer, IntSet> first : counter.entrySet()) {
			if (exact != -1) {
				int key = first.getKey();
				if (counter.containsKey(Math.abs(exact - key)))
					process(ten, exact, result, first.getValue(), counter.get(Math.abs(exact - key)), first.getKey(), Math.abs(exact - key));
				if (counter.containsKey(exact + key))
					process(ten, exact, result, first.getValue(), counter.get(exact + key), first.getKey(), exact + key);
				if (key != 0 && exact % key == 0 && counter.containsKey(exact / key))
					process(ten, exact, result, first.getValue(), counter.get(exact / key), first.getKey(), exact / key);
			} else {
				for (Map.Entry<Integer, IntSet> second : counter.entrySet()) {
					process(ten, exact, result, first.getValue(), second.getValue(), first.getKey(), second.getKey());
				}
			}
		}
		return result;
	}

	private void process(int ten, int exact, Map<Integer, IntSet> result, IntSet first, IntSet second, int firstKey, int secondKey) {
		Integer sum = firstKey + secondKey;
		Integer difference = Math.abs(firstKey - secondKey);
		Integer product = firstKey * secondKey;
		if (exact != -1 && sum != exact && difference != exact && product != exact)
			return;
		for (IntIterator i = first.iterator(); i.isValid(); i.advance()) {
			for (IntIterator j = second.iterator(); j.isValid(); j.advance()) {
				if (exact == -1 || exact == sum)
					result.get(sum).add(i.value() * ten + j.value());
				if (exact == -1 || exact == difference)
					result.get(difference).add(i.value() * ten + j.value());
				if (exact == -1 || exact == product)
					result.get(product).add(i.value() * ten + j.value());
				if (exact != -1 && result.get(exact).size() == 300000)
					return;
			}
		}
	}

	static class SpecialSet extends IntHashSet {
		@Override
		public void add(int value) {
			if (size() >= 300000)
				return;
			super.add(value);
		}
	}
}
