package gov.usgs.wma.wqp.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

	@Value("${springdoc.version}")
	private String appVersion;
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
	@Value("${max.result.rows}")
	private Integer maxResultRows;
	@Value("${site.url.base}")
	private String siteUrlBase;
	@Value("${site.swagger.apiDocsUrl}")
	private String swaggerApiDocsUrl;
	@Value("${site.swagger.deployName}")
	private String deployName;
	@Value("${server.servlet.context-path}")
	private String serverContextPath;
	@Value("${result.select.table:result}")
	private String resultSelectTable;
	@Value("${count.enabled:true}")
	private String resultCountEnabled;

	public String getAppVersion() {
		return appVersion;
	}
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
	public Integer getMaxResultRows() {
		return maxResultRows;
	}
	public String getSwaggerApiDocsUrl() {
		return swaggerApiDocsUrl;
	}
	public String getDeployName() {
		return deployName;
	}
	public String getMyUrlBase() {
		return StringUtils.removeEnd(siteUrlBase, "/") + serverContextPath;
	}

	/**
	 * The name of the prime result table.
	 * Defaults to 'result' if not specified.
	 * @return
	 */
	public String getResultSelectTable() { return resultSelectTable; }

	/**
	 * Are result counts enabled?
	 * Defaults to true if not spec'ed.
	 * @return
	 */
	public boolean getResultCountEnabled() {
		return !("false".equalsIgnoreCase(resultCountEnabled) || "no".equalsIgnoreCase(resultCountEnabled));
	}
}
