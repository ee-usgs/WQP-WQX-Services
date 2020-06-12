package gov.usgs.wma.wqp.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Version {
	public Build build;

	public class Build {
		@Schema(example="wqp")
		public String artifact;
		@Schema(example="wqpgateway")
		public String name;
		@Schema(example="2019-12-03T17:43:14.683Z")
		public String time;
		@Schema(example="1.3.0-SNAPSHOT")
		public String version;
		@Schema(example="gov.usgs.wma.wqp")
		public String group;
	}
}
