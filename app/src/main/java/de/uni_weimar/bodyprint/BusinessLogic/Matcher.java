package de.uni_weimar.bodyprint.BusinessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.uni_weimar.bodyprint.DAL.IDataProvider;
import de.uni_weimar.bodyprint.Model.FeatureVector;
import de.uni_weimar.bodyprint.Model.SimilarityResult;
import de.uni_weimar.bodyprint.Model.SimilarityResultComparator;
import de.uni_weimar.bodyprint.Model.User;

/**
 * Created by befi8957 on 23.06.16.
 */
public class Matcher {
    List<User> users;
    public Matcher(IDataProvider dataProvider)
    {
        users = dataProvider.getAllUsers();
    }

    public User match(FeatureVector featureVector)
    {
        List<SimilarityResult> similarityResults = new ArrayList<SimilarityResult>();
        for(User user: users)
        {
            similarityResults.add(computeSimilarity(featureVector,user));
        }
        Collections.sort(similarityResults, new SimilarityResultComparator());
        return similarityResults.get(0).getUser();
    }

    public SimilarityResult computeSimilarity(FeatureVector vector1, User user)
    {
        SimilarityResult result = new SimilarityResult();
        result.setUser(user);
        result.setSimilarity(computeSimilarity(vector1,user.getFeatureVector()));
        return result;
    }

    public double computeSimilarity(FeatureVector vector1, FeatureVector vector2)
    {
        return vector1.computeSimilarity(vector2);
    }
}
