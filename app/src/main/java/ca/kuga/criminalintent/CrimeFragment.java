package ca.kuga.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Fragment Controller
 * Present the details of a specific crime and
 * update those details as the user changes them.
 */

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //expicitly inflate fragments view
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        //referencing to EditText
        mTitleField = (EditText) v.findViewById(R.id.crime_title);

        //adding EditText Listener
        mTitleField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentional blank
            }

            //this returns string from user, setting to crimeTitle
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentional blank
            }

        });// end of addTextChangedListener

        //adding button Listener
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getMdate().toString());
        mDateButton.setEnabled(false);

        return v;

    }
}
