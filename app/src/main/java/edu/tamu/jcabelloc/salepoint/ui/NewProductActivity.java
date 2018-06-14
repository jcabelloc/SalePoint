package edu.tamu.jcabelloc.salepoint.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import edu.tamu.jcabelloc.salepoint.R;


public class NewProductActivity extends AppCompatActivity {

    ImageView productImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        productImageView = findViewById(R.id.productImageView);
    }

    //TODO Check Permissions
    public void getImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                InputStream input = this.getContentResolver().openInputStream(selectedImageUri);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(input, null, options);
                input.close();
                options.inSampleSize = calculateInSampleSize(options, 128, 128);
                // Decode bitmap with inSampleSize set
                options.inJustDecodeBounds = false;
                input = this.getContentResolver().openInputStream(selectedImageUri);
                Bitmap imageBitmap = BitmapFactory.decodeStream(input, null, options);

                input.close();

                //Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                productImageView.setImageBitmap(imageBitmap);
                Log.d("JCC", "imageBitmap.getWidth(): " + imageBitmap.getWidth());
                Log.d("JCC", "imageBitmap.getHeight(): " + imageBitmap.getHeight());
                Log.d("JCC", "imageBitmap.getByteCount(): " + imageBitmap.getByteCount());
                Log.d("JCC", "imageBitmap.getConfig().toString(): " + imageBitmap.getConfig().toString());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                Bitmap newImageBitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
                Log.d("JCC", "newImageBitmap.getWidth(): " + newImageBitmap.getWidth());
                Log.d("JCC", "newImageBitmap.getHeight(): " + newImageBitmap.getHeight());
                Log.d("JCC", "newImageBitmap.getByteCount(): " + newImageBitmap.getByteCount());
                Log.d("JCC", "newImageBitmap.getConfig().toString(): " + newImageBitmap.getConfig().toString());

                OutputStream outputStream = openFileOutput("newImage.png", Context.MODE_PRIVATE);
                newImageBitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
