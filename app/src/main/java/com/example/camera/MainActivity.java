package com.example.camera;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView) findViewById(R.id.image_screen);
        button=(Button) findViewById(R.id.make_screen);

//        if(ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
//                    Manifest.permission.CAMERA
//            },100);
//        }
    }

    public void btn_camera(View view) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent,100);
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.CAMERA
            },100);
        }else{
            ScanOptions options = new ScanOptions();
            options.setPrompt("Скан кода");
            options.setBeepEnabled(false);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CaptureAct.class);
            barLauncher.launch(options);
        }
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result -> {
        int answer = Integer.parseInt(result.getContents());
        if(answer==1){
            Intent intent = new Intent(MainActivity.this, CatActivity.class);
            startActivity(intent);
            finish();
        }
        else if (answer==2) {
            Intent intent = new Intent(MainActivity.this, DogActivity.class);
            startActivity(intent);
            finish();
        }
        else if (answer==3){
            Intent intent = new Intent(MainActivity.this, DogsActivity.class);
            startActivity(intent);
            finish();
        }
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("Результат");
//            builder.setMessage(result.getContents());
//            builder.show();

    });

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

}