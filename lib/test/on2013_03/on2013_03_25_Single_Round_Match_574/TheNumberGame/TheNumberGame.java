package on2013_03.on2013_03_25_Single_Round_Match_574.TheNumberGame;



import net.egork.string.StringUtils;

public class TheNumberGame {
    public String determineOutcome(int A, int B) {
		if (Integer.toString(A).contains(Integer.toString(B)) || Integer.toString(A).contains(StringUtils.reverse(Integer.toString(B))))
			return "Manao wins";
		return "Manao loses";
    }
}
