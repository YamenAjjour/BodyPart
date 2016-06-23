package de.uni_weimar.bodyprint.BusinessLogic;

import android.content.Context;
import android.graphics.Bitmap;

import de.uni_weimar.bodyprint.DAL.IDataProvider;
import de.uni_weimar.bodyprint.DAL.MocDataProvider;
import de.uni_weimar.bodyprint.Model.FeatureVector;
import de.uni_weimar.bodyprint.Model.User;

/**
 * Created by befi8957 on 23.06.16.
 */
public class Controller {
    FeatureExtractor featureExtractor = new FeatureExtractor();
    Matcher matcher;
    public Controller (Context context)
    {
        IDataProvider dataProvider = new MocDataProvider(context);
        matcher = new Matcher(dataProvider);
    }
    public User scan(Bitmap bitmap)
    {
        FeatureVector featureVector = featureExtractor.getFeatureVectorForImage(bitmap);
        return matcher.match(featureVector);
    }
}
