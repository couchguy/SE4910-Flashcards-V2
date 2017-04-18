package com.dankass.flashcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditCard extends Activity {
	
	EditText myTitle;
	EditText myFront;
	EditText myBack;
	//Button pictureButton;
	//ImageView image;
	boolean editing = false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_card);
		
		 myTitle = (EditText) findViewById(R.id.title);
		 myFront = (EditText) findViewById(R.id.front);
		 myBack  = (EditText) findViewById(R.id.back);
		 
		 //pictureButton = (Button) findViewById(R.id.pictureButton);
		
		 
		
		 
		 Bundle extras = getIntent().getExtras();
			if(extras != null){
				String value = extras.getString("oldCard");
				//add new card to data base here split value by `
				
				String cards[] = value.split("`");
				String title = cards[0];
				String front = cards[1];
				String back = cards[2];
				
				myTitle.setText(title);
				myFront.setText(front);
				myBack.setText(back);
				editing = true;
			}
		 
		 
	}
	
	public void save(View view) {
		String title = myTitle.getText().toString();
		String front = myFront.getText().toString();
		String back  =  myBack.getText().toString();
		String send = title + "`" + front + "`" + back;
		
		//Error checking checking for blank or null
		if(title.equals(null) || title.equals("")){
			Toast.makeText(getBaseContext(), "Title cannot be blank", Toast.LENGTH_LONG).show();
		} else if(front.equals(null) || front.equals("")){
			Toast.makeText(getBaseContext(), "Front cannot be blank", Toast.LENGTH_LONG).show();
		} else if(back.equals(null) || back.equals("")){
			Toast.makeText(getBaseContext(), "Back cannot be blank", Toast.LENGTH_LONG).show();
		}else {
			
			Intent i = new Intent(getApplicationContext(), EditActivity.class);
			i.putExtra("Card", send);
			startActivity(i);
		
			Toast.makeText(getBaseContext(), title + " has been added!", Toast.LENGTH_LONG).show();
		
			finish();
		}
	}
	
	public void cancel(View view) {
		if(editing == true){
			//this means that the user is editing but hit cancel, but we already deleted it from database
			//so we need to add it back in. 
			String title = myTitle.getText().toString();
			String front = myFront.getText().toString();
			String back  =  myBack.getText().toString();
			String send = title + "`" + front + "`" + back;
			
			Intent i = new Intent(getApplicationContext(), EditActivity.class);
			i.putExtra("Card", send);
			startActivity(i);
			//this should work. only need if the user cancels after clicking to edit it.
		}else{
			
			Intent i = new Intent("com.dankass.flashcards.EditActivity");
			startActivity(i);
		}
		finish();
	}
	
	@Override
	public void onBackPressed() {
	    cancel(findViewById(R.layout.activity_edit_card));
	    return;
	}
	
		
}
