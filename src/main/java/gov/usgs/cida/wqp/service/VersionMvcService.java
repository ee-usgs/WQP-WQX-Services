package gov.usgs.cida.wqp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VersionMvcService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public VersionMvcService() {
        log.trace(getClass().getName());
	}
	
	
    @RequestMapping(value="version", method=RequestMethod.GET)
    @ResponseBody
    public String getVersion() {
        return ApplicationVersion.getVersion();
    }

}
