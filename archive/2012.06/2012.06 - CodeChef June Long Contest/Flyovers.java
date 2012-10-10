package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Flyovers {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger vertexCount = in.readBigInteger();
		String costInput = in.readString();
		BigInteger cost;
		if (costInput.indexOf('.') == -1)
			cost = new BigInteger(costInput).multiply(BigInteger.valueOf((long) 1e9));
		else
			cost = new BigInteger(costInput.split("[.]")[0]).multiply(BigInteger.valueOf((long) 1e9)).add(new BigInteger(costInput.split("[.]")[1]).multiply(BigInteger.valueOf(10).pow(9 - costInput.split("[.]")[1].length())));
		int freeCount = in.readInt();
		int[] from = new int[freeCount];
		int[] to = new int[freeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		BigInteger answer = vertexCount.multiply(vertexCount.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2)).multiply(BigInteger.valueOf((long) 2e9).add(cost)).subtract(cost.multiply(BigInteger.valueOf(freeCount)));
		if (freeCount < 2 || from[0] == from[1] || from[0] == to[1] || to[0] == from[1] || to[0] == to[1])
			answer = answer.min(BigInteger.valueOf((long) 2e9).multiply(vertexCount.subtract(BigInteger.ONE)).multiply(vertexCount.subtract(BigInteger.ONE)).add(cost.multiply(vertexCount.subtract(BigInteger.valueOf(1 + freeCount)))));
		else {
			answer = answer.min(BigInteger.valueOf((long) 2e9).multiply(vertexCount).multiply(vertexCount.subtract(BigInteger.valueOf(2))).add(cost.multiply(vertexCount.subtract(BigInteger.valueOf(2)))));
			answer = answer.min(BigInteger.valueOf((long) 2e9).multiply(vertexCount.add(BigInteger.ONE)).multiply(vertexCount.subtract(BigInteger.valueOf(2))).add(cost.multiply(vertexCount.subtract(BigInteger.valueOf(3)))));
		}
		out.print(answer.divide(BigInteger.valueOf((long) 1e9)));
		out.print('.');
		for (int i = 8; i >= 0; i--)
			out.print(answer.divide(BigInteger.TEN.pow(i)).mod(BigInteger.TEN));
		out.printLine();
	}
}
