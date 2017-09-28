package ca.kuga.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Controller for CrimeList
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
