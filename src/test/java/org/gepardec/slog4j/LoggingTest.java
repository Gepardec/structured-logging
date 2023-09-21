package org.gepardec.slog4j;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

class LoggingTest {
	
	static Logger log = LoggerFactory.getLogger(LoggingTest.class);
    
    @Test
	void simpleLog() throws Exception {
		
    	Person fritz = new Person("Fritz", 25);
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	String result = jsonb.toJson(fritz);
    	
    	log.info(result);

	}
}
