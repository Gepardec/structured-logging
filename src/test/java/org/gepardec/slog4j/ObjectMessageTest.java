package org.gepardec.slog4j;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.jboss.weld.junit5.EnableWeld;
import org.junit.jupiter.api.Test;

@EnableWeld
class ObjectMessageTest {
	
	static Logger logger = LogManager.getLogger(ObjectMessageTest.class);
    
    @Test
	void simpleLog() throws Exception {
		
    	Person fritz = new Person("Fritz", 25);
    	
    	ObjectMessage message = new ObjectMessage(fritz);

    	logger.log(Level.INFO, message);
    	
	}
}
