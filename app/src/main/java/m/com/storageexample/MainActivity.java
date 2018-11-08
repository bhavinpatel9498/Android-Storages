package m.com.storageexample;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    private Button saveBtn;
    private Button viewBtn;
    private Button deleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = findViewById(R.id.button);
        viewBtn = findViewById(R.id.button2);
        deleteBtn = findViewById(R.id.button3);


        SaveButtonMethod(saveBtn);
        viewButtonMethod(viewBtn);
        deleteButtonMethod(deleteBtn);

    }

    private void SaveButtonMethod(Button btn)
    {
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                ObjectOutputStream o = null;
                FileOutputStream f = null;

                try
                {
                   File fileCache = File.createTempFile("datacache.txt", null, getApplicationContext().getCacheDir());

                    File file = new File(getApplicationContext().getFilesDir(), "data.txt");

                    File fileSDCard = new File(Environment.getExternalStorageDirectory(), "data.txt");

                    f = getApplicationContext().openFileOutput("data.txt", Context.MODE_PRIVATE);
                    o = new ObjectOutputStream(f);

                    EditText ele1 =(EditText) findViewById(R.id.editText);
                    String sName = ele1.getText().toString();
                    o.writeObject(sName);

                    Toast.makeText(getApplicationContext(), "Details Saved Storage", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error in save", Toast.LENGTH_SHORT).show();
                }
                finally
                {
                    try
                    {
                        if (o != null)
                            o.close();
                        if (f != null)
                            f.close();
                    }
                    catch (Exception e)
                    {
                    }
                }




            }
        });
    }

    private void viewButtonMethod(Button btn)
    {
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                FileInputStream fi = null;
                ObjectInputStream oi = null;


                try
                {
                    fi = getApplicationContext().openFileInput("data.txt");
                    oi = new ObjectInputStream(fi);
                    String valString = (String) oi.readObject();

                    TextView t1 =  findViewById(R.id.textView);
                    t1.setText(valString);

                    Toast.makeText(getApplicationContext(), "View Details now", Toast.LENGTH_SHORT).show();


                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error in view", Toast.LENGTH_SHORT).show();
                }
                finally
                {
                    try
                    {
                        if (oi != null)
                            oi.close();
                        if (fi != null)
                            fi.close();
                    }
                    catch (Exception e)
                    {
                    }
                }


            }
        });
    }

    private void deleteButtonMethod(Button btn)
    {
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                try
                {
                    File file = new File(getApplicationContext().getFilesDir(), "data.txt");
                    file.delete();

                    TextView t1 =  findViewById(R.id.textView);
                    t1.setText("");

                    Toast.makeText(getApplicationContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error in delete", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
