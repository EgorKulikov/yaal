package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Taskk {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Map<String, Object> values = (Map<String, Object>) read(in);
		List<List<String>> table = new ArrayList<>();
		in.readString();
		while (!in.isExhausted()) {
			String s = in.readLine();
			int start = s.indexOf('|');
			int end;
			List<String> current = new ArrayList<>();
			while ((end = s.indexOf('|', start + 1)) != -1) {
				String element = s.substring(start + 1, end);
				start = end;
				if (element.trim().startsWith("Value:")) {
					String resolve = resolve(values, element.trim().substring(6));
					if (resolve != null) {
						element = resolve;
					}
				}
				current.add(element);
			}
			table.add(current);
			in.readString();
		}
		int[] sizes = new int[table.get(0).size()];
		for (List<String> list : table) {
			for (int i = 0; i < sizes.length; i++) {
				sizes[i] = Math.max(sizes[i], list.get(i).length());
			}
		}
		printBorder(out, sizes);
		for (List<String> list : table) {
			for (int i = 0; i < list.size(); i++) {
				out.print('|');
				String s = list.get(i);
				out.print(s);
				for (int j = 0; j < sizes[i] - s.length(); j++) {
					out.print(' ');
				}
			}
			out.printLine('|');
			printBorder(out, sizes);
		}
	}

	private String resolve(Map<String, Object> values, String key) {
		int length = key.indexOf('.');
		if (length == -1) {
			if (values.containsKey(key) && values.get(key) instanceof String) {
				return (String) values.get(key);
			} else {
				return null;
			}
		}
		String current = key.substring(0, length);
		if (values.containsKey(current) && values.get(current) instanceof Map) {
			return resolve((Map<String, Object>) values.get(current), key.substring(length + 1));
		}
		return null;
	}

	private void printBorder(OutputWriter out, int[] sizes) {
		for (int i : sizes) {
			out.print('+');
			for (int j = 0; j < i; j++) {
				out.print('-');
			}
		}
		out.printLine('+');
	}

	private Object read(InputReader in) {
		if (in.readCharacter() == '"') {
			StringBuilder result = new StringBuilder();
			char c;
			while ((c = (char) in.read()) != '"') {
				result.append(c);
			}
			return result.toString();
		}
		Map<String, Object> map = new HashMap<>();
		while (true) {
			if (in.readCharacter() == '"') {
				StringBuilder name = new StringBuilder();
				char c;
				while ((c = (char) in.read()) != '"') {
					name.append(c);
				}
				in.readCharacter();
				map.put(name.toString(), read(in));
				if (in.readCharacter() == '}') {
					return map;
				}
			} else {
				return map;
			}
		}
	}
}
