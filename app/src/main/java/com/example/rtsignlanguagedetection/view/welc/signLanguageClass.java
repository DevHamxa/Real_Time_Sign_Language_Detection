package com.example.rtsignlanguagedetection.view.welc;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.TreeMap;
import android.os.Handler;
import android.os.Looper;
import org.tensorflow.lite.gpu.GpuDelegate;

public class  signLanguageClass {
    private Interpreter interpreter;
    private Interpreter interpreter2;
    private Interpreter interpreter3;
    private int INPUT_SIZE;
    private int check=0;
    private int height=0;
    private  int width=0;
    private int Classification_InputSize=0;
    private String final_text="";
    private String current_text="";
    private Handler handler;
    private Runnable addLetterRunnable;
    private boolean isLetterBeingAdded = false;

    signLanguageClass(ImageButton clear_button, TextView change_text, AssetManager assetManager, String modelPath, int labelClass, int inputSize, String classification_model, int classification_inputsize) throws IOException{
        check=labelClass;
        INPUT_SIZE=inputSize;
        Classification_InputSize=classification_inputsize;

        Interpreter.Options options = new Interpreter.Options();
        GpuDelegate gpuDelegate = new GpuDelegate();
        options.addDelegate(gpuDelegate);
        options.setNumThreads(4);

        Interpreter.Options options3=new Interpreter.Options();
        options3.setNumThreads(4);
        interpreter3 = new Interpreter(loadModelFile(assetManager, modelPath), options3);
        interpreter=new Interpreter(loadModelFile(assetManager,modelPath),options);

        Interpreter.Options options2=new Interpreter.Options();
        options2.setNumThreads(2);
        interpreter2=new Interpreter(loadModelFile(assetManager, classification_model), options2);

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lengthstr1 = final_text.length();
                if(lengthstr1>=1)
                {
                    final_text = final_text.substring(0, final_text.length() - 1);
                }
                change_text.setText(final_text);
            }
        });

        handler = new Handler(Looper.getMainLooper());
        addLetterRunnable = new Runnable() {
            @Override
            public void run() {
                final_text = final_text + current_text;
                int lengthstr = final_text.length();
                char characterspace = final_text.charAt(lengthstr - 1);
                if(lengthstr>=70 && characterspace == ' ' )
                {
                    final_text="";
                }
                if(lengthstr>=100)
                {
                    final_text="";
                }
                change_text.setText(final_text);
                isLetterBeingAdded = false;
            }
        };
    }

    private ByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        // use to get description of file
        AssetFileDescriptor fileDescriptor=assetManager.openFd(modelPath);
        FileInputStream inputStream=new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel=inputStream.getChannel();
        long startOffset =fileDescriptor.getStartOffset();
        long declaredLength=fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset,declaredLength);
    }

    public Mat recognizeImage(Mat mat_image, boolean[] isbackcamera){
        // Rotating original image by 90 degree to get portrait frame
        Mat rotated_mat_image=new Mat();
        Mat a=mat_image.t();
        if(isbackcamera[0])
        {
            Core.flip(a,rotated_mat_image,1);
        }
        else {
            Core.flip(a,rotated_mat_image,-1);
        }

        a.release();

        Bitmap bitmap=null;
        bitmap=Bitmap.createBitmap(rotated_mat_image.cols(),rotated_mat_image.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(rotated_mat_image,bitmap);
        height=bitmap.getHeight();
        width=bitmap.getWidth();

        // scale the bitmap to input size of model
        Bitmap scaledBitmap=Bitmap.createScaledBitmap(bitmap,INPUT_SIZE,INPUT_SIZE,false);
        // convert bitmap to bytebuffer as model input should be in it
        ByteBuffer byteBuffer=convertBitmapToByteBuffer(scaledBitmap);
        Object[] input=new Object[1];
        input[0]=byteBuffer;
        Map<Integer,Object> output_map=new TreeMap<>();
        // create treemap of three array (boxes,score,classes)

        float[][][]boxes =new float[1][10][4];
        // 10: top 10 object detected
        // 4:  coordinates in image
        float[][] scores=new float[1][10];
        // stores scores of 10 object
        float[][] classes=new float[1][10];
        // stores class of object

        // adding it to object_map;
        output_map.put(0,boxes);
        output_map.put(1,classes);
        output_map.put(2,scores);

        // Predicting
        try {
            interpreter.runForMultipleInputsOutputs(input,output_map);
            Log.d("DebugProcess1","delegate is used");
        } catch (Exception e) {
            interpreter3.runForMultipleInputsOutputs(input,output_map);
            Log.d("DebugProcess2","delegate not used");
        }


        // drawing boxes and label it with it's name
        Object value=output_map.get(0);
        Object Object_class=output_map.get(1);
        Object score=output_map.get(2);

        // looping through each object
        // as output has only 10 boxes
        for (int i=0;i<10;i++){
            float class_value=(float) Array.get(Array.get(Object_class,0),i);
            float score_value=(float) Array.get(Array.get(score,0),i);

            // define threshold for score
            // changeable threshold according to the model
            if(score_value>0.5){
                Object box1=Array.get(Array.get(value,0),i);

                // multiplying it with Original height and width of frame
                float y1=(float) Array.get(box1,0)*height;
                float x1=(float) Array.get(box1,1)*width;
                float y2=(float) Array.get(box1,2)*height;
                float x2=(float) Array.get(box1,3)*width;

                //setting boundary limit
                if(y1<0){
                    y1=0;
                }
                if(x1<0){
                    x1=0;
                }
                if(x2>width){
                    x2=width;
                }
                if(y2>height){
                    y2=height;
                }
                float w1=x2-x1;
                float h1=y2-y1;


                //croping hand from original frame
                Rect cropped_roi=new Rect((int)x1, (int)y1, (int)w1, (int)h1);
                Mat cropped=new Mat(rotated_mat_image, cropped_roi).clone();
                Bitmap bitmap1=null;
                bitmap1=Bitmap.createBitmap(cropped.cols(), cropped.rows(),Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(cropped, bitmap1);
                Bitmap scaledBitmap1=Bitmap.createScaledBitmap(bitmap1, Classification_InputSize, Classification_InputSize, false);
                ByteBuffer byteBuffer1= convertBitmapToByteBuffer1(scaledBitmap1);
                float[][] output_class_value=new float[1][31];
                interpreter2.run(byteBuffer1, output_class_value);
                int maxIndex = 0;
                float maxValue = output_class_value[0][0];
                for (int ind = 1; ind < output_class_value[0].length; ind++) {
                    if (output_class_value[0][ind] > maxValue) {
                        maxValue = output_class_value[0][ind];
                        maxIndex = ind;
                    }
                }
                Log.d("signLanguageClass", "output_class_value: " +maxIndex);

                String sign_val;
                if(check==2)
                {
                    sign_val= get_alphabets1(maxIndex);
                }
                else {
                    sign_val= get_alphabets(output_class_value[0][0]);
                }


                // Checking if the letter is already being added
                if (!isLetterBeingAdded) {
                    // If not, start adding the letter after a delay of 2 seconds
                    handler.postDelayed(addLetterRunnable, 2000);
                    isLetterBeingAdded = true;
                }
                // Checking if the current recognized letter is different from the previously recognized letter
                if (!sign_val.equals(current_text)) {
                    // Cancel the delayed addition if the letter is different
                    handler.removeCallbacks(addLetterRunnable);
                    isLetterBeingAdded = false;
                }
                current_text=sign_val;


                float centerX = (x1 + x2) / 2;
                float centerY = (y1 + y2) / 2;
                Size textSize = Imgproc.getTextSize("" + sign_val, 2, 1.5, 2, null);
                double textX = centerX - textSize.width / 2;
                double textY = centerY + textSize.height / 2;
                //Imgproc.putText(rotated_mat_image, ""+sign_val,new Point(x1+10,y1+40),2,1.5,new Scalar(255, 255, 255, 255),2);
                Imgproc.putText(rotated_mat_image, ""+sign_val,new Point(textX,textY),2,1.5,new Scalar(255, 0, 0, 255),2);
                Imgproc.rectangle(rotated_mat_image,new Point(x1,y1),new Point(x2,y2),new Scalar(255, 0, 0, 255),2);


            }
        }

        Mat b=rotated_mat_image.t();
        Core.flip(b,mat_image,0);
        b.release();
        // return to CameraBridgeViewBase
        return mat_image;
    }
    private String get_alphabets(float sign_v) {
        String val= "";
        if (sign_v >= -0.5 & sign_v < 0.5){
            val= "A";
        }
        else if (sign_v >= 0.5 & sign_v < 1.5){
            val= "B";
        }
        else if (sign_v >= 1.5 & sign_v < 2.5){
            val= "C";
        }
        else if (sign_v >= 2.5 & sign_v < 3.5){
            val= "D";
        }
        else if (sign_v >= 3.5 & sign_v < 4.5){
            val= "E";
        }
        else if (sign_v >= 4.5 & sign_v < 5.5){
            val= "F";
        }
        else if (sign_v >= 5.5 & sign_v < 6.5){
            val= "G";
        }
        else if (sign_v >= 6.5 & sign_v < 7.5){
            val= "H";
        }
        else if (sign_v >= 7.5 & sign_v < 8.5){
            val= "I";
        }
        else if (sign_v >= 8.5 & sign_v < 9.5){
            val= "J";
        }
        else if (sign_v >= 9.5 & sign_v < 10.5){
            val= "K";
        }
        else if (sign_v >= 10.5 & sign_v < 11.5){
            val= "L";
        }
        else if (sign_v >= 11.5 & sign_v < 12.5){
            val= "M";
        }
        else if (sign_v >= 12.5 & sign_v < 13.5){
            val= "N";
        }
        else if (sign_v >= 13.5 & sign_v < 14.5){
            val= "O";
        }
        else if (sign_v >= 14.5 & sign_v < 15.5){
            val= "P";
        }
        else if (sign_v >= 15.5 & sign_v < 16.5){
            val= "Q";
        }
        else if (sign_v >= 16.5 & sign_v < 17.5){
            val= "R";
        }
        else if (sign_v >= 17.5 & sign_v < 18.5){
            val= "S";
        }
        else if (sign_v >= 18.5 & sign_v < 19.5){
            val= "T";
        }
        else if (sign_v >= 19.5 & sign_v < 20.5){
            val= "U";
        }
        else if (sign_v >= 20.5 & sign_v < 21.5){
            val= "V";
        }
        else if (sign_v >= 21.5 & sign_v < 22.5){
            val= "W";
        }
        else if (sign_v >= 22.5 & sign_v < 23.5){
            val= "X";
        }
        else {
            val= "Y";
        }

        return val;
    }

    private String get_alphabets1(float sign_v) {
        String val= "";
        if (sign_v >= -0.5 & sign_v < 0.5){
//            val= "A"+ sign_v;
            val= "A";
        }
        else if (sign_v >= 0.5 & sign_v < 1.5){
//            val= "B"+ sign_v;
            val= "B";
        }
        else if (sign_v >= 1.5 & sign_v < 2.5){
//            val= "C"+ sign_v;
            val= "C";
        }
        else if (sign_v >= 2.5 & sign_v < 3.5){
//            val= "D"+ sign_v;
            val= "D";
        }
        else if (sign_v >= 3.5 & sign_v < 4.5){
//            val= "E"+ sign_v;
            val= "E";
        }
        else if (sign_v >= 4.5 & sign_v < 5.5){
//            val= "F"+ sign_v;
            val= "F";
        }
        else if (sign_v >= 5.5 & sign_v < 6.5){
//            val= "G"+ sign_v;
            val= "G";
        }
        else if (sign_v >= 6.5 & sign_v < 7.5){
//            val= "H"+ sign_v;
            val= "H";
        }
        else if (sign_v >= 7.5 & sign_v < 8.5){
//            val= "I"+ sign_v;
            val= "I";
        }
        else if (sign_v >= 8.5 & sign_v < 9.5){
//            val= "J"+ sign_v;
            val= "J";
        }
        else if (sign_v >= 9.5 & sign_v < 10.5){
//            val= "K"+ sign_v;
            val= "K";
        }
        else if (sign_v >= 10.5 & sign_v < 11.5){
//            val= "L"+ sign_v;
            val= "L";
        }
        else if (sign_v >= 11.5 & sign_v < 12.5){
//            val= "M"+ sign_v;
            val= "M";
        }
        else if (sign_v >= 12.5 & sign_v < 13.5){
//            val= "N"+ sign_v;
            val= "N";
        }
        else if (sign_v >= 13.5 & sign_v < 14.5){
//            val= "O"+ sign_v;
            val= "O";
        }
        else if (sign_v >= 14.5 & sign_v < 15.5){
//            val= "P"+ sign_v;
            val= "P";
        }
        else if (sign_v >= 15.5 & sign_v < 16.5){
//            val= "Q"+ sign_v;
            val= "Q";
        }
        else if (sign_v >= 16.5 & sign_v < 17.5){
//            val= "R"+ sign_v;
            val= "R";
        }
        else if (sign_v >= 17.5 & sign_v < 18.5){
//            val= "S"+ sign_v;
            val= "S";
        }
        else if (sign_v >= 18.5 & sign_v < 19.5){
//            val= "T"+ sign_v;
            val= "T";
        }
        else if (sign_v >= 19.5 & sign_v < 20.5){
//            val= "U"+ sign_v;
            val= "U";
        }
        else if (sign_v >= 20.5 & sign_v < 21.5){
//            val= "V"+ sign_v;
            val= "V";
        }
        else if (sign_v >= 21.5 & sign_v < 22.5){
//            val= "W"+ sign_v;
            val= "W";
        }
        else if (sign_v >= 22.5 & sign_v < 23.5){
//            val= "X"+ sign_v;
            val= "X";
        }
        else if (sign_v >= 23.5 & sign_v < 24.5){
//            val= "Y"+ sign_v;
            val= "Y";
        }
        else if (sign_v >= 24.5 & sign_v < 25.5){
//            val= "Z"+ sign_v;
            val= "Z";
        }
        else if (sign_v >= 25.5 & sign_v < 26.5){
//            val= "HELLO "+ sign_v;
            val= "HELLO ";
        }
        else if (sign_v >= 26.5 & sign_v < 27.5){
//            val= "MY "+ sign_v;
            val= "MY ";
        }
        else if (sign_v >= 27.5 & sign_v < 28.5){
//            val= "NAME "+ sign_v;
            val= "NAME ";
        }
        else if (sign_v >= 28.5 & sign_v < 29.5){
//            val= " "+ sign_v;
            val= " ";
        }
        else {
//            val= "YOU "+ sign_v;
            val= "YOU";
        }

        return val;
    }

    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer;
        // some model input should be quant=0  for some quant=1
        // for this quant=0
        // Change quant=1
        // As we are scaling image from 0-255 to 0-1
        int quant=1;
        int size_images=INPUT_SIZE;
        if(quant==0){
            byteBuffer=ByteBuffer.allocateDirect(1*size_images*size_images*3);
        }
        else {
            byteBuffer=ByteBuffer.allocateDirect(4*1*size_images*size_images*3);
        }
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues=new int[size_images*size_images];
        bitmap.getPixels(intValues,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
        int pixel=0;

        for (int i=0;i<size_images;++i){
            for (int j=0;j<size_images;++j){
                final  int val=intValues[pixel++];
                if(quant==0){
                    byteBuffer.put((byte) ((val>>16)&0xFF));
                    byteBuffer.put((byte) ((val>>8)&0xFF));
                    byteBuffer.put((byte) (val&0xFF));
                }
                else {
                    byteBuffer.putFloat((((val >> 16) & 0xFF))/255.0f);
                    byteBuffer.putFloat((((val >> 8) & 0xFF))/255.0f);
                    byteBuffer.putFloat((((val) & 0xFF))/255.0f);
                }
            }
        }
        return byteBuffer;
    }


    private ByteBuffer convertBitmapToByteBuffer1(Bitmap bitmap) {
        ByteBuffer byteBuffer;
        int quant=1;
        int size_images=Classification_InputSize;
        if(quant==0){
            byteBuffer=ByteBuffer.allocateDirect(1*size_images*size_images*3);
        }
        else {
            byteBuffer=ByteBuffer.allocateDirect(4*1*size_images*size_images*3);
        }
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues=new int[size_images*size_images];
        bitmap.getPixels(intValues,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
        int pixel=0;

        for (int i=0;i<size_images;++i){
            for (int j=0;j<size_images;++j){
                final  int val=intValues[pixel++];
                if(quant==0){
                    byteBuffer.put((byte) ((val>>16)&0xFF));
                    byteBuffer.put((byte) ((val>>8)&0xFF));
                    byteBuffer.put((byte) (val&0xFF));
                }
                else {
                    byteBuffer.putFloat((((val >> 16) & 0xFF)));
                    byteBuffer.putFloat((((val >> 8) & 0xFF)));
                    byteBuffer.putFloat((((val) & 0xFF)));
                }
            }
        }
        return byteBuffer;
    }
}