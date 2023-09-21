package org.gepardec.slog;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableWeld
class CdiTest {

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(TestBean.class).activate(RequestScoped.class).build();

    @Inject
    TestBean testBean;

    @Test
	void doSomethingTest() {

        int sum = testBean.add(1, 2);
        assertEquals(3, sum);
    }
}
