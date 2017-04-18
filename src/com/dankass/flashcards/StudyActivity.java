package com.dankass.flashcards;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class StudyActivity extends Activity {
	
		private CardsDataSource datasource;
	
		List<Card> flashcards;
		int count = 0;
		Button myButton;
		TextView myTextView;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_study);
			
			
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			//ListView myListView = (ListView) findViewById(R.id.myListView);
			myTextView = (TextView) findViewById(R.id.studyView);
			myButton = (Button) findViewById(R.id.next);
			
			datasource = new CardsDataSource(this);
			datasource.open();
			
			flashcards = datasource.getAllCards();
			
			if(flashcards.isEmpty()){
				myTextView.setText("Please add Cards to Study");
				myButton.setVisibility(0);
			}else{
				Collections.shuffle(flashcards);
				myTextView.setText(flashcards.get(count).getFront());
				myButton.setText("See Back of Card");
			}
			//myTextView.setText("Yay! you completed the set");
			//myButton.setVisibility(0);
		
		}
		
		public void next(View view){
			if(myButton.getText().equals("See Back of Card")){
				myTextView.setText(flashcards.get(count).getBack());
				myButton.setText("See Next Card");
			}else if(myButton.getText().equals("See Next Card")){
				count++;
				if(count >= flashcards.size()){
					myTextView.setText("Yay!");
					myButton.setText("Startover?");
					
				}else{
					myTextView.setText(flashcards.get(count).getFront());
					myButton.setText("See Back of Card");
				}
			}else if(myButton.getText().equals("Startover?")){
				count = 0;
				Collections.shuffle(flashcards);
				myTextView.setText(flashcards.get(count).getFront());
				myButton.setText("See Back of Card");
				}
		}
		
		
		@Override
		protected void onResume() {
			datasource.open();
			super.onResume();		
		}

		@Override
		protected void onPause() {
			datasource.close();
			super.onPause();
		}
		
		
}
