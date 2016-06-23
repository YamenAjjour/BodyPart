package de.uni_weimar.bodyprint.DAL;

import java.util.List;

import de.uni_weimar.bodyprint.Model.FeatureVector;
import de.uni_weimar.bodyprint.Model.User;

/**
 * Created by befi8957 on 23.06.16.
 */
public interface IDataProvider {
     void addUser(User user);
     void addFeatureVectorForUser(User user, FeatureVector featureVector);
     List<User> getAllUsers();
     boolean initialize();
}
