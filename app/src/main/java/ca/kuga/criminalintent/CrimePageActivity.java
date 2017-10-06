package ca.kuga.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * View Pager
 */

public class CrimePageActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "ca.kuga.criminalintent.crime_id";


    private ViewPager mViewPager;
    private List<Crime> mCrimes;


//intent method
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePageActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    //setting up viewpager
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);


        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //setting up page adapter
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {


            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getmID());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });


        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getmID().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }


    }
}
