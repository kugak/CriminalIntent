package ca.kuga.criminalintent;

import android.content.Context;
import android.support.constraint.solver.ArrayLinkedVariables;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Singleton Class
 */

public class CrimeLab {

    //static variable (s)CrimeLab
    private static CrimeLab sCrimeLab;

    //private List<Crime> mCrimes;

    //challenge -- using Map to improve performance.
    private Map<UUID, Crime> mCrimes;


    public static CrimeLab get (Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    //private constructor
    private CrimeLab (Context context) {
        mCrimes = new LinkedHashMap<>();

        //generating crime list
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setmTitle("Crime # " + i);
            crime.setmSolved(i % 2 == 0); //Every other one
            crime.setmRequiresPolice(i % 2 == 0);//require police
            mCrimes.put(crime.getmID(), crime);
        }
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

    //returns the crime with given ID
    public Crime getCrime(UUID id) {
        return mCrimes.get(id);

    } //getCrime
}
