package com.giocom.sharingit;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Add a new contact
 */
public class AddContactActivity extends AppCompatActivity {

    private EditText username;
    private EditText phone;
    private EditText email;

    private ImageView photo;
    private Bitmap image;
    private int REQUEST_CODE = 1;

    private ContactList contact_list = new ContactList();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        photo = (ImageView) findViewById(R.id.contacts_image_view);

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

        Contact contact = new Contact(username_str, phone_str, email_str, image, null);

        contact_list.addContact(contact);
        contact_list.saveContacts(context);

        // End AddItemActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void deletePhoto(View view) {
        image = null;
        photo.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){
        if (request_code == REQUEST_CODE && result_code == RESULT_OK){
            Bundle extras = intent.getExtras();
            image = (Bitmap) extras.get("data");
            photo.setImageBitmap(image);
        }
    }
}

