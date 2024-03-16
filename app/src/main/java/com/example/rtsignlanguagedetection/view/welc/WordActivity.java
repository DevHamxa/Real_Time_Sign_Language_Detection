package com.example.rtsignlanguagedetection.view.welc;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rtsignlanguagedetection.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;


import java.io.IOException;

public class WordActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2{
    static {
        if(OpenCVLoader.initDebug()){
            Log.d("mainactivitymanager: ","Opencv is loaded");
        }
        else {
            Log.d("mainactivitymanager: ","Opencv failed to load");
        }
    }
    private static final String TAG="mainactivitymanager";

    public final boolean[] isBackCamera = {true}; // The back camera is initially active.
    private Mat mRgba;
    //    private Mat mGray;
    private CameraBridgeViewBase mOpenCvCameraView;
    private signLanguageClass signLanguageClass;
    private ImageButton clear_button;
    private TextView change_text;



    private BaseLoaderCallback mLoaderCallback =new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface
                        .SUCCESS:{
                    Log.i(TAG,"OpenCv Is loaded");
                    mOpenCvCameraView.enableView();
                }
                default:
                {
                    super.onManagerConnected(status);

                }
                break;
            }
        }
    };

    public WordActivity(){
        Log.i(TAG,"Instantiated new "+this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        int MY_PERMISSIONS_REQUEST_CAMERA=0;
        // if camera permission is not given it will ask for it on device
        if (ContextCompat.checkSelfPermission(WordActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(WordActivity.this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

        setContentView(R.layout.activity_combine_letters);

        ImageButton switchCameraButton = findViewById(R.id.switch_Camera);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.frame_Surface);
        mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(WordActivity.this);
        switchCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.frame_Surface);
                if (isBackCamera[0]) {
                    mOpenCvCameraView.setCameraIndex(CameraBridgeViewBase.CAMERA_ID_FRONT);
                    isBackCamera[0] = false;
                } else {
                    mOpenCvCameraView.setCameraIndex(CameraBridgeViewBase.CAMERA_ID_BACK);
                    isBackCamera[0] = true;
                }
                refreshCameraPreview();
                mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
                mOpenCvCameraView.setCvCameraViewListener(WordActivity.this);
                Log.d("faloda", "Button is faloda");
            }
        });



        clear_button=findViewById(R.id.clear_button);
        change_text=findViewById(R.id.change_text);


        try{

            signLanguageClass=new signLanguageClass(clear_button, change_text,getAssets(),"hand_model.tflite",2,300, "classappmodel.tflite",96);
            Log.d("Cameraactivitymanager","Model is successfully loaded");
        }
        catch (IOException e){
            Log.d("Cameraactivitymanager","Getting some error");
            e.printStackTrace();
        }
    }

    private void refreshCameraPreview() {
        mOpenCvCameraView.disableView();
        mOpenCvCameraView.enableView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()){
            //if load success
            Log.d(TAG,"Opencv initialization is done");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else{
            //if not loaded
            Log.d(TAG,"Opencv is not loaded. try again");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION,this,mLoaderCallback);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mOpenCvCameraView !=null){
            mOpenCvCameraView.disableView();
        }
    }

    public void onDestroy(){
        super.onDestroy();
        if(mOpenCvCameraView !=null){
            mOpenCvCameraView.disableView();
        }

    }

    public void onCameraViewStarted(int width ,int height){
        mRgba=new Mat(height,width, CvType.CV_8UC4);
    }
    public void onCameraViewStopped(){
        mRgba.release();
    }
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame){
        mRgba=inputFrame.rgba();
        Mat out=new Mat();
        out=signLanguageClass.recognizeImage(mRgba, isBackCamera);
        return out;
    }

}