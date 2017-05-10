# Launcher
This is the simple launcher application which is use as a launcher for android devices.

## How it works:-
* This is the simple android application without default activity so if you have to launch this application you have to press home button then the device shows number of launcher installed on your device if you dont have installed launcher except this one then after pressing home button you will be redirect to Simple launcher application.

## How to create launcher application in AndroidStudio:-
1 This is the launcher application so there is no default activity present, So at the time of project creation in android studio you have to select "No avtivity" option.
2 After that once the project created successfully you have to create Home activity which is open when you press home button from device.

### Steps to create Home activity:-
* Right click on src folder -> select new java class -> enter the name for java class, Here we declare name as HomeActivity.java -> click on create button.
* Write below code to your HomeActivity class.

``` java

package event.caldroid.com.simplelauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.View;

/**
 * Created by gaurav on 09/05/17.
 */

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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


```

## Steps to create activity_home.xml file:-
* acticity_home.xml file is nothing but the UI for your home screen.
* To create activity_home.xml file right click on layout folder present in src/res/ -> choose resource layout file -> write the name for resource file -> click on create button.
* Write below code in activity_home.xml file:-

 ``` xml

<?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    android:layout_alignParentBottom="true"
    tools:context=".HomeActivity"
    android:background="@drawable/background">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom">

        <Button
            android:id="@+id/button_contact"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@drawable/ic_contact"
            android:layout_marginLeft="10dp"
            android:onClick="contact"/>

        <Button
            android:id="@+id/button_message"
            android:background="@drawable/ic_message"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_toRightOf="@+id/button_contact"/>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/main_menu"
            android:onClick="showApps"
            android:layout_toRightOf="@+id/button_message"
            android:background="@drawable/ic_main_menu"/>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/button_music"
            android:layout_toRightOf="@+id/main_menu"
            android:background="@drawable/ic_music"
            android:onClick="audioIntent"/>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/button_pdf"
            android:layout_toRightOf="@+id/button_music"
            android:background="@drawable/ic_pdf"
            android:onClick="pdfIntent"/>
    </RelativeLayout>

</LinearLayout>


 ```

