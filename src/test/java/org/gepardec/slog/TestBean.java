package org.gepardec.slog;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TestBean {

    public int add(int x, int y){
        return x + y;
    }
}
