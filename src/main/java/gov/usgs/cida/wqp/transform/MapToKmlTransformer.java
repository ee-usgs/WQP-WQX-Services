package gov.usgs.cida.wqp.transform;

import static gov.usgs.cida.wqp.mapping.xml.StationKml.KML_DOCUMENT;

import java.io.OutputStream;
import java.math.BigDecimal;

import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.service.ILogService;

public class MapToKmlTransformer extends MapToXmlTransformer {

	public MapToKmlTransformer(OutputStream target, IXmlMapping mapping, ILogService logService, BigDecimal logId, Profile profile) {
		super(target, mapping, logService, logId, profile);
	}

	@Override
	protected void writeHeader() {
		writeToStream(header);
		nodes.push(fieldMapping.getRoot());
		nodes.push(KML_DOCUMENT);
	}

}
