package net.egork;

public class NoRepeatPlaylist {
	private static final long MOD = (long)1e9 + 7;

	public int numPlaylists(int N, int M, int P) {
		long[][] result = new long[P + 1][N + 1];
		result[0][0] = 1;
		for (int i = 0; i < P; i++) {
			for (int j = 0; j <= N; j++) {
				result[i + 1][j] = (result[i + 1][j] + Math.max(j - M, 0) * result[i][j]) % MOD;
				if (j != N)
					result[i + 1][j + 1] = (result[i + 1][j + 1] + (N - j) * result[i][j]) % MOD;
			}
		}
		return (int) result[P][N];
	}

	public static void main(String[] args) {
		NoRepeatPlaylist solver = new NoRepeatPlaylist();
		for (int i = 1; i <= 6; i++) {
			for (int j = i; j <= 8; j++) {
				for (int k = 0; k <= i; k++) {
					if (solver.numPlaylists(i, k, j) != stupid(i, j, k))
						throw new RuntimeException();
				}
			}
		}
	}

	private static int stupid(int count, int total, int delta) {
		int[] song = new int[total];
		return go(song, 0, count, delta);
	}

	private static int go(int[] song, int step, int count, int delta) {
		if (step == song.length) {
			int used = 0;
			for (int i : song)
				used |= 1 << i;
			if (used + 1 != (1 << count))
				return 0;
			return 1;
		}
		int result = 0;
		for (int i = 0; i < count; i++) {
			boolean good = true;
			for (int j = Math.max(step - delta, 0); j < step; j++) {
				if (song[j] == i) {
					good = false;
					break;
				}
			}
			if (good) {
				song[step] = i;
				result += go(song, step + 1, count, delta);
			}
		}
		return result;
	}
}

