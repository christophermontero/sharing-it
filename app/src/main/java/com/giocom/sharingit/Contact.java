package com.giocom.sharingit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.UUID;


public class Contact {
    private String username;
    private String phone;
    private String email;
    protected transient Bitmap image;
    protected String image_base64;
    private String id;

    Contact(String username, String phone, String email, Bitmap image, String id) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        addImage(image);

        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }

    private void addImage(Bitmap new_image) {
        if (new_image != null) {
            image = new_image;
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            new_image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
            byte[] b = byteArrayBitmapStream.toByteArray();
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }

    public Bitmap getImage(){
        if (image == null && image_base64 != null) {
            byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT);
            image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        }
        return image;
    }

    public String getId(){
        return this.id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public void updateId(String id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}