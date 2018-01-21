package com.example.jay.worknasidemo5.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;

/**
 * Created by Jay on 12/2/2017.
 */

public class BackgroundTask extends AsyncTask<String,  Void, String> {

    AlertDialog alertDialog;
    Context context;
    public BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Information");
    }

    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://worknasi.000webhostapp.com/add_info.php";
        String login_url = "http://worknasi.000webhostapp.com/login.php";
        String method = params[0];

        if(method.equals("register")){
            String firstName = params[1];
            String lastName = params[2];
            String username = params[3];
            String phoneNo = params[4];
            String email = params[5];
            String password = params[6];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("firstName", "UTF-8")+"="+URLEncoder.encode(firstName, "UTF-8")+"&"+
                        URLEncoder.encode("lastName", "UTF-8")+"="+URLEncoder.encode(lastName, "UTF-8")+"&"+
                        URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"+
                        URLEncoder.encode("phoneNo", "UTF-8")+"="+URLEncoder.encode(phoneNo, "UTF-8")+"&"+
                        URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "registration is successfully";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("login")){
            String login_username = params[1];
            String login_password = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("login_username", "UTF-8")+"="+URLEncoder.encode(login_username, "UTF-8")
                        +"&"+URLEncoder.encode("login_password", "UTF-8")+"="+URLEncoder.encode(login_password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = " ";
                String line = " ";

                while((line = bufferedReader.readLine()) != null){
                    response += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("registration is successfully")){
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }else{
            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }
}
