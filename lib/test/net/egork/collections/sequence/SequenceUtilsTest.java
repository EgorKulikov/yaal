package net.egork.collections.sequence;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SequenceUtilsTest {
	@Test
	public void testSort() throws Exception {
		int[] array = {5, 2, 5, 3, 7, 1};
		Sequence<Integer> sorted = SequenceUtils.sort(ArrayWrapper.wrap(array));
		Assert.assertEquals((int)sorted.get(0), 1);
		Assert.assertEquals((int)sorted.get(1), 2);
		Assert.assertEquals((int)sorted.get(2), 3);
		Assert.assertEquals((int)sorted.get(3), 5);
		Assert.assertEquals((int)sorted.get(4), 5);
		Assert.assertEquals((int)sorted.get(5), 7);
	}
}
