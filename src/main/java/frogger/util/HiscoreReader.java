package frogger.util;

import frogger.constant.FilePath;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HiscoreReader {

	private String nicknames;
	private String hiscores;
	private String ranks;

	public HiscoreReader(int levelNum) {
		read(levelNum);
	}

	public void read(int levelNum) {
		try { // reads entire file
			File scoreFile = new File(FilePath.HISCORES_DIR + "hiscores_lv" + levelNum);
			List<String> lines = FileUtils.readLines(scoreFile, StandardCharsets.UTF_8);
			StringBuilder nicknameBuilder = new StringBuilder(90);
			StringBuilder hiscoresBuilder = new StringBuilder(60);
			StringBuilder ranksBuilder = new StringBuilder(40);

			// sort
			lines.sort(
				(line1, line2) -> {
					String score1 = line1.split(",", 2)[1];
					String score2 = line2.split(",", 2)[1];
					return -score1.compareTo(score2);
				});

			for (int i = 0 ; i < 10 && i < lines.size() ; i++) {
				String[] entry = lines.get(i).split(",",2);
				nicknameBuilder.append(entry[0]).append("\n");
				hiscoresBuilder.append(entry[1]).append("\n");
				ranksBuilder.append(ranksList.get(i)).append(("\n"));
			}

			nicknames = nicknameBuilder.toString();
			hiscores = hiscoresBuilder.toString();
			ranks = ranksBuilder.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// GETTER
	public String getNicknames() {
		return nicknames;
	}

	public String getHiscores() {
		return hiscores;
	}

	public String getRanks() {
		return ranks;
	}

	private ArrayList<String> ranksList =
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
