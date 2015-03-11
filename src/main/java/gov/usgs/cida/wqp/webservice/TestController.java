package gov.usgs.cida.wqp.webservice;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class TestController {
	private final Logger log = Logger.getLogger(getClass());

	public TestController() {
		log.trace(getClass());
	}
	
    @RequestMapping(method=RequestMethod.GET)
    public void home(@RequestBody final String body, final Writer writer) throws IOException {
    	log.trace("home test");
    	
        writer.append("<h2>Welcome, XML Free Spring MVC!</h2>");
    }
}