package net.egork;

import net.egork.collections.map.Counter;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThePunctuationCorrectorWhatIsIt {
	Counter<String> itIs = new Counter<String>();
	Counter<String> its = new Counter<String>();

	int totalIts;
	int totalItIs;

	Random random = new Random(239);

	{
		try {
			InputReader reader = new InputReader(new FileInputStream("corpus.txt"));
			boolean lastIts = false;
			boolean lastItIs = false;
			boolean lastIt = false;
			int totalTokens = 0;
			while (!reader.isExhausted()) {
				totalTokens++;
				String token = reader.readString();
				StringBuilder normalized = new StringBuilder();
				for (int i = 0; i < token.length(); i++) {
					if (Character.isLetter(token.charAt(i)) || token.charAt(i) == '\'')
						normalized.append(Character.toLowerCase(token.charAt(i)));
				}
				if (normalized.length() == 0)
					continue;
				token = normalized.toString();
				if (lastItIs) {
					itIs.add(token);
					totalItIs++;
				} else if (lastIts) {
					its.add(token);
					totalIts++;
				}
				lastItIs = token.equals("it's") || token.equals("is") && lastIt;
				lastIts = token.equals("its") || token.equals("his") || token.equals("her") || token.equals("mine") || token.equals("yours");
				lastIt = token.equals("it") || token.equals("he") || token.equals("she");
			}
			System.err.println(totalTokens);
			System.err.println(totalItIs);
			System.err.println(totalIts);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String line = in.readLine();
		InputReader reader = new InputReader(new StringBufferInputStream(line));
		boolean toDecide = false;
		List<String> answer = new ArrayList<String>();
		while (!reader.isExhausted()) {
			String token = reader.readString();
			StringBuilder normalized = new StringBuilder();
			for (int i = 0; i < token.length(); i++) {
				if (Character.isLetter(token.charAt(i)) || token.charAt(i) == '\'' || token.charAt(i) == '?')
					normalized.append(Character.toLowerCase(token.charAt(i)));
			}
			if (normalized.length() == 0)
				continue;
			token = normalized.toString();
			if ("???".equals(token)) {
				toDecide = true;
				continue;
			}
			normalized = new StringBuilder();
			for (int i = 0; i < token.length(); i++) {
				if (Character.isLetter(token.charAt(i)) || token.charAt(i) == '\'')
					normalized.append(Character.toLowerCase(token.charAt(i)));
			}
			if (normalized.length() == 0)
				continue;
			token = normalized.toString();
			if (toDecide) {
				long qtyItIs = itIs.get(token) * totalIts;
				long qtyIts = its.get(token) * totalItIs;
				if (qtyItIs > qtyIts || qtyItIs == qtyIts && random.nextBoolean())
					answer.add("it's");
				else
					answer.add("its");
			}
		}
		for (String s : answer)
			line = line.replaceFirst("[?][?][?]", s);
		out.printLine(line);
    }
}
