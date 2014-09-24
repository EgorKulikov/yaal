package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.string.SimpleStringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String sample = in.readString();
		String target = in.readString();
		SimpleStringHash hash = new SimpleStringHash(target);
		int as = ArrayUtils.count(sample.toCharArray(), 'a');
		int bs = sample.length() - as;
		if (as == 0 || bs == 0) {
			if (target.length() % (as + bs) != 0) {
				out.printLine("NIE");
				return;
			}
			int length = target.length() / (as + bs);
			for (int i = length; i < target.length(); i += length) {
				if (hash.hash(0, length) != hash.hash(i, i + length)) {
					out.printLine("NIE");
					return;
				}
			}
			if (as == 0) {
				out.printLine('-');
			}
			out.printLine(target.substring(0, length));
			if (bs == 0) {
				out.printLine('-');
			}
			return;
		}
		int ansALength = -1;
		int ansBLength = -1;
		if (as < bs) {
			for (int i = 1; i * bs < target.length(); i++) {
				int bLength = i;
				int aLength = (target.length() - bLength * bs) / as;
				if (ok(hash, as, bs, aLength, bLength, sample)) {
					ansALength = aLength;
					ansBLength = bLength;
					break;
				}
			}
		} else {
			for (int i = 1; i * as < target.length(); i++) {
				int aLength = i;
				int bLength = (target.length() - aLength * as) / bs;
				if (ok(hash, as, bs, aLength, bLength, sample)) {
					ansALength = aLength;
					ansBLength = bLength;
					break;
				}
			}
		}
		if (ansALength == -1) {
			out.printLine("NIE");
			return;
		}
		String aAnswer = null;
		String bAnswer = null;
		int at = 0;
		for (int i = 0; i < sample.length(); i++) {
			if (sample.charAt(i) == 'a') {
				aAnswer = target.substring(at, at + ansALength);
				at += ansALength;
			} else {
				bAnswer = target.substring(at, at + ansBLength);
				at += ansBLength;
			}
		}
		out.printLine(aAnswer);
		out.printLine(bAnswer);
    }

	private boolean ok(SimpleStringHash hash, int as, int bs, int aLength, int bLength, String sample) {
		if (as * aLength + bs * bLength != hash.length()) {
			return false;
		}
		long aHash = 0;
		long bHash = 0;
		boolean aSet = false;
		boolean bSet = false;
		int at = 0;
		for (int i = 0; i < sample.length(); i++) {
			if (sample.charAt(i) == 'a') {
				if (aSet) {
					if (hash.hash(at, at + aLength) != aHash) {
						return false;
					}
				} else {
					aSet = true;
					aHash = hash.hash(at, at + aLength);
				}
				at += aLength;
			} else {
				if (bSet) {
					if (hash.hash(at, at + bLength) != bHash) {
						return false;
					}
				} else {
					bSet = true;
					bHash = hash.hash(at, at + bLength);
				}
				at += bLength;
			}
		}
		return true;
	}
}
