package de.uni_weimar.bodyprint;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;
import org.opencv.features2d.FeatureDetector;

import de.uni_weimar.bodyprint.BusinessLogic.Controller;
import de.uni_weimar.bodyprint.Model.User;

public class MainActivity extends AppCompatActivity {


    static{
        System.loadLibrary("opencv_java3");
    }
    Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        controller = new Controller(this);
        Bitmap query = BitmapFactory.decodeResource(getResources(), R.drawable.query);
        User user = controller.scan(query);
        Toast.makeText(this, (String)user.getName(),Toast.LENGTH_LONG).show();
    }


}
