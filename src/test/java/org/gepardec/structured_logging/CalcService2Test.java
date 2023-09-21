package org.gepardec.structured_logging;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddEnabledInterceptors;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoWeld
@AddEnabledInterceptors(StrucuredLoggingInterceptor.class)
@AddPackages(CalcService2Test.class)
@AddPackages(LogSystem.class)
@ActivateScopes(RequestScoped.class)
class CalcService2Test {
//    @WeldSetup
//    public WeldInitiator weld = WeldInitiator
//            .from(CalcService.class, CalcLogObject.class).activate(RequestScoped.class).build();

    @Inject
    CalcService2 service;

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

    @Test
    void testSum_failure() {
        // given

        // when
        CalcResponse response = service.calcWithError(new CalcRequest(10, 5, Operation.SUM));

        // then
    }
}