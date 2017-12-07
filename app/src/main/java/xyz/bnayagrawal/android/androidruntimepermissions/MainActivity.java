package xyz.bnayagrawal.android.androidruntimepermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSION_READ_CALENDAR = 01,
            PERMISSION_CAMERA = 02,
            PERMISSION_READ_CONTACTS = 03,
            PERMISSION_ACCESS_FINE_LOCATION = 04,
            PERMISSION_READ_CALL_LOG = 05,
            PERMISSION_READ_SMS = 06;

    private HashMap<Switch,String> switches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switches = new HashMap<>();
        switches.put((Switch) findViewById(R.id.switch_read_calendar), Manifest.permission.READ_CALENDAR);
        switches.put((Switch) findViewById(R.id.switch_camera),Manifest.permission.CAMERA);
        switches.put((Switch) findViewById(R.id.switch_read_contacts),Manifest.permission.READ_CONTACTS);
        switches.put((Switch) findViewById(R.id.switch_access_fine_location),Manifest.permission.ACCESS_FINE_LOCATION);
        switches.put((Switch) findViewById(R.id.switch_read_call_log),Manifest.permission.READ_CALL_LOG);
        switches.put((Switch) findViewById(R.id.switch_read_sms),Manifest.permission.READ_SMS);

        updatePermissionInfo();
        setSwitchListeners();
    }


    protected void setSwitchListeners() {
        for(final HashMap.Entry<Switch,String> entry: switches.entrySet()) {
            entry.getKey().setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            if(isChecked) {
                                if(ContextCompat.checkSelfPermission(getApplicationContext(),
                                        entry.getValue())
                                        != PackageManager.PERMISSION_GRANTED)
                                    requestPermission(entry.getValue(),getRequestCode(entry.getValue()));
                                else
                                    Toast.makeText(getApplicationContext(),"LOL, You already have granted this permission",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        }
    }

    protected void updatePermissionInfo() {
        for(final HashMap.Entry<Switch,String> entry: switches.entrySet()) {
            if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getApplicationContext(),
                    entry.getValue()))
                entry.getKey().setChecked(true);
        }
    }

    protected int getRequestCode(String permission) {
        int code = 0;
        switch (permission) {
            case Manifest.permission.READ_CALENDAR:
                code = PERMISSION_READ_CALENDAR;
                break;
            case Manifest.permission.CAMERA:
                code = PERMISSION_CAMERA;
                break;
            case Manifest.permission.READ_CONTACTS:
                code = PERMISSION_READ_CONTACTS;
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
                code = PERMISSION_ACCESS_FINE_LOCATION;
                break;
            case Manifest.permission.READ_CALL_LOG:
                code = PERMISSION_READ_CALL_LOG;
                break;
            case Manifest.permission.READ_SMS:
                code = PERMISSION_READ_SMS;
                break;
        }
        return code;
    }

    protected void requestPermission(final String permission, int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                permission)) {
            Toast.makeText(this,"Why on earth you disabled this permission?",Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_CALENDAR: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    ((Switch) findViewById(R.id.switch_read_calendar)).setChecked(true);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    ((Switch) findViewById(R.id.switch_read_calendar)).setChecked(false);
                }
                break;
            }
            case PERMISSION_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ((Switch) findViewById(R.id.switch_camera)).setChecked(true);
                } else {
                    ((Switch) findViewById(R.id.switch_camera)).setChecked(false);
                }
                break;
            }
            case PERMISSION_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ((Switch) findViewById(R.id.switch_read_contacts)).setChecked(true);
                } else {
                    ((Switch) findViewById(R.id.switch_read_contacts)).setChecked(false);
                }
                break;
            }
            case PERMISSION_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ((Switch) findViewById(R.id.switch_access_fine_location)).setChecked(true);
                } else {
                    ((Switch) findViewById(R.id.switch_access_fine_location)).setChecked(false);
                }
                break;
            }
            case PERMISSION_READ_CALL_LOG:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ((Switch) findViewById(R.id.switch_read_call_log)).setChecked(true);
                } else {
                    ((Switch) findViewById(R.id.switch_read_call_log)).setChecked(false);
                }
                break;
            }
            case PERMISSION_READ_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ((Switch) findViewById(R.id.switch_read_sms)).setChecked(true);
                } else {
                    ((Switch) findViewById(R.id.switch_read_sms)).setChecked(false);
                }
                break;
            }
        }
    }
}
