/**
 * This class launches the Impossible Matches game.
 * - A frustrating game where the computer always wins.
 * 
 * @author Benjamin Penz, Hochschule Emden/Leer
 * @version 1.0 - 08. 11. 2015
 */
public class ImpossibleMatchesLauncher {
	
	/**
	 * Entry point to the Impossible Matches game.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		GameProcessor gp = new GameProcessor();
		gp.startGame();
	}
	
}

