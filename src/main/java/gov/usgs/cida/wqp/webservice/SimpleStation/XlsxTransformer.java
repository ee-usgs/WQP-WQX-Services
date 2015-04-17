package gov.usgs.cida.wqp.webservice.SimpleStation;

import gov.usgs.cida.wqp.service.ILogService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XlsxTransformer extends TransformOutputStream {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected Map<String, String> mapping;
	
	/** Is this the first write to the stream. */
	private boolean first = true;
	
	/** Has this been initialized. */
	private boolean initialized = false;

	/** The work book to which we will add a sheet for this export. */
	private XSSFWorkbook workbook = null;

	/** The sheet that we are working on. */
	private XSSFSheet sheet = null;

	/** Within the workbook, the name of the spreadsheet tab. */
	private String sheetName = "report";

	/** Name of the zip entry holding sheet data, e.g. /xl/worksheets/report.xml */
	private String sheetRef = "";

	/** Keep track of the row we are writing. 0-based. */
	private int rowCount = 0;

	/** Zip output stream for the spreadsheet. */
	private ZipOutputStream zos;
	
	/** Default output buffer size. */
	private static final int DEFAULT_BUFFER_SIZE = 1024;

	public XlsxTransformer(OutputStream target, ILogService logService, BigDecimal logId, Map<String, String> mapping) {
		super(target, logService, logId, null);
		this.mapping = mapping;
	}

	/**
	 * Passes to parent to extract object. If object is available, process.
	 * 
	 *  {@inheritDoc}
	 */
	public void write(final byte[] b, final int off, final int len) throws IOException {
		super.write(b, off, len);
		if (null == result) {
			return;
		}

		if (!initialized) {
			init();
		}

		if (first) {
			writeHeader();
			logService.logFirstRowComplete(logId);
			first = false;
		}
		writeData();
	}
	
	/**
	 * Initialize a workbook if needed, a sheet to work on and write the
	 * template to the stream.
	 * 
	 * @throws IOException when issues with the streaming.
	 */
	private void init() throws IOException {
		if (null == workbook) {
			workbook = new XSSFWorkbook();
		}

		if (null == sheet) {
			sheet = workbook.createSheet(sheetName);
		}

		sheetRef = sheet.getPackagePart().getPartName().getName().substring(1);
		zos = new ZipOutputStream(target);

		// stream the template - don't include the empty sheet (sheetRef).
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		ZipInputStream strm = new ZipInputStream(new ByteArrayInputStream(os.toByteArray()));
		ZipEntry ze;
		while (null != (ze = strm.getNextEntry())) {
			if (!ze.getName().equals(sheetRef)) {
				zos.putNextEntry(new ZipEntry(ze.getName()));
				copyStream(strm, zos);
			}
		}

		initialized = true;
	}

	/**
	 * Write the header.
	 * 
	 * @throws IOException when issues with the streaming.
	 */
	private void writeHeader() throws IOException {
		zos.putNextEntry(new ZipEntry(sheetRef));
		beginSheet();
		insertRow();
		int cellCount = 0;
		for (String name : result.keySet()) {
//			createCell(cellCount, name);
			
			String hName =  mapping.get(name);
			if (null != hName) {
				createCell(cellCount, mapping.get(name));
			} else {
				createCell(cellCount, name);
			}

			cellCount++;
		}
		endRow();
	}

	
	/**
	 * Write the data.  Null cells are skipped to cut some of the bloat out of the file.
	 * 
	 * @throws IOException when issues with the streaming.
	 */
	private void writeData() throws IOException {
		insertRow();
		int cellCount = 0;
		for (Object obj : result.values()) {
			if (null != obj) {
				if (obj instanceof BigDecimal) {
					createCell(cellCount, ((BigDecimal) obj).doubleValue());
				} else {
					createCell(cellCount, obj.toString());
				}
			}
			cellCount++;
		}
		endRow();
	}

	/**
	 * Once we are done with the filter, the sheet needs it's ending tag.
	 */
//	public void finishWorkbook() {
	public void end() {
		try {
			endSheet();
			zos.finish();
		} catch (Exception e) {
			log.error("Problem encountered finishing the workbook.", e);
			throw new RuntimeException(e);
		}
	}

	
	/** 
	 * Converts a string to a byte array and stream it.
	 * @param in the string to be streamed.
	 * @param out the stream to write it to.
	 * @throws IOException when issues with the streaming.
	 */
	private void copyString(final String in, final OutputStream out) throws IOException {
		copyStream(new ByteArrayInputStream(in.getBytes()), out);
	}

	/** 
	 * Writes a byte array to the stream. 
	 * @param in the byte array to stream.
	 * @param out the stream to write it to.
	 * @throws IOException when issues with the streaming.
	 */
	private void copyStream(final InputStream in, final OutputStream out)
			throws IOException {
		byte[] chunk = new byte[DEFAULT_BUFFER_SIZE];
		int count;
		while ((count = in.read(chunk)) >= 0) {
			out.write(chunk, 0, count);
		}
	}
	/** 
	 * Output the xml required at the beginning of a sheet.
	 * @throws IOException  when issues with the streaming.
	 */
	public void beginSheet() throws IOException {
		copyString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">",
				zos);
		copyString("<sheetData>\n", zos);
	}

	/** 
	 * Output the xml required at the end of sheet.
	 * @throws IOException when issues with the streaming.
	 */
	public void endSheet() throws IOException {
		copyString("</sheetData>", zos);
		copyString("</worksheet>", zos);
	}

	/**
	 * Output the xml required at the beginning of a row. 
	 * @throws IOException when issues with the streaming.
	 */
	public void insertRow() throws IOException {
		copyString("<row r=\"" + (rowCount + 1) + "\">\n", zos);
	}

	/**
	 * Output the xml required at the end of a row and increment the counter.
	 * @throws IOException when issues with the streaming.
	 */
	public void endRow() throws IOException {
		copyString("</row>\n", zos);
		rowCount++;
	}

	/** 
	 * Output the xml for a string cell at the given index in the current row. 
	 * @param columnIndex - 0-based index of the cell within the current row.
	 * @param value - the string value to populate the column with.  The method handles escaping necessary characters.
	 * @throws IOException when issues with the streaming.
	 */
	public void createCell(final int columnIndex, final String value) throws IOException {
		String ref = new CellReference(rowCount, columnIndex).formatAsString();
		copyString("<c r=\"" + ref + "\" t=\"inlineStr\"", zos);
		copyString(">", zos);
		copyString("<is><t>" + StringEscapeUtils.escapeXml10(value)
				+ "</t></is>", zos);
		copyString("</c>", zos);
	}

	/** 
	 * Output the xml for a numeric cell at the given index in the current row. 
	 * @param columnIndex - 0-based index of the cell within the current row.
	 * @param value - the numeric value to populate the column with.
	 * @throws IOException when issues with the streaming.
	 */
	public void createCell(final int columnIndex, final double value) throws IOException {
		String ref = new CellReference(rowCount, columnIndex).formatAsString();
		copyString("<c r=\"" + ref + "\" t=\"n\"", zos);
		copyString(">", zos);
		copyString("<v>" + value + "</v>", zos);
		copyString("</c>", zos);
	}

}
