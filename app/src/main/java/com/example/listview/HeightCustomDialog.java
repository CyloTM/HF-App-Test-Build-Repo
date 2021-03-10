package com.example.listview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.content.Context.MODE_PRIVATE;

public class HeightCustomDialog extends AppCompatDialogFragment {

    private EditText editHeight;
//    SharedPreferences prefs = this.getSharedPreferences(
//            "com.example.listview", Context.MODE_PRIVATE);
//
//    private SharedPreferences getSharedPreferences(String s, int modePrivate) {
//        return prefs;
//    }

    public static final String hText = "text3";

    public void savehData(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(hText, editHeight.getText().toString());
        editor.apply();

        Toast.makeText(getActivity(),"Data Saved",Toast.LENGTH_SHORT).show();
        return;
    }
//    private dialogListner listner;


    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.heightdialog, null);

        editHeight = v.findViewById(R.id.edituserheight);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(v)
                .setTitle("Height")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String height = editHeight.getText().toString();
                        profileFragment profileFragment1 = new profileFragment();
                        if (!editHeight.equals("")) {
//
//                    profileFragment.textView2.setText(username);
//                     profileFragment profileFragment = (profileFragment) getActivity().getSupportFragmentManager().findFragmentByTag("profileFragment");

                            dialogListner.applyhText(height);

                        }

                    }
                });

        return builder.create();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        FragmentManager fm = getParentFragmentManager();
////if you added fragment via layout xml
//        profileFragment fragment = (profileFragment) fm.findFragmentById(R.id.profileFragment);
//        fragment.savehData();
        savehData();
    }

    public interface heightdialogListner {
        void applyhText(String height);
    }


    public HeightCustomDialog.heightdialogListner dialogListner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListner = (HeightCustomDialog.heightdialogListner) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement the ting");
        }
    }
}