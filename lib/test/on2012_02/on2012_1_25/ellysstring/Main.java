package on2012_02.on2012_1_25.ellysstring;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int theMin(String[] s, String[] t)",
			"on2012_02.on2012_1_25.ellysstring.EllysString",
			"usagi;;unagi;;1;;true::unagi;;nugai;;2;;true::ellys,string;;e,ll,ysst,ring;;0;;true::fox;;xfo;;2;;true::salmon;;camlon;;2;;true::boajxuidva;;jcayduvida;;6;;true::vykdnujyezbcbmnatipqfuxqmgkvtn;;qokbqyujeqcbwsinatupqfoegmvkdz;;22;;true"))
		{
			Assert.fail();
		}
	}
}
