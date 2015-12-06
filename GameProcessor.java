/**
 * This class is responsible for processing the Impossible Matches game.
 * - A frustrating game where the computer always wins. The computer and human
 * player take matches from a stack in turn. The one who takes the last matches
 * wins the game.
 * 
 * @author Benjamin Penz, Hochschule Emden/Leer
 * @version 1.0 - 08. 11. 2015
 */
public class GameProcessor {
	
	// Handles input/output operations
	private TextManager tm = new TextManager();
	
	// Initial number of matches on the stack
	private final int MAX_MATCHES = 12;
	
	// Temporary number of matches on the stack
	private int matchesNumber = MAX_MATCHES;
	
	// Minimum and maximum number of matches the players can take from the stack per turn 
	private final int MIN_GETTABLE_MATCHES = 1;
	private final int MAX_GETTABLE_MATCHES = 3;
	
	/**
	 * Initializes the game and starts it.
	 */
	public void startGame() {
		showInfo();
		String playerName = askForPlayerName();
		runGameLoop(playerName);
	}
	
	/**
	 * Starts the main game loop. After each match the game can be repeated or quit.
	 *
	 * @param playerName the name of the player
	 */
	private void runGameLoop(String playerName) {
		
		// Start the game once and once again?
		boolean repeatGameFlag = true;
		
		while (repeatGameFlag) {
			
			// The actual game
			startTurns(playerName);
			
			// Should the game be repeated? J / j for yes, N / n for no
			String repeatAnswer = "";
			
			boolean answerInvalidFlag = true;
			
			// Ask the player whether he wants to repeat the game as long as the answer is not valid
			while (answerInvalidFlag) {
				tm.write("Nochmal spielen (J/N)?");
				repeatAnswer = tm.readText();
				if (repeatAnswer.equals("j") || repeatAnswer.equals("J") || repeatAnswer.equals("n") || repeatAnswer.equals("N")) {
					answerInvalidFlag = false;
				} else {			
					answerInvalidFlag = true;
				}
			}
			
			// Repeat the game if the answer is J / j, else quit
			if (repeatAnswer.equals("j") || repeatAnswer.equals("J")) {
				tm.write("Neue Partie wird gestartet...");
				matchesNumber = MAX_MATCHES;
			} else {
				tm.write("Spiel wird beendet...");
				repeatGameFlag = false;
			}
		}
	}
	
	/**
	 * Shows information about the game.
	 */
	private void showInfo() {
		tm.write("Impossible Matches 1.0 - \u00a9 2015 by Benjamin Penz");
		tm.write("Spieler und Computergegner nehmen abwechselnd 1 - 3 Streichhölzer von einem Stapel.");
		tm.write("Wer das letzte Streichholz genommen hat, hat gewonnen. Viel Spaß!");
		tm.write("");
	}
	
	/**
	 * Receives the name from the player.
	 * 
	 * @return name of the player
	 */
	private String askForPlayerName() {
		tm.write("Bitte gib deinen Namen ein: ");
		String playerName = tm.readText();
		return playerName;
	}
	
	/**
	 * Runs the game turns as long as no one has won.
	 * 
	 * @param playerName the name of the player
	 */
	private void startTurns(String playerName) {
		
		boolean gameRunFlag = true;
		int playerTakenMatches = 0;
		
		while (gameRunFlag) {
			showMatchesNumber();
			playerTakenMatches = processPlayerTurn(playerName);
			gameRunFlag = processComputerTurn(playerTakenMatches);
		}
	}
	
	/**
	 * Informs the player of the number of matches on the stack.
	 */
	private void showMatchesNumber() {
		tm.write("Es befinden sich noch " + matchesNumber + " Streichhölzer auf dem Stapel.");
	}
	
	/**
	 * Performs the players' turn. The player takes 1 - 3 matches from the stack.
	 * 
	 * @param playerName name of the player
	 * @return matches the player has taken
	 */
	private int processPlayerTurn(String playerName) {
		
		// Is the number of matches outside the allowed range?
		boolean invalidNumberFlag = true;
		
		int playerTakenMatches = 0;
		
		// The player is asked to give the number of matches he wants to take from the stack, as long
		// as the number is invalid.
		while (invalidNumberFlag) {
			tm.write(playerName + " ist am Zug. Wieviele Streichhölzer (" + MIN_GETTABLE_MATCHES 
					+ " - " + MAX_GETTABLE_MATCHES + ") möchtest du nehmen?");
			try {
				playerTakenMatches = tm.readNumber();
			} catch (NumberFormatException ne) {
				tm.write("Das ist keine Nummer.");
				playerTakenMatches = MAX_GETTABLE_MATCHES + 1;
			}
			
			if (playerTakenMatches >= MIN_GETTABLE_MATCHES && playerTakenMatches <= MAX_GETTABLE_MATCHES) {
				invalidNumberFlag = false;
				matchesNumber -= playerTakenMatches;
			} else {
				tm.write("Ungültige Eingabe!");
			}
		}
		tm.write(playerName + " hat " + playerTakenMatches + " Streichhölzer genommen.");
		return playerTakenMatches;
	}
	
	/**
	 * Processes the computers' turn. The computer takes a number of matches that completes
	 * the players' previously taken number of matches to a total of 4. If there are less than
	 * 4 matches on the stack, the computer takes them all and wins the game.
	 * 
	 * @param playerTakenMatches
	 * @return flag that indicates whether the game still runs
	 */
	private boolean processComputerTurn(int playerTakenMatches) {
		
		int takenMatches = 0;
		
		if (matchesNumber <= 3) {
			takenMatches = matchesNumber;
		} else {
			takenMatches = 4 - playerTakenMatches;
		}
	
		boolean gameRunFlag = true;
		matchesNumber -= takenMatches;
		tm.write("Der Computer hat " + takenMatches + " genommen.");
		
		if (matchesNumber <= 0) {
			tm.write("Der Computer hat gewonnen! Das tut mir aber leid...");
			gameRunFlag = false;
		}
		return gameRunFlag;
	}
}
