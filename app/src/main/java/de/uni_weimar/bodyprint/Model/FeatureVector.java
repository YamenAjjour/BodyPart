package de.uni_weimar.bodyprint.Model;

import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorMatcher;

/**
 * Created by befi8957 on 23.06.16.
 */
public class FeatureVector {
    static  DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
    public MatOfKeyPoint getMatOfKeyPoint() {
        return matOfKeyPoint;
    }

    public void setMatOfKeyPoint(MatOfKeyPoint matOfKeyPoint) {
        this.matOfKeyPoint = matOfKeyPoint;
    }

    public double computeSimilarity(FeatureVector featureVector)
    {
        return computeAverageKeyPointSimilarity(featureVector);
    }
    MatOfKeyPoint matOfKeyPoint;

    public Mat getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Mat descriptor) {
        this.descriptor = descriptor;
    }

    private double computeAverageKeyPointSimilarity(FeatureVector featureVector)
    {
        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(getDescriptor(),featureVector.getDescriptor(),matches);
        double average = 0;
        for(DMatch match: matches.toList())
        {
            average =average+match.distance;
        }
        return average/matches.toList().size();
    }

    Mat descriptor;
}
