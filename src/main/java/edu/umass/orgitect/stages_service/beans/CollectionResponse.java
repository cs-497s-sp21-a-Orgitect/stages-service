package edu.umass.orgitect.stages_service.beans;

import java.util.Collection;

public class CollectionResponse<T> {
    private int count;
    private Collection<T> results;

    public CollectionResponse(Collection<T> results) {
        this.results = results;
        this.count = results != null ? results.size() : 0;
    }

    public Collection<T> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }
}
