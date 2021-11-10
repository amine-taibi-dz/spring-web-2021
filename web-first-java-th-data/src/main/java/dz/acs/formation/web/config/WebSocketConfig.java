package dz.acs.formation.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import dz.acs.formation.web.handler.MessageHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	 @Override
	    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
	        registry.addHandler(messageHandler(), "/api/wss/msg-handler"); //").withSockJS();
	    }

	    @Bean
	    public WebSocketHandler messageHandler() {
	        return new MessageHandler();
	    }
	    
//	    @Bean
//	    public DefaultHandshakeHandler handshakeHandler() {
//
//	    	WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
//	        policy.setInputBufferSize(8192);
//	        policy.setIdleTimeout(600000);
//
//	        return new DefaultHandshakeHandler(
//	                new JettyRequestUpgradeStrategy(new WebSocketServerFactory(policy)));
//	    }
	
}
