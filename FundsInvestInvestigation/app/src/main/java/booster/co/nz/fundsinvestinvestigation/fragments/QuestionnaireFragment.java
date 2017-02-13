package booster.co.nz.fundsinvestinvestigation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import booster.co.nz.fundsinvestinvestigation.R;
import booster.co.nz.fundsinvestinvestigation.common.Utility;
import booster.co.nz.fundsinvestinvestigation.db.DataBaseAdapter;
import booster.co.nz.fundsinvestinvestigation.model.Question;
import java.util.HashMap;
import java.util.List;

/**
 *  questionnaire fragment
 */
public class QuestionnaireFragment extends Fragment {
    private final String TAG = QuestionnaireFragment.class.getSimpleName();
    private List<Question> questionList;
    private Question currentQuestion;
    private int currentQestionNo =0;
    private int answeredQNum = 0;
    private String selectedAnswer = "";

    private TextView tvQuestion, tvNoOfQuestion;
    private RadioButton rbtnA, rbtnB,rbtnC,rbtnD,rbtnE;
    private RadioGroup radioGroup;


    private Button btNext;
    private int score;


   private HashMap<String, Integer> hashMap = new HashMap<>();

    DataBaseAdapter dataBaseAdapter;

    public QuestionnaireFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_questionnaire, container, false);
        selectedAnswer ="";
        score = 0;
        init(view);

        initHashmap(hashMap);

        //init the dataBase
        dataBaseAdapter = new DataBaseAdapter(QuestionnaireFragment.this.getActivity());
        questionList = dataBaseAdapter.getAllQuestions();
        currentQestionNo = 0;
        currentQuestion = questionList.get(currentQestionNo);

        //load question
        loadQuestion();

        return view;
    }


    public void init(View view){
        tvNoOfQuestion =(TextView)view.findViewById(R.id.questionNumber);
        tvQuestion = (TextView)view.findViewById(R.id.question);
        rbtnA = (RadioButton) view.findViewById(R.id.radioA);
        rbtnB = (RadioButton)view.findViewById(R.id.radioB);
        rbtnC = (RadioButton)view.findViewById(R.id.radioC);
        rbtnD = (RadioButton)view.findViewById(R.id.radioD);
        rbtnE = (RadioButton)view.findViewById(R.id.radioE);

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //find which radio button is selected
                switch (checkedId){
                    case R.id.radioA:
                        selectedAnswer ="A";
                        break;
                    case R.id.radioB:
                        selectedAnswer ="B";
                        break;
                    case R.id.radioC:
                        selectedAnswer = "C";
                        break;
                    case R.id.radioD:
                        selectedAnswer = "D";
                        break;
                    case R.id.radioE:
                        selectedAnswer ="E";
                        break;
                }


            }
        });


        btNext =(Button)view.findViewById(R.id.btnNext);
        btNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                if(selectedAnswer.equalsIgnoreCase("")){
                    Utility.alertSimpleMessage(QuestionnaireFragment.this.getActivity(),"Please select an answer");
                }else{
                    if(currentQestionNo < dataBaseAdapter.getRowCount()){
                        score += hashMap.get(selectedAnswer);
                        currentQuestion = questionList.get(currentQestionNo);
                        loadQuestion();

                    }else{
                        score += hashMap.get(selectedAnswer);
                        QuestionnaireResultFragment fragment = QuestionnaireResultFragment.newInstance(score);
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.layout_for_fragment,fragment,fragment.getTag()).commit();
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void initHashmap(HashMap map){
        map.put("A",1);
        map.put("B",3);
        map.put("C",5);
        map.put("D",7);
        map.put("E",10);
    }


    private void loadQuestion(){
        rbtnA.setChecked(false);
        rbtnB.setChecked(false);
        rbtnC.setChecked(false);
        rbtnD.setChecked(false);
        rbtnE.setChecked(false);
        answeredQNum +=1;
        tvNoOfQuestion.setText("Question " + answeredQNum +" of "+questionList.size());

        tvQuestion.setText(currentQuestion.getQuestion());
        rbtnA.setText(currentQuestion.getOptA());
        rbtnB.setText(currentQuestion.getOptB());
        rbtnC.setText(currentQuestion.getOptC());
        rbtnD.setText(currentQuestion.getOptD());
        rbtnE.setText(currentQuestion.getOptE());

        currentQestionNo ++;
    }



}
