import edu.uc3m.game.GameBoardGUI;

import java.util.Random;

public class Level {
	private int fX, fY;
	private int fDirection;
	private int enemyAnimate;
	private int level;
	private int enemyId;
	private int maxAtkEnemies;
	private static int currentAtkEnemies;

	public Level() {
		this.level = 0;
		this.enemyId = Constants.FIRST_ENEMY_ID;
		this.maxAtkEnemies = 0;
		currentAtkEnemies = 0;
	}

	public Enemies[] nextLevel() {
		Enemies enemies[];

		switch (this.level) {
		case 0:
			enemies = createLevel1();
			break;
		case 1:
			enemies = createLevel2();
			break;
		case 2:
			enemies = createLevel3();
			break;
		case 3:
			enemies = createLevel4();
			break;
		default:
			enemies = null;
			System.out.println("");
			break;
		}
		
		enemies = createLevel4();

		// Establish the initial values for the formation
		this.fX = 0;
		this.fY = 0;
		// Variable that controls the direction and length of enemies'steps
		this.fDirection = 1;
		this.enemyAnimate = Constants.ENEMY_ANIMATION;

		// Updates the level and returns the created enemies
		this.level++;
		return enemies;
	}

	private Enemies[] createLevel1() {
		Enemies enemies[] = new Enemies[24];
		int enemyIndex = 0;

		// Creates the first row enemies
		enemies[enemyIndex++] = new Commander(30, 50, 70, 30, this.enemyId++, 0, true);
		enemies[enemyIndex++] = new Commander(140, 50, 50, 30, this.enemyId++, 0, false);

		// Creates the second row enemies
		for (int x = 4, i = 0; x < 9; x++, i -= 3) {
			enemies[enemyIndex++] = new Goei(5, 150, x * 10, 40, this.enemyId++, i, true);
		}
		for (int x = 9, i = 0; x < 14; x++, i -= 3) {
			enemies[enemyIndex++] = new Goei(165, 150, x * 10, 40, this.enemyId++, i, false);
		}

		// Creates the third row enemies
		for (int x = 3, i = 0; x < 8; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(5, 120, x * 10, 50, this.enemyId++, i, true);
		}
		for (int x = 8, i = 0; x < 15; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(165, 120, x * 10, 50, this.enemyId++, i, false);
		}

		this.maxAtkEnemies = 4;
		return enemies;
	}

	private Enemies[] createLevel2() {
		Enemies enemies[] = new Enemies[25];
		int enemyIndex = 0;
		// Creates the first row enemies
		Commander commander = new Commander(30, 50, 70, 30, this.enemyId++, 0, true);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		commander = new Commander(80, 50, 50, 30, this.enemyId++, 0, false);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		commander = new Commander(140,50, 80, 30, this.enemyId++, 0, true);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		// Creates the second row enemies
		for (int x = 4, i = 0; x < 9; x++, i -= 3) {
			enemies[enemyIndex++] = new Goei(5, 120, x * 10, 40, this.enemyId++, i, true);
		}
		for (int x = 9, i = 0; x < 14; x++, i -= 3) {
			enemies[enemyIndex++] = new Goei(165, 120, x * 10, 40, this.enemyId++, i, false);
		}

		// Creates the third row enemies
		for (int x = 3, i = 0; x < 8; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(5, 120, x * 10, 50, this.enemyId++, i, true);
		}
		for (int x = 8, i = 0; x < 15; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(165, 120, x * 10, 50, this.enemyId++, i, false);
		}

		this.maxAtkEnemies = 5;
		return enemies;
	}

	private Enemies[] createLevel3() {
		Enemies enemies[] = new Enemies[30];
		int enemyIndex = 0;

		// Creates the first row enemies
		Commander commander = new Commander(30, 50, 40, 50, this.enemyId++, 0, true);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		commander = new Commander(80, 50, 50, 50, this.enemyId++, 0, false);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		commander = new Commander(140, 50, 70, 50, this.enemyId++, 0, true);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		commander = new Commander(50, 50, 80, 50, this.enemyId++, 0, true);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		commander = new Commander(90, 50, 100, 50, this.enemyId++, 0, false);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		commander = new Commander(100, 50, 110, 50, this.enemyId++, 0, true);
		commander.canChangeOfColour();
		enemies[enemyIndex++] = commander;

		// Creates the second row of enemies
		enemies[enemyIndex++] = new Goei(165, 120, 50, 40, this.enemyId++, -2, false);
		enemies[enemyIndex++] = new Goei(165, 120, 60, 40, this.enemyId++, -5, false);
		enemies[enemyIndex++] = new Goei(165, 120, 70, 40, this.enemyId++, -8, false);
		enemies[enemyIndex++] = new Goei(165, 120, 80, 40, this.enemyId++, -11, false);
		enemies[enemyIndex++] = new Goei(165, 120, 90, 40, this.enemyId++, -14, false);
		enemies[enemyIndex++] = new Goei(165, 120, 100, 40, this.enemyId++,-17, false);

		// Creates the third row of enemies
		enemies[enemyIndex++] = new Goei(5, 120, 50, 50, this.enemyId++, -2, true);
		enemies[enemyIndex++] = new Goei(5, 120, 60, 50, this.enemyId++, -5, true);
		enemies[enemyIndex++] = new Goei(5, 120, 70, 50, this.enemyId++, -8, true);
		enemies[enemyIndex++] = new Goei(5, 120, 80, 50, this.enemyId++, -11, true);
		enemies[enemyIndex++] = new Goei(5, 120, 90, 50, this.enemyId++, -14, true);
		enemies[enemyIndex++] = new Goei(5, 120, 100, 50, this.enemyId++, -17, true);

		// Creates the fourth row
		for (int x = 3, i = 0; x < 8; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(5, 120, x * 10, 50, this.enemyId++, i, true);
		}
		for (int x = 8, i = 0; x < 15; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(160, 120, x * 10, 50, this.enemyId++, i, false);
		}
		
		this.maxAtkEnemies = 7;
		return enemies;
	}

	public Enemies[] createLevel4() {
		Enemies enemies[] = new Enemies[44];
		int enemyIndex = 0;

		// Creates the first row enemies
		for (int x = 4, i = 0; x < 9; x++, i -= 3) {
			enemies[enemyIndex++] = new Commander(30, 50, x * 10, 40, this.enemyId++, i, true);
		}
		for (int x = 9, i = 0; x < 14; x++, i -= 3) {
			enemies[enemyIndex++] = new Commander(140, 50, x * 10, 40, this.enemyId++, i, false);
		}

		// Creates the second row enemies
		for (int x = 4, i = 0; x < 9; x++, i -= 3) {
			enemies[enemyIndex++] = new Goei(5, 150, x * 10, 50, this.enemyId++, i, true);
		}
		for (int x = 9, i = 0; x < 14; x++, i -= 3) {
			enemies[enemyIndex++] = new Goei(165, 150, x * 10, 50, this.enemyId++, i, false);
		}
		// Creates the third row enemies
		for (int x = 3, i = 0; x < 15; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(0, 120, x * 10, 60, this.enemyId++, i, true);
		}
		// Creates the fourth row of enemies
		for (int x = 3, i = 0; x < 8; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(0, 120, x * 10, 70, this.enemyId++, i, true);
		}
		for (int x = 8, i = 0; x < 15; x++, i -= 3) {
			enemies[enemyIndex++] = new Zako(160, 120, x * 10, 70, this.enemyId++, i, false);
		}
		
		this.maxAtkEnemies = 9;
		return enemies;
	}

	public int getCurrentLevel() {
		return this.level;
	}
	
	public void updateAttacking(Enemies enemies[]) {		
		for (int e = 0; e < enemies.length; e++) {
			if (currentAtkEnemies < this.maxAtkEnemies && enemies[e].isVisible() && enemies[e].getStatus() == Enemies.Status.FORMATING) {
				Random rnd = new Random();
				int value = rnd.nextInt(100);
				if (value < 3) {
					enemies[e].setStatus(Enemies.Status.ATTACKING);
					currentAtkEnemies++;
					
					// If the enemy is a Commander, then finds 2 goei to protect it and assigns the same movements paths
					if (enemies[e].getEnemyType() == Enemies.EnemyType.COMMANDER) {
						int goeiIndexes[] = this.find2Goei(enemies);
						int paths[] = enemies[e].getAtkPaths();
						if (goeiIndexes[0] >= 0) {
							enemies[goeiIndexes[0]].setStatus(Enemies.Status.ATTACKING);
							currentAtkEnemies++;
							enemies[goeiIndexes[0]].setAtkPaths(paths);
							enemies[goeiIndexes[0]].setX(enemies[e].getX() - 10);
							enemies[goeiIndexes[0]].setY(enemies[e].getY());
						}
						if (goeiIndexes[1] >= 0) {
							enemies[goeiIndexes[1]].setStatus(Enemies.Status.ATTACKING);
							currentAtkEnemies++;
							enemies[goeiIndexes[1]].setAtkPaths(paths);
							enemies[goeiIndexes[1]].setX(enemies[e].getX() + 10);
							enemies[goeiIndexes[1]].setY(enemies[e].getY());
						}
					}
				}
			}
			
			if (enemies[e].getStatus() == Enemies.Status.ATTACKING) {
				enemies[e].moveAttacking();		
			}
		}
	}

	// This function updates the enemies that are entrying to formating position
	public void updateEntrying(Enemies enemies[]) {
		for (int e = 0; e < enemies.length; e++) {
			if (enemies[e].getStatus() == Enemies.Status.ENTRYING) {
				enemies[e].moveEntrying();
			}
		}
	}
	
	public static void emptyAttackingSlot(Enemies enemy) {
		// If the enemy is attacking, then its "slot" is empty
		if (enemy.getStatus() == Enemies.Status.ATTACKING) {
			currentAtkEnemies--;
			if (currentAtkEnemies < 0) {
				currentAtkEnemies = 0;
			}
		}
	}

	public void updateFormation(Enemies enemies[]) {
		// Checks if the direction shall change
		fX += fDirection;
		if (Math.abs(fX) > Constants.MAX_X_FORMATION) {
			if (fDirection == 1) {
				fDirection = -1;
			} else {
				fDirection = 1;
			}

			// When the direction changes, the formation will move down instead
			fY++;
		}

		// Checks if the enemies shall change its image
		if (--enemyAnimate <= 0) {
			enemies[0].animate();
		}

		// Loops over the enemies to move them
		for (int e = 0; e < enemies.length; e++) {
			if (enemies[e].getStatus() == Enemies.Status.FORMATING) {
				enemies[e].setY(enemies[e].getFormatingY() + fY);
				enemies[e].setX(enemies[e].getFormatingX() + fX);
			}
		}

		// Checks if an animation or a return to top was performed to reset the control
		// variables
		if (enemyAnimate <= 0) {
			enemyAnimate = Constants.ENEMY_ANIMATION;
		}
		if (fY >= Constants.MAX_Y_FORMATION) {
			fY = 0;
		}
	}
	
	private int[] find2Goei(Enemies enemies[]) {
		int ret[] = new int[2];
		int num = 0;
		
		ret[0] = -1;
		ret[1] = -1;
		
		// Loops over the enemies searching for goeis formatting, when 2 are found, the loops ends
		for (int i = 0; i < enemies.length && num < 2; i++) {
			if (enemies[i].getEnemyType() == Enemies.EnemyType.GOEI && enemies[i].getStatus() == Enemies.Status.FORMATING) {
				ret[num] = i;
				num++;
			}			
		}
		
		return ret;
	}
}