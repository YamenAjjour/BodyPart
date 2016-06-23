package de.uni_weimar.bodyprint.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by befi8957 on 23.06.16.
 */
public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public FeatureVector getFeatureVector() {
        return featureVector;
    }

    public void setFeatureVector (FeatureVector featureVector) {
        this.featureVector = featureVector;
    }

    FeatureVector featureVector = new FeatureVector();

}
