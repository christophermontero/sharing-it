package com.giocom.sharingit;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity {

    private ContactList contact_list = new ContactList();
    private Contact contact;
    private EditText email;
    private EditText phone;
    private EditText username;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();
        contact_list.loadContacts(context);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        contact = contact_list.getContact(pos);

        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);

        username.setText(contact.getUsername());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());
    }

    public void saveContact(View view) {

        String phone_str = phone.getText().toString();
        String email_str = email.getText().toString();

        if (phone_str.equals("")) {
            email.setError("Campo vacío!");
            return;
        }

        if (email_str.equals("")) {
            email.setError("Campo vacío!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Debe ser una dirección válida!");
            return;
        }

        String username_str = username.getText().toString();
        String id = contact.getId(); // Reuse the contact id

        contact_list.deleteContact(contact);

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contact_list.isUsernameAvailable(username_str) && !(contact.getUsername().equals(username_str))) {
            username.setError("Este nombre ya existe!");
            return;
        }

        Contact updated_contact = new Contact(username_str, phone_str, email_str, id);

        contact_list.addContact(updated_contact);
        contact_list.saveContacts(context);

        // End EditContactActivity
        finish();
    }

    public void deleteContact(View view) {

        contact_list.deleteContact(contact);
        contact_list.saveContacts(context);

        // End EditContactActivity
        finish();
    }
}
