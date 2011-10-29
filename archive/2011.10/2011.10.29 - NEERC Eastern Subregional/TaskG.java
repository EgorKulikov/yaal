package net.egork;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class TaskG {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int functionCount = in.readInt();
		Map<Pair<String, List<String>>, String> functions = new HashMap<Pair<String, List<String>>, String>();
		for (int i = 0; i < functionCount; i++) {
			String description = in.readLine().replaceAll("[():,]", " ");
			String[] tokens = description.split(" +");
			functions.put(Pair.makePair(tokens[0], Arrays.asList(tokens).subList(1, tokens.length - 1)),
				tokens[tokens.length - 1]);
		}
		int lineCount = in.readInt();
		Map<String, String> variables = new HashMap<String, String>();
		List<String> auto = new ArrayList<String>();
		for (int i = 0; i < lineCount; i++) {
			String line = in.readLine().replaceAll("[(),=]", " ");
			String[] tokens = line.split(" +");
			if (variables.containsKey(tokens[1])) {
				out.println("Error on line " + (i + 1) + ": Double declaration");
				return;
			}
			List<String> arguments = new ArrayList<String>();
			for (int j = 3; j < tokens.length; j++) {
				if (!variables.containsKey(tokens[j])) {
					out.println("Error on line " + (i + 1) + ": Unknown variable");
					return;
				}
				arguments.add(variables.get(tokens[j]));
			}
			String returnType = functions.get(Pair.makePair(tokens[2], arguments));
			if (returnType == null) {
				out.println("Error on line " + (i + 1) + ": No such function");
				return;
			}
			if (!returnType.equals(tokens[0]) && !"auto".equals(tokens[0])) {
				out.println("Error on line " + (i + 1) + ": Invalid initialization");
				return;
			}
			variables.put(tokens[1], returnType);
			if ("auto".equals(tokens[0]))
				auto.add(tokens[1]);
		}
		for (String variable : auto)
			out.println(variable + " : " + variables.get(variable));
	}
}
