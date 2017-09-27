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
    private Date mdate;
    private boolean mSolved;

    //constructor to initialize ID & date
    public Crime() {
        mID = UUID.randomUUID();
        mdate = new Date();
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

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
