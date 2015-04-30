package net.egork;

public class ProblemSets {
    public long maxSets(long E, long EM, long M, long MH, long H) {
		return Math.min(Math.min(E + EM, EM + M + MH), Math.min(Math.min(MH + H, (E + EM + M + MH) / 2),
            Math.min((EM + M + MH + H) / 2, (E + EM + M + MH + H) / 3)));
    }
}
