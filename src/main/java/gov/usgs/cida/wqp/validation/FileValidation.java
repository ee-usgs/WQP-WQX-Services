package gov.usgs.cida.wqp.validation;

import org.apache.log4j.Logger;


public class FileValidation {
	private final Logger log = Logger.getLogger(getClass());

	public static final char[] ILLEGAL_CHARACTERS = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };
	
	
	public FileValidation() {
        log.trace(getClass());
	}
	
	public boolean filenameIsValid(String name) {
		for(char character : ILLEGAL_CHARACTERS) {
			if(name.indexOf(character) != -1) {
				return false;
			}
		}
		
		return true;
	}
	

}
