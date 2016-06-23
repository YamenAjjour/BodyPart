package de.uni_weimar.bodyprint.Model;

/**
 * Created by befi8957 on 23.06.16.
 */
public class SimilarityResult {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    Double similarity;
}
