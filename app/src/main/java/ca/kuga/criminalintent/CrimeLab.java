package ca.kuga.criminalintent;

import android.content.Context;
import android.support.constraint.solver.ArrayLinkedVariables;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Singleton Class
 */

public class CrimeLab {

    //static variable (s)CrimeLab
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get (Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    //private constructor
    private CrimeLab (Context context) {
        mCrimes = new ArrayList<>();

        //generating crime list
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setmTitle("Crime #" + i);
            crime.setmSolved(i % 2 == 0); //Every other one
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    //returns the crime with given ID
    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getmID().equals(id)) {
                return crime;
            }
        }
        return null;

    } //getCrime
}
