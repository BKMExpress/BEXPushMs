package com.bkm.bkmpushnotification.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akif Hatipoglu on 23.02.2018.
 */
public class IOSTokenResponse {
    private List<IOSTokenResult> results = new ArrayList<IOSTokenResult>();

    public List<IOSTokenResult> getResults() {
        return results;
    }

    public void setResults(List<IOSTokenResult> results) {
        this.results = results;
    }
}
