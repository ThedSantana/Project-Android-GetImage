package com.unpam.getimagae6i;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	static final int REQ_CAMERA = 0;
	static final int REQ_GALLERY = 1;
	ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView = (ImageView) findViewById(R.id.imageView1);
        findViewById(R.id.AmbilGambarButton).setOnClickListener(this);
        findViewById(R.id.TampilkanGambarButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.AmbilGambarButton:
			Intent cameraIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, REQ_CAMERA);
			break;
		case R.id.TampilkanGambarButton:
			Intent galeriIntent = new Intent(Intent.ACTION_PICK);
			galeriIntent.setType("image/*");
			startActivityForResult(galeriIntent, REQ_GALLERY);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data){
		switch (requestCode){
		case REQ_CAMERA:
			if (resultCode == RESULT_OK){
				Bitmap bmp = (Bitmap) data.getExtras().get("data");
				imageView.setImageBitmap(bmp);
				
				android.provider.MediaStore.Images.Media.insertImage(
						getContentResolver(), bmp, null, null);
			} else {
				Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
			}
		case REQ_GALLERY:
			if (resultCode == RESULT_OK){
				Uri galeriUri = data.getData();
				imageView.setImageURI(galeriUri);
			}
		}
	}
}
