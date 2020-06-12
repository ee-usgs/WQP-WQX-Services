package gov.usgs.wma.wqp;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import gov.usgs.wma.wqp.parameter.FilterParameters;

public class TestConstraintViolation implements ConstraintViolation<FilterParameters> {

	private String message;

	public TestConstraintViolation(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getMessageTemplate() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public FilterParameters getRootBean() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public Class<FilterParameters> getRootBeanClass() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public Object getLeafBean() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getExecutableParameters() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public Object getExecutableReturnValue() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public Path getPropertyPath() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public Object getInvalidValue() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintDescriptor<?> getConstraintDescriptor() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public <U> U unwrap(Class<U> type) {
		// Auto-generated method stub
		return null;
	}

}
