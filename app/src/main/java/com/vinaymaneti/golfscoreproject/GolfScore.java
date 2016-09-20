package com.vinaymaneti.golfscoreproject;

/**
 * Created by vinaymaneti on 8/23/16.
 */
public class GolfScore {

    public String name;
    public int mStrokeCount;

    public GolfScore(String name, int mStrokeCount) {
        this.name = name;
        this.mStrokeCount = mStrokeCount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrokeCount() {
        return mStrokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        mStrokeCount = strokeCount;
    }
}
