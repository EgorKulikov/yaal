import java.util.*;

public class Amazons {
/* Head ends here */
static void nextMove(String player, int x, int y, String[] board){
		char[][] charBoard = new char[board.length][];
		for (int i = 0; i < charBoard.length; i++)
			charBoard[i] = board[i].toCharArray();
		Move move = solve(player.charAt(0), x, y, charBoard, 3);
		System.out.println(move);
		System.err.println(move.delta + " " + move.points);
	}

	private static Move solve(char player, int x, int y, char[][] board, int depth) {
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (board[i][j] == player) {
					board[i][j] = '-';
					for (int dx = -1; dx <= 1; dx++) {
						for (int dy = -1; dy <= 1; dy++) {
							if (dx == 0 && dy == 0)
								continue;
							int row = i + dx;
							int column = j + dy;
							if (!isValid(row, column, x, y, board))
								continue;
							while (isValid(row, column, x, y, board)) {
								for (int ddx = -1; ddx <= 1; ddx++) {
									for (int ddy = -1; ddy <= 1; ddy++) {
										if (ddx == 0 && ddy == 0)
											continue;
										int nextRow = row + ddx;
										int nextColumn = column + ddy;
										if (!isValid(nextRow, nextColumn, x, y, board))
											continue;
										while (isValid(nextRow, nextColumn, x, y, board)) {
											board[i][j] = player;
											Move candidate = new Move(i, j, row, column, nextRow, nextColumn, board, 0);
											board[i][j] = '-';
											moves.add(candidate);
											nextRow += ddx;
											nextColumn += ddy;
										}
									}
								}
								row += dx;
								column += dy;
							}
						}
					}
					board[i][j] = player;
				}
			}
		}
		Collections.sort(moves);
		if (depth >= 2) {
			int qty = 10;
			for (int i = 0; i < qty && i < moves.size(); i++) {
				Move move = moves.get(i);
				moves.set(i, new Move(move.fromRow, move.fromColumn, move.toRow, move.toColumn, move.shootRow, move.shootColumn, board, 1));
			}
			Collections.sort(moves.subList(0, Math.min(qty, moves.size())));
		}
		if (depth >= 3) {
			int qty = 4;
			for (int i = 0; i < qty && i < moves.size(); i++) {
				Move move = moves.get(i);
				moves.set(i, new Move(move.fromRow, move.fromColumn, move.toRow, move.toColumn, move.shootRow, move.shootColumn, board, 2));
			}
			Collections.sort(moves.subList(0, Math.min(qty, moves.size())));
		}
		if (depth >= 4) {
			int qty = 2;
			for (int i = 0; i < qty && i < moves.size(); i++) {
				Move move = moves.get(i);
				moves.set(i, new Move(move.fromRow, move.fromColumn, move.toRow, move.toColumn, move.shootRow, move.shootColumn, board, 3));
			}
			Collections.sort(moves.subList(0, Math.min(qty, moves.size())));
		}
		if (moves.isEmpty())
			return new Move();
		else
			return moves.get(0);
	}

	private static char getChar(int i) {
		return (char) ('a' + i);
	}

	static boolean isValid(int row, int column, int x, int y, char[][] board) {
		return !(row < 0 || row >= x || column < 0 || column >= y || board[row][column] != '-');
	}

	static int[] rowQueue = new int[100];
	static int[] columnQueue = new int[100];

	static class Move implements Comparable<Move> {
		final int fromRow;
		final int fromColumn;
		final int toRow;
		final int toColumn;
		final int shootRow;
		final int shootColumn;
		private final int x;
		private final int y;

		int delta;
		int shootDistance;
		long points;

		Move(int fromRow, int fromColumn, int toRow, int toColumn, int shootRow, int shootColumn, char[][] board, int depth) {
			this.fromRow = fromRow;
			this.fromColumn = fromColumn;
			this.toRow = toRow;
			this.toColumn = toColumn;
			this.shootRow = shootRow;
			this.shootColumn = shootColumn;
			char color = board[fromRow][fromColumn];
			board[fromRow][fromColumn] = '-';
			board[toRow][toColumn] = color;
			x = board.length;
			y = board[fromRow].length;
			board[shootRow][shootColumn] = '.';
			if (depth == 0) {
				for (int i = 0; i < x; i++) {
					for (int j = 0; j < y; j++) {
						if (board[i][j] == '-' || Character.isUpperCase(board[i][j])) {
							rowQueue[0] = i;
							columnQueue[0] = j;
							int size = 1;
							board[i][j] = convert(board[i][j]);
							boolean seeFriend = board[i][j] == Character.toLowerCase(color);
							boolean seeFoe = !seeFriend && Character.isLetter(board[i][j]);
							for (int k = 0; k < size; k++) {
								int row = rowQueue[k];
								int column = columnQueue[k];
								for (int dx = -1; dx <= 1; dx++) {
									for (int dy = -1; dy <= 1; dy++) {
										if (dx == 0 && dy == 0)
											continue;
										if (isValid(row + dx, column + dy, x, y, board)) {
											rowQueue[size] = row + dx;
											columnQueue[size++] = column + dy;
											board[row + dx][column + dy] = ',';
										} else {
											int rr = row + dx;
											int cc = column + dy;
											if (rr >= 0 && rr < x && cc >= 0 && cc < y && Character.isUpperCase(board[rr][cc])) {
												if (board[rr][cc] == color)
													seeFriend = true;
												else
													seeFoe = true;
												rowQueue[size] = row + dx;
												columnQueue[size++] = column + dy;
												board[row + dx][column + dy] = convert(board[row + dx][column + dy]);
											}
										}
									}
								}
							}
							if (seeFriend && !seeFoe) {
								if (size <= 5)
									delta -= 100 - size;
//								else
//									delta += size;
							} else if (seeFoe && !seeFriend) {
								if (size <= 5)
									delta += 100 - size;
//								else
//									delta -= size;
							}
						}
					}
				}
				for (int i = 0; i < x; i++) {
					for (int j = 0; j < y; j++) {
						if (board[i][j] == ',')
							board[i][j] = '-';
						else if (Character.isLetter(board[i][j]))
							board[i][j] = Character.toUpperCase(board[i][j]);
					}
				}
				for (int i = 0; i < x; i++) {
					for (int j = 0; j < y; j++) {
						if (!Character.isLetter(board[i][j]))
							continue;
						int free = 0;
						for (int dx = -1; dx <= 1; dx++) {
							for (int dy = -1; dy <= 1; dy++) {
								if (dx == 0 && dy == 0)
									continue;
								int rr = i + dx;
								int cc = j + dy;
								while (isValid(rr, cc, x, y, board)) {
									free++;
									rr += dx;
									cc += dy;
								}
							}
						}
						if (board[i][j] == color) {
							points -= 1L << (40 - free);
						} else {
							points += 1L << (40 - free);
						}
					}
				}
				shootDistance = Math.max(Math.abs(toRow - shootRow), Math.abs(toColumn - shootColumn));
			} else {
				Move opponent = solve((char) ('W' + 'B' - color), x, y, board, depth);
				delta = -opponent.delta;
				shootDistance = -opponent.shootDistance;
				points = -opponent.points;
			}
			board[shootRow][shootColumn] = '-';
			board[toRow][toColumn] = '-';
			board[fromRow][fromColumn] = color;
		}

		public Move() {
			fromRow = -1;
			toRow = -1;
			fromColumn = -1;
			toColumn = -1;
			shootRow = -1;
			shootColumn = -1;
			x = -1;
			y = -1;
			delta = Integer.MIN_VALUE / 10;
		}

		private char convert(char c) {
			if (c == '-')
				return ',';
			return Character.toLowerCase(c);
		}

		public int compareTo(Move o) {
			if (delta != o.delta)
				return o.delta - delta;
			if (points < o.points)
				return 1;
			if (points > o.points)
				return -1;
			return o.shootDistance - shootDistance;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(getChar(fromColumn));
			builder.append(x - fromRow - 1);
			builder.append(' ');
			builder.append(getChar(toColumn));
			builder.append(x - toRow - 1);
			builder.append(' ');
			builder.append(getChar(shootColumn));
			builder.append(x - shootRow - 1);
			return builder.toString();
		}
	}

/* Tail starts here */
public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String player = in.next();
		int x = in.nextInt();
		int y = in.nextInt();

		String board[] = new String[x];

		for(int i = 0; i < x; i++) {
			board[i] = in.next();
		}

		nextMove(player, x, y, board);
	}
}
