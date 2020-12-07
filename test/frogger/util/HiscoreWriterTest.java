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
	public void testWriter() {

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

//	@Test
//	public void testUnsorted() {
//
//		HiscoreWriter hiscoreWriter = new HiscoreWriter(0);
//		hiscoreWriter.write("C",4);
//		hiscoreWriter.write("A",2);
//		hiscoreWriter.write("F",1);
//		hiscoreWriter.write("A",3);
//		hiscoreWriter.write("X",1);
//
//		try {
//			boolean isUnsorted = false;
//			List<String> lines = FileUtils.readLines(new File(FilePath.HISCORES_DIR + "hiscores_lv0"), StandardCharsets.UTF_8);
//
//			int previous = -1;
//
//			for (String line : lines) {
//
//				int next = Integer.parseInt(line.split(",", 2)[1]);
//
//				if (previous != -1 && previous < next) {
//					isUnsorted = true;
//					System.out.println(previous + " vs " + next);
//				}
//				previous = next;
//
//			}
//
//			assertTrue(isUnsorted);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

//	@Test
//	public void testSort() {
//
//		// inserts scores out of order
//		HiscoreWriter hiscoreWriter = new HiscoreWriter(0);
//		hiscoreWriter.write("C",2);
//		hiscoreWriter.write("A",4);
//		hiscoreWriter.write("F",7);
//		hiscoreWriter.write("A",4);
//		hiscoreWriter.write("X",8);
//
//		try { // they should be in order when read
//			boolean isUnsorted = false;
//			List<String> lines = FileUtils.readLines(new File(FilePath.HISCORES_DIR + "hiscores_lv0"), StandardCharsets.UTF_8);
//
//			int previous = -1;
//
//			for (String line : lines) {
//
//				int next = Integer.parseInt(line.split(",", 2)[1]);
//				if (previous != -1 && previous < next) {
//					isUnsorted = true;
//					System.out.println(previous + " vs " + next);
//				}
//				previous = next;
//
//			}
//
//			assertFalse(isUnsorted);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

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