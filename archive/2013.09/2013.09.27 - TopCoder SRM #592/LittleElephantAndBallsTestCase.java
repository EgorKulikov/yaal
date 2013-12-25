package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;

import java.util.*;

public class LittleElephantAndBallsTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		CPPMap<String, Integer> map = new CPPMap<String, Integer>(new Factory<Integer>() {
			public Integer create() {
				return 0;
			}
		});
		map.put("", 0);
		go(tests, "", map);
        return tests;
    }

	void go(List<NewTopCoderTest> tests, String current, CPPMap<String, Integer> map) {
		if (current.length() != 0) {
			int answer = 0;
			for (int i : map.values())
				answer = Math.max(answer, i);
			tests.add(createTest(answer, current));
		}
		if (current.length() == 8)
			return;
		char[] colors = "RGB".toCharArray();
		for (char c : colors) {
			CPPMap<String, Integer> newMap = new CPPMap<String, Integer>(new Factory<Integer>() {
				public Integer create() {
					return 0;
				}
			});
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				for (int i = 0; i <= current.length(); i++) {
					String key = entry.getKey().substring(0, i) + c + entry.getKey().substring(i);
					int score = entry.getValue();
					for (char c1 : colors) {
						for (int j = 0; j < i; j++) {
							if (entry.getKey().charAt(j) == c1) {
								score++;
								break;
							}
						}
						for (int j = i; j < entry.getKey().length(); j++) {
							if (entry.getKey().charAt(j) == c1) {
								score++;
								break;
							}
						}
					}
					newMap.put(key, Math.max(newMap.get(key), score));
				}
			}
			go(tests, current + c, newMap);
		}
	}

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
