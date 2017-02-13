package booster.co.nz.fundsinvestinvestigation.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import booster.co.nz.fundsinvestinvestigation.R;
import booster.co.nz.fundsinvestinvestigation.common.Utility;


public class SubmitFragment extends Fragment {
    private EditText name,email,phone;
    private Button bt_submit;
    final int REQUEST_CODE =999;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SCORE = "arg_score";
    private String mScore;
    public SubmitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment SuccessSubmitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubmitFragment newInstance(String param1) {
        SubmitFragment fragment = new SubmitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SCORE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mScore = getArguments().getString(ARG_SCORE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submit, container, false);
        initConguration(view);

        return view;
    }

   @Override
   public  void onActivityResult(int requestCode, int resultCode, Intent data){
       super.onActivityResult(requestCode, resultCode, data);
       if (requestCode == REQUEST_CODE){
           SuccessSubmitFragment fragment = new SuccessSubmitFragment();
           FragmentManager manager = getActivity().getSupportFragmentManager();
           manager.beginTransaction().replace(R.id.layout_for_fragment,fragment,fragment.getTag()).commit();

       }
   }
    /*
    * init view configuration
     */

    private void initConguration(View view){
        name =(EditText)view.findViewById(R.id.name);
        email =(EditText)view.findViewById(R.id.email);
        phone = (EditText)view.findViewById(R.id.phone);
        bt_submit = (Button)view.findViewById(R.id.submit_bt);

        bt_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                submit();
            }
        });


    }

    /*
    * Validate and submit
     */
    private void submit(){
        if(!checkName()){
            Utility.alertSimpleMessage(SubmitFragment.this.getActivity(),
                    "Input name is empty, please input name!");
            return;
        }

        if(!inputEmpty(email)){
            Utility.alertSimpleMessage(SubmitFragment.this.getActivity(),
                    "Email is empty, please input email address!");
            return;
        }
        if(!checkEmail()){
            Utility.alertSimpleMessage(SubmitFragment.this.getActivity(),
                    "Email address is invalid, please input email address again!");
            email.setText("");
            email.requestFocus();
            return;
        }
        if(!inputEmpty(phone)){
            Utility.alertSimpleMessage(SubmitFragment.this.getActivity(),
                    "Phone number is empty, please input phone number!");
            return;
        }

        if(!checkPhone()){
            Utility.alertSimpleMessage(SubmitFragment.this.getActivity(),
                    "Phone number is invalid, please input Phone number again!");
            phone.setText("");
            phone.requestFocus();
            return;
        }


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] emailAddress={"me@example.com "};
        intent.putExtra(Intent.EXTRA_EMAIL,emailAddress);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Test App from Lisa Liu");
        String emailContent = "name: "+ name.getText().toString()+"\n"+
                                "email: "+email.getText().toString()+ "\n"+
                                "phone: "+ phone.getText().toString() +"\n"+
                                "score: "+ mScore +"\n";

        intent.putExtra(Intent.EXTRA_TEXT,emailContent);
        intent.setType("message/nfc822");
        startActivityForResult(Intent.createChooser(intent,"Choice email App"),REQUEST_CODE);

    }

    private boolean inputEmpty(TextView textView){
        if(textView.getText().toString().trim().isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkName(){
        if(name.getText().toString().trim().isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkEmail(){
        String emailText =email.getText().toString().trim();
        if(emailText.isEmpty() || !isValidEmail(emailText)){
            return false;
        }else{
            return true;
        }
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean checkPhone(){
        String phoneText = phone.getText().toString().trim();
        if(phoneText.isEmpty() || !isValidPhone(phoneText)){
            return false;
        }else{
            return true;
        }

    }

    private boolean isValidPhone(String phone){
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }


}
