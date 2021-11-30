package com.giocom.sharingit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Add a new item
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;

    private ImageView photo;
    private Bitmap image;
    private int REQUEST_CODE = 1;

    private ItemList item_list = new ItemList();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        photo = (ImageView) findViewById(R.id.image_view);

        photo.setImageResource(android.R.drawable.ic_menu_gallery);

        context = getApplicationContext();
        item_list.loadItems(context);
    }

    public void saveItem (View view) {

        String title_str = title.getText().toString();
        String description_str = description.getText().toString();

        if (title_str.equals("")) {
            title.setError("Campo vacío!");
            return;
        }

        if (description_str.equals("")) {
            description.setError("Campo vacío!");
            return;
        }

        Item item = new Item(title_str, description_str, image, null );

        item_list.addItem(item);
        item_list.saveItems(context);

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