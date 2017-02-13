package booster.co.nz.fundsinvestinvestigation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import booster.co.nz.fundsinvestinvestigation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsageFragment extends Fragment {


    public UsageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usage, container, false);
    }

}
