package on2011_10.on2011_9_26.pointerasing;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int[] getOutcomes(int[] y)",
			"on2011_10.on2011_9_26.pointerasing.PointErasing",
			"{ 1, 2, 1, 1, 0, 4, 3 };;{4, 6 }::{ 0, 0, 4, 4, 8, 8, 4, 4 };;{6 }::{ 522 };;{1 }::{ 19, 19, 19, 19, 19, 19 };;{6 }::{ 0, 1, 2, 3, 4 };;{2 }::{ 7, 8, 8, 7, 6, 7, 9, 3, 5, 0 };;{3, 4, 5 }::{ 3, 2, 3, 3, 4, 3, 4, 3, 3, 1, 5, 3 };;{4, 5, 6, 7, 9 }::{ 5, 5, 4, 4, 5, 8, 5, 5, 5, 5, 5, 9, 2, 0, 9, 4, 5, 5, 3, 4, 4, 4, 4, 7, 4 };;{6, 7, 8, 10, 11, 12, 13, 15, 16, 17 }::{0, 0, 1, -1, 0, 0};;{6}"))
		{
			Assert.fail();
		}
	}
}
