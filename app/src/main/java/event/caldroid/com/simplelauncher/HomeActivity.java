package event.caldroid.com.simplelauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by gaurav on 09/05/17.
 */

public class HomeActivity extends Activity {

    private Switch flashSwitch;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*
        flashSwitch = (Switch) findViewById(R.id.flash_intent);

        flashSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Camera.Parameters param;
                if(isChecked){

                    param = camera.getParameters();
                    param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(param);
                    camera.startPreview();
                }else {
                    param = camera.getParameters();
                    param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(param);
                    camera.startPreview();
                }
            }
        });
                */
    }

    public void showApps(View v){
        Intent i = new Intent(this, AppsListActivity.class);
        startActivity(i);
    }

    public void audioIntent(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card");
        intent.setDataAndType(uri,"audio/*");
        startActivityForResult(intent,30);
    }

    public void videoIntent(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card");
        intent.setDataAndType(uri,"video/*");
        startActivityForResult(intent,20);
    }

    public void pdfIntent(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card");
        intent.setDataAndType(uri,"pdf/*");
        startActivityForResult(intent,10);
    }

    public void gameIntent(View v){
        Intent i;
        PackageManager manager = getPackageManager();
        try {
            i = manager.getLaunchIntentForPackage("com.king.candycrushsaga");
            if (i == null)
                throw new PackageManager.NameNotFoundException();
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {

        }
    }


    public void contact(View v){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,40);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode == RESULT_OK && requestCode == 10){
            Uri uriPdf = data.getData();
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(uriPdf,"pdf/*");
            startActivity(pdfIntent);
        }

        if (resultCode == RESULT_OK && requestCode == 20){
            Uri uriVideo = data.getData();
            Intent tostart = new Intent(Intent.ACTION_VIEW);
            tostart.setDataAndType(uriVideo,"video/*");
            startActivity(tostart);

        }

        if(resultCode == RESULT_OK && requestCode== 30){
            Uri uriVidio = data.getData();
            Intent videoIntent = new Intent(Intent.ACTION_VIEW);
            videoIntent.setDataAndType(uriVidio,"audio/*");
            startActivity(videoIntent);
        }

        if(resultCode == RESULT_OK && requestCode == 40){
            Uri contactUri = data.getData();
            Intent contactIntent = new Intent(Intent.ACTION_CALL);
            contactIntent.setData(contactUri);
            startActivity(contactIntent);
        }
    }
}
