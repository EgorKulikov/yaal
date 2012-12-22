package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Stack;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String firstAddress = in.readString();
		String secondAddress = in.readString();
		if (canonical(firstAddress).equals(canonical(secondAddress)))
			out.printLine("YES");
		else
			out.printLine("NO");
	}

	private String canonical(String address) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < address.length(); i++) {
			char c = address.charAt(i);
			if (c == '%') {
				builder.append((char)Integer.parseInt(address.substring(i + 1, i + 3), 16));
				i += 2;
			} else
				builder.append(c);
		}
		address = builder.toString();
		address = address.toLowerCase();
		String protocol;
		int index = address.indexOf("://");
		if (index == -1)
			protocol = "http";
		else {
			protocol = address.substring(0, index);
			address = address.substring(index + 3);
		}
		int portPosition = address.indexOf(':');
		int pathPosition = address.indexOf('/');
		int filePosition = address.lastIndexOf('/');
		int sectionPosition = address.indexOf('#');
		int position = address.length();
		if (portPosition != -1)
			position = portPosition;
		else if (pathPosition != -1)
			position = pathPosition;
		else if (sectionPosition != -1)
			position = sectionPosition;
		String host = address.substring(0, position);
		int currentPosition = position;
		String port;
		if (portPosition != -1) {
			currentPosition++;
			position = address.length();
			if (pathPosition != -1)
				position = pathPosition;
			else if (sectionPosition != -1)
				position = sectionPosition;
			port = address.substring(currentPosition, position);
			currentPosition = position;
		} else
			port = "80";
		position = address.length();
		if (filePosition != -1)
			position = filePosition;
		else if (sectionPosition != -1)
			position = sectionPosition;
		String path = canonicalPath(address.substring(currentPosition, position));
		currentPosition = position;
		String file;
		if (filePosition != -1) {
			currentPosition++;
			position = address.length();
			if (sectionPosition != -1)
				position = sectionPosition;
			file = address.substring(currentPosition, position);
		} else
			file = "index.html";
		String section;
		if (sectionPosition != -1)
			section = address.substring(sectionPosition + 1);
		else
			section = "";
		if (file.length() == 0)
			file = "index.html";
		return protocol + "://" + host + ":" + port + path + file + "#" + section;
	}

	private String canonicalPath(String path) {
		if (path.startsWith("/"))
			path = path.substring(1);
		if (path.length() == 0)
			return "/";
		String[] parts = path.split("/");
		Stack<String> result = new Stack<String>();
		for (String part : parts) {
			if ("".equals(part))
				result.pop();
			else if (!".".equals(part))
				result.add(part);
		}
		StringBuilder builder = new StringBuilder();
		for (String part : result)
			builder.append("/").append(part);
		builder.append("/");
		return builder.toString();
	}
}
