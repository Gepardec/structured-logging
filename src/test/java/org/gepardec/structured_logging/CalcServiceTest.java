package org.gepardec.structured_logging;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoWeld
@AddPackages(CalcServiceTest.class)
@ActivateScopes(RequestScoped.class)
class CalcServiceTest {
//    @WeldSetup
//    public WeldInitiator weld = WeldInitiator
//            .from(CalcService.class, CalcLogObject.class).activate(RequestScoped.class).build();

    @Inject
    CalcService service;

//    @BeforeEach
//    void setUp() {
//        service = new CalcService();
//    }

    @Test
    void testSum() {
        // given

        // when
        CalcResponse response = service.calc(new CalcRequest(10, 5, Operation.SUM));

        // then
        assertNotNull(response);
        assertEquals(15, response.getResult());
    }
}