package com.example.englishhelper1.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.englishhelper1.R;
import com.example.englishhelper1.activities.TestActivity;

public class ResultDialog extends AppCompatDialogFragment {

    private ResultDialogListener listener;

    private int variable;

    private TextView messageTv;

    private String message;
    private String negativeText;
    private String positiveText;

    public ResultDialog(int variable) {
        this.variable = variable;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /*LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_learning_result, null);*/

        switch (variable){
            case 1:
                message = getResources().getString(R.string.result_dialog__message1);
                negativeText = getResources().getString(R.string.result_dialog__negative_text1);
                positiveText = getResources().getString(R.string.result_dialog__positive_text1);
                break;
            case 2:
                message = getResources().getString(R.string.result_dialog__message2);
                negativeText = getResources().getString(R.string.result_dialog__negative_text2);
                positiveText = getResources().getString(R.string.result_dialog__positive_text2);
        }

        builder.setMessage(message)
                .setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.getResult((variable - 1) * 2);
                    }
                })
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.getResult((variable - 1) * 2 + 1);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ResultDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ResultDialogListener");
        }
    }

    public interface ResultDialogListener {
        void getResult(int value);
    }
}
