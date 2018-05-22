package gov.usgs.cida.wqp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

	@Value("${kml.style.url}")
	private String kmlStyleUrl;
	@Value("${codes.url}")
	private String codesUrl;
	@Value("${codes.mimeType}")
	private String codesMimeType;
	@Value("${codes.timeout.milli}")
	private int codesTimeoutMilli;
	@Value("${nldi.timeout.milli}")
	private int nldiTimeoutMilli;
	@Value("${swagger.display.host}")
	private String swaggerDisplayHost;
	@Value("${swagger.display.path}")
	private String swaggerDisplayPath;
	@Value("file:${swaggerServicesConfigFile}")
	private Resource swaggweServicesConfigFile;
	@Value("${max.result.rows}")
	private Integer maxResultRows;
	@Value("${site.url.base}")
	private String siteUrlBase;
	public String getKmlStyleUrl() {
		return kmlStyleUrl;
	}
	public String getCodesUrl() {
		return codesUrl;
	}
	public String getCodesMimeType() {
		return codesMimeType;
	}
	public int getCodesTimeoutMilli() {
		return codesTimeoutMilli;
	}
	public int getNldiTimeoutMilli() {
		return nldiTimeoutMilli;
	}
	public String getSwaggerDisplayHost() {
		return swaggerDisplayHost;
	}
	public String getSwaggerDisplayPath() {
		return swaggerDisplayPath;
	}
	public Resource getSwaggerServicesConfigFile() {
		return swaggweServicesConfigFile;
	}
	public Integer getMaxResultRows() {
		return maxResultRows;
	}
	public String getSiteUrlBase() {
		return siteUrlBase;
	}

}
