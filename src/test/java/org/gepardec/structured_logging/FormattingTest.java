package org.gepardec.structured_logging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

class FormattingTest {
    
    @Test
	void serializeSimpleJavaObject() throws Exception {
		
    	Person fritz = new Person("Fritz", 25);
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	String result = jsonb.toJson(fritz);
    	
    	assertEquals("{\"age\":25,\"name\":\"Fritz\"}", result);

	}
}
