import java.util.ArrayList;

public class Puzzle {
    private char[][] soup;
	private int width, height;
	private ArrayList<Word> words;
	private ArrayList<int[]> positionsOccupied;

	public Puzzle(int width, int height) {
		this.width = width;
		this.height = height;
		this.words = new ArrayList<Word>();
		this.soup = new char[height][width];
		this.positionsOccupied = new ArrayList<int[]>();
	}

	/*
	 * Getters and Setters
	 */

	public void setSoup(ArrayList<String> puzzleLines) {
		for (int i = 0; i < puzzleLines.size(); i++) {
			for (int j = 0; j < puzzleLines.get(i).length(); j++) {
				this.soup[i][j] = puzzleLines.get(i).charAt(j);
			}
		}
	}

	public void setWords(ArrayList<String> wordLines) {
		for (String word : wordLines) {
			Word newWord = new Word(word);
			words.add(newWord);
		}
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	public char[][] getSoup() {
		return soup;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/*
	 * Methods
	 */

	public int[] getIncrements(Direction direction) {
		int incX = 0;
		int incY = 0;

		switch (direction) {
			case DOWN:
				incY = 1;
				break;
			case UP:
				incY = -1;
				break;
			case RIGHT:
				incX = 1;
				break;
			case LEFT:
				incX = -1;
				break;
			case DOWN_RIGHT:
				incX = 1;
				incY = 1;
				break;
			case UP_LEFT:
				incX = -1;
				incY = -1;
				break;
			case UP_RIGHT:
				incX = 1;
				incY = -1;
				break;
			case DOWN_LEFT:
				incX = -1;
				incY = 1;
				break;
		}

		return new int[]{incX, incY};
	}

	public boolean isPositionOccupied(int xPos, int yPos) {
		for (int[] position : positionsOccupied) {
			if (position[0] == xPos && position[1] == yPos) {
				return true;
			}
		}
		return false;
	}

	 
	private boolean solverHead(Word word, int xPos, int yPos, Direction direction, boolean generating) {
		int[] increments = getIncrements(direction);

		ArrayList<int[]> positions = new ArrayList<int[]>();
		positions.add(new int[] {xPos, yPos});

		for (int i = 0; i < word.getWord().length(); i++) {
			if (xPos < 0 || xPos >= width || yPos < 0 || yPos >= height) {
				return false;
			}

			if (!generating && soup[yPos][xPos] != word.getWord().toUpperCase().charAt(i)) {
				return false;
			}

			if (isPositionOccupied(xPos, yPos)) {
				return false;
			}

			xPos += increments[0];
			yPos += increments[1];

			if (i != word.getWord().length() - 1) {
				positions.add(new int[] {xPos, yPos});
			}
		}

		for (int[] position : positions) {
			positionsOccupied.add(position);

			if (generating) {
				soup[position[1]][position[0]] = word.getWord().toUpperCase().charAt(positions.indexOf(position));
			}
		}

		return true;
	}

	public void solve() {
		ArrayList<Word> sortedWords = new ArrayList<Word>(words);
        sortedWords.sort((a, b) -> b.getWord().length() - a.getWord().length());

		for (Word word : sortedWords) {
			char firstChar = word.getWord().toUpperCase().charAt(0);
			boolean found = false;
			
			for (int i = 0; i < height; i++) {
				if (found) break;

				for (int j = 0; j < width; j++) {
					if (found) break;

					if (soup[i][j] == firstChar) {
						for (Direction direction : Direction.values()) {
							if (solverHead(word, j, i, direction, false)) {
								word.setFound(true);
								word.setStartPosition(j + 1, i + 1);
								word.setDirection(direction);
								found = true;
								break;
							}
						}
					}
				}
			}
		}
	}

	public void generateFromWords() {
		ArrayList<Word> sortedWords = new ArrayList<Word>(words);
		sortedWords.sort((a, b) -> b.getWord().length() - a.getWord().length());
		
		for (Word word : sortedWords) {
			boolean placed = false;

			while (!placed) {
				int xPos = (int) (Math.random() * width);
				int yPos = (int) (Math.random() * height);
				Direction direction = Direction.values()[(int) (Math.random() * 8)];

				if (solverHead(word, xPos, yPos, direction, true)) {
					word.setStartPosition(xPos + 1, yPos + 1);
					word.setDirection(direction);
					word.setFound(true);
					placed = true;
				}
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (soup[i][j] == 0) {
					soup[i][j] = (char) (Math.random() * 26 + 'A');
				}
			}
		}

	}

	public String solutionToString() {
		StringBuilder sb = new StringBuilder();

		for (Word word : words) {
			sb.append(word.toString());
			sb.append("\n");
		}

		sb.append("\n");

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				boolean found = false;

				for (int[] position : positionsOccupied) {
					if (position[0] == j && position[1] == i) {
						sb.append(soup[i][j]);
						found = true;
						break;
					}
				}

				if (!found) {
					sb.append(".");
				}
			}

			if (i != height - 1) sb.append("\n");
		}

		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sb.append(soup[i][j]);
			}
			
			if (i != height - 1) sb.append("\n");
		}

		return sb.toString();
	}

	/*
	 * Validations
	 */

	public boolean isInputValid(ArrayList<String> puzzleLines) {
		return isPuzzleValid(puzzleLines) && isWordsValid(words);
	}

	public static boolean isPuzzleValid(ArrayList<String> puzzleLines) {
		for (String puzzleLine : puzzleLines) {
			if (puzzleLine.isEmpty() || !puzzleLine.equals(puzzleLine.toUpperCase())) {
				return false;
			}

			if (puzzleLine.length() != puzzleLines.get(0).length() || puzzleLine.length() > 40) {
				return false;
			}
		}

		return true;
	}

	private static boolean isWordsValid(ArrayList<Word> words) {
		for (Word word : words) {
			if (word.getWord().isEmpty() || word.getWord().equals(word.getWord().toUpperCase()) || !word.getWord().matches("[a-zA-Z]+")) {
				return false;
			}
		}

		return true;
	}
}