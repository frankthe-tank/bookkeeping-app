package com.example.bookkeepingking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class AddInvoice extends AppCompatActivity implements View.OnClickListener{
   private static final String TAG="date";
    //for camera
    Button photo;
    Button pdf;
    Button finish;
    ImageView currentInvoice;
    TextView photofile;
    TextView pdfloc;
    private static final int PERMISSION_CODE=100;
    private static final int IMAGE_CAPTURE_CODE=1001;
    Uri image_uri;
    Intent myFileIntent;
    String filePath;
    String photoLocation;
    boolean photoTaken=false;
    String finalfile;
    boolean pdfSelected;
    boolean pdfchoice;
    boolean photoselected;


    //for date
    private TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);

        //camera
        currentInvoice = findViewById(R.id.invoiceTaken);
        photofile=findViewById(R.id.photofile);
        photo = (Button) findViewById(R.id.fileUpload);
        pdf= (Button) findViewById(R.id.pdfupload);
        pdfloc=findViewById(R.id.pdffile);
        finish=(Button) findViewById(R.id.done);


        //photo button clicked
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if system os is>= marshmallow, request runtime position
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        //request permission if not given
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //show popup requesting permission
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission already granted
                        if(pdfSelected==false) {
                            photoselected = true;
                            openCamera();
                        }
                        else{
                            Toast.makeText(AddInvoice.this, "Already selected pdf!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    //system os<marshmallow
                    openCamera();
                }
            }
        });
        pdf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(photoTaken==false) {
                    myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    myFileIntent.addCategory(myFileIntent.CATEGORY_OPENABLE);
                    myFileIntent.setType("application/pdf");
                    pdfchoice=true;
                    startActivityForResult(myFileIntent, 10);

                }
                else{
                    Toast.makeText(AddInvoice.this, "Already selected photo!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(photoTaken==false&&filePath==null)
                    finish();
                else if(photoTaken==false) {
                    finalfile=filePath;
                    finish();
                }
                else if(filePath !=null) {
                    finalfile = photoLocation;
                    finish();
                }
                else
                    finish();
            }
        });
    }
    public void openCamera(){

        ContentValues value=new ContentValues();
        value.put(MediaStore.Images.Media.TITLE,"New Picture");
        value.put(MediaStore.Images.Media.DESCRIPTION,"invoice");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value);
        //Camera intent

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);

    }




    //handles permission for camera
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
        //will activate once user presses allow or deny
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){
                    //was granted
                    openCamera();

                }
                else{
                    //permission denied
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        //if pdf selected
        if(resultCode==RESULT_OK) {
            if (pdfchoice == true) {
                pdfSelected=true;
                pdfchoice=false;
                filePath = data.getData().getPath();
                pdfloc.setText(filePath);

            }
            //if photo selected
            else if (photoselected == true) {
                //set image capture to image view
                photoTaken=true;
                photoselected=false;
                currentInvoice.setImageURI(image_uri);

                photoLocation = image_uri.getPath();

                photofile.setText(photoLocation);

            }
        }else {
            return;
        }

    }

    public void onClick(View view){
        if(view.getId()== R.id.fileUpload){

        }
    }

}