import java.io.*;

/**
 * @author Egor Kulikov (egor@egork.net)
 * Actual solution is at the bottom
 */
class Test implements Serializable {
	private static final long serialVersionUID = 42L;

	private final String input;
	private final String expectedOutput;

	public Test(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	public String getInput() {
		return input;
	}

	public String getExpectedOutput() {
		return expectedOutput;
	}

	@Override
	public String toString() {
		return input + "\n";
	}
}

class Tests {
	public static final Test[] TESTS = {
		new Test("Gambino, Christine*35*0,0.1,-0.35,1.3,-0.55,0,.05,0 \nMartim, Gustavo*29*0,0.1,-0.5,1.5,-1,0,0.075,0 \nGuzman, Isabel*15*0,0,-0.05,-0.1,0.1,0.25,0.5,0.7,0.4,0.2,-0.01,-0.25,-0.5,-0.25", "Gambino, Christine \n35 \nTriage PASS \n---\nMartim, Gustavo \n29 \nTriage PASS \n---\nGuzman, Isabel \n15 \nTriage NO PASS"),
	};
}

