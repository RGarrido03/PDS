public enum Direction {
	UP("Up"),
	DOWN("Down"),
	LEFT("Left"),
	RIGHT("Right"),
	UP_LEFT("Up Left"),
	UP_RIGHT("Up Right"),
	DOWN_LEFT("Down Left"),
	DOWN_RIGHT("Down Right");

	private final String name;

	private Direction(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}