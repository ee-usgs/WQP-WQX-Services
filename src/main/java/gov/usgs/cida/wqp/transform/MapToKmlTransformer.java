package gov.usgs.cida.wqp.transform;

import static gov.usgs.cida.wqp.mapping.StationKml.KML_DOCUMENT;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.OutputStream;
import java.math.BigDecimal;

public class MapToKmlTransformer extends MapToXmlTransformer {

	public MapToKmlTransformer(OutputStream target, IXmlMapping mapping, ILogService logService, BigDecimal logId) {
		super(target, mapping, logService, logId);
	}

	@Override
	protected void writeHeader() {
		writeToStream(header);
		nodes.push(fieldMapping.getRoot());
		nodes.push(KML_DOCUMENT);
	}
	
}
