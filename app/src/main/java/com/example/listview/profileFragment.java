package com.example.listview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class profileFragment extends Fragment implements UserNameDialog.dialogListner, WeightCustomDialog.weightdialogListner, HeightCustomDialog.heightdialogListner{

    private static final String TAG = "profileFragment";
    private EditText editText;
    private EditText editText2;
    private String name;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    public TextView textView2;
    public TextView bmiTotal;

    private MyInterface myInterface;



    //Save
    public static final String Shared_Pref = "sharedPrefs";
    public static final String Text = "text";
    public static final String wText = "text2";
    public static final String hText = "text3";

    //Load
    private String text;
    private String wtext;
    private String htext;

    public profileFragment() {
        super(R.layout.profile);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myInterface = (MyInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.profile, container, false);
        editText2 = view.findViewById(R.id.editText2);
        editText = view.findViewById(R.id.editText3);
        textView2 = view.findViewById(R.id.textView2);
        btn1 = view.findViewById(R.id.button3);
        btn2 = view.findViewById(R.id.button4);
        btn3 = view.findViewById(R.id.button5);
        btn4 = view.findViewById(R.id.button6);
        btn5 = view.findViewById(R.id.button7);
        bmiTotal = view.findViewById(R.id.bmiTotal);

        myInterface.lockDrawer();

        btn1.setOnClickListener(v -> openDialog());
//        btn1.setOnClickListener(v ->{
//            textView.setText(editText.getText().toString());
//            saveData();});

        btn5.setOnClickListener(v -> calculateBMI());

        btn2.setOnClickListener(v -> saveData());
        btn3.setOnClickListener(v -> openwDialog());

        btn4.setOnClickListener(v -> saveData());
        editText2.setOnClickListener(v -> openhDialog());

//        if(savedInstanceState != null){
//            editText.setText(savedInstanceState.getString("Name"));
//        }
//
        loadData();
        updateData();
//        loadwData();
//        updatewData();

        return view;
    }
//


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myInterface.unlockDrawer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    public void saveData(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Text, textView2.getText().toString());
        editor.putString(wText, editText.getText().toString());
        editor.putString(hText, editText2.getText().toString());
        editor.apply();

        Toast.makeText(getActivity(),"Data Saved",Toast.LENGTH_SHORT).show();
    }

    public void calculateBMI(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        wtext = sharedPreferences.getString(wText, "");
        htext = sharedPreferences.getString(hText, "");
        Float hInt = Float.valueOf(htext);
        Float wInt = Float.valueOf(wtext);
        if (!wText.contentEquals("") & !hText.contentEquals("")){
            Float bmi =   wInt/(hInt * hInt) ;
            bmi = bmi * 10000f;
            Float bmit = Math.round(bmi * 10f)/10f;
            bmiTotal.setText(bmit.toString());
        }
    }
//    public void savewData(){
//        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(wText, editText.getText().toString());
//        editor.apply();
//
//        Toast.makeText(getActivity(),"Data w Saved",Toast.LENGTH_SHORT).show();
//    }

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        text = sharedPreferences.getString(Text, "");
        wtext = sharedPreferences.getString(wText, "");
        htext = sharedPreferences.getString(hText, "");
    }
//    public void loadwData(){
//        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
//        wtext = sharedPreferences.getString(wText, "");
//    }

    public void updateData(){
        textView2.setText(text);
        editText.setText(wtext);
        editText2.setText(htext);
    }
//    public void updatewData(){
//        editText.setText(wtext);
//    }

    public void openDialog(){
        UserNameDialog userNameDialog = new UserNameDialog();
        userNameDialog.setTargetFragment(profileFragment.this, 1);
        userNameDialog.show(getParentFragmentManager(), "userNameDialog");
    }

    public void openwDialog(){
        WeightCustomDialog weightDialog = new WeightCustomDialog();
        weightDialog.setTargetFragment(profileFragment.this, 1);
        weightDialog.show(getParentFragmentManager(), "WeightDialog");
    }

    public void openhDialog(){
        HeightCustomDialog heightDialog = new HeightCustomDialog();
        heightDialog.setTargetFragment(profileFragment.this, 0);
//        heightDialog.show(getParentFragmentManager(), "WeightDialog");
        heightDialog.show(getFragmentManager(), "HeightDialog");
    }

    public void applyText(String username) {
        textView2.setText(username);
    }

    public void applywText(String weight) {
        editText.setText(weight);
    }

    public void applyhText(String height) { editText2.setText(height);
    }
}



