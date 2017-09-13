package com.yomo.templateapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yomo.templateapp.utils.FontUtils;
import com.yomo.templateapp.R;
import com.yomo.templateapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.TransactionApi;
import io.swagger.client.model.Amount;
import io.swagger.client.model.GiroTransaction;
import io.swagger.client.model.Transaction;

public class SmartcheckActivity extends AppCompatActivity {

	private ListView listView;

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

		listView = (ListView) findViewById(R.id.list);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateListView(MainActivity.cachedTransactions);

			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	private void showErrorText(ApiException e) {
		TextView error = findViewById(R.id.error_text);
		FontUtils.getInstance().applyYOMOFont(error);
		error.setVisibility(View.VISIBLE);
		error.setText(error.getText().toString() + "\n\n" + e.getResponseBody());
	}

	private void updateListView(List<Transaction> result) {
		SmartTransactionAdapter adapter = new SmartTransactionAdapter(this, new ArrayList<>(result));
		listView.setAdapter(adapter);
	}

	public class SmartTransactionAdapter extends ArrayAdapter<Transaction> {

		SmartTransactionAdapter(Context context, ArrayList<Transaction> txs) {
			super(context, 0, txs);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null || convertView.findViewById(R.id.transactionContainer) == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.transaction_item, parent, false);
			}

			GiroTransaction t = (GiroTransaction) getItem(position);

			TextView a1 = convertView.findViewById(R.id.amount_value);
			TextView a2 = convertView.findViewById(R.id.amount_value_decimal);
			TextView nameTv = convertView.findViewById(R.id.name);

			Amount amount = t.getAmount();
			Long value = amount.getValue();

			String name = value > 0 ? t.getDebtor() : t.getCreditor();

			a1.setText(StringUtils.getAmountBigPart(value));
			a2.setText(StringUtils.getAmountSmallPart(value));

			nameTv.setText(name);

			FontUtils.getInstance().applyYOMOFont(a1);
			FontUtils.getInstance().applyYOMOFont(a2);
			FontUtils.getInstance().applyYOMOFont(nameTv);

			return convertView;
		}

	}

}
