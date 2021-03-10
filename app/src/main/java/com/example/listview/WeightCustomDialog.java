package com.example.listview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

public class WeightCustomDialog extends AppCompatDialogFragment {

    private EditText editWeight;
//    private dialogListner listner;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.weightdialog, null);
        v.bringToFront();
        editWeight = v.findViewById(R.id.edituserweight);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setTitle("Weight").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String weight = editWeight.getText().toString();
                        profileFragment profileFragment1 = new profileFragment();
                        if (!editWeight.equals("")) {
//
//                    profileFragment.textView2.setText(username);
//                     profileFragment profileFragment = (profileFragment) getActivity().getSupportFragmentManager().findFragmentByTag("profileFragment");

                            dialogListner.applywText(weight);
//                    profileFragment1.saveData();
                        }

                    }
                });

        return builder.create();
    }

    public interface weightdialogListner {
        void applywText(String weight);
    }

    public WeightCustomDialog.weightdialogListner dialogListner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListner = (WeightCustomDialog.weightdialogListner) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement the ting");
        }
    }
}
