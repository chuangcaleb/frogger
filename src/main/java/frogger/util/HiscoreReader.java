package frogger.util;

import frogger.constant.FilePath;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code HiscoreReader} reads the top 10 entries from a given hiscore file.
 * <p>
 * Has a dependency on {@link org.apache.commons.io.FileUtils}.
 * </p>
 */
public class HiscoreReader {

	/** The String of nicknames. */
	private String nicknames;
	/** The String of hiscores. */
	private String hiscores;
	/** The String of ranks. */
	private String ranks;

	//  ######################################## GENERAL ########################################

	/**
	 * Constructor that loads and arranges the data from a given file.
	 *
	 * @param levelNum the level number to determine which high score file to read from
	 */
	public HiscoreReader(int levelNum) {
		List<String> unsortedLines = read(levelNum);
		List<String> sortedLines = sort(unsortedLines);
		build(sortedLines);
	}

	/**
	 * Reads the data from the corresponding hiscore file into a List.
	 * <p>
	 * Has a dependency on {@link org.apache.commons.io.FileUtils}.
	 * </p>
	 *
	 * @param levelNum the level number to determine which high score file to read from
	 * @return the list of every entry in the file.
	 */
	public List<String> read(int levelNum) {

		List<String> data = null;

		try {
			// Reads the score file for data
			File scoreFile = new File(FilePath.HISCORES_DIR + "hiscores_lv" + levelNum);
			data = FileUtils.readLines(scoreFile, StandardCharsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * Sorts the List of entries in ascending order according to their score value.
	 *
	 * @param lines the unsorted list of entries
	 * @return 		the sorted list of entries
	 */
	private List<String> sort(List<String> lines) {

		// Sorts the List according to the score value (after the comma)
		lines.sort(
				(line1, line2) -> {
					String score1 = line1.split(",", 2)[1];
					String score2 = line2.split(",", 2)[1];
					return -score1.compareTo(score2);
				});

		return lines;

	}

	/**
	 *  Builds the display Strings based on the first 10 entries in the List.
	 *
	 * @param lines the sorted list of entries
	 */
	private void build(List<String> lines) {

		StringBuilder nicknameBuilder = new StringBuilder(90);
		StringBuilder hiscoresBuilder = new StringBuilder(60);
		StringBuilder ranksBuilder = new StringBuilder(40);

		// Concatenate a long string of the top 10 (or the top "n" of as many entries that exist) entries
		// 		according to their category,
		// 		alternating each entry with newline breaks
		for (int i = 0 ; i < 10 && i < lines.size() ; i++) {
			String[] entry = lines.get(i).split(",",2);
			nicknameBuilder.append(entry[0]).append("\n");
			hiscoresBuilder.append(entry[1]).append("\n");
			ranksBuilder.append(ranksList.get(i)).append(("\n"));
		}

		// Assign the result of the builder to our Strings
		nicknames = nicknameBuilder.toString();
		hiscores = hiscoresBuilder.toString();
		ranks = ranksBuilder.toString();

	}

	//  ######################################## GETTERS ########################################

	/** @return the built String of nicknames */
	public String getNicknames() {
		return nicknames;
	}

	/** @return the built String of hiscores */
	public String getHiscores() {
		return hiscores;
	}

	/** @return the built String of ranks */
	public String getRanks() {
		return ranks;
	}

	//  ######################################## LIBRARY ########################################

	/**
	 * The ArrayList of the Strings representing the ranks from 1st-10th.
	 */
	private final ArrayList<String> ranksList =
		new ArrayList<>() {
			{
				add("1st");
				add("2nd");
				add("3rd");
				add("4th");
				add("5th");
				add("6th");
				add("7th");
				add("8th");
				add("9th");
				add("10th");
			}
		};

}
