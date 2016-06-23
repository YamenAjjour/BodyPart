package de.uni_weimar.bodyprint;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
public class MainActivity extends AppCompatActivity {


    static{
        System.loadLibrary("opencv_java3");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageViewOriginal = (ImageView) findViewById(R.id.image_view_origin);
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.ear2);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.ear1);
        Mat mat = new Mat();
        Mat mat2 = new Mat();
        Mat result = new Mat();
        Utils.bitmapToMat(bm2,mat2);
        Utils.bitmapToMat(bm,mat);

        FeatureDetector featureDetector= FeatureDetector.create(FeatureDetector.BRISK);
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
        MatOfKeyPoint matOfKeyPoints = new MatOfKeyPoint();
        featureDetector.detect(mat,matOfKeyPoints);
        Mat descriptors = new Mat();
        MatOfKeyPoint matOfKeyPoints2 = new MatOfKeyPoint();
        featureDetector.detect(mat2,matOfKeyPoints2);
        Mat descriptors2 = new Mat();
        descriptorExtractor.compute(mat,matOfKeyPoints,descriptors);
        descriptorExtractor.compute(mat2,matOfKeyPoints2,descriptors2);


        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(descriptors,descriptors2,matches);
        Features2d.drawMatches(mat,matOfKeyPoints,mat2,matOfKeyPoints2,matches,result);
        Bitmap resultBmp= Bitmap.createBitmap(result.cols(), result.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(result,resultBmp);
        imageViewOriginal.setImageBitmap(resultBmp);

    }
    private BaseLoaderCallback  mOpenCVCallBack = new BaseLoaderCallback(this) {};

}
