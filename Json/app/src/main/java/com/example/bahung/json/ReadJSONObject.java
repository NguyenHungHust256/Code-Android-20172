package com.example.bahung.json;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bahung on 24/06/2018.
 **/

public class ReadJSONObject extends AsyncTask<String, Void, String>{
    Context context;

    public ReadJSONObject(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line="";
            while((line = bufferedReader.readLine())!= null){
                content.append(line);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s , Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            String hung = jsonObject.getString("state");
            Toast.makeText(context, hung +"ok", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Log.d("error",e.toString());
            e.printStackTrace();
        }
    }
}
