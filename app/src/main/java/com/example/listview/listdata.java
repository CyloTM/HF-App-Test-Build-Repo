package com.example.listview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class  listdata extends AppCompatActivity implements MyInterface2 {


    public ListView listView;
    public Exercise e;


    //fragment
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragment;

    FrameLayout frameLayout;

    //List view Data
    private ArrayList<String> exercises = new ArrayList<>();
    private ArrayList<String> exercisesReps = new ArrayList<>();
    
    String[] exerciseAmount = {"1","2","3","4","5","6"};

    public TextView eName;
    //ImageView image;
    /*
    public listdata() {
        super(R.layout.activity_listdata);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_listdata, container, false);
        return view;
    }*/

    int count;
    boolean mFragmentOpen = false;

    //Interface

    boolean mWD = false;

    Button button;

    //Save
    public static final String Shared_Pref = "sharedPrefs";


    private TextView mTxtVwWorkoutsComplete;
    public static final String mSPWorkOutsCompleted = "text5";
    private int mWorkoutsCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        Toolbar toolbar = findViewById(R.id.toolbar);
        eName = findViewById(R.id.listdata);
        listView = findViewById(R.id.exersiceListview);
        Intent intent = getIntent();
        button = findViewById(R.id.playlist);
        mTxtVwWorkoutsComplete = findViewById(R.id.textView4);
        eName.setText(intent.getStringExtra("name"));
//        String name = eName.getText().toString();
//        String name2 = "Upper Body";
        workoutArray();
        fragment = new WorkoutPlaylistFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("exercises", e.getExerciseName());
        bundle.putStringArrayList("exerciseReps", e.getExerciseRep());
        fragment.setArguments(bundle);
//        intent = new Intent(this, WorkoutPlaylistFragment.class);
//        intent.putExtra("array_list", exercises);

        fragmentManager = getSupportFragmentManager();
        frameLayout = findViewById(R.id.frameLayout2);

        button.setOnClickListener(v -> {
            if(!mFragmentOpen){
                fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout2, fragment).addToBackStack(null);
                fragmentTransaction.commit();
                frameLayout.setClickable(true);
                button.setVisibility(View.INVISIBLE);
            }
            Toast.makeText(listdata.this,"hit",Toast.LENGTH_SHORT).show();
//            if(fragment!=null){
//                onBackPressed();
//            }

//            if(fragment!=null){
//                int count = fragmentManager.getBackStackEntryCount();
//                for (int i = 0; i<count;i++){
//                    fragmentManager.popBackStack();
//
//                }
//                fragmentTransaction = fragmentManager.beginTransaction().remove(fragment);
//                fragmentTransaction.commit();
//                frameLayout.setClickable(false);
//
//            }
//            if(fragment!=null){
//                button.setVisibility(View.VISIBLE);
//            }
        });


        //Array Adpter
        toolbar.setTitle(eName.getText());
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        loadData();
        updateData();
        saveData();

        intent = new Intent(getApplicationContext(), MainActivity.class);
        //listdata list = new listdata();
        //list.
        intent.putExtra("WC", String.valueOf(mWorkoutsCompleted));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
        count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i<count;i++){
            fragmentManager.popBackStack();
        }
        frameLayout.setClickable(false);
        mFragmentOpen = false;
        button.setVisibility(View.VISIBLE);
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return exercises.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.exercise_rowdata, null);

            TextView name = view1.findViewById((R.id.exercise));
            TextView amount = view1.findViewById((R.id.exerciseAmount));

            name.setText(e.getExerciseName().get(i));
            amount.setText(e.getExerciseRep().get(i));
            return view1;
        }

        //Turn off list view click event
        @Override
        public boolean isEnabled(int position) {
            return false;
        }
    }

    public void workoutArray(){

        if (eName.getText().toString().equals("Upper Body")){

            e = new Exercise(exercises, exercisesReps);

            exercises.add(0,"Push Ups");
            exercises.add(1,"Lateral Push Ups");
            exercises.add(2,"Shoulder Push Ups");
            exercises.add(3,"Incline Push Ups");
            exercises.add(4,"Knee Push Ups");
            exercises.add(5,"Decline Push Ups");

            exercisesReps.add(0,"1");
            exercisesReps.add(1,"2");
            exercisesReps.add(2,"3");
            exercisesReps.add(3,"4");
            exercisesReps.add(4,"5");
            exercisesReps.add(5,"6");


        }
        else if (eName.getText().toString().equals("Lower body")){

            e = new Exercise(exercises, exercisesReps);

            exercises.add(0,"Legs1");
            exercises.add(1,"Legs2");
            exercises.add(2,"Legs3");
            exercises.add(3,"Legs4");
            exercises.add(4,"Legs5");
            exercises.add(5,"Legs6");

            exercisesReps.add(0, "1");
            exercisesReps.add(0, "2");
            exercisesReps.add(0, "3");
            exercisesReps.add(0, "4");
            exercisesReps.add(0, "5");
            exercisesReps.add(0, "6");
        }
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_Pref, MODE_PRIVATE);
        mWorkoutsCompleted = sharedPreferences.getInt(mSPWorkOutsCompleted, 0);
    }

    public void updateData() {
        mTxtVwWorkoutsComplete.setText(String.valueOf(mWorkoutsCompleted));
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_Pref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(mSPWorkOutsCompleted, mWorkoutsCompleted);
        editor.apply();

        Toast.makeText(listdata.this, "Workout Count Saved", Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exercises.clear();
    }

    @Override
    public void btnShow() {

        mWorkoutsCompleted = mWorkoutsCompleted + 1;
        saveData();
        loadData();
        updateData();
        button.setVisibility(View.VISIBLE);


//
//        mTxtVwWorkoutsComplete.setText(Integer.toString(mWorkoutsCompleted + 1));
    }



}