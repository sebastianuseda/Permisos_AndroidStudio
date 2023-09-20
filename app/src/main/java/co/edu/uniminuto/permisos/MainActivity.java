package co.edu.uniminuto.permisos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 100;
    private static final int REQUEST_CODE_MICROPHONE = 102;
    private static final int REQUEST_CODE_LOCATION = 103;
    private Button checkPermissions;
    private Button requestPermissions;
    private TextView tvCamera;
    private TextView tvDactilar;

    private TextView tvMicrophone;
    private TextView tvLocation;
    private Button btnCheckMicrophonePermissions;
    private Button btnRequestMicrophonePermissions;
    private Button btnCheckLocationPermissions;
    private Button btnRequestLocationPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin();
        checkPermissions.setOnClickListener(this::verificarPermisos);
        btnCheckMicrophonePermissions.setOnClickListener(this::verificarPermisosMicrofono);
        btnCheckLocationPermissions.setOnClickListener(this::verificarPermisosUbicacion);

    }

    private void verificarPermisos(View view){
        // 0 otorgado -1 no otorgado
        int statusPermisoCam = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA);
        int statusPermisoBio = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.USE_BIOMETRIC);
        tvCamera.setText("Estatus ver permisos de la camara:"+statusPermisoCam);
        tvDactilar.setText("Estatus ver permisos de la huella:"+statusPermisoBio);
        requestPermissions.setEnabled(true);
    }

    public void solicitarPermisos(View view){
        if ((ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)
                !=PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},REQUEST_CODE);

        }

    }

    private void verificarPermisosMicrofono(View view) {
        int statusPermisoMicrofono = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        tvMicrophone.setText("Estatus ver permisos de micrófono: " + statusPermisoMicrofono);

    }

    private void verificarPermisosUbicacion(View view) {
        int statusPermisoUbicacion = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        tvLocation.setText("Estatus ver permisos de ubicación: " + statusPermisoUbicacion);

    }

    public void solicitarPermisosMicrofono(View view) {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_MICROPHONE);
        }
    }

    public void solicitarPermisosUbicacion(View view) {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }
    }


    private void begin(){
        tvCamera = findViewById(R.id.tvCamera);
        tvDactilar =findViewById(R.id.tvBiometric);
        tvLocation =findViewById(R.id.tvLocation);
        tvMicrophone =findViewById(R.id.tvMicrophone);
        btnCheckLocationPermissions =findViewById(R.id.btnCheckLocationPermissions);
        btnCheckMicrophonePermissions =findViewById(R.id.btnCheckMicrophonePermissions);
        btnRequestLocationPermissions =findViewById(R.id.btnRequestLocationPermissions);
        btnRequestMicrophonePermissions =findViewById(R.id.btnRequestMicrophonePermissions);
        checkPermissions = findViewById(R.id.btnCheckPermissions);
        requestPermissions =findViewById(R.id.btnRequestPermissions);
        //Deshabilitar el botonrequest
        requestPermissions.setEnabled(false);
    }

}