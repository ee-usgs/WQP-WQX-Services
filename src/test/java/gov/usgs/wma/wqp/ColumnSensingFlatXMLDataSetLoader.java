package gov.usgs.wma.wqp;

import java.io.InputStream;
import java.time.LocalDate;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

import gov.usgs.wma.wqp.dao.LogDaoTest;

public class ColumnSensingFlatXMLDataSetLoader extends AbstractDataSetLoader {
	LocalDate currentDate = LocalDate.now();

	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		try (InputStream inputStream = resource.getInputStream()) {
			return createReplacementDataSet(builder.build(inputStream));
		}
	}

	private ReplacementDataSet createReplacementDataSet(FlatXmlDataSet dataSet) {
		ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
		replacementDataSet.addReplacementSubstring("`", "\"");
		replacementDataSet.addReplacementSubstring("[year-0]", String.valueOf(currentDate.getYear()));
		replacementDataSet.addReplacementSubstring("[year-1]", String.valueOf(currentDate.getYear() - 1));
		replacementDataSet.addReplacementSubstring("[year-2]", String.valueOf(currentDate.getYear() - 2));
		replacementDataSet.addReplacementSubstring("[year-3]", String.valueOf(currentDate.getYear() - 3));
		replacementDataSet.addReplacementSubstring("[year-4]", String.valueOf(currentDate.getYear() - 4));
		replacementDataSet.addReplacementSubstring("[year-5]", String.valueOf(currentDate.getYear() - 5));

		replacementDataSet.addReplacementSubstring("[id]", "1");
		replacementDataSet.addReplacementSubstring("[dataStoreCounts]", LogDaoTest.DATA_COUNTS_IN_DB);
		replacementDataSet.addReplacementSubstring("[downloadDetails]", LogDaoTest.DOWNLOAD_DETAILS);
		return replacementDataSet;
	}
}
