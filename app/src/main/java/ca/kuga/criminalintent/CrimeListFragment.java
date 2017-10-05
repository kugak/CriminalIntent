package ca.kuga.criminalintent;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Controller Crime List Fragment
 */

public class CrimeListFragment extends Fragment {

    //set up view for recycler view
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        //delegates job to layout manager
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);

        //positions each item and defines scrolling
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private abstract class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent, int layout) {
            super(inflater.inflate(layout, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }

        //bind
        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText((mCrime.getmTitle()));
            mDateTextView.setText(mCrime.getmDate().toString());
            mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", mCrime.getmDate()));

            //mSolvedImageView.setVisibility(mCrime.ismSolved() ? View.VISIBLE : View.GONE);
        }

        //on click toast message
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), CrimeActivity.class);
            startActivity(intent);
        }
    }

    private class RegularCrimeHolder extends CrimeHolder {

        public RegularCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.list_item_crime);
        }
    }

    private class SeriousCrimeHolder extends CrimeHolder {

        private Button mContactPoliceButton;

        public SeriousCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.list_item_require_police);
        }

        @Override
        public void bind(Crime crime) {
            super.bind(crime);

            mContactPoliceButton = (Button) itemView.findViewById(R.id.contact_police);
            mContactPoliceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Police contacted for " + mCrime.getmTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //chapter 8 challenge
            if (viewType == 1) {
                return new SeriousCrimeHolder(layoutInflater, parent);
            }
            return new RegularCrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (mCrimes.get(position).ismRequiresPolice()) {
                return 1;
            }
            return 0;
        }
    }

}
