package on2015_09.on2015_09_05_Booking_com_Hackathon.Facilities_Extraction;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacilitiesExtraction {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		String[] facilities = new String[n];
		for (int i = 0; i < n; i++) {
			facilities[i] = in.readLine();
		}
		StringBuilder text = new StringBuilder();
		while (!in.isExhausted()) {
			text.append(in.readLine()).append('\n');
		}
		String description = text.toString().toLowerCase();
		List<String> answer = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (StringUtils.contains(description, facilities[i].toLowerCase())) {
				answer.add(facilities[i]);
			}
		}
		Collections.sort(answer);
		answer.forEach(out::printLine);
	}
}
