package dz.acs.web.formation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/dispatcher0-servlet.xml" )
public class AbstractContextControllerTests {

	
//	Caused by: org.springframework.beans.factory.BeanDefinitionStoreException: IOException parsing XML document from class path resource [dispatcher-servlet.xml]; 
//	nested exception is java.io.FileNotFoundException: class path resource [dispatcher-servlet.xml] cannot be opened because it does not exist


	@Autowired
	protected WebApplicationContext wac;

}
