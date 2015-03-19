package gov.usgs.cida.wqp.util;
/**
 *
 * @author duselman
 */
public interface Transformer<T> {
	T transform(String value);
}