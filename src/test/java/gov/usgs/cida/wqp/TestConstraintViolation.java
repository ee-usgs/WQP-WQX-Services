package gov.usgs.cida.wqp;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import gov.usgs.cida.wqp.parameter.FilterParameters;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterParameters getRootBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<FilterParameters> getRootBeanClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getLeafBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getExecutableParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getExecutableReturnValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path getPropertyPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getInvalidValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintDescriptor<?> getConstraintDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> U unwrap(Class<U> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
