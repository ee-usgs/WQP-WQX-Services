package gov.usgs.cida.wqp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.BaseIT;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, BlobDao.class})
@DatabaseSetup("classpath:/testData/blob/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BlobDaoIT extends BaseIT {

	public static final String SINGLE_FILE_PROJECT_ORG = "21NYDECA_WQX";
	public static final String SINGLE_FILE_PROJECT_PROJECT = "EPABEACH";
	public static final String SINGLE_FILE_PROJECT_ENTRY = "EPABEACH_three.txt";
	public static final String SINGLE_FILE_PROJECT_CONTENTS = "A fifth project text file.";

	public static final String THREE_FILE_PROJECT_ORG = "WIDNR_WQX";
	public static final String THREE_FILE_PROJECT_PROJECT = "WR047";
	public static final String THREE_FILE_PROJECT_ENTRY_ONE = "WR047_one.txt";
	public static final String THREE_FILE_PROJECT_CONTENTS_ONE = "A project text file.";
	public static final String THREE_FILE_PROJECT_ENTRY_TWO = "WR047_two.txt";
	public static final String THREE_FILE_PROJECT_CONTENTS_TWO = "A second project text file.";
	public static final String THREE_FILE_PROJECT_ENTRY_THREE = "WR047_three.txt";
	public static final String THREE_FILE_PROJECT_CONTENTS_THREE = "A third project text file.";

	public static final String SINGLE_FILE_STATION_ORG = "21NYDECA_WQX";
	public static final String SINGLE_FILE_STATION_STATION = "21NYDECA_WQX-ONTARIO-02";
	public static final String SINGLE_FILE_STATION_ENTRY = "21NYDECA_WQX-ONTARIO-02_three.txt";
	public static final String SINGLE_FILE_STATION_CONTENTS = "This is a single small text file, but you might think it is the third one.";

	public static final String THREE_FILE_STATION_ORG = "WIDNR_WQX";
	public static final String THREE_FILE_STATION_STATION = "WIDNR_WQX-113086";
	public static final String THREE_FILE_STATION_ENTRY_ONE = "WIDNR_WQX-113086_one.txt";
	public static final String THREE_FILE_STATION_CONTENTS_ONE = "This is a small text file.";
	public static final String THREE_FILE_STATION_ENTRY_TWO = "WIDNR_WQX-113086_two.txt";
	public static final String THREE_FILE_STATION_CONTENTS_TWO = "This is another small text file.";
	public static final String THREE_FILE_STATION_ENTRY_THREE = "WIDNR_WQX-113086_three.txt";
	public static final String THREE_FILE_STATION_CONTENTS_THREE = "This is yet another small text file.";

	public static final String SINGLE_FILE_RESULT_ORG = "21NYDECA_WQX";
	public static final String SINGLE_FILE_RESULT_ACTIVITY = "21NYDECA_WQX-020206-7";
	public static final String SINGLE_FILE_RESULT_RESULT = "21";
	public static final String SINGLE_FILE_RESULT_ENTRY= "21NYDECA_WQX-020206_three.txt";
	public static final String SINGLE_FILE_RESULT_CONTENTS= "This is a single small text result file, but you might think it is the third one.";

	public static final String THREE_FILE_RESULT_ORG = "WIDNR_WQX";
	public static final String THREE_FILE_RESULT_ACTIVITY = "WIDNR_WQX-7788480-4";
	public static final String THREE_FILE_RESULT_RESULT= "4";
	public static final String THREE_FILE_RESULT_ENTRY_ONE= "WIDNR_WQX-7788480_one.txt";
	public static final String THREE_FILE_RESULT_CONTENTS_ONE= "This is a small text result file.";
	public static final String THREE_FILE_RESULT_ENTRY_TWO= "WIDNR_WQX-7788480_two.txt";
	public static final String THREE_FILE_RESULT_CONTENTS_TWO= "This is another small text result file.";
	public static final String THREE_FILE_RESULT_ENTRY_THREE= "WIDNR_WQX-7788480_three.txt";
	public static final String THREE_FILE_RESULT_CONTENTS_THREE= "This is yet another small text result file.";

	public static final String SINGLE_FILE_ACTIVITY_ORG = "11NPSWRD_WQX";
	public static final String SINGLE_FILE_ACTIVITY_ACTIVITY = "STORET_SAMPLING";
	public static final String SINGLE_FILE_ACTIVITY_ENTRY = "STORET_SAMPLING_three.txt";
	public static final String SINGLE_FILE_ACTIVITY_CONTENTS = "A fifth activity text file.";

	public static final String THREE_FILE_ACTIVITY_ORG = "BR549_WQX";
	public static final String THREE_FILE_ACTIVITY_ACTIVITY = "BR549";
	public static final String THREE_FILE_ACTIVITY_ENTRY_ONE = "BR549_one.txt";
	public static final String THREE_FILE_ACTIVITY_CONTENTS_ONE = "An activity text file.";
	public static final String THREE_FILE_ACTIVITY_ENTRY_TWO = "BR549_two.txt";
	public static final String THREE_FILE_ACTIVITY_CONTENTS_TWO = "A second activity text file.";
	public static final String THREE_FILE_ACTIVITY_ENTRY_THREE = "BR549_three.txt";
	public static final String THREE_FILE_ACTIVITY_CONTENTS_THREE = "A third activity text file.";

	ByteArrayOutputStream response;
	ZipOutputStream target;

	@Autowired
	BlobDao blobDao;

	public void setup() {
		response = new ByteArrayOutputStream();
		target = new ZipOutputStream(response);
	}

	@Test
	public void testHarness() throws Exception {
		singleFileStationTest();
		threeFileStationTest();
		singleFileProjectTest();
		threeFileProjectTest();
		singleFileResultTest();
		threeFileResultTest();
		singleFileActivityTest();
		threeFileActivityTest();
	}

	public void singleFileStationTest() {
		try {
			setup();
			assertEquals(1, blobDao.getMonitoringLocationFiles(target, getRestProvider(), SINGLE_FILE_STATION_ORG, SINGLE_FILE_STATION_STATION));
			ZipInputStream stream = getStream(response);
			assertSingleFileStation(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	public void threeFileStationTest() {
		try {
			setup();
			assertEquals(3, blobDao.getMonitoringLocationFiles(target, getRestProvider(), THREE_FILE_STATION_ORG, THREE_FILE_STATION_STATION));
			ZipInputStream stream = getStream(response);
			assertThreeFileStation(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	public void singleFileProjectTest() {
		try {
			setup();
			assertEquals(1, blobDao.getProjectFiles(target, getRestProvider(), SINGLE_FILE_PROJECT_ORG, SINGLE_FILE_PROJECT_PROJECT));
			ZipInputStream stream = getStream(response);
			assertSingleFileProject(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	public void threeFileProjectTest() {
		try {
			setup();
			assertEquals(3, blobDao.getProjectFiles(target, getRestProvider(), THREE_FILE_PROJECT_ORG, THREE_FILE_PROJECT_PROJECT));
			ZipInputStream stream = getStream(response);
			assertThreeFileProject(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	public void singleFileResultTest() {
		try {
			setup();
			assertEquals(1, blobDao.getResultFiles(target, getRestProvider(), SINGLE_FILE_RESULT_ORG, SINGLE_FILE_RESULT_ACTIVITY, SINGLE_FILE_RESULT_RESULT));
			ZipInputStream stream = getStream(response);
			assertSingleFileResult(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	public void threeFileResultTest() {
		try {
			setup();
			assertEquals(3, blobDao.getResultFiles(target, getRestProvider(), THREE_FILE_RESULT_ORG, THREE_FILE_RESULT_ACTIVITY, THREE_FILE_RESULT_RESULT));
			ZipInputStream stream = getStream(response);
			assertThreeFileResult(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	public void singleFileActivityTest() {
		try {
			setup();
			assertEquals(1, blobDao.getActivityFiles(target, getRestProvider(), SINGLE_FILE_ACTIVITY_ORG, SINGLE_FILE_ACTIVITY_ACTIVITY));
			ZipInputStream stream = getStream(response);
			assertSingleFileActivity(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	public void threeFileActivityTest() {
		try {
			setup();
			assertEquals(3, blobDao.getActivityFiles(target, getRestProvider(), THREE_FILE_ACTIVITY_ORG, THREE_FILE_ACTIVITY_ACTIVITY));
			ZipInputStream stream = getStream(response);
			assertThreeFileActivity(stream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	protected ZipInputStream getStream(ByteArrayOutputStream response) {
		return new ZipInputStream(new ByteArrayInputStream(response.toByteArray()));
	}

	public static void assertSingleFileStation(ZipInputStream stream) throws IOException {
		assertContents(stream, SINGLE_FILE_STATION_ENTRY, SINGLE_FILE_STATION_CONTENTS);
		assertNull(stream.getNextEntry());
	}

	public static void assertThreeFileStation(ZipInputStream stream) throws IOException {
		assertContents(stream, THREE_FILE_STATION_ENTRY_ONE, THREE_FILE_STATION_CONTENTS_ONE);
		assertContents(stream, THREE_FILE_STATION_ENTRY_TWO, THREE_FILE_STATION_CONTENTS_TWO);
		assertContents(stream, THREE_FILE_STATION_ENTRY_THREE, THREE_FILE_STATION_CONTENTS_THREE);
		assertNull(stream.getNextEntry());
	}

	public static void assertSingleFileProject(ZipInputStream stream) throws IOException {
		assertContents(stream, SINGLE_FILE_PROJECT_ENTRY, SINGLE_FILE_PROJECT_CONTENTS);
		assertNull(stream.getNextEntry());
	}

	public static void assertThreeFileProject(ZipInputStream stream) throws IOException {
		assertContents(stream, THREE_FILE_PROJECT_ENTRY_ONE, THREE_FILE_PROJECT_CONTENTS_ONE);
		assertContents(stream, THREE_FILE_PROJECT_ENTRY_TWO, THREE_FILE_PROJECT_CONTENTS_TWO);
		assertContents(stream, THREE_FILE_PROJECT_ENTRY_THREE, THREE_FILE_PROJECT_CONTENTS_THREE);
		assertNull(stream.getNextEntry());
	}

	public static void assertSingleFileResult(ZipInputStream stream) throws IOException {
		assertContents(stream, SINGLE_FILE_RESULT_ENTRY, SINGLE_FILE_RESULT_CONTENTS);
		assertNull(stream.getNextEntry());
	}

	public static void assertThreeFileResult(ZipInputStream stream) throws IOException {
		assertContents(stream, THREE_FILE_RESULT_ENTRY_ONE, THREE_FILE_RESULT_CONTENTS_ONE);
		assertContents(stream, THREE_FILE_RESULT_ENTRY_TWO, THREE_FILE_RESULT_CONTENTS_TWO);
		assertContents(stream, THREE_FILE_RESULT_ENTRY_THREE, THREE_FILE_RESULT_CONTENTS_THREE);
		assertNull(stream.getNextEntry());
	}
	
	public static void assertSingleFileActivity(ZipInputStream stream) throws IOException {
		assertContents(stream, SINGLE_FILE_ACTIVITY_ENTRY, SINGLE_FILE_ACTIVITY_CONTENTS);
		assertNull(stream.getNextEntry());
	}

	public static void assertThreeFileActivity(ZipInputStream stream) throws IOException {
		assertContents(stream, THREE_FILE_ACTIVITY_ENTRY_ONE, THREE_FILE_ACTIVITY_CONTENTS_ONE);
		assertContents(stream, THREE_FILE_ACTIVITY_ENTRY_TWO, THREE_FILE_ACTIVITY_CONTENTS_TWO);
		assertContents(stream, THREE_FILE_ACTIVITY_ENTRY_THREE, THREE_FILE_ACTIVITY_CONTENTS_THREE);
		assertNull(stream.getNextEntry());
	}

	public static void assertContents(ZipInputStream stream, String entryName, String entryContents) throws IOException {
		ZipEntry entry = stream.getNextEntry();
		assertEquals(entryName, entry.getName());

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int len;
		byte[] buffer = new byte[1024];
		while ((len = stream.read(buffer)) > 0) {
			os.write(buffer, 0, len);
		}
		assertEquals(entryContents, os.toString());
	}

}
