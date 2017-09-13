package com.yomo.templateapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yomo.templateapp.utils.FontUtils;
import com.yomo.templateapp.R;

public class SettingsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		applyFonts();
	}

	private void applyFonts() {
		TextView title = findViewById(R.id.title);
		TextView changePw = findViewById(R.id.change_pw);
		TextView changePin = findViewById(R.id.change_pin);
		TextView cardActive = findViewById(R.id.card_active);
		TextView orderCard = findViewById(R.id.order_card);

		FontUtils.getInstance().applyYOMOFont(title);
		FontUtils.getInstance().applyYOMOFont(changePw);
		FontUtils.getInstance().applyYOMOFont(changePin);
		FontUtils.getInstance().applyYOMOFont(cardActive);
		FontUtils.getInstance().applyYOMOFont(orderCard);
	}
}
