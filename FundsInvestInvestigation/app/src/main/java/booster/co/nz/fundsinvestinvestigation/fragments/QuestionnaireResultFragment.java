package booster.co.nz.fundsinvestinvestigation.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import booster.co.nz.fundsinvestinvestigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionnaireResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionnaireResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionnaireResultFragment extends Fragment {
    private TextView tvDescription;
    private TextView tvscore;
    private TextView tvInvestDescription;
    private TextView tvInvestType;
    private TextView tvInvestor;

    private Button bt_show;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SCORE = "SCORE";


    private int score;
    private String investType;

    private QuestionnaireResultFragment.OnFragmentInteractionListener mListener;

    public QuestionnaireResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param score  Questionnaire score.
     * @return A new instance of fragment QuestionnaireResultFragment.
     */
    public static QuestionnaireResultFragment newInstance(int score) {
        QuestionnaireResultFragment fragment = new QuestionnaireResultFragment();
        Bundle args = new Bundle();
        args.putInt(SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(SCORE);
            if(score >=5 && score <=12){
                investType = "Defensive";
            }else if(score >=13 && score <=20){
                investType = "Conservative";
            }else if (score >=21 && score <=29){
                investType = "Balanced";
            }else if (score >=30 && score <=37){
                investType = "Balanced Growth";
            }else if(score >=38 && score <= 44){
                investType = "Growth";
            }else{
                investType = "Aggressive Growth";
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callBackQuestionnaireScore(String.valueOf(score));
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_questionnaire_result, container, false);
        tvDescription =(TextView)view.findViewById(R.id.result_description);
        tvscore = (TextView)view.findViewById(R.id.tv_score);
        tvInvestDescription =(TextView)view.findViewById(R.id.investTypeDesc);
        tvInvestType = (TextView)view.findViewById(R.id.investType);
        tvInvestor = (TextView)view.findViewById(R.id.investor);

        tvscore.setText(score+"");
        tvInvestType.setText(investType);

        bt_show = (Button) view.findViewById(R.id.btnShow);
        bt_show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("result", investType);
                InvestTypeFragment investTypeFragment = InvestTypeFragment.newInstance(investType);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragment,investTypeFragment,investTypeFragment.getTag()).commit();
            }
        });
        return view;


    }

    public void callBackQuestionnaireScore(String score) {
        if (mListener != null) {
            mListener.onFragmentInteraction(score);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof QuestionnaireResultFragment.OnFragmentInteractionListener) {
            mListener = (QuestionnaireResultFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String score);
    }



}
