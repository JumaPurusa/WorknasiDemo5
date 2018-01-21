package com.example.jay.worknasidemo5.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jay.worknasidemo5.Dialogs.Login;
import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 11/21/2017.
 */

public class Account extends Fragment{

    private LinearLayout loginLayout;
    public Account(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_account, container, false);

         loginLayout = view.findViewById(R.id.account_login);

         loginLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                 Login loginDialog = new Login();
                 loginDialog.show(getFragmentManager(), "Login Dialog");
             }
         });

         return view;
    }

}
