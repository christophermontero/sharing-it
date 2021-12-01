package com.giocom.sharingit;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Add a new contact
 */
public class AddContactActivity extends AppCompatActivity {

    private ContactList contact_list = new ContactList();
    private Context context;

    private EditText username;
    private EditText phone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);

        context = getApplicationContext();
        contact_list.loadContacts(context);
    }

    public void saveContact(View view) {

        String username_str = username.getText().toString();
        String phone_str = phone.getText().toString();
        String email_str = email.getText().toString();

        if (username_str.equals("")) {
            username.setError("Campo vacío!");
            return;
        }

        if (email_str.equals("")) {
            email.setError("Campo vacío!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Debe ser un correo electrónico!");
            return;
        }

        if (!contact_list.isUsernameAvailable(username_str)){
            username.setError("Este nombre ya existe!");
            return;
        }

        Contact contact = new Contact(username_str, phone_str, email_str, null);

        contact_list.addContact(contact);
        contact_list.saveContacts(context);

        // End AddContactActivity
        finish();
    }
}

