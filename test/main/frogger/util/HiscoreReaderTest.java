package main.frogger.util;

import frogger.constant.FilePath;
import frogger.util.HiscoreReader;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/**
 * {@code HiscoreReaderTest} is a Junit test for the class {@link HiscoreReader}
 */
class HiscoreReaderTest {

	@BeforeAll
	static void setDummyScores() {

		ArrayList<String> writeinScores =
				new ArrayList<>() {
					{
						add("C,4");
						add("A,2");
						add("F,1");
						add("A,3");
						add("X,1");
					}
				};

		File scoreFile = new File(FilePath.HISCORES_DIR + "hiscores_lv100");
		try {
			FileUtils.writeLines(scoreFile,writeinScores, System.getProperty("line.separator"),true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	void testSortUnsorted() {

		HiscoreReader hiscoreReader = new HiscoreReader(100);

		String hiscoresNewlined = hiscoreReader.getHiscores();
		String[] hiscores = hiscoresNewlined.split("\n");

		boolean isUnsorted = false;
		int previous = -1;

		for (String hiscore : hiscores) {

			int next = Integer.parseInt(hiscore);
			if (previous != -1 && previous < next) {
				isUnsorted = true;
				System.out.println("Error: " + previous + " vs " + next);
			}
			previous = next;
		}

		assertFalse(isUnsorted);
	}

	@Test
	void testRanksTillFifth() {

		HiscoreReader hiscoreReader = new HiscoreReader(100);
		String hiscoresNewlined = hiscoreReader.getRanks();

		assertEquals("1st\n2nd\n3rd\n4th\n5th\n", hiscoresNewlined);

	}

	@AfterAll
	static void cleanupAll() {
		File testFile = new File(FilePath.HISCORES_DIR + "hiscores_lv100");
		testFile.delete();

		File dir = new File(FilePath.HISCORES_DIR);
		if (dir.listFiles() == null) { // if directory empty, delete directory too
			dir.delete();
		}
	}

}