package gov.usgs.wma.wqp.parameter;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonValue;

@Deprecated
public class Command {
	public static final String REGEX_AVOID = "NWIS|STORET";
	@Pattern(
			regexp=REGEX_AVOID,
			message="The value of command.avoid=${validatedValue} must match the format {regexp}")
	private String avoid;
	public Command(){}
	public Command(String avoid) {
		this.avoid = avoid;
	}
	@JsonValue
	public String getAvoid() {
		return avoid;
	}
	public void setAvoid(String avoid) {
		this.avoid = avoid;
	}
}
