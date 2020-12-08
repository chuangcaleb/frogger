package frogger.util;

import frogger.constant.FilePath;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class HiscoreWriterTest {

	@Test
	public void testWriteNew() {

		HiscoreWriter hiscoreWriter = new HiscoreWriter(0);
		hiscoreWriter.write("test1", 3);
		hiscoreWriter.write("test2", 5);

		try {

			List<String> lines = FileUtils.readLines(new File(FilePath.HISCORES_DIR + "hiscores_lv0"), StandardCharsets.UTF_8);

			assertEquals("test1,00003",lines.get(0));
			assertEquals("test2,00005",lines.get(1));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@AfterEach
	public void cleanupEach() {
		File testFile = new File(FilePath.HISCORES_DIR + "hiscores_lv0");
		testFile.delete();
	}

	@AfterAll
	public static void cleanupAll() {
		File dir = new File(FilePath.HISCORES_DIR);
		if (dir.listFiles() == null) { // if directory empty, delete directory too
			dir.delete();
		}
	}



}