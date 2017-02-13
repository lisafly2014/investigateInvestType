package booster.co.nz.fundsinvestinvestigation.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import booster.co.nz.fundsinvestinvestigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class SuccessSubmitFragment extends Fragment {
    private Button bt_return;
    public SuccessSubmitFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_success_submit, container, false);
        bt_return = (Button) view.findViewById(R.id.return_bt);
        bt_return.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UsageFragment usageFragment = new UsageFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragment,usageFragment,usageFragment.getTag()).commit();
            }
        });
        return view;
    }


}
