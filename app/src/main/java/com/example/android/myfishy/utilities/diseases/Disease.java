package com.example.android.myfishy.utilities.diseases;

import com.example.android.myfishy.db.entities.Diet;
import com.example.android.myfishy.db.entities.Nourishment;
import com.example.android.myfishy.db.entities.User;

import java.util.List;

public abstract class Disease {

    protected List<Nourishment> nList;
    protected Nourishment nourishment;
    protected User user;

    public Disease(List<Nourishment> nourishmentList, User user) {
        this.nList = nourishmentList;
        this.user = user;
        this.nourishment = null;
    }

    public abstract Diet evaluateDietaryAppliance();
}
