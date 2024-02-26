public class Word {
    private String word;
    private Boolean isFound;
    private Direction direction;
    private int xPosition, yPosition, length;

    public Word(String word) {
        this.word = word;
        this.isFound = false;
        this.direction = null;
        this.xPosition = -1;
        this.yPosition = -1;
        this.length = word.length();
    }

    /*
     * Getters and Setters
     */

    public void setStartPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void setXStartPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setYStartPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setFound(Boolean isFound) {
        this.isFound = isFound;
    }

    public int[] getStartPosition() {
        return new int[] { this.xPosition, this.yPosition };
    }

    public int getXStartPosition() {
        return this.xPosition;
    }

    public int getYStartPosition() {
        return this.yPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getWord() {
        return word;
    }

    public int getLength() {
        return length;
    }

    public Boolean isFound() {
        return isFound;
    }

    /*
     * Methods
     */

    public String toString() {
        return String.format("%-15s\t%d\t%d,%d\t%s", word, length, yPosition, xPosition, direction);
    }

}
