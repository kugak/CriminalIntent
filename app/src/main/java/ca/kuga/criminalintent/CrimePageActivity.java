package ca.kuga.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

/**
 * View Pager
 */

public class CrimePageActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "ca.kuga.criminalintent.crime_id";


    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    //challenge 11
    private Button mFirstButton;
    private Button mLastButton;


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

        //Challenge 11 - Page

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //setting first button visibility based on current item
                if (mViewPager.getCurrentItem() == 0) {
                    mFirstButton.setVisibility(View.INVISIBLE);
                } else {
                    mFirstButton.setVisibility(View.VISIBLE);
                }

                //setting last button visibility based on current item
                if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) {
                    mLastButton.setVisibility(View.INVISIBLE);
                } else {
                    mLastButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                //intentional blank

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //intentional blank
            }
        });

        //first page button
        mFirstButton = (Button) findViewById(R.id.first_crime);
        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });

        //last page button
        mLastButton = (Button) findViewById(R.id.last_crime);
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 1);
            }
        });
    }


}
