package gov.usgs.cida.wqp.util;


//import gov.usgs.webservices.ibatis.stax.XMLMapMarshaller;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.reflection.factory.ObjectFactory;

public class SqlSessionContextObjectFactory implements ObjectFactory {
	
	private static final ThreadLocal<ContextObjectFactory> CONTEXT_OBJECT_FACTORY = new ThreadLocal<ContextObjectFactory>();
	
	private static final ThreadLocal<Object> CONTEXT_OBJECT =
		new ThreadLocal<Object>() {
		@Override protected Object initialValue() {
			Object value = null;
			ContextObjectFactory tof = CONTEXT_OBJECT_FACTORY.get();
			if (tof != null) {
				value = tof.create();
			}
			return value;
		}
	};
	
	private ObjectFactory wrappedObjectFactory;
	
	public SqlSessionContextObjectFactory(ObjectFactory objectFactory) {
		this.wrappedObjectFactory = objectFactory;
	}
	
	@Override
	public <T> T create(Class<T> type) {
		return null;
//		return XMLMapMarshaller.class.isAssignableFrom(type) ? 
//				CONTEXT_OBJECT.get() : wrappedObjectFactory.create(type) ;	
	}
	
	@Override
	public void setProperties(Properties properties) {
		wrappedObjectFactory.setProperties(properties);
	}

	public void setContextObject(Object threadObject) {
		CONTEXT_OBJECT.set(threadObject);
	}
	
	public void setContextObjectFactory(ContextObjectFactory threadObjectFactory) {
		CONTEXT_OBJECT_FACTORY.set(threadObjectFactory);
	}
	
	public void cleanupContext() {
		System.err.println("cleaning up context");
		CONTEXT_OBJECT_FACTORY.remove();
		CONTEXT_OBJECT.remove();
	}
	
	// if one wants lazy initialization for thread variables...
	public static interface ContextObjectFactory {
		public Object create();
	}

	@Override
	public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
		return wrappedObjectFactory.create(type, constructorArgTypes, constructorArgs);
	}

	@Override
	public <T> boolean isCollection(Class<T> type) {
		return wrappedObjectFactory.isCollection(type);
	}

}
