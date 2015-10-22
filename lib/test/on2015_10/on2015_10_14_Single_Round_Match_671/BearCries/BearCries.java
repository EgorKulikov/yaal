package on2015_10.on2015_10_14_Single_Round_Match_671.BearCries;



import net.egork.misc.ArrayUtils;

public class BearCries {
    private static final long MOD = (long) (1e9 + 7);
    char[] message;
    long[][][] ways;
    
    public int count(String message) {
        this.message = message.toCharArray();
        ways = new long[message.length() + 1][message.length() + 1][message.length() + 1];
        ArrayUtils.fill(ways, -1);
		return (int)go(0, 0, 0);
    }
    
    long go(int position, int good, int bad) {
        if (good < 0 || bad < 0) {
            return 0;
        }
        if (ways[position][good][bad] != -1) {
            return ways[position][good][bad];
        }
        if (position == message.length) {
            return ways[position][good][bad] = good == 0 && bad == 0 ? 1 : 0;
        }
        if (message[position] == '_') {
            return ways[position][good][bad] = (good * go(position + 1, good, bad) + bad * go(position + 1, good + 1, bad - 1)) % MOD;
        }
        return ways[position][good][bad] = (go(position + 1, good, bad + 1) + good * go(position + 1, good - 1, bad)) % MOD;
    }
}
