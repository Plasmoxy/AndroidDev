package io.github.plasmoxy.begin.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

	protected Toaster toaster = new Toaster(this);
	
	protected Button topButton;
	protected Button botButton;
	protected TextView helloText;

	private final String[] messages = {
			"uyyy", "fgt", "y u do dis", "somebody T O U C H E by btn", "KYSKYSKYS", "neinnein99", "cyka blyat"
	};
	
	private MapView mapView;
	private GoogleMap gmap;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		topButton = findViewById(R.id.topButton);
		botButton = findViewById(R.id.botButton);
		helloText = findViewById(R.id.helloText);

		mapView = findViewById(R.id.mapView);
		mapView.onCreate(savedInstanceState);
		mapView.getMapAsync(this);
		
		toaster.make("APP LAUNCHED");
	}
	
	public void changeMessage() {
		helloText.setText(messages[(int)(Math.random()*messages.length)]);
	}


	public void topClick(View v) {
		changeMessage();
	}

	public void bottomClick(View v) {
		toaster.make("BOOTOM BUTNNE PRESSE XDDD");
		changeMessage();
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		gmap = googleMap;
		gmap.getUiSettings().setMyLocationButtonEnabled(true);
		gmap.setMinZoomPreference(12);
		LatLng ny = new LatLng(40.7143528, -74.0059731);
		gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
		toaster.make("MAP READY");
	}
}
