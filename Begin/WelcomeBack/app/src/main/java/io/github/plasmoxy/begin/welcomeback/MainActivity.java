package io.github.plasmoxy.begin.welcomeback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ImageView image = findViewById(R.id.imageView);
		final Button b = findViewById(R.id.button);

		final TextView text = findViewById(R.id.textView);
		final Button generat = findViewById(R.id.generat);

		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				image.setVisibility(View.VISIBLE);
			}
		});

	}
}
