package gov.usgs.wma.wqp.openapi.annotation.query;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@Analyticalmethod
//@Assemblage
//@BBox
//@CharacteristicName
//@CharacteristicType
//@Countrycode
//@Countycode
//@Huc
//@Lat
//@Longitude
//@Minactivities
//@Minresults
//@Nldiurl
//@Organizations
//@PCode
//@Projects
//@Providers
//@SampleMedia
//@Siteid
//@SiteType
//@Sorted
//@StartDateHi
//@StartDateLo
//@Statecode
//@SubjectTaxonomicName
//@Within
//@Zip
public @interface FullParameterList {

}
