package booster.co.nz.fundsinvestinvestigation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import booster.co.nz.fundsinvestinvestigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvestTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestTypeFragment extends Fragment {
    private static final String ARG_INVEST_TYPE = "param1";
    private String investType;

    private TextView tv_headline;
    private TextView tv_title;
    private TextView tv_item1;
    private TextView tv_item2;
    private TextView tv_item3;
    private TextView tv_item4;
    private ImageView iv_fund_type;

    public InvestTypeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment InvestTypeFragment.
     */
    public static InvestTypeFragment newInstance(String param1) {
        InvestTypeFragment fragment = new InvestTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INVEST_TYPE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            investType = getArguments().getString(ARG_INVEST_TYPE);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invest_type, container, false);
        tv_headline = (TextView)view.findViewById(R.id.invest_type_headline);
        tv_title = (TextView)view.findViewById(R.id.invest_type_title);
        tv_item1 = (TextView)view.findViewById(R.id.item1);
        tv_item2 = (TextView)view.findViewById(R.id.item2);
        tv_item3 = (TextView)view.findViewById(R.id.item3);
        tv_item4 = (TextView)view.findViewById(R.id.item4);
        iv_fund_type =(ImageView)view.findViewById(R.id.invest_type_image);
        if(investType.equalsIgnoreCase("Defensive")){
            configureView(R.string.capital_guaranteed_fund_headline,R.string.fund_title,
                    R.string.capital_guaranteed_fund_item1,R.string.capital_guaranteed_fund_item2,
                    R.string.capital_guaranteed_fund_item3,R.string.capital_guaranteed_fund_item4,
                    R.drawable.capital_guaranteed_fund_pie_graph);

        }else if(investType.equalsIgnoreCase("Conservative")){
            configureView(R.string.default_saver_fund,R.string.fund_title,
                        R.string.default_saver_fund_item1,R.string.default_saver_fund_item2,
                        R.string.default_saver_fund_item3,R.string.default_saver_fund_item4,
                        R.drawable.default_saver_fund_pie_graph);

        }else if(investType.equalsIgnoreCase("Balanced")){
            configureView(R.string.balanced_fund,R.string.fund_title,
                    R.string.balanced_fund_item1,R.string.balanced_fund_item2,
                    R.string.balanced_fund_item3,R.string.balanced_fund_item4,
                    R.drawable.balanced_fund_pie_graph);

        }else if(investType.equalsIgnoreCase("Balanced Growth")){
            configureView(R.string.balanced_growth_fund,R.string.fund_title,
                    R.string.balanced_growth_fund_item1,R.string.balanced_growth_fund_item2,
                    R.string.balanced_growth_fund_item3,R.string.balanced_growth_fund_item4,
                    R.drawable.balanced_growth_fund_pie_graph);

        }else if(investType.equalsIgnoreCase("Growth")){
            configureView(R.string.high_growth_fund,R.string.fund_title,
                    R.string.high_growth_fund_item1,R.string.high_growth_fund_item2,
                    R.string.high_growth_fund_item3,R.string.high_growth_fund_item4,
                    R.drawable.high_growth_fund_pie_graph);
        }else{
            configureView(R.string.high_growth_fund,R.string.fund_title,
                    R.string.high_growth_fund_item1,R.string.high_growth_fund_item2,
                    R.string.high_growth_fund_item3,R.string.high_growth_fund_item4,
                    R.drawable.high_growth_fund_pie_graph);

        }

        return view;
    }

    private void configureView(int headline,int title,int item1,int item2,int item3,int item4,int imageId){
        tv_headline.setText(headline);
        tv_title.setText(title);
        tv_item1.setText(item1);
        tv_item2.setText(item2);
        tv_item3.setText(item3);
        tv_item4.setText(item4);
        iv_fund_type.setImageResource(imageId);
    }

}
