package gov.usgs.cida.wqp.validation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.FetchService;

public class FetchValidator extends AbstractValidator<String[]> {
	private static final Logger LOG = LoggerFactory.getLogger(LookupValidator.class);

	private FetchService fetchService;
	private String tokenName;

	public FetchValidator(String tokenName, FetchService fetchService, Parameters parameter)  {
		this(tokenName, fetchService, parameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER);
	}

	public FetchValidator(String tokenName, FetchService fetchService, Parameters parameter, int minOccurs, int maxOccurs, String delimiter) {
		super(parameter, minOccurs, maxOccurs, delimiter);
		this.tokenName = tokenName;
		this.fetchService = fetchService;
		LOG.trace(getClass().getName());
	}

	@Override
	public ValidationResult<String[]> validate(final String value) {
		ValidationResult<String[]> vr = new ValidationResult<String[]>();
		String[] strings = transformer.transform(value);
		Set<String> fetchValues = new HashSet<>();
		if (strings.length < getMinOccurs() || strings.length > getMaxOccurs()) {
			vr.setValid(false);
			vr.getValidationMessages().add(getErrorMessage(value, IS_NOT_BETWEEN + getMinOccurs() + " and " + getMaxOccurs() + " occurences."));
		} else {
			for (String code : strings) {
				try {
					URL url = new URL(code);
					Set<String> codeValues = fetchService.fetch(tokenName, url);
					if (codeValues.isEmpty()) {
						vr.setValid(false);
						vr.getValidationMessages().add(getErrorMessage(code, "no values were found."));
					} else {
						fetchValues.addAll(codeValues);
					}
				} catch (MalformedURLException e) {
					vr.setValid(false);
					vr.getValidationMessages().add(getErrorMessage(code, "is not a valid URL."));
				} catch (IOException e) {
					vr.setValid(false);
					vr.getValidationMessages().add(getErrorMessage(code, "cannot be accessed to validate your request."));
				}
			}
			vr.setTransformedValue(fetchValues.toArray(new String[0]));
		}
		return vr;
	}

}
