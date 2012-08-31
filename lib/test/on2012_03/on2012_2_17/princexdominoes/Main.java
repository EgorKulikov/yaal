package on2012_03.on2012_2_17.princexdominoes;

import net.egork.chelper.tester.TopCoderTester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!TopCoderTester.test("int play(String[] dominoes)",
			"on2012_03.on2012_2_17.princexdominoes.PrinceXDominoes",
			".A.,..B,A.A;;4;;true::A..,.B.,..C;;-1;;true::ZZ,ZZ;;104;;true::THIS.SRM,IS.SPONS,ORED.BY.,CITI.THA,NKS.FOR.,PARTICIP,ATING.DO,LPHINIGL;;612;;true::A.A.A.A.A.,DOLPHINIGL,A.Z.X.D.F.,IVANMETELS,T.W.W.X.M.,RNGRNGRNGR,W.S.C.E.F.,FUSHARFUSH,A.B.C.D.E.,CITICITICI;;-1;;true"))
		{
			Assert.fail();
		}
	}
}
