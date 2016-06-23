package de.uni_weimar.bodyprint.Model;

import java.util.Comparator;

/**
 * Created by befi8957 on 23.06.16.
 */
public class SimilarityResultComparator implements Comparator<SimilarityResult> {
    @Override
    public int compare(SimilarityResult lhs, SimilarityResult rhs) {
        return lhs.getSimilarity().compareTo(rhs.getSimilarity());
    }
}
