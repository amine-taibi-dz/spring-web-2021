package dz.acs.webflow.tests;

import org.springframework.faces.model.converter.FacesConversionService;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import dz.acs.webflow.activation.ActivationService;
import dz.acs.webflow.activation.UserMe;

public class ActivationFlowExecutionTests extends AbstractXmlFlowExecutionTests {

	private ActivationService activationService;

	protected void setUp() {
//		activationService = EasyMock.createMock(ActivationService.class);
	}

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/activation/activation-flow.xml");
	}

	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
		//builderContext.registerBean("activationService", activationService);
		builderContext.getFlowBuilderServices().setConversionService(new FacesConversionService());
	}

	public void testStartActivationFlow() {
		
		MockExternalContext context = new MockExternalContext();
		startFlow(context);
		assertCurrentStateEquals("activation");
		assertResponseWrittenEquals("activation", context);
		assertTrue(getRequiredFlowAttribute("user1") instanceof UserMe);
	}
	
}
