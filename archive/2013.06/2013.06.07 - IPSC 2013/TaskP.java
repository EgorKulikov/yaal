package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TaskP {
	Map<String, Integer> map = new HashMap<String, Integer>();

	{
		map.put("zero", 0);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);
		map.put("ten", 10);
		map.put("eleven", 11);
		map.put("twelve", 12);
		map.put("thirteen", 13);
		map.put("fourteen", 14);
		map.put("fifteen", 15);
		map.put("sixteen", 16);
		map.put("seventeen", 17);
		map.put("eighteen", 18);
		map.put("nineteen", 19);
		map.put("twenty", 20);
		map.put("thirty", 30);
		map.put("forty", 40);
		map.put("fifty", 50);
		map.put("sixty", 60);
		map.put("seventy", 70);
		map.put("eighty", 80);
		map.put("ninety", 90);
	}

	BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		String s = in.readLine();
		try {
			out.printLine(Integer.parseInt(s) + 1);
		} catch (NumberFormatException e) {
			String orS = s;
			int sign = 1;
			if (s.startsWith("minus ")) {
				sign = -1;
				s = s.substring(6);
			}
			String[] tokens = s.split("[-]");
			int number = 0;
			for (String x : tokens) {
				if (map.containsKey(x))
					number += map.get(x);
				else {
					System.out.println("Unknown: " + orS);
					try {
						out.printLine(scanner.readLine());
						return;
					} catch (IOException e1) {
						throw new RuntimeException(e1);
					}
				}
			}
			number *= sign;
			number++;
			StringBuilder result = new StringBuilder();
			boolean minus = number < 0;
			number = Math.abs(number);
			while (number != 0) {
				int max = 0;
				String x = null;
				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					if (entry.getValue() <= number && entry.getValue() > max) {
						max = entry.getValue();
						x = entry.getKey();
					}
				}
				number -= max;
				if (result.length() > 0)
					result.append('-');
				result.append(x);
			}
			if (result.length() == 0)
				result.append("zero");
			out.printLine((minus ? "minus " : "") + result);
		}
    }
}
