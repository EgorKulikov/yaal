package on2012_06.on2012_5_13.taskc;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long money = in.readLong();
		long fee = in.readLong();
		int count = in.readInt();
		long[] price = new long[count];
		long[] stale = new long[count];
		IOUtils.readLongArrays(in, price, stale);
		long currentDeliveryCost = fee;
		long lastStale = -1;
		long[] antiStale = stale.clone();
		for (int i = 0; i < count; i++)
			antiStale[i] = -antiStale[i];
		Integer[] order = ListUtils.order(Array.wrap(price), Array.wrap(antiStale));
		long answer = 0;
		for (int i : order) {
			if (lastStale >= stale[i])
				continue;
			long p1 = currentDeliveryCost;
			long p2 = price[i];
			long q = stale[i] - lastStale;
			long c = lastStale + 1;
			if (p1 / p2 >= c) {
				if (money / q >= price[i]) {
					long alpha1 = money / (currentDeliveryCost + q * price[i]);
					long beta1 = q * alpha1;
					if (alpha1 * p1 + beta1 * p2 > money)
						throw new RuntimeException();
					if (beta1 > q * alpha1)
						throw new RuntimeException();
					answer = Math.max(answer, c * alpha1 + beta1);
					long alpha2 = alpha1 + 1;
					if (alpha2 * currentDeliveryCost <= money) {
						long remainingMoney = money - alpha2 * p1;
						long beta2 = remainingMoney / p2;
						if (alpha2 * p1 + beta2 * p2 > money)
							throw new RuntimeException();
						if (beta2 > q * alpha2)
							throw new RuntimeException();
						answer = Math.max(answer, c * alpha2 + beta2);
					}
				} else {
					long alpha = 1;
					long beta = (money - currentDeliveryCost) / p2;
					if (alpha * p1 + beta * p2 > money)
						throw new RuntimeException();
					if (beta > q * alpha)
						throw new RuntimeException();
					answer = Math.max(answer, c * alpha + beta);
				}
			} else {
				long alpha = money / p1;
				long remainingMoney = money - alpha * p1;
				long beta = remainingMoney / p2;
				if (remainingMoney / q >= alpha)
					beta = Math.min(beta, alpha * q);
				if (alpha * p1 + beta * p2 > money)
					throw new RuntimeException();
				if (beta > q * alpha)
					throw new RuntimeException();
				answer = Math.max(answer, c * alpha + beta);
			}
			if (money / price[i] >= stale[i] - lastStale) {
				currentDeliveryCost += (stale[i] - lastStale) * price[i];
				lastStale = stale[i];
			} else
				break;
			if (currentDeliveryCost > money)
				break;
		}
		out.printLine("Case #" + testNumber + ":", answer);
	}
}
