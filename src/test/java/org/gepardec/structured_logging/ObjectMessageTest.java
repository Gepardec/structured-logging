package org.gepardec.structured_logging;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.junit.jupiter.api.Test;

class ObjectMessageTest {
	
	static Logger logger = LogManager.getLogger(ObjectMessageTest.class);
    
    @Test
	void simpleLog() throws Exception {
		
    	Person fritz = new Person("Fritz", 25);
    	
    	ObjectMessage message = new ObjectMessage(fritz);

    	logger.log(Level.INFO, message);
    	
	}
}
