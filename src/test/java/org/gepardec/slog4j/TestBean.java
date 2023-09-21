package org.gepardec.slog4j;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TestBean {

    public int add(int x, int y){
        return x + y;
    }
}
