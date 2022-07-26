import edu.uc3m.game.GameBoardGUI;
import java.util.Locale;
import java.util.Random;

public class Galaga {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en"));

		// Creates the game board
		GameBoardGUI board = new GameBoardGUI(Constants.BOARD_X, Constants.BOARD_Y);
		for (int x = 0; x < Constants.BOARD_X; x++) {
			for (int y = 0; y < Constants.BOARD_Y; y++) {
				board.gb_setSquareImage(x, y, "fondoEstrellao.png");
				board.gb_setGridColor(0, 0, 0);
			}
		}

		// Creates the player and its lifes
		Player player = new Player(Constants.PLAYER_X, Constants.PLAYER_Y, Constants.PLAYER_ID, "player.png");
		board.gb_addSprite(player.getid(), player.getimage(), true);
		board.gb_moveSpriteCoord(player.getid(), player.getX(), player.getY());
		board.gb_setSpriteVisible(player.getid(), true);
		
		board.gb_addSprite(Constants.LIFE_1, "player.png", true);
		board.gb_moveSpriteCoord(Constants.LIFE_1, 5, 210);
		board.gb_setSpriteVisible(Constants.LIFE_1, true);
		
		board.gb_addSprite(Constants.LIFE_2, "player.png", true);
		board.gb_moveSpriteCoord(Constants.LIFE_2, 15, 210);
		board.gb_setSpriteVisible(Constants.LIFE_2, true);

		// Creates a bunch of invisible torpedoes, then when fired, they will be visible
		Torpedo torpedoes[] = new Torpedo[Constants.MAX_TORPEDOES];
		for (int t = 0; t < torpedoes.length; t++) {
			torpedoes[t] = new Torpedo(Constants.FIRST_TORPEDO_ID + t, "torpedo100.png");
			board.gb_addSprite(torpedoes[t].getId(), torpedoes[t].getImage(), true);
			board.gb_setSpriteVisible(torpedoes[t].getId(), false);
		}

		// The same, creates a lot of explosions to show them when needed
		Explosion explosions[] = new Explosion[Constants.MAX_EXPLOSIONS];
		for (int x = 0; x < explosions.length; x++) {
			explosions[x] = new Explosion(Constants.FIRST_EXPLOSION_ID + x, "explosion2.png");
			board.gb_addSprite(explosions[x].getId(), explosions[x].getImage(), true);
			board.gb_setSpriteVisible(explosions[x].getId(), false);
		}

		// Creates the first level
		Level level = new Level();
		Enemies enemies[] = level.nextLevel();

		// Adds the enemies to the board
		for (int e = 0; e < enemies.length; e++) {
			board.gb_addSprite(enemies[e].getId(), enemies[e].getImage(), true);
			board.gb_moveSpriteCoord(enemies[e].getId(), enemies[e].getX(), enemies[e].getY());
			board.gb_setSpriteVisible(enemies[e].getId(), true);
		}

		// Configures the board and makes it visible
		board.gb_setPortraitPlayer("galagaLogo.jpg");
		board.gb_setTextLevel("Level");
		board.gb_setTextAbility1("Torpedoes");
		board.gb_setTextAbility2("Dead enemies");
		board.gb_setTextPointsDown("Score");
		board.gb_setValueAbility1(0);
		board.gb_setValueAbility2(0);
		board.gb_setValueLevel(level.getCurrentLevel());
		board.gb_setValuePointsDown(0);
		board.setVisible(true);

		// Game loop
		String lastAction;
		int iTorpedo = 0;
		int iExplosion = 0;
		int deadEnemies = 0;
		int numTorpedoes = 0;
		boolean gameover = false;
		//Here I control the movement of the spaceship thanks to the keyboard
		do {
			lastAction = board.gb_getLastAction();
			if (lastAction.equals("right")) {
				int currentX = player.getX();
				currentX += 5;
				player.setX(currentX);
			} else if (lastAction.equals("left")) {
				int currentX = player.getX();
				currentX -= 5;
				player.setX(currentX);
			} else if (lastAction.equals("up")) {
				int currentY = player.getY();
				currentY -= 5;
				player.setY(currentY);
			} else if (lastAction.equals("down")) {
				int currentY = player.getY();
				currentY += 5;
				player.setY(currentY);
			} else if (lastAction.equals("space")) {
				torpedoes[iTorpedo].launchPlayer(player.getX(), player.getY());
				board.gb_setSpriteImage(torpedoes[iTorpedo].getId(), torpedoes[iTorpedo].getImage());
				board.gb_setSpriteVisible(torpedoes[iTorpedo].getId(), true);
				board.gb_moveSpriteCoord(torpedoes[iTorpedo].getId(), torpedoes[iTorpedo].getX(), torpedoes[iTorpedo].getY());

				numTorpedoes++;
				board.gb_setValueAbility1(numTorpedoes);

				if (++iTorpedo >= Constants.MAX_TORPEDOES) {
					iTorpedo = 0;
				}
			} else if (lastAction.equals("exit game")) {
				gameover = true;
			} else if (lastAction.startsWith("new game")) {
				// Resets all entities of the game
				// Resets the player
				player = new Player(Constants.PLAYER_X, Constants.PLAYER_Y, Constants.PLAYER_ID, "player.png");
				board.gb_addSprite(Constants.LIFE_1, "player.png", true);
				board.gb_moveSpriteCoord(Constants.LIFE_1, 5, 210);
				board.gb_setSpriteVisible(Constants.LIFE_1, true);
				board.gb_addSprite(Constants.LIFE_2, "player.png", true);
				board.gb_moveSpriteCoord(Constants.LIFE_2, 15, 210);
				board.gb_setSpriteVisible(Constants.LIFE_2, true);
				
				// Resets all the torpedoes
				iTorpedo = 0;
				for (int t = 0; t < torpedoes.length; t++) {
					torpedoes[t] = new Torpedo(Constants.FIRST_TORPEDO_ID + t, "torpedo100.png");
					board.gb_addSprite(torpedoes[t].getId(), torpedoes[t].getImage(), true);
					board.gb_setSpriteVisible(torpedoes[t].getId(), false);
				}
				
				// Resets all explosions
				for (int x = 0; x < explosions.length; x++) {
					explosions[x] = new Explosion(Constants.FIRST_EXPLOSION_ID + x, "explosion2.png");
					board.gb_addSprite(explosions[x].getId(), explosions[x].getImage(), true);
					board.gb_setSpriteVisible(explosions[x].getId(), false);
				}
				iExplosion = 0;
				
				// Starts from level 1
				level = new Level();
				enemies = level.nextLevel();
				board.gb_setValueLevel(level.getCurrentLevel());
				for (int e = 0; e < enemies.length; e++) {
					board.gb_addSprite(enemies[e].getId(), enemies[e].getImage(), true);
					board.gb_moveSpriteCoord(enemies[e].getId(), enemies[e].getX(), enemies[e].getY());
					board.gb_setSpriteVisible(enemies[e].getId(), true);
				}
				
				// Resets the score
				board.gb_setValuePointsDown(0);
				
				// Resets the stats
				numTorpedoes = 0;
				board.gb_setValueAbility1(0);
				deadEnemies = 0;
				board.gb_setValueAbility2(0);
				
				// Sets the name of the player
				String newgame[] = lastAction.split(" ");
				String name = "";
				for (int i = 2; i < newgame.length; i++) {
					name += newgame[i] + " ";
				}
				board.gb_setTextPlayerName(name);
				board.gb_println("New game as " + name);
			}
			
			// Loops over enemies to check if any one shoots
			Random rnd = new Random();
			for (int e = 0; e < enemies.length; e++) {
				if (enemies[e].isVisible() && enemies[e].getStatus() == Enemies.Status.ATTACKING) {
					int value = rnd.nextInt(100);
					//Shot probability of the enemies
					if (value < 2) {
						torpedoes[iTorpedo].launchEnemy(enemies[e].getX(), enemies[e].getY());
						board.gb_setSpriteImage(torpedoes[iTorpedo].getId(), torpedoes[iTorpedo].getImage());
						board.gb_setSpriteVisible(torpedoes[iTorpedo].getId(), true);
						board.gb_moveSpriteCoord(torpedoes[iTorpedo].getId(), torpedoes[iTorpedo].getX(), torpedoes[iTorpedo].getY());

						if (++iTorpedo >= Constants.MAX_TORPEDOES) {
							iTorpedo = 0;
						}
					}
				}
			}

			// Updates the position of the player
			board.gb_moveSpriteCoord(player.getid(), player.getX(), player.getY());

			// Animates the formation, also animates the enemy
			level.updateFormation(enemies);
			level.updateEntrying(enemies);
			level.updateAttacking(enemies);

			// Loops the enemies to check for player collision and draw them on board
			deadEnemies = 0;
			for (int e = 0; e < enemies.length; e++) {
				if (enemies[e].isVisible()) {
					
					if (checkCollision(enemies[e], player)) {
						gameover = true;
					}

					// Updates the enemy image
					board.gb_setSpriteImage(enemies[e].getId(), enemies[e].getImage());

					// Puts the enemy on its position
					board.gb_moveSpriteCoord(enemies[e].getId(), enemies[e].getX(), enemies[e].getY());
				} else {
					deadEnemies++;
				}
			}

			if (deadEnemies == enemies.length) {
				board.gb_showMessageDialog("The next level is going to be charged");
				enemies = level.nextLevel();

				// Adds the enemies to the board
				for (int e = 0; e < enemies.length; e++) {
					board.gb_addSprite(enemies[e].getId(), enemies[e].getImage(), true);
					board.gb_moveSpriteCoord(enemies[e].getId(), enemies[e].getX(), enemies[e].getY());
					board.gb_setSpriteVisible(enemies[e].getId(), true);
				}

				board.gb_setValueLevel(level.getCurrentLevel());
			}

			// Loops the torpedoes to update its positions and draw them on screen
			for (int t = 0; t < torpedoes.length; t++) {
				if (torpedoes[t].updatePosition()) {
					// Checks if the torpedo is from the player to iterate over the enemies
					if (torpedoes[t].getSpeed() < 0) {
						// Checks the enemies for collisions
						for (int e = 0; e < enemies.length; e++) {
							if (enemies[e].isVisible()) {
								if (checkCollision(enemies[e], torpedoes[t])) {
									// "Destroy" both the enemy and the torpedo
									if (enemies[e].setVisible(false)) {
										board.gb_setSpriteVisible(enemies[e].getId(), false);
										Level.emptyAttackingSlot(enemies[e]);
									}
	
									torpedoes[t].setVisible(false);
									board.gb_setSpriteVisible(torpedoes[t].getId(), false);
	
									// The score is updated
									player.addScore(enemies[e].getScore());
									board.gb_println("The enemy " + e + " has been destroyed +" + enemies[e].getScore()
											+ " Points.");
									board.gb_setValuePointsDown(player.getScore());
	
									deadEnemies++;
									board.gb_setValueAbility2(deadEnemies);
	
									// A explosion is created in the position of the enemy
									explosions[iExplosion].startExplosion(enemies[e].getX(), enemies[e].getY(), false);
									board.gb_moveSpriteCoord(explosions[iExplosion].getId(), explosions[iExplosion].getX(),
											explosions[iExplosion].getY());
									board.gb_setSpriteVisible(explosions[iExplosion].getId(), true);
									iExplosion++;
									if (++iExplosion >= Constants.MAX_EXPLOSIONS) {
										iExplosion = 0;
									}
								}
							}
						}
					// Else, the torpedo is from an enemy
					} else {
						if (checkCollision(player, torpedoes[t])) {
							torpedoes[t].setVisible(false);
							board.gb_setSpriteVisible(torpedoes[t].getId(), false);
							
							// A explosion is created in the position of the player
							explosions[iExplosion].startExplosion(player.getX(), player.getY(), true);
							board.gb_moveSpriteCoord(explosions[iExplosion].getId(), explosions[iExplosion].getX(),
									explosions[iExplosion].getY());
							board.gb_setSpriteVisible(explosions[iExplosion].getId(), true);
							iExplosion++;
							if (++iExplosion >= Constants.MAX_EXPLOSIONS) {
								iExplosion = 0;
							}

							// if player have more lifes
							if (player.loseLife()) {
								board.gb_showMessageDialog("One life is lost.");
								switch (player.getLifes()) {
								case 2:
									board.gb_setSpriteVisible(Constants.LIFE_2, false);
									break;
								case 1:
									board.gb_setSpriteVisible(Constants.LIFE_1, false);
									break;
								}
							    board.gb_animateDamage();
							    player.setX(Constants.PLAYER_X);
							    player.setY(Constants.PLAYER_Y);
							} else {
								gameover = true;
							}
						}
					}

					board.gb_moveSpriteCoord(torpedoes[t].getId(), torpedoes[t].getX(), torpedoes[t].getY());
				} else {
					board.gb_setSpriteVisible(torpedoes[t].getId(), false);
				}
			}

			// Loops explosions
			for (int x = 0; x < explosions.length; x++) {
				if (explosions[x].isVisible()) {
					if (explosions[x].updateAnimation()) {
						// Updates the explosion image
						board.gb_setSpriteImage(explosions[x].getId(), explosions[x].getImage());
					} else {
						board.gb_setSpriteVisible(explosions[x].getId(), false);
					}
				}
			}

			// Sleep to make the game slower
			try {
				Thread.sleep(Constants.GAME_DELAY);
			} catch (InterruptedException ie) {
				// Nothing to do
			}
			
			if (gameover) {
				board.gb_showMessageDialog("Game Over");
			}

		} while (!gameover);
	}

	public static boolean checkCollision(Enemies e, Torpedo t) {
		return t.getSpeed() < 0 && ((t.getX() - 2) < (e.getX() - 6) + 12 &&
									(t.getX() - 2) + 3 > (e.getX() - 6) &&
									(t.getY() - 4) < (e.getY() - 6) + 12 &&
									(t.getY() - 4) + 5 > (e.getY() - 6));
	}

	public static boolean checkCollision(Enemies e, Player p) {
		return (p.getX() - 6) < (e.getX() - 6) + 12 &&
			   (p.getX() - 6) + 3 > (e.getX() - 6) &&
			   (p.getY() - 6) < (e.getY() - 6) + 12 &&
			   (p.getY() - 6) + 5 > (e.getY() - 6);
	}
	
	public static boolean checkCollision(Player p, Torpedo t) {
		return t.getSpeed() > 0 && ((p.getX() - 6) < (t.getX() - 2) + 3 &&
									(p.getX() - 6) + 12 > (t.getX() - 2) &&
									(p.getY() - 6) < (t.getY() - 4) + 5 &&
									(p.getY() - 6) + 12 > (t.getY() - 4));
	}
}