package ca.kuga.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    //no other classes with need it
    private static final String EXTRA_CRIME_ID = "ca.kuga.criminalintent.crime_id";

    //new Intent method
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment () {
        return new CrimeFragment();
    }


}
