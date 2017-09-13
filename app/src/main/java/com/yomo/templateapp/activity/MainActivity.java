package com.yomo.templateapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.yomo.templateapp.utils.FontUtils;
import com.yomo.templateapp.R;
import com.yomo.templateapp.utils.SmartcheckUtils;
import com.yomo.templateapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiException;
import io.swagger.client.api.TransactionApi;
import io.swagger.client.model.Amount;
import io.swagger.client.model.GiroTransaction;
import io.swagger.client.model.Transaction;

public class MainActivity extends AppCompatActivity implements ApiCallback<List<Transaction>> {

	private final TransactionApi transactionApi = new TransactionApi();

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// configure the API Client
		transactionApi.getApiClient().setBasePath(getString(R.string.api_base_path));
		transactionApi.getApiClient().setApiKey(getString(R.string.api_key));

		// retrieve user specific ids
		Long accessId = (long) getResources().getInteger(R.integer.accessId);
		Long accountId = (long) getResources().getInteger(R.integer.accountId);

		listView = (ListView) findViewById(R.id.list);

		// retrieve transactions (async) & saldo
		try {
			transactionApi.listTransactionsAsync(accessId, accountId, null, 30, 0, null, null, this);
		} catch (ApiException e) {
			e.printStackTrace();
			showErrorText(e);
		}

		initYomoButtonMenuActions();
	}

	private void initYomoButtonMenuActions() {
		View settingsButton = findViewById(R.id.btnSettings);
		settingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(intent);
				FloatingActionMenu menu = findViewById(R.id.menu);
				menu.close(false);

			}
		});
	}

	private void hideProgressBar() {
		ProgressBar progressBar = findViewById(R.id.progressbar);
		progressBar.setVisibility(View.GONE);
	}

	private void showErrorText(ApiException e) {
		TextView error = findViewById(R.id.error_text);
		FontUtils.getInstance().applyYOMOFont(error);
		error.setVisibility(View.VISIBLE);
		error.setText(error.getText().toString() + "\n\n" + e.getResponseBody());
	}

	private void updateListView(List<Transaction> result) {
		TransactionAdapter adapter = new TransactionAdapter(this, new ArrayList<>(result));
		listView.setAdapter(adapter);
	}

	// Callbacks von der API:

	@Override
	public void onSuccess(final List<Transaction> result, int statusCode, Map<String, List<String>> responseHeaders) {
		SmartcheckUtils.setTransactionList(result);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateListView(result);
				hideProgressBar();
			}
		});
	}

	@Override
	public void onFailure(final ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				showErrorText(e);
				hideProgressBar();
			}
		});
	}

	@Override
	public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

	}

	@Override
	public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

	}

	// showing transactions and custom elements in the listview

	public class TransactionAdapter extends ArrayAdapter<Transaction> {

		int numOfItemsAboveTransactions = 2;

		TransactionAdapter(Context context, ArrayList<Transaction> txs) {
			super(context, 0, txs);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (position == 0) {
				if (convertView == null || convertView.findViewById(R.id.saldoContainer) == null) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.saldo_view, parent, false);
				}

				TextView title = convertView.findViewById(R.id.saldoTitle);
				TextView amount1 = convertView.findViewById(R.id.amount_value);
				TextView amount2 = convertView.findViewById(R.id.amount_value_decimal);

				long accountSaldo = 1235500;
				amount1.setText(StringUtils.getAmountBigPart(accountSaldo));
				amount2.setText(StringUtils.getAmountSmallPart(accountSaldo));

				FontUtils.getInstance().applyYOMOFont(title);
				FontUtils.getInstance().applyYOMOFont(amount1);
				FontUtils.getInstance().applyYOMOFont(amount2);

			} else if (position == 1) {
				if (convertView == null || convertView.findViewById(R.id.smartcheck_teaser_card) == null) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.smartcheck_card, parent, false);
					TextView button = convertView.findViewById(R.id.smartcheck_card_btn);
					button.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Intent intent = new Intent(MainActivity.this, SmartcheckActivity.class);
							startActivity(intent);
						}
					});

					TextView cardTitle = convertView.findViewById(R.id.smartcheck_card_title);
					TextView cardText = convertView.findViewById(R.id.smartcheck_card_text);
					TextView cardButton = convertView.findViewById(R.id.smartcheck_card_btn);

					FontUtils.getInstance().applyYOMOFont(cardTitle, FontUtils.Type.SEMI_BOLD);
					FontUtils.getInstance().applyYOMOFont(cardText);
					FontUtils.getInstance().applyYOMOFont(cardButton);

				}
			} else {
				if (convertView == null || convertView.findViewById(R.id.transactionContainer) == null) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.transaction_item, parent, false);
				}

				GiroTransaction t = (GiroTransaction) getItem(position - numOfItemsAboveTransactions);

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

			}

			return convertView;
		}

		@Override
		public int getCount() {
			return super.getCount() + numOfItemsAboveTransactions;
		}
	}

}
