package ca.kuga.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Model Layer
 */

public class Crime {

    //crimeID, title, date, status
    private UUID mID; //universally unique ID
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //constructor to initialize ID & date
    public Crime() {
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    //getter for mID
    public UUID getmID() {
        return mID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

}
