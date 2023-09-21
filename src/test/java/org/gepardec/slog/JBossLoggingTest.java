package org.gepardec.slog;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

class JBossLoggingTest {
	
	static Logger logger = Logger.getLogger(JBossLoggingTest.class);
	
    // setProperty "java.util.logging.manager" to "org.jboss.logmanager.LogManager"    
    
    @Test
	void simpleLog() throws Exception {
		
    	Person fritz = new Person("Fritz", 25);
    	org.jboss.logmanager.LogManager.getLogManager();
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(fritz);
    	
    	logger.info(result);
    	
	}
}
