import java.util.Scanner;

// Aenderung

/**
 * The text manager performs basic input/output console operations.
 * 
 * @author Benjamin Penz, Hochschule Emden/Leer
 * @version 1.0 - 08. 11. 2015
 */
public class TextManager {
	
	Scanner scanner = null;
	
	public TextManager() {
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Writes given text to the console.
	 * 
	 * @param text
	 */
	public void write(String text) {
		System.out.println(text);
	}
	
	/**
	 * Reads users' text input.
	 * 
	 * @return
	 */
	public String readText() {
		String text = "";
		text = scanner.nextLine();
		return text;
	}
	
	/**
	 * Reads users' input of a number.
	 * 
	 * @return
	 */
	public int readNumber() {
		int number = 0;
		number = Integer.parseInt(readText());
		return number;
	}
}
