package com.yomo.templateapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yomo.templateapp.utils.FontUtils;
import com.yomo.templateapp.R;

public class SmartcheckActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smartcheck);

		TextView infoText = findViewById(R.id.smartcheck_start_info_text);
		TextView title = findViewById(R.id.title);
		TextView button = findViewById(R.id.button);

		FontUtils.getInstance().applyYOMOFont(infoText);
		FontUtils.getInstance().applyYOMOFont(title);
		FontUtils.getInstance().applyYOMOFont(button, FontUtils.Type.SEMI_BOLD);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
}
