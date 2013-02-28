package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class HotelBalifornia {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int roomCount = in.readInt();
		int guestCount = in.readInt();
		int[] time = new int[guestCount];
		int[] room = new int[guestCount];
		int[] stay = new int[guestCount];
		int free = 0;
		int[] current = new int[roomCount];
		Arrays.fill(current, -1);
		for (int i = 0; i < guestCount; i++) {
			time[i] = in.readInt();
			int inconvenience = in.readInt();
			if (inconvenience == roomCount) {
				room[i] = 0;
				stay[i] = 1;
				continue;
			}
			room[i] = free - inconvenience;
			if (room[i] < 0)
				room[i] += roomCount;
			stay[i] = 31415926;
			if (current[free] != -1)
				stay[current[free]] = time[i] - time[current[free]];
			current[free] = i;
			free++;
			if (free == roomCount)
				free = 0;
		}
		for (int i = 0; i < guestCount; i++)
			out.printLine(room[i], stay[i]);
    }
}
