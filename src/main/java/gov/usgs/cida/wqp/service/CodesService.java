package gov.usgs.cida.wqp.service;

import gov.usgs.cida.wqp.parameter.Parameters;

import org.apache.log4j.Logger;

public class CodesService {
	private final Logger log = Logger.getLogger(getClass());

	public String fetch(Parameters codeType, String code) {
		log.warn("CodesService has not yet been configured nor implemented.");
		return code; // TODO must contact the code service microservice
	}
}
