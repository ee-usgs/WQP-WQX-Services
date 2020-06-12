package gov.usgs.wma.wqp.transform;

import static gov.usgs.wma.wqp.mapping.xml.StationKml.KML_DOCUMENT;

import java.io.OutputStream;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.service.ILogService;

public class MapToKmlTransformer extends MapToXmlTransformer {

	public MapToKmlTransformer(OutputStream target, IXmlMapping mapping, ILogService logService, Integer logId, Profile profile) {
		super(target, mapping, logService, logId, profile);
	}

	@Override
	protected void writeHeader() {
		writeToStream(header);
		nodes.push(fieldMapping.getRoot());
		nodes.push(KML_DOCUMENT);
	}

}
