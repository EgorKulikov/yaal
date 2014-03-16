package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class AlienAndHamburgersTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		Random random = new Random(239);
		List<Integer> types = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++)
			types.add(i + 1);
		for (int i = 0; i < 100; i++) {
			int size = random.nextInt(20) + 1;
			int difTypes = random.nextInt(size) + 1;
			Collections.shuffle(types, random);
			int[] type = new int[size];
			int[] taste = new int[size];
			for (int j = 0; j < size; j++) {
				type[j] = types.get(random.nextInt(difTypes));
				taste[j] = random.nextInt(200001) - 100000;
			}
			int answer = 0;
			for (int j = 0; j < (1 << size); j++) {
				int totalTaste = 0;
				int totalTypes = 0;
				for (int k = 0; k < size; k++) {
					if ((j >> k & 1) == 0)
						continue;
					totalTaste += taste[k];
					totalTypes++;
					for (int l = 0; l < k; l++) {
						if ((j >> l & 1) == 1 && type[l] == type[k]) {
							totalTypes--;
							break;
						}
					}
				}
				answer = Math.max(answer, totalTaste * totalTypes);
			}
			tests.add(createTest(answer, type, taste));
		}
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
