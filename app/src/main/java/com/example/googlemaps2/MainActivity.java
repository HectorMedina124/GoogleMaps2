package com.example.googlemaps2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener,GoogleMap.OnInfoWindowClickListener, AdapterView.OnItemSelectedListener, GoogleMap.OnMapClickListener , GoogleMap.OnMapLongClickListener {
    private FirstMapFragment mFirstMapFragment;
    private static final int LOCATION_REQUEST_CODE = 1;
    private  GoogleMap mMap;
    private Marker markerPais;
    private Marker markerEcuador;
    private Marker markerArgentina;
    private Spinner mMapTypeSelector;
    private int mMapTypes[] = {
            GoogleMap.MAP_TYPE_NONE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstMapFragment=FirstMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.Layout_principal, mFirstMapFragment)
                .commit();
        // Registrar escucha onMapReadyCallback
        mFirstMapFragment.getMapAsync(this);
        mMapTypeSelector = findViewById(R.id.map_type_selector);
        mMapTypeSelector.setOnItemSelectedListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.moveCamera(CameraUpdateFactory.zoomBy(20));
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        // Coordenadas Japón
        LatLng latLng = new LatLng(36.2048, 138.2529);
        MarkerOptions markerOptions =
                new MarkerOptions()
                        .position(latLng)
                        .title("Japón Marcador cyan")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                        .snippet("Primer ministro: Shinzō Abe");
        Marker marker = googleMap.addMarker(markerOptions);
        LatLng Micasa = new LatLng(19.431104, -100.349610);
        googleMap.addMarker(new MarkerOptions()
                .position(Micasa)
                .title("Marcador con icono personalizado")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag
                )));
        //Escucha
        LatLng mexico = new LatLng(24.267696,-102.341350);
         markerPais = googleMap.addMarker(new MarkerOptions()
                .position(mexico)
                .title("Mexico")
        );
              // Cámara
      //  googleMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
        googleMap.setOnMarkerClickListener(this);

        //Drag
        LatLng ecuador = new LatLng(-0.217, -78.51);
        markerEcuador = googleMap.addMarker(new MarkerOptions()
                .position(ecuador)
                .title("Ecuador")
                .draggable(true)
        );
              // Cámara
       //googleMap.moveCamera(CameraUpdateFactory.newLatLng(ecuador));
             // Eventos
        googleMap.setOnMarkerDragListener(this);
        //INFORMACION
        LatLng argentina = new LatLng(-34.6, -58.4);
        markerArgentina = googleMap.addMarker(
                new MarkerOptions()
                        .position(argentina)
                        .title("Argentina")
        );
// Cámara
       // googleMap.moveCamera(CameraUpdateFactory.newLatLng(argentina));
// Eventos
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnInfoWindowClickListener(this);


        /*LatLng cali = new LatLng(3.4383, -76.5161);
        googleMap.addMarker(new MarkerOptions()
                .position(cali)
                .title("Cali la Sucursal del cielo"));
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(cali)
                .zoom(10)
                .build();
                 googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition ));*/

        //No permitir desplazamiento
        /*GoogleMap mMap = googleMap;
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setTiltGesturesEnabled(false);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(40.3839, -100.9565), 2));*/

        /*LatLng nicaragua = new LatLng(13, -85);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nicaragua,
                7));*/
//españa
        /*LatLng españa = new LatLng(40.416667, -3.75);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(españa)
                .zoom(7)
                .bearing(90)
                .tilt(90)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/


        // Ejemplo: Delimitar a Sudamérica con un rectángulo
        PolylineOptions sudamericaRect = new PolylineOptions();
                sudamericaRect.add(new LatLng(12.897489, -82.441406)); // P1
        sudamericaRect.add(new LatLng(12.897489, -32.167969)); // P2
        sudamericaRect.add(new LatLng(-55.37911, -32.167969)); // P3
        sudamericaRect.add(new LatLng(-55.37911, -82.441406)); // P4
        sudamericaRect.add(new LatLng(12.897489, -82.441406)); // P1
        sudamericaRect.color(Color.parseColor("#f44336")); // Rojo 500
        Polyline polyline = mMap.addPolyline(sudamericaRect);
        /*mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(-20.96144, -
                61.347656)));*/

        // Ejemplo: Encerrar a Cuba con un polígono de bajo detalle
        LatLng p1 = new LatLng(21.88661065803621, -85.01541511562505);
        LatLng p2 = new LatLng(22.927294359193038, -83.76297370937505);
        LatLng p3 = new LatLng(23.26620799401109, -82.35672370937505);
        LatLng p4 = new LatLng(23.387267854439315, -80.79666511562505);
        LatLng p5 = new LatLng(22.496957602618004, -77.98416511562505);
        LatLng p6 = new LatLng(20.20512046753661, -74.16092292812505);
        LatLng p7 = new LatLng(19.70944706110937, -77.65457527187505);

        Polygon cubaPolygon = mMap.addPolygon(new PolygonOptions()
                .add(p1, p2, p3, p4, p5, p6, p7, p1)
                .strokeColor(Color.parseColor("#AB47BC"))
                .fillColor(Color.parseColor("#7B1FA2")));

        /*mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(21.5034305704608, -78.95096199062505), 5));*/

// Ejemplo: Crear círculo con radio de 40m
// y centro (3.4003755294523828, -76.54801384952702)
        LatLng center = new LatLng(19.433231, -100.345671);
        int radius = 60;

        CircleOptions circleOptions = new CircleOptions()
                .center(center)
                .radius(radius)
                .strokeColor(Color.parseColor("#2D99CE"))
                .strokeWidth(4)
                .fillColor(Color.argb(45, 45, 188, 206));

        Circle circle= mMap.addCircle(circleOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 17));

        //---------------eventos en el mapa--------------------------------------------------------------
        mMap.setOnMapClickListener(this);

        // Configuración UI
        mMap.getUiSettings().setAllGesturesEnabled(false);
        // Eventos
        mMap.setOnMapLongClickListener(this);



        // Controles UI
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Marcadores
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(markerPais)) {

            mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()),
                    new GoogleMap.CancelableCallback() {
                        @Override
                        public void onFinish() {
                            Intent intent = new Intent(MainActivity.this, MarkerDetailActivity.class);
                            intent.putExtra("EXTRA_LATITUD",
                                    markerPais.getPosition().latitude);
                            intent.putExtra("EXTRA_LONGITUD",
                                    markerPais.getPosition().longitude);
                            startActivity(intent);
                        }
                        @Override
                        public void onCancel() {
                        }
                    });
            return true;
        }
        return false;
        }


    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerEcuador)) {
            Toast.makeText(this, "START", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerEcuador)) {
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);
            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerEcuador)) {
            Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(markerArgentina)) {
            ArgentinaDialogFragment.newInstance(marker.getTitle(),
                    getString(R.string.argentina_full_snippet))
                    .show(getSupportFragmentManager(), null);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mMap.setMapType(mMapTypes[i]);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.add("Inicio").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
            if (title != null && title.equals("Inicio")) {
                LatLng zero = new LatLng(80, 80);
                mMap.animateCamera(
                        CameraUpdateFactory.newLatLng(zero), // update
                        1000, // durationMs
                        new GoogleMap.CancelableCallback() { // callback
                            @Override
                            public void onFinish() {
                                Toast.makeText(MainActivity.this, "Animación finalizada",
                                        Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onCancel() {
                            }
                        });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        String formatLatLng = String.format(Locale.getDefault(),
                "Lat/Lng = (%f,%f)", latLng.latitude, latLng.longitude);
        Point screentPoint = mMap.getProjection().toScreenLocation(latLng);
        String formatScreenPoint = String.format(Locale.getDefault(),
                "\nPoint = (%d,%d)", screentPoint.x, screentPoint.y);
        Toast.makeText(this, formatLatLng + formatScreenPoint,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // Añadir marker en la posición
        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng));
        // Obtener pixeles reales
        Point point = mMap.getProjection().toScreenLocation(latLng);
        // Determinar el ancho total de la pantalla
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int width = display.widthPixels;
        float hue;
        // ¿La coordenada de pantalla es menor o igual que la mitad del ancho?
        if (point.x <= width / 2) {
            hue = BitmapDescriptorFactory.HUE_GREEN;
        } else {
            hue = BitmapDescriptorFactory.HUE_ORANGE;
        }
        // Cambiar color del marker según el grupo
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(hue));
    }
}