package event.caldroid.com.simplelauncher;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

/**
 * Created by gaurav on 09/05/17.
 */

/**
 * Simple Launcher starts when you click on home button
 */

public class HomeActivity extends Activity {
    DevicePolicyManager devicePolicyManager;
    ComponentName adminComponent;
    Button apps;
    ViewPager viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_home_activity);

        viewPage = (ViewPager) findViewById(R.id.viewpager);
        viewPage.setAdapter(new CustomPageAdapter(this));
        apps = (Button) findViewById(R.id.main_menu);

        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AppsListActivity.class);
                startActivity(i);
            }
        });

    }
    public void lockScreen(View view){
        Button lock = (Button) findViewById(R.id.button_lock);
        Settings.System.putInt(getContentResolver(),Settings.System.SCREEN_OFF_TIMEOUT,15000);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminComponent = new ComponentName(HomeActivity.this, AdminReceiver.class);
                devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

                if (!devicePolicyManager.isAdminActive(adminComponent)) {

                    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
                    startActivityForResult(intent, 50);
                } else {
                    devicePolicyManager.lockNow();
                }
            }
        });
    }

    public void coverFlow(View view){
        viewPage.setPageTransformer(false, new ViewPager.PageTransformer(){

            @Override
            public void transformPage(View page, float position) {

                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);

            }
        });
    }

    public void scroll(View view){
        viewPage.setPageTransformer(false, new ViewPager.PageTransformer(){

            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position * -30);
            }
        });
    }

    public void contact(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,10);
        }
    }


    public void audioIntent(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card");
        intent.setDataAndType(uri,"audio/*");
        startActivityForResult(intent,20);
    }

    public void videoIntent(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card");
        intent.setDataAndType(uri,"video/*");
        startActivityForResult(intent,30);
    }

    public void pdfIntent(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext.card");
        intent.setDataAndType(uri,"pdf/*");
        startActivityForResult(intent,40);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 10 && resultCode == RESULT_OK){
            Uri contactUri = data.getData();
            Intent contactIntent = new Intent(Intent.ACTION_CALL);
            contactIntent.setData(contactUri);
            startActivity(contactIntent);
        }

        if(resultCode == RESULT_OK && requestCode == 20){
            Uri audioUri = data.getData();
            Intent audioIntent = new Intent(Intent.ACTION_VIEW);
            audioIntent.setDataAndType(audioUri,"audio/*");
            startActivity(audioIntent);
        }

        if(requestCode == 30 && resultCode == RESULT_OK){
            Uri videoUri = data.getData();
            Intent videoIntent = new Intent(Intent.ACTION_VIEW);
            videoIntent.setDataAndType(videoUri,"video/*");
            startActivity(videoIntent);
        }

        if(requestCode ==  40 && resultCode == RESULT_OK){
            Uri pdfUri = data.getData();
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(pdfUri,"pdf/*");
            startActivity(pdfIntent);
        }

    }
}
