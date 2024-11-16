package com.ca1.igorspetitions;

import java.util.ArrayList;
import java.util.List;

public class Petition {
    private String title;
    private String description;
    private int signatories;
    private List<Signatory> signatoryList;

    public Petition(String title, String description) {
        this.title = title;
        this.description = description;
        this.signatories = 0;
        this.signatoryList = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getSignatories() {
        return signatories;
    }

    public List<Signatory> getSignatoryList() {
        return signatoryList;
    }

    public void addSignatory(Signatory signatory) {
        signatoryList.add(signatory);
        this.signatories++;
    }
}
