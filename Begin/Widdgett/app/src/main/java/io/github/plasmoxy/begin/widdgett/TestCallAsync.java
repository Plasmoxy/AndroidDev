package io.github.plasmoxy.begin.widdgett;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class TestCallAsync {
    
    public void texst() {
        
        LoginTask t = new LoginTask(new Function1<String, Unit>() {
            @Override
            public Unit invoke(String s) {
                
                return null;
            }
        });
        
        t.execute("", "");
        
    }
    
}
