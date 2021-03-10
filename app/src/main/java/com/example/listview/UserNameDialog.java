package com.example.listview;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class UserNameDialog extends AppCompatDialogFragment {

    private EditText edituserName;
//    private dialogListner listner;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setTitle("UserName")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = edituserName.getText().toString();
                        profileFragment profileFragment1 = new profileFragment();
                        if(!edituserName.equals("")){
//
//                    profileFragment.textView2.setText(username);
//                     profileFragment profileFragment = (profileFragment) getActivity().getSupportFragmentManager().findFragmentByTag("profileFragment");

                            dialogListner.applyText(username);
//                    profileFragment1.saveData();
                        }

                    }
                });
        edituserName = v.findViewById(R.id.editusername);
        return builder.create();

    }

//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View v = inflater.inflate(R.layout.dialog, null);
//        v.bringToFront();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(v).setTitle("UserName")
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        })
//        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String username = edituserName.getText().toString();
//                profileFragment profileFragment1 = new profileFragment();
//                if(!edituserName.equals("")){
////
////                    profileFragment.textView2.setText(username);
////                     profileFragment profileFragment = (profileFragment) getActivity().getSupportFragmentManager().findFragmentByTag("profileFragment");
//
//                    dialogListner.applyText(username);
////                    profileFragment1.saveData();
//                }
//
//            }
//        });
//        edituserName = v.findViewById(R.id.editusername);
//        builder.show();
//
//
//    }
    public interface dialogListner{
        void applyText(String username);
    }

    public dialogListner dialogListner;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListner = (dialogListner) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"implement the ting");
        }
    }


}
