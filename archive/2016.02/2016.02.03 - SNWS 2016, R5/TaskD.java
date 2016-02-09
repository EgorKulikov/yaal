package net.egork;

import net.egork.collections.map.CPPMap;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in = new InputReader(System.in);
		out = new OutputWriter(System.out);
		int n = in.readInt();
		Map<String, Integer> map = new CPPMap<>(() -> 0);
		for (int i = 0; (1 << i) < n; i++) {
			IntList request = new IntArrayList();
			for (int j = 0; j < n; j++) {
				if ((j >> i & 1) == 1) {
					request.add(j + 1);
				}
			}
			out.printLine("?");
			out.printLine(request.size());
			out.printLine(request);
			out.flush();
			in.readInt();
			String[] answer = readStringArray(in, request.size());
			for (String s : answer) {
				map.put(s, map.get(s) + (1 << i));
			}
		}
		out.printLine("?");
		out.printLine(1);
		out.printLine(1);
		out.flush();
		String[] answer = new String[n];
		in.readInt();
		answer[0] = in.readString();
		out.printLine("!");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			answer[entry.getValue()] = entry.getKey();
		}
		out.printLine(answer);
		out.flush();
	}
}
