package net.egork.y2011.m4.d3.eleventhzhejianguniversityprogrammingcontest;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.*;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		in.readString();
		Map<String, Set<String>> methods = new HashMap<String, Set<String>>();
		Map<String, String> superClasses = new HashMap<String, String>();
		while (true) {
			String command = in.readString();
			if ("end".equals(command)) {
				out.println();
				return;
			}
			String argument = in.readString();
			if ("class".equals(command)) {
				String[] classDefinition = fastSplit(argument, ':');
				String className = classDefinition[0];
				String superClassName = classDefinition.length == 2 ? classDefinition[1] : null;
				if (methods.containsKey(className)) {
					out.println("oops!");
					continue;
				}
				if (classDefinition.length == 2 && !methods.containsKey(superClassName)) {
					out.println("oops!");
					continue;
				}
				methods.put(className, new HashSet<String>());
				if (classDefinition.length == 2)
					superClasses.put(className, superClassName);
				out.println("class " + argument);
			} else if ("def".equals(command)) {
				String[] methodDefinition = fastSplit(argument, '.');
				final String className = methodDefinition[0];
				if (!methods.containsKey(className)) {
					out.println("oops!");
					continue;
				}
				final String methodName = methodDefinition[1];
				if (methods.get(className).contains(methodName))
					out.println("redef " + argument);
				else {
					methods.get(className).add(methodName);
					out.println("def " + argument);
				}
			} else if ("undef".equals(command)) {
				String[] methodDefinition = fastSplit(argument, '.');
				final String className = methodDefinition[0];
				final String methodName = methodDefinition[1];
				if (!methods.containsKey(className) || !methods.get(className).contains(methodName)) {
					out.println("oops!");
					continue;
				}
				methods.get(className).remove(methodName);
				out.println("undef " + argument);
			} else if ("call".equals(command)) {
				String[] methodDefinition = fastSplit(argument, '.');
				String className = methodDefinition[0];
				String methodName = methodDefinition[1];
				if (!methods.containsKey(className)) {
					out.println("oops!");
					continue;
				}
				while (className != null) {
					if (methods.get(className).contains(methodName)) {
						out.println("invoke " + className + "." + methodName);
						break;
					}
					className = superClasses.get(className);
				}
				if (className == null)
					out.println("oops!");
			}
		}
	}

	private String[] fastSplit(String s, char c) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c)
				return new String[]{s.substring(0, i), s.substring(i + 1)};
		}
		return new String[]{s};
	}
}

