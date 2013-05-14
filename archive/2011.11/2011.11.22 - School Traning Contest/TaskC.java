package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int source = in.readInt() - 1;
		int destination = in.readInt() - 1;
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		char[] startColor = new char[vertexCount];
		int[] timeRemaining = new int[vertexCount];
		int[] blueTime = new int[vertexCount];
		int[] purpleTime = new int[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			startColor[i] = in.readCharacter();
			timeRemaining[i] = in.readInt();
			blueTime[i] = in.readInt();
			purpleTime[i] = in.readInt();
		}
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] distance = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, distance);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildGraph(vertexCount, from, to);
		int[] sourceDistance = new int[vertexCount];
		Arrays.fill(sourceDistance, Integer.MAX_VALUE / 2);
		sourceDistance[source] = 0;
		boolean[] processed = new boolean[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			int minDistance = Integer.MAX_VALUE / 2;
			int minIndex = -1;
			for (int j = 0; j < vertexCount; j++) {
				if (sourceDistance[j] < minDistance && !processed[j]) {
					minDistance = sourceDistance[j];
					minIndex = j;
				}
			}
			if (minIndex == -1)
				break;
			processed[minIndex] = true;
			for (int j : graph[minIndex]) {
				int vertex = GraphUtils.otherVertex(minIndex, from[j], to[j]);
				int totalTime = nextAllow(startColor[minIndex], timeRemaining[minIndex], blueTime[minIndex],
					purpleTime[minIndex], startColor[vertex], timeRemaining[vertex], blueTime[vertex],
					purpleTime[vertex], minDistance) + distance[j];
				if (sourceDistance[vertex] > totalTime)
					sourceDistance[vertex] = totalTime;
			}
		}
		if (sourceDistance[destination] == Integer.MAX_VALUE / 2)
			sourceDistance[destination] = 0;
		out.printLine(sourceDistance[destination]);
	}

	private int nextAllow(char startColor1, int timeRemaining1, int blueTime1, int purpleTime1, char startColor2,
		int timeRemaining2, int blueTime2, int purpleTime2, int minDistance)
	{
		if (timeRemaining1 == timeRemaining2 && blueTime1 == purpleTime2 && blueTime2 == purpleTime1 &&
			startColor1 != startColor2)
		{
			return Integer.MAX_VALUE / 2;
		}
		if (color(startColor1, timeRemaining1, blueTime1, purpleTime1, minDistance) ==
			color(startColor2, timeRemaining2, blueTime2, purpleTime2, minDistance))
		{
			return minDistance;
		}
		while (true) {
			int nextSwitch1 = nextSwitch(startColor1, timeRemaining1, blueTime1, purpleTime1, minDistance);
			int nextSwitch2 = nextSwitch(startColor2, timeRemaining2, blueTime2, purpleTime2, minDistance);
			if (nextSwitch1 != nextSwitch2)
				return Math.min(nextSwitch1, nextSwitch2);
			minDistance = nextSwitch1;
		}
	}

	private int nextSwitch(char startColor, int timeRemaining, int blueTime, int purpleTime, int currentTime) {
		if (currentTime < timeRemaining)
			return timeRemaining;
		int delta = timeRemaining;
		currentTime -= timeRemaining;
		delta += (currentTime / (blueTime + purpleTime)) * (blueTime + purpleTime);
		currentTime %= blueTime + purpleTime;
		if (startColor == 'B' && currentTime < purpleTime)
			return delta + purpleTime;
		if (startColor == 'P' && currentTime < blueTime)
			return delta + blueTime;
		return delta + blueTime + purpleTime;
	}

	private char color(char startColor, int timeRemaining, int blueTime, int purpleTime, int currentTime) {
		if (currentTime < timeRemaining)
			return startColor;
		currentTime -= timeRemaining;
		currentTime %= blueTime + purpleTime;
		if (startColor == 'P' && currentTime < blueTime || startColor == 'B' && currentTime >= purpleTime)
			return 'B';
		else
			return 'P';
	}
}
