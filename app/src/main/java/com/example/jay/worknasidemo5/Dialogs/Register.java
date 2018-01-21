package com.example.jay.worknasidemo5.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jay.worknasidemo5.Model.BackgroundTask;
import com.example.jay.worknasidemo5.Model.User;
import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 12/2/2017.
 */

public class Register extends DialogFragment {

    EditText firstName, lastName, username, phoneNo, email, password, confirmPass;
    Button regButton;

    public Register(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_register, null);
        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.last_name);
        username = view.findViewById(R.id.username);
        phoneNo = view.findViewById(R.id.phoneNo);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPass = view.findViewById(R.id.confirm_password);
        regButton = view.findViewById(R.id.register_button);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userFirstName = firstName.getText().toString();
                String userLastName = lastName.getText().toString();
                String userName = username.getText().toString();
                String userPhoneNo = phoneNo.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String confirmPassword = confirmPass.getText().toString();

                /*
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setUsername(username.getText().toString());
                user.setPhoneNo(phoneNo.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                String confPassword = confirmPass.getText().toString();
                */

                /* if(user.getFirstName().equals("") || user.getLastName().equals("") || user.getUsername().equals("") ||
                        user.getPhoneNo().equals("") || user.getEmail().equals("")
                        || user.getPassword().equals("") || confPassword.equals("")) {
                    Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else if(Patterns.PHONE.matcher( user.getPhoneNo()).matches() == false){
                    Toast.makeText(getActivity(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }else if(!confPassword.equals(user.getPassword())){
                    Toast.makeText(getActivity(), "passwords do not match", Toast.LENGTH_SHORT).show();
                }else if(Patterns.EMAIL_ADDRESS.matcher((CharSequence) user.getEmail()).matches() == false){
                    Toast.makeText(getActivity(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }else{
                    String method = "register";
                    BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                    backgroundTask.execute(method, user.getFirstName(), user.getLastName(), user.getUsername(),
                            user.getPhoneNo(), user.getEmail(), user.getPassword());
                    dismiss();
                }
                */

                if(userFirstName.equals("") || userLastName.equals("") || userName.equals("") || userPhoneNo.equals("")
                        || userEmail.equals("") || userPassword.equals("") || confirmPassword.equals("")){
                    Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else if(Patterns.PHONE.matcher(userPhoneNo).matches() == false){
                    Toast.makeText(getActivity(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }else if(!confirmPassword.equals(userPassword)){
                    Toast.makeText(getActivity(), "passwords do not match", Toast.LENGTH_SHORT).show();
                }else if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() == false){
                    Toast.makeText(getActivity(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }else{
                    String method = "register";
                    BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                    backgroundTask.execute(method, userFirstName, userLastName, userName, userPhoneNo, userEmail,
                            userPassword);
                    dismiss();
                }

            }
        });

        builder.setView(view);
        Dialog dialog = builder.create();
        return dialog;
    }
}
