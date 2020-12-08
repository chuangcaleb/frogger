package frogger.util;

import frogger.constant.FilePath;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class HiscoreWriter {

	private int levelNum;

	public HiscoreWriter(int levelNum) {
		this.levelNum = levelNum;
	}

	public void write(String name, int score) {

		// add newline to the entry
		String entry = name + "," + String.format("%05d",score) + System.getProperty("line.separator");

		try { // writes at end of file
			File scoreFile = new File(FilePath.HISCORES_DIR + "hiscores_lv" + levelNum);

			// make high score directory if it doesn't exist
			new File(FilePath.HISCORES_DIR).mkdirs();

			// write to File
			FileUtils.writeStringToFile(scoreFile, entry, StandardCharsets.UTF_8, true);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
