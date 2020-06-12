package gov.usgs.wma.wqp;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import java.time.LocalDate;
import org.dbunit.dataset.ReplacementDataSet;

public class CsvDataSetLoader extends AbstractDataSetLoader {
	LocalDate currentDate = LocalDate.now();
	protected IDataSet createDataSet(Resource resource) throws Exception {		
		return createReplacementDataSet(new CsvURLDataSet(resource.getURL()));
	}
	
	private ReplacementDataSet createReplacementDataSet(CsvURLDataSet dataSet) {
		ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
		replacementDataSet.addReplacementSubstring("`", "\"");
		replacementDataSet.addReplacementSubstring("[year-0]", String.valueOf(currentDate.getYear()));
		replacementDataSet.addReplacementSubstring("[year-1]", String.valueOf(currentDate.getYear() - 1));
		replacementDataSet.addReplacementSubstring("[year-2]", String.valueOf(currentDate.getYear() - 2));
		replacementDataSet.addReplacementSubstring("[year-3]", String.valueOf(currentDate.getYear() - 3));
		replacementDataSet.addReplacementSubstring("[year-4]", String.valueOf(currentDate.getYear() - 4));
		replacementDataSet.addReplacementSubstring("[year-5]", String.valueOf(currentDate.getYear() - 5));
		return replacementDataSet;
	}
}
