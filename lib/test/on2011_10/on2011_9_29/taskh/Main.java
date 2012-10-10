package on2011_10.on2011_9_29.taskh;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_29.taskh.TaskH",
			"SINGLE",
			"60 60 60 60/__2/__07/:01/:00/__07/:01/:30/__2/__07/:00/:00/__07/:02/:10;;07/:01/:00/__07/:02/:00/__07/:00/:00/__07/:03/:00::60 120 120 60/__2/__07/:01/:00/__07/:01/:30/__2/__07/:00/:00/__07/:02/:10;;07/:02/:00/__07/:06/:00/__07/:00/:00/__07/:04/:00/__::60 130 130 60/__2/__07/:01/:00/__07/:01/:30/__2/__07/:00/:00/__07/:01/:10;;07/:03/:20/__07/:04/:20/__07/:00/:00/__07/:01/:10/__"))
		{
			Assert.fail();
		}
	}
}
