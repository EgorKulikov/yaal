package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int roomCount = in.readInt();
		int placeCount = in.readInt();
		@SuppressWarnings({"unchecked"})
		NavigableSet<Integer>[] rooms = new NavigableSet[roomCount];
		for (int i = 0; i < roomCount; i++)
			rooms[i] = new TreeSet<Integer>();
		@SuppressWarnings({"unchecked"})
		NavigableSet<Integer>[] quantities = new NavigableSet[placeCount + 1];
		for (int i = 0; i <= placeCount; i++)
			quantities[i] = new TreeSet<Integer>();
		quantities[0].addAll(Arrays.asList(ArrayUtils.generateOrder(roomCount)));
		int min = 0;
		int max = 0;
		int curId = 1;
		int[] roomIndex = new int[count + 1];
		for (int i = 0; i < count; i++) {
			char type = in.readCharacter();
			if (type == '+') {
				int room = quantities[min].pollFirst();
				roomIndex[curId] = room;
				rooms[room].add(curId);
				quantities[min + 1].add(room);
				if (max == min)
					max++;
				if (quantities[min].isEmpty())
					min++;
				curId++;
			} else {
				int id = in.readInt();
				int room = roomIndex[id];
				int quantity = rooms[room].size();
				rooms[room].remove(id);
				quantities[quantity].remove(room);
				quantities[quantity - 1].add(room);
				if (quantity == min)
					min--;
				if (quantities[max].isEmpty())
					max--;
				if (max - min >= 2) {
					NavigableSet<Integer> head = quantities[max].headSet(room, false);
					NavigableSet<Integer> tail = quantities[max].tailSet(room, false);
					int otherRoom;
					if (head.isEmpty())
						otherRoom = tail.first();
					else if (tail.isEmpty())
						otherRoom = head.last();
					else if (room - head.last() <= tail.first() - room)
						otherRoom = head.last();
					else
						otherRoom = tail.first();
					id = rooms[otherRoom].pollFirst();
					quantities[max].remove(otherRoom);
					quantities[max - 1].add(otherRoom);
					rooms[room].add(id);
					roomIndex[id] = room;
					quantities[min].remove(room);
					quantities[min + 1].add(room);
					min++;
					if (quantities[max].isEmpty())
						max--;
				}
			}
		}
		for (int i = 0; i < roomCount; i++) {
			out.print(rooms[i].size());
			if (!rooms[i].isEmpty()) {
				out.print(' ');
				out.printLine(rooms[i].toArray());
			} else
				out.printLine();
		}
	}
}
