package de.uni_weimar.bodyprint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import de.uni_weimar.bodyprint.BusinessLogic.Controller;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Main";

    static{
        System.loadLibrary("opencv_java3");
    }

    TextView outText;
    Button printPositionButton;
    Button printTestButton;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        controller = new Controller(this);
//        Bitmap query = BitmapFactory.decodeResource(getResources(), R.drawable.query3);
//        User user = controller.scan(query);
//        Toast.makeText(this, (String) user.getName(), Toast.LENGTH_LONG).show();

        outText = (TextView) findViewById(R.id.text_out);

        printPositionButton = (Button) findViewById(R.id.button_position);
        printPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outText.setText(getProcData("absolute_position_reporting"));
            }
        });

        printTestButton = (Button) findViewById(R.id.button_test);
        printTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outText.setText(getProcData("test_reporting"));
            }
        });
    }

    String getProcData(String fileName){

        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        OutputStreamWriter osw = null;

        String command="/system/bin/cat /proc/"+fileName;

        try { // Run Script
            proc = runtime.exec("su");
            osw = new OutputStreamWriter(proc.getOutputStream());
            osw.write(command);
            osw.flush();
            osw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if (proc != null) {
                proc.waitFor();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getStringFromInputStream(proc.getInputStream());
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }


}
