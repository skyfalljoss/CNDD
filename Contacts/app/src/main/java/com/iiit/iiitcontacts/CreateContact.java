package com.iiit.iiitcontacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iiit.iiitcontacts.dummy.DummyContent;

import java.io.ByteArrayOutputStream;
import java.util.Collections;

import static com.iiit.iiitcontacts.dummy.DummyContent.ITEMS;

public class CreateContact extends AppCompatActivity {

    EditText name, number, email;
    Button button;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_create_contact);

        //Getting data
        name = (EditText) findViewById(R.id.new_name);
        number = (EditText) findViewById(R.id.new_number);
        email = (EditText) findViewById(R.id.new_email);
        button = (Button) findViewById(R.id.button_create);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (name.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Name cannot be empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }

                else if (number.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Phone Number cannot be empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }

                else if (number.getText().toString().length() < 10 || number.getText().toString().length() > 13) {
                    Snackbar.make(view, "Invalid Phone Number, Should me minimum 10 digits", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }

                else if (!email.getText().toString().isEmpty() && !(email.getText().toString().contains("@"))) {
                    Snackbar.make(view, "Invalid Email Address format", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                else {

                    int new_id = ITEMS.size() + 1;

                    Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_contacts_launcher);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] b = baos.toByteArray();
                    String temp1 = Base64.encodeToString(b, Base64.DEFAULT);

                    DummyContent.DummyItem newcontact = new DummyContent.DummyItem(String.valueOf(new_id), name.getText().toString(), number.getText().toString(), email.getText().toString(), temp1);
                    DummyContent.addItem(newcontact);

                    Collections.sort(DummyContent.ITEMS);

                    Intent newIntent = new Intent(CreateContact.this, ContactListActivity.class);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    newIntent.putExtra("flag", true);
                    startActivity(newIntent);
                }
            }
        });
    }
}