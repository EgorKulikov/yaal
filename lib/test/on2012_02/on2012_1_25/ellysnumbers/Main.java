package on2012_02.on2012_1_25.ellysnumbers;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("long getSubsets(long n, String[] special)",
			"on2012_02.on2012_1_25.ellysnumbers.EllysNumbers",
			"12;;4 2 5 6 3;;1;;true::42;;1 2 3 4 5 6 7 13 14 21 42;;10;;true::1337;;1 13 42 666 2674;;0;;true::1073741824;;1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 1;;0;;true::7420738134810;;\"435 625199055 4199 33263 17 222870 284870433 72093\",\n" +
				" \"2379 7 11 31 247110827 23 1771 81809 851968487 13 \", \n" +
				" \"476379795 1001 5 435274114 38264554 7429 239906525\",\n" +
				" \" 3 227183706 887045414 606786670 3795 797605175 29\",\n" +
				" \" 30 747186719 19 2 562347843 74 2294 588002688 743\",\n" +
				" \"6429 703 591740547 36657492 37 444178205 1002001 2\",\n" +
				" \"17626404\";;110;;true"))
		{
			Assert.fail();
		}
	}
}
