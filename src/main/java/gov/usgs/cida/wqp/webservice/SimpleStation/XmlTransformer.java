package gov.usgs.cida.wqp.webservice.SimpleStation;

import gov.usgs.cida.wqp.service.ILogService;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringEscapeUtils;

public class XmlTransformer extends TransformOutputStream {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected IXmlMapping mapping;
	
	protected Map<String, String> groupings;
	
    private Deque<String> nodes = new LinkedList<>();


	/** Is this the first write to the stream. */
	private boolean first = true;
	
	public XmlTransformer(OutputStream target, ILogService webServiceLogService, BigDecimal logId, IXmlMapping mapping) {
		super(target, webServiceLogService, logId, null);
		this.mapping = mapping;
		groupings = new HashMap<>();
		for (String key : mapping.getHardBreak().keySet()) {
			groupings.put(key, null);
		}
	}

	/**
	 * Passes to parent to extract object. If object is available, process.
	 * 
	 *  {@inheritDoc}
	 */
	public void write(final byte[] b, final int off, final int len) throws IOException {
		super.write(b, off, len);
		if (null == result) {
			return;
		}
		if (first) {
			prepareHeader();
			webServiceLogService.logFirstRowComplete(logId);
			first = false;
		}
		prepareData();
	}
	
	/** Output the file header. */
	private void prepareHeader() {
		writeToStream("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writeToStream("<" + mapping.getRoot() + " " + mapping.getRootNamespace() + ">");
		nodes.push(mapping.getRoot());
	}
	
	/**
	 * Extract the data and write to the stream.
	 */
	private void prepareData() {
		//TODO write bytes as we get them, rather than a "record" at a time
		StringBuilder sb = new StringBuilder();
		
		for (String key : mapping.getHardBreak().keySet()) {
			String val = getValue(key);
			if (!val.equalsIgnoreCase(groupings.get(key))) {
				if (nodes.contains(mapping.getHardBreak().get(key))) {
					closeNodes(sb, mapping.getHardBreak().get(key));
				}
				writeGrouping(sb, key);
				groupings.put(key, val);
			}
		}
		
		writeToStream(sb.toString());
	}
	
	/** output the closing tag. */
	public void end() {
    	while (!nodes.isEmpty()) {
    		writeToStream("</" + nodes.element() + ">");
    		nodes.pop();
    	}
	}
	
	private String getValue(String key) {
		return StringEscapeUtils.escapeXml11(result.get(key).toString());
	}
	
	private void writeGrouping(StringBuilder sb, String key) {
		List<String> cols = mapping.getGrouping().get(key);
		Iterator<String> i = cols.iterator();
		while (i.hasNext()) {
			String col = i.next();
			writeNode(sb, col, getValue(col));
		}
	}
	
	private void writeNode(StringBuilder sb, String key, String val) {
		String lNode = "Provider";
		List<String> pos = mapping.getStructure().get(key);
		Iterator<String> i = pos.iterator();
		while (i.hasNext()) {
			String node = i.next();
			log.trace("writeData - positionNode:" + node + " lastNode:" + lNode);
			if (!nodes.contains(node)) {
				if (!lNode.equalsIgnoreCase(nodes.peek())) {
					closeNodes(sb, lNode);
				}
				sb.append("<").append(node).append(">");
				nodes.push(node);
			}
			lNode = node;
		}
		sb.append(val);
		sb.append("</").append(nodes.peek()).append(">");
		nodes.pop();
	}
	
	private void closeNodes(StringBuilder sb, String targetNode) {
		log.trace("closeNodes - targetNode: " + targetNode);
		
    	while (!nodes.isEmpty() && !mapping.getRoot().equalsIgnoreCase(nodes.peek())) {
    		String node = nodes.peek();
    		log.trace("closeNodes - currentNode: " + node);
    		if (targetNode.equalsIgnoreCase(node)) {
    			break;
    		} else {
	    		sb.append("</").append(node).append(">");
	    		nodes.pop();
    		}
    	}

	}
	
	/**
	 * Write the results to the underlying outputStream.
	 * 
	 * @param str string to write.
	 */
	private void writeToStream(final String str) {
		try {
			target.write(str.getBytes("UTF-8"));
		} catch (Exception e) {
//				LOG.error("Problem writing '" + str + "' to underlying OutputStream.", e);
			throw new RuntimeException(e);
		}
	}

}
