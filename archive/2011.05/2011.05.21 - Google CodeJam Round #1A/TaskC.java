import net.egork.collections.comparators.ReverseComparator;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		System.err.println(testNumber);
		int inHand = in.readInt();
		int[] cardPoints = new int[inHand];
		int[] scorePoints = new int[inHand];
		int[] turnPoints = new int[inHand];
		IOUtils.readIntArrays(in, cardPoints, scorePoints, turnPoints);
		int inDeck = in.readInt();
		cardPoints = Arrays.copyOf(cardPoints, inHand + inDeck);
		scorePoints = Arrays.copyOf(scorePoints, inHand + inDeck);
		turnPoints = Arrays.copyOf(turnPoints, inHand + inDeck);
		for (int i = 0; i < inDeck; i++) {
			cardPoints[inHand + i] = in.readInt();
			scorePoints[inHand + i] = in.readInt();
			turnPoints[inHand + i] = in.readInt();
		}
		int time = 1;
		int index = inHand;
		int score = 0;
		PriorityQueue<Integer> noCards = new PriorityQueue<Integer>(inDeck + inHand, new ReverseComparator<Integer>());
		PriorityQueue<Integer> oneCard = new PriorityQueue<Integer>(inDeck + inHand, new ReverseComparator<Integer>());
		PriorityQueue<Integer> twoCard = new PriorityQueue<Integer>(inDeck + inHand, new ReverseComparator<Integer>());
		for (int i = 0; i < index; i++) {
			if (turnPoints[i] != 0) {
				time += turnPoints[i] - 1;
				index = Math.min(index + cardPoints[i], inDeck + inHand);
				score += scorePoints[i];
			} else {
				if (cardPoints[i] == 0)
					noCards.add(scorePoints[i]);
				else if (cardPoints[i] == 1)
					oneCard.add(scorePoints[i]);
				else
					twoCard.add(scorePoints[i]);
			}
		}
		int maxScore = tryTwo(inDeck + inHand, cardPoints, scorePoints, turnPoints, time, index, score, noCards, oneCard, twoCard);
		out.println("Case #" + testNumber + ": " + maxScore);
	}

	private int tryTwo(int cardCount, int[] cardPoints, int[] scorePoints, int[] turnPoints, int time, int index, int score, PriorityQueue<Integer> noCards, PriorityQueue<Integer> oneCard, PriorityQueue<Integer> twoCard) {
		int maxScore = score;
		while (time != 0) {
			int currentScore = score;
			List<Integer> top = new ArrayList<Integer>();
			for (int i = 0; i < time && !noCards.isEmpty(); i++) {
				int s = noCards.poll();
				currentScore += s;
				top.add(s);
			}
			noCards.addAll(top);
			maxScore = Math.max(maxScore, currentScore);
			if (twoCard.isEmpty() || !oneCard.isEmpty() && oneCard.peek() > twoCard.peek())
				maxScore = Math.max(maxScore, tryOne(cardCount, cardPoints, scorePoints, turnPoints, time, index, score, new PriorityQueue<Integer>(noCards), new PriorityQueue<Integer>(oneCard)));
			if (twoCard.isEmpty())
				break;
			int s = twoCard.poll();
			score += s;
			time--;
			maxScore = Math.max(maxScore, score);
			if (time == 0)
				break;
			int maxIndex = index + 2;
			while (index < cardCount && index < maxIndex) {
				if (turnPoints[index] == 0) {
					if (cardPoints[index] == 0)
						noCards.add(scorePoints[index]);
					else if (cardPoints[index] == 1)
						oneCard.add(scorePoints[index]);
					else
						twoCard.add(scorePoints[index]);
					index++;
				} else {
					score += scorePoints[index];
					time += turnPoints[index] - 1;
					maxIndex += cardPoints[index];
					index++;
				}
			}
		}
		return maxScore;
	}

	private int tryOne(int cardCount, int[] cardPoints, int[] scorePoints, int[] turnPoints, int time, int index, int score, PriorityQueue<Integer> noCards, PriorityQueue<Integer> oneCard) {
		PriorityQueue<Integer> twoCard = new PriorityQueue<Integer>(cardCount, new ReverseComparator<Integer>());
		int maxScore = score;
		while (time != 0) {
			int currentScore = score;
			List<Integer> top = new ArrayList<Integer>();
			for (int i = 0; i < time && !noCards.isEmpty(); i++) {
				int s = noCards.poll();
				currentScore += s;
				top.add(s);
			}
			noCards.addAll(top);
			maxScore = Math.max(maxScore, currentScore);
			if (oneCard.isEmpty())
				break;
			int s = oneCard.poll();
			score += s;
			time--;
			maxScore = Math.max(maxScore, score);
			if (time == 0)
				break;
			int maxIndex = index + 1;
			while (index < cardCount && index < maxIndex) {
				if (turnPoints[index] == 0) {
					if (cardPoints[index] == 0)
						noCards.add(scorePoints[index]);
					else if (cardPoints[index] == 1)
						oneCard.add(scorePoints[index]);
					else
						twoCard.add(scorePoints[index]);
					index++;
				} else {
					score += scorePoints[index];
					time += turnPoints[index] - 1;
					maxIndex += cardPoints[index];
					index++;
				}
			}
			if (!twoCard.isEmpty())
				return tryTwo(cardCount, cardPoints, scorePoints, turnPoints, time, index, score, noCards, oneCard, twoCard);
		}
		return maxScore;
	}
}

