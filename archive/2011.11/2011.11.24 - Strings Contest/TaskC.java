package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class TaskC {
	private String[][] notes = new String[][]{new String[]{"A"}, new String[]{"A#", "Bb"}, new String[]{"B"},
		new String[]{"C"}, new String[]{"C#", "Db"}, new String[]{"D"}, new String[]{"D#", "Eb"},
		new String[]{"E"}, new String[]{"F"}, new String[]{"F#", "Gb"}, new String[]{"G"}, new String[]{"G#", "Ab"}};
	private Map<String, Integer> index = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);

	{
		for (int i = 0; i < notes.length; i++) {
			for (String note : notes[i])
				index.put(note, i);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		String[] currentNotes = IOUtils.readStringArray(in, 3);
		int[] positions = new int[6];
		for (int i = 0; i < 3; i++)
			positions[i] = index.get(currentNotes[i]);
		Arrays.sort(positions, 0, 3);
		for (int i = 0; i < 3; i++)
			positions[i + 3] = positions[i] + notes.length;
		out.print(currentNotes);
		out.print(" is ");
		for (int i = 0; i < 3; i++) {
			if (positions[i + 1] - positions[i] == 4 && positions[i + 2] - positions[i] == 7) {
				out.printLine("a", notes[positions[i]][0], "Major chord.");
				return;
			}
			if (positions[i + 1] - positions[i] == 3 && positions[i + 2] - positions[i] == 7) {
				out.printLine("a", notes[positions[i]][0], "Minor chord.");
				return;
			}
		}
		out.printLine("unrecognized.");
	}
}
