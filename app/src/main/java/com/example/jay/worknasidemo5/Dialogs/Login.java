package com.example.jay.worknasidemo5.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jay.worknasidemo5.Model.BackgroundTask;
import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 11/21/2017.
 */

public class Login extends DialogFragment {

    EditText edit_username, edit_password;
    Button button_login, goRegister;
    TextView forgetPassword;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_login, null);

        edit_username = view.findViewById(R.id.login_username);
        edit_password = view.findViewById(R.id.login_password);
        button_login = view.findViewById(R.id.login_button);
        goRegister = view.findViewById(R.id.go_register);

        builder.setView(view);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_username = edit_username.getText().toString();
                String login_password = edit_password.getText().toString();
                String method = "login";

                BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                backgroundTask.execute(method, login_username, login_password);
            }
        });


        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register registerDialog = new Register();
                registerDialog.show(getFragmentManager(), "Register Dialog");
                dismiss();
            }
        });

        Dialog dialog = builder.create();

        return dialog;
    }
}
