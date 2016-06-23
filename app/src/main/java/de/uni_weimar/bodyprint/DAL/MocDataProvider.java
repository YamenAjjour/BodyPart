package de.uni_weimar.bodyprint.DAL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import de.uni_weimar.bodyprint.BusinessLogic.FeatureExtractor;
import de.uni_weimar.bodyprint.Model.FeatureVector;
import de.uni_weimar.bodyprint.Model.User;
import de.uni_weimar.bodyprint.R;

/**
 * Created by befi8957 on 23.06.16.
 */
public class MocDataProvider implements IDataProvider {

    Context context;
    FeatureExtractor extractor = new FeatureExtractor();
    public MocDataProvider(Context context)
    {
        this.context = context;
        initialize();
    }
    List<User> users = new ArrayList<User>();
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void addFeatureVectorForUser(User user, FeatureVector featureVector) {
        user.setFeatureVector(featureVector);
    }


    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean initialize() {
        User user1 = new User();
        user1.setName("Maria");
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ear1);
        FeatureVector featureVector1 = extractor.getFeatureVectorForImage(bm);
        user1.setFeatureVector(featureVector1);
        User user2 = new User();
        Bitmap bm2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ear2);
        FeatureVector featureVector2 = extractor.getFeatureVectorForImage(bm2);
        user2.setFeatureVector(featureVector2);
        user2.setName("Yamen");
        User user3 = new User();
        Bitmap bm3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ear3);
        FeatureVector featureVector3 = extractor.getFeatureVectorForImage(bm3);
        user3.setFeatureVector(featureVector3);
        user3.setName("User3");
        User user4 = new User();
        Bitmap bm4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ear4);
        FeatureVector featureVector4 = extractor.getFeatureVectorForImage(bm4);
        user4.setFeatureVector(featureVector4);
        user4.setName("User4");
        User user5 = new User();
        Bitmap bm5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ear5);
        FeatureVector featureVector5= extractor.getFeatureVectorForImage(bm5);
        user5.setFeatureVector(featureVector5);
        user5.setName("User5");
        User user6 = new User();
        Bitmap bm6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ear6);
        FeatureVector featureVector6= extractor.getFeatureVectorForImage(bm6);
        user6.setFeatureVector(featureVector6);
        user6.setName("User6");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        return true;
    }
}
