package gov.usgs.cida.wqp.webservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CacheController {
	private final Logger log = Logger.getLogger(getClass());

	/* ========================================================================
	 * Beans		===========================================================
	 * ========================================================================
	 */
	//@Autowired
	//private ProxyService proxyService;
	
	//@Autowired
	//private RESTService restService;

	@Autowired
	private Environment environment;
	/* ========================================================================
	 * Actions		===========================================================
	 * ========================================================================
	 */
	@RequestMapping(value="/cache", method=RequestMethod.GET)
    public DeferredResult<ModelAndView> entry() {
		String msg = "CacheController.entry() called";							
		log.info(msg);
		
		ModelAndView mv = new ModelAndView("index.jsp");
		mv.addObject("version", environment.getProperty("app.version"));

		DeferredResult<ModelAndView> finalResult = new DeferredResult<ModelAndView>();
		finalResult.setResult(mv);
		return finalResult;
    }
	
	@RequestMapping(value="/cache/status/{filter}", method=RequestMethod.GET)
    public DeferredResult<ModelAndView> restCacheStatus(@PathVariable String filter) {
		DeferredResult<ModelAndView> finalResult = new DeferredResult<ModelAndView>();
		
		//CacheService.cacheStatus(filter, finalResult);
		
		return finalResult;
	}
	
	@RequestMapping(value="/cache/refresh/{filter}", method=RequestMethod.GET)
    public DeferredResult<ModelAndView> restCacheRefresh(@PathVariable String filter) {
		DeferredResult<ModelAndView> finalResult = new DeferredResult<ModelAndView>();
		
		//CacheService.cacheRefresh(filter, finalResult);
		
		return finalResult;
	}
}
