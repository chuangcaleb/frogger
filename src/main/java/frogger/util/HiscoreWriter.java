package frogger.util;

import frogger.constant.FilePath;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

/**
 * {@code HiscoreWriter} writes entries into a hiscore file.
 */
public class HiscoreWriter {

	/** The current level number. */
	private final int levelNum;

	/**
	 * Constructor to initialize the hiscore file to write to.
	 *
	 * @param levelNum the level number of the hiscore file to write to
	 */
	public HiscoreWriter(int levelNum) {
		this.levelNum = levelNum;
	}

	/**
	 * Writes an entry into the hiscore file.
	 *
	 * @param name 	the nickname of the player
	 * @param score	the score that the player achieved for this level number
	 */
	public void write(String name, int score) {

		// Format the entry String
		String entry = name + "," + String.format("%05d",score) + System.getProperty("line.separator");

		try { // Writes at end of file

			File scoreFile = new File(FilePath.HISCORES_DIR + "hiscores_lv" + levelNum);

			// Make high score directory if it doesn't already exist
			new File(FilePath.HISCORES_DIR).mkdirs();

			// Write to scoreFile
			FileUtils.writeStringToFile(scoreFile, entry, StandardCharsets.UTF_8, true);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
