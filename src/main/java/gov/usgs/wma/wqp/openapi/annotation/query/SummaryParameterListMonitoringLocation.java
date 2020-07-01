package gov.usgs.wma.wqp.openapi.annotation.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@BBox
@Countrycode
@Countycode
@DataProfilePeriodOfRecord
@Huc
@Lat
@Longitude
@MimeTypeCsvGeo
@Nldiurl
@Organizations
@Providers
@Siteid
@SiteType
@Statecode
@SummaryYears
@Within
@Zip
public @interface SummaryParameterListMonitoringLocation {

}
