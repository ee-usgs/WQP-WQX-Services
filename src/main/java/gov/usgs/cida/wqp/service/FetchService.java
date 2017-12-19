package gov.usgs.cida.wqp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

@Service
public class FetchService {

	private final int timeoutMilli;

	@Autowired
	public FetchService(@Qualifier("nldiTimeoutMilli") int timeoutMilli) {
		this.timeoutMilli = timeoutMilli;
	}

	public List<String> fetch(String tokenName, URL url) throws IOException {
		//We are opening our own connection so we can set the timeouts. The JsonFactor.createParser(URL) does not allow this.
		URLConnection conn = url.openConnection();
		conn.setReadTimeout(timeoutMilli);
		conn.setConnectTimeout(timeoutMilli);
		return parse(tokenName, conn.getInputStream());
	}

	protected List<String> parse(String tokenName, InputStream stream) throws IOException {
		Set<String> fetchValues = new HashSet<>();
		JsonFactory jsonfactory = new JsonFactory();
		JsonParser jsonParser = jsonfactory.createParser(stream);

		//Brute force, assumes every unique occurrence of tokens with this name should be included in the output.
		while (null != jsonParser.nextToken()) {
			if (tokenName.equalsIgnoreCase(jsonParser.getCurrentName())) {
				fetchValues.add(jsonParser.nextTextValue());
			}
		}
		jsonParser.close();

		return fetchValues.stream().collect(Collectors.toList());
	}

}
