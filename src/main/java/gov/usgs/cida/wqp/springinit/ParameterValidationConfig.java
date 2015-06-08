package gov.usgs.cida.wqp.springinit;

import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.ParameterTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitAndReplaceTransformer;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.util.WqpEnv;
import gov.usgs.cida.wqp.util.WqpEnvProperties;
import gov.usgs.cida.wqp.validation.AbstractValidator;
import gov.usgs.cida.wqp.validation.BoundedFloatingPointValidator;
import gov.usgs.cida.wqp.validation.DateFormatValidator;
import gov.usgs.cida.wqp.validation.LatLonBoundingBoxValidator;
import gov.usgs.cida.wqp.validation.LatitudeValidator;
import gov.usgs.cida.wqp.validation.LongitudeValidator;
import gov.usgs.cida.wqp.validation.LookupValidator;
import gov.usgs.cida.wqp.validation.RegexValidator;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParameterValidationConfig implements ValidationConstants, WqpEnvProperties {
	
	@Bean
	public CodesService codesService() {
		return new CodesService(WqpEnv.get(CODES_URL), WqpEnv.get(CODES_MIME_TYPE));
	};

	@Bean
	public RegexValidator<String> analyticalMethodValidator() {
		return new RegexValidator<String>(Parameters.ANALYTICAL_METHOD, REGEX_ANALYTICAL_METHOD);
	}
	
	@Bean
	public RegexValidator<String[]> avoidValidator(){
		// semicolon list of databases to exclude as 'command.avoid'
		return new RegexValidator<String[]>(Parameters.AVOID,REGEX_AVOID);
	}
	
	@Bean
	public LatLonBoundingBoxValidator bboxValidator() {
		// comma list of four float values
		return new LatLonBoundingBoxValidator(Parameters.BBOX);
	}
	
	@Bean
	public LookupValidator characteristicNameValidator() {
		// semicolon list of string characteristic names
		return new LookupValidator(codesService(), Parameters.CHARACTERISTIC_NAME);
	}
	
	@Bean
	public LookupValidator characteristicTypeValidator() {
		// semicolon list of string characteristic types
		return new LookupValidator(codesService(), Parameters.CHARACTERISTIC_TYPE);
	}
	
	@Bean
	public RegexValidator<String[]> countryValidator() {
		// semicolon list of string country codes
		return new RegexValidator<String[]>(Parameters.COUNTRY, REGEX_FIPS_COUNTRY);
	}
	
	@Bean
	public RegexValidator<String[]> countyValidator() {
		// semicolon list of country:state:county code strings
		return new RegexValidator<String[]>(Parameters.COUNTY, REGEX_FIPS_COUNTY);
	}
	
	@Bean
	public RegexValidator<String[]> dataProfileValidator() {
		// semicolon list of country:state:county code strings
		return new RegexValidator<String[]>(Parameters.DATA_PROFILE, REGEX_DATA_PROFILE);
	}
	
	@Bean
	public AbstractValidator<String[]> hucValidator() {
		// semicolon list of HUC codes
		AbstractValidator<String[]> hucValidator = new RegexValidator<String[]>(Parameters.HUC, REGEX_HUC);
		ParameterTransformer<String[]> hucTransformer = new SplitAndReplaceTransformer(DEFAULT_DELIMITER, REGEX_HUC_WILDCARD_IN, REGEX_HUC_WILDCARD_OUT);
		hucValidator.setTransformer(hucTransformer);
		return hucValidator;
	}
	
	@Bean
	public LatitudeValidator latitudeValidator() {
		// one float value
		return new LatitudeValidator(Parameters.LATITUDE);
	}
	
	@Bean
	public LongitudeValidator longitudeValidator() {
		// one float value
		return new LongitudeValidator(Parameters.LONGITUDE);
	}
	
	@Bean
	public RegexValidator<String[]> mimeTypeValidator() {
		// one string mimetype
		return new RegexValidator<String[]>(Parameters.MIMETYPE, 1, 1, null, REGEX_MIMETYPES);
	}
	
	@Bean
	public LookupValidator organizationValidator() {
		// semicolon list of string ORG names
		return new LookupValidator(codesService(), Parameters.ORGANIZATION);
	}
	
	@Bean
	public RegexValidator<String[]> parameterCodeValidator() {
		// semicolon list of 5digit pCodes
		return new RegexValidator<String[]>(Parameters.PCODE,REGEX_PCODE);
	}
	
	@Bean
	public LookupValidator projectValidator() {
		// semicolon list of string project ids
		return new LookupValidator(codesService(), Parameters.PROJECT);
	}
	
	@Bean
	public LookupValidator providerValidator() {
		// semicolon list of databases to include
		return new LookupValidator(codesService(), Parameters.PROVIDERS);
	}
	
	@Bean
	public LookupValidator sampleMediaValidator() {
		// semicolon list of string media type names
		return new LookupValidator(codesService(), Parameters.SAMPLE_MEDIA);
	}
	
	@Bean
	public LookupValidator siteTypeValidator() {
		// semicolon list of string site type names
		return new LookupValidator(codesService(), Parameters.SITE_TYPE);
	}
	
	@Bean
	public RegexValidator<String[]> siteIdValidator() {
		// agency-site string
		return new RegexValidator<String[]>(Parameters.SITEID, REGEX_SITEID);
	}
	
	@Bean
	public DateFormatValidator startDateHiValidator() {
		// one string date MM-DD-YYYY
		return new DateFormatValidator(Parameters.START_DATE_HI, FORMAT_DATE);
	}
	
	@Bean
	public DateFormatValidator startDateLoValidator() {
		// one string date MM-DD-YYYY
		return new DateFormatValidator(Parameters.START_DATE_LO, FORMAT_DATE);
	}
	
	@Bean
	public RegexValidator<String[]> stateValidator() {
		// country:state code string
		return new RegexValidator<String[]>(Parameters.STATE, REGEX_FIPS_STATE);
	}
	
	@Bean
	public BoundedFloatingPointValidator withinValidator() {
		// one float value
		return new BoundedFloatingPointValidator(Parameters.WITHIN, 0, Double.MAX_VALUE);
	}

	@Bean
	public RegexValidator<String[]> zipValidator() {
		// one string 'yes' or omitted
		return new RegexValidator<String[]>(Parameters.ZIP, 0, 1, null, REGEX_ZIP);
	}
	
	@Bean
	public Map<Parameters, AbstractValidator<?>> validatorMap() {
		Map<Parameters, AbstractValidator<?>> validatorMap = new HashMap<Parameters, AbstractValidator<?>>();
		validatorMap.put(Parameters.ANALYTICAL_METHOD, analyticalMethodValidator());
		validatorMap.put(Parameters.AVOID, avoidValidator());
		validatorMap.put(Parameters.BBOX, bboxValidator());
		validatorMap.put(Parameters.CHARACTERISTIC_NAME, characteristicNameValidator());
		validatorMap.put(Parameters.CHARACTERISTIC_TYPE, characteristicTypeValidator());
		validatorMap.put(Parameters.COUNTRY, countryValidator());
		validatorMap.put(Parameters.COUNTY, countyValidator());
		validatorMap.put(Parameters.DATA_PROFILE, dataProfileValidator());
		validatorMap.put(Parameters.HUC, hucValidator());
		validatorMap.put(Parameters.LATITUDE, latitudeValidator());
		validatorMap.put(Parameters.LONGITUDE, longitudeValidator());
		validatorMap.put(Parameters.MIMETYPE, mimeTypeValidator());
		validatorMap.put(Parameters.ORGANIZATION, organizationValidator());
		validatorMap.put(Parameters.PCODE, parameterCodeValidator());
		validatorMap.put(Parameters.PROJECT, projectValidator());
		validatorMap.put(Parameters.PROVIDERS, providerValidator());
		validatorMap.put(Parameters.SAMPLE_MEDIA, sampleMediaValidator());
		validatorMap.put(Parameters.SITE_TYPE, siteTypeValidator());
		validatorMap.put(Parameters.SITEID, siteIdValidator());
		validatorMap.put(Parameters.START_DATE_HI, startDateHiValidator());
		validatorMap.put(Parameters.START_DATE_LO, startDateLoValidator());
		validatorMap.put(Parameters.STATE, stateValidator());
		validatorMap.put(Parameters.WITHIN, withinValidator());
		validatorMap.put(Parameters.ZIP, zipValidator());
        return validatorMap;
	}

	@Bean
	public IParameterHandler hashMapParameterHandler() {
		return new HashMapParameterHandler(validatorMap());
	}
	
}
