package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TopCoderTestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class KCTestCase implements TopCoderTestProvider {
	public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				boolean[][] visited = new boolean[i][j];
				int[] rowQueue = new int[i * j];
				int[] columnQueue = new int[i * j];
				int answer = 0;
				for (int k = 0; k < i; k++) {
					for (int l = 0; l < j; l++) {
						if (visited[k][l])
							continue;
						rowQueue[0] = k;
						columnQueue[0] = l;
						visited[k][l] = true;
						int size = 1;
						for (int m = 0; m < size; m++) {
							int row = rowQueue[m];
							int column = columnQueue[m];
							for (int n = -2; n <= 2; n++) {
								if (n == 0)
									continue;
								for (int o = -3 + Math.abs(n); o <= 3 - Math.abs(n); o += 6 - 2 * Math.abs(n)) {
									int newRow = row + n;
									int newColumn = column + o;
									if (newRow >= 0 && newRow < i && newColumn >= 0 && newColumn < j && !visited[newRow][newColumn]) {
										visited[newRow][newColumn] = true;
										rowQueue[size] = newRow;
										columnQueue[size++] = newColumn;
									}
								}
							}
						}
						answer = Math.max(answer, size);
					}
				}
				tests.add(new NewTopCoderTest(new Object[]{i, j}, answer));
			}
		}
		return tests;
	}
}
