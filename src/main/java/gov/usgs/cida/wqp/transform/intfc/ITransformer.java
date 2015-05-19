package gov.usgs.cida.wqp.transform.intfc;

import java.io.IOException;

public interface ITransformer {

	void write(Object object) throws IOException;

	String encode(String value);
	
	void end() throws IOException;

}
