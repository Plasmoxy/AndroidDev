package io.github.plasmoxy.begin.helloworld;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
    
    private Context ctx;
    
    public Toaster(Context c) {
        ctx = c;
    }
    
    public void make(CharSequence seq) {
        Toast.makeText(ctx, seq, Toast.LENGTH_SHORT).show();
    }
    
    public void makeLong(CharSequence seq) {
        Toast.makeText(ctx, seq, Toast.LENGTH_LONG).show();
    }
    
}
