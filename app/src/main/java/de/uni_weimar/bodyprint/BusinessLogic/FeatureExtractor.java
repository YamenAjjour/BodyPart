package de.uni_weimar.bodyprint.BusinessLogic;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;

import de.uni_weimar.bodyprint.Model.FeatureVector;

/**
 * Created by befi8957 on 23.06.16.
 */
public class FeatureExtractor {
    public FeatureVector getFeatureVectorForImage(Bitmap bmp)
    {
        Mat mat = new Mat();
        Utils.bitmapToMat(bmp,mat);
        FeatureDetector featureDetector= FeatureDetector.create(FeatureDetector.BRISK);
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
        MatOfKeyPoint matOfKeyPoints = new MatOfKeyPoint();
        featureDetector.detect(mat,matOfKeyPoints);
        FeatureVector featureVector = new FeatureVector();
        featureVector.setMatOfKeyPoint(matOfKeyPoints);
        Mat descriptor = new Mat();
        descriptorExtractor.compute(mat,matOfKeyPoints,descriptor);
        featureVector.setDescriptor(descriptor);
        return featureVector;
    }
}
