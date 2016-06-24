package de.uni_weimar.bodyprint.BusinessLogic;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Size;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;

import de.uni_weimar.bodyprint.Model.FeatureVector;

/**
 * Created by befi8957 on 23.06.16.
 */
public class FeatureExtractor {
    public FeatureVector getFeatureVectorForImage(Bitmap bmp)
    {
        Mat mat = new Mat();
        Utils.bitmapToMat(bmp,mat);
        Mat resizeimage = new Mat();
        Size sz = new Size(120,80);
        Imgproc.resize( mat, resizeimage, sz );
        Imgproc.cvtColor(resizeimage, resizeimage, Imgproc.COLOR_RGB2GRAY);
        FeatureDetector featureDetector= FeatureDetector.create(FeatureDetector.BRISK);
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
        MatOfKeyPoint matOfKeyPoints = new MatOfKeyPoint();
        featureDetector.detect(resizeimage,matOfKeyPoints);
        FeatureVector featureVector = new FeatureVector();
        featureVector.setMatOfKeyPoint(matOfKeyPoints);
        Mat descriptor = new Mat();
        descriptorExtractor.compute(resizeimage,matOfKeyPoints,descriptor);
        featureVector.setDescriptor(descriptor);
        return featureVector;
    }
}
