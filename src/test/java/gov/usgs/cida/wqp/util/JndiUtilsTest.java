package gov.usgs.cida.wqp.util;

import static org.junit.Assert.*;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.sql.DataSource;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class JndiUtilsTest {

	public static final String CTX_NAME = "java:/comp/env";
	
	public static final String TEST_JNDI = "testExistingObject";
	public static final DataSource TEST_DS = new SimpleDriverDataSource();

	public static final String TEST_JNDI_NOT_FOUND = "notFoundName";

	public static class TestInitialContextFactory implements InitialContextFactory {

	    public Context getInitialContext(Hashtable<?, ?> arg0) throws NamingException {
			System.out.println("getInitialContext called");
			
	        Context envContext = Mockito.mock(Context.class);
	        Mockito.when(envContext.lookup(TEST_JNDI)).thenReturn(TEST_DS);
	        
	        Context context = Mockito.mock(Context.class);
	        Mockito.when(context.lookup(CTX_NAME)).thenReturn(envContext);
	        
	        return context;
	    }
	}	

	static {
		System.out.println("Setting java.naming.factory.initial for testing to " + TestInitialContextFactory.class.getName());
		System.setProperty("java.naming.factory.initial",TestInitialContextFactory.class.getName());
	}

	
	@Test
	public void testJndiDataSource_availableResource() {
		DataSource ds = JndiUtils.jndiDataSource(TEST_JNDI);
		assertNotNull(ds);
	}

	@Test
	public void testJndiDataSource_unavailableResourceDoesNotThrowException() {
		DataSource ds = JndiUtils.jndiDataSource(TEST_JNDI_NOT_FOUND);
		assertNull(ds);
	}
}
