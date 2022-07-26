
public class Player {
	private int x;
	private int y;
	private int id;
	private String image;
	private int lives;
	private final int max_lives = 3;
	private int scores;
	private int shoots;
	private int hits;

// Player constructor
	public Player(int x, int y, int id, String image) {
		setX(x);
		setY(y);
		setid(id);
		setimage(image);
		this.lives = this.max_lives;
		this.scores = 0;
		this.shoots = 0;
		this.hits = 0;
	}

	public void setX(int x) {
		if (x >= 5 && x <= 165) {
			this.x = x;
		}
	}

	public void addScore(int increment) {
		this.scores += increment;

	}

	public int getScore() {
		return this.scores;
	}

	public void addHits() {
		this.hits++;
	}

	public void setY(int y) {
		if (y >= 1 && y <= 210) {
			this.y = y;
		}
	}

	public void setid(int id) {
		this.id = id;
	}

	public void setimage(String image) {
		this.image = image;
	}

	// Returns true if there are lives
	public boolean loseLife() {
		return (--this.lives > 0);
	}

	public int getLifes() {
		return this.lives;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getid() {
		return this.id;
	}

	public String getimage() {
		return this.image;
	}
}