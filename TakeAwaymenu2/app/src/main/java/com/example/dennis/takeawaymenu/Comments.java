package com.example.dennis.takeawaymenu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dennis.takeawaymenu.model.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class Comments extends AppCompatActivity {
    private EditText id_EditText;
    private EditText Comment_EditText;
    private Button Input_Button;
    private TextView status_TextView;

    private ListView comment_TextView;

    private ArrayList<Comment> jsonToArrayList_comment(String jsonStr) {
        ArrayList<Comment> commentsArrayList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonStr);
            JSONArray jsonArray = root.getJSONArray("comment");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("_id");
                String datetime = jsonObject.getString("date");
                String comment = jsonObject.getString("comment");
                Comment comments = new Comment(id, datetime, comment);

                commentsArrayList.add(comments);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;

        }
        return commentsArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        String urlString2 = "http://192.168.35.128:8080/comment/getComments_json.php";
        MyAsynTask2 newTask = new MyAsynTask2();
        newTask.execute(urlString2);
        Comment_EditText = (EditText) findViewById(R.id.Comment_EditText);
        Input_Button = (Button) findViewById(R.id.Input_Button);
        status_TextView = (TextView) findViewById(R.id.status_TextView);
        Input_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postdata();
                Intent intent = new Intent(Comments.this, ShowComment.class);
                startActivity(intent);
            }

        });

    }

    private void postdata() {

        String postParams =
                "comment=" + Comment_EditText.getText().toString();
        System.out.println(postParams);
        String urlString = "http://192.168.35.128:8080/comment/postComment.php";
        MyAsynTask newTask = new MyAsynTask();
        newTask.execute(urlString, postParams);
    }


    private String postHttpURLConnection(String urlStr, String postParams) {
        String response = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            OutputStream outputStream = urlConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(postParams);
            dataOutputStream.flush();
            dataOutputStream.close();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }


            } else {
                response = "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getHttpURLConnection2(String urlStr) {
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String jsonStr = null;
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");

            }
            jsonStr = stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    private class MyAsynTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String retStr = postHttpURLConnection(params[0], params[1]);
            return retStr;
        }

        @Override
        protected void onPostExecute(String retStr) {
            super.onPostExecute(retStr);
            System.out.println("==============");
            System.out.println(retStr);

            status_TextView.setText(retStr);
            Toast.makeText(getApplicationContext(), retStr, Toast.LENGTH_LONG).show();
        }

    }

    private class MyAsynTask2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String jsonStr = getHttpURLConnection2(urlString);
            return jsonStr;
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);
            System.out.println("=============================");
            System.out.println(jsonStr);

            ArrayList<Comment> comment = jsonToArrayList_comment(jsonStr);
            System.out.println(comment);
//
            ArrayAdapter<Comment> arrayAdapter = new ArrayAdapter<Comment>(getApplicationContext()
                    , android.R.layout.simple_list_item_1, comment);

            comment_TextView = (ListView) findViewById(R.id.comment_TextView);
            comment_TextView.setAdapter(arrayAdapter);
        }
    }
}
