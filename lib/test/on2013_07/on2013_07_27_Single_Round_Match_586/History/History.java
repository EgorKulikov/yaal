package on2013_07.on2013_07_27_Single_Round_Match_586.History;



import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.string.StringUtils;

public class History {
    public String verifyClaims(String[] dynasties, String[] battles, String[] queries) {
		int count = dynasties.length;
		int[][] years = new int[count][];
		for (int i = 0; i < count; i++)
			years[i] = MiscUtils.getIntArray(dynasties[i]);
		int[][] difference = new int[count][count];
		ArrayUtils.fill(difference, Integer.MAX_VALUE / 2);
		for (int i = 0; i < count; i++)
			difference[i][i] = 0;
		for (String battle : StringUtils.unite(battles).split(" ")) {
			int first = battle.charAt(0) - 'A';
			int firstKing = battle.charAt(1) - '0';
			int second = battle.charAt(3) - 'A';
			int secondKing = battle.charAt(4) - '0';
			difference[first][second] = Math.min(difference[first][second], years[second][secondKing + 1] - years[first][firstKing] - 1);
			difference[second][first] = Math.min(difference[second][first], years[first][firstKing + 1] - years[second][secondKing] - 1);
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					difference[j][k] = Math.min(difference[j][k], difference[j][i] + difference[i][k]);
			}
		}
		StringBuilder result = new StringBuilder(queries.length);
		for (String query : queries) {
			int first = query.charAt(0) - 'A';
			int firstKing = query.charAt(1) - '0';
			int second = query.charAt(3) - 'A';
			int secondKing = query.charAt(4) - '0';
			if (difference[first][second] >= years[second][secondKing] - years[first][firstKing + 1] + 1 &&
				difference[second][first] >= years[first][firstKing] - years[second][secondKing + 1] + 1)
			{
				result.append('Y');
			} else
				result.append('N');
		}
		return result.toString();
    }
}
