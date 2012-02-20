package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BytelandIce {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int singlePrice = in.readInt();
		int doublePrice = in.readInt();
		int triplePrice = in.readInt();
		int[] strawberry = new int[count];
		int[] chocolate = new int[count];
		IOUtils.readIntArrays(in, strawberry, chocolate);
		int standaloneStrawberry = 0;
		int standaloneChocolate = 0;
		int mixedStrawberry = 0;
		int mixedChocolate = 0;
		for (int i = 0; i < count; i++) {
			if (strawberry[i] == 0 || chocolate[i] == 0) {
				standaloneStrawberry += strawberry[i];
				standaloneChocolate += chocolate[i];
			} else {
				mixedStrawberry += strawberry[i];
				mixedChocolate += chocolate[i];
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int singleStrawberry = 0; singleStrawberry < 3; singleStrawberry++) {
			for (int singleChocolate = 0; singleChocolate < 3; singleChocolate++) {
				for (int doubleStrawberry = 0; doubleStrawberry < 3; doubleStrawberry++) {
					for (int doubleChocolate = 0; doubleChocolate < 3; doubleChocolate++) {
						for (int doubleMixed = 0; doubleMixed < 3; doubleMixed++) {
							for (int tripleMixedStrawberry = 0; tripleMixedStrawberry < 3; tripleMixedStrawberry++) {
								for (int tripleMixedChocolate = 0; tripleMixedChocolate < 3; tripleMixedChocolate++) {
									int remainingMixedStrawberry = mixedStrawberry - doubleMixed - 2 * tripleMixedStrawberry - tripleMixedChocolate;
									if (remainingMixedStrawberry < 0)
										continue;
									int remainingStrawberry = remainingMixedStrawberry + standaloneStrawberry - singleStrawberry - 2 * doubleStrawberry;
									if (remainingStrawberry < 0 || remainingStrawberry % 3 != 0)
										continue;
									int remainingMixedChocolate = mixedChocolate - doubleMixed - tripleMixedStrawberry - tripleMixedChocolate * 2;
									if (remainingMixedChocolate < 0)
										continue;
									int remainingChocolate = remainingMixedChocolate + standaloneChocolate - singleChocolate - 2 * doubleChocolate;
									if (remainingChocolate < 0 || remainingChocolate % 3 != 0)
										continue;
									answer = Math.min(answer, singleStrawberry * singlePrice + singleChocolate * singlePrice + doubleStrawberry * doublePrice + doubleChocolate * doublePrice +
										doubleMixed * doublePrice + tripleMixedStrawberry * triplePrice + tripleMixedChocolate * triplePrice +
										remainingChocolate / 3 * triplePrice + remainingStrawberry / 3 * triplePrice);
								}
							}
						}
					}
				}
			}
		}
		out.printLine(answer);
	}
}
