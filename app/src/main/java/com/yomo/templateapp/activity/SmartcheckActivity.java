package com.yomo.templateapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yomo.templateapp.R;
import com.yomo.templateapp.fragment.FilingFragment;
import com.yomo.templateapp.fragment.TransactionCategorizerFragment;
import com.yomo.templateapp.utils.FontUtils;
import com.yomo.templateapp.utils.SmartcheckUtils;

import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.model.SmartTransaction;

public class SmartcheckActivity extends AppCompatActivity
        implements TransactionCategorizerFragment.OnFragmentInteractionListener,
                   FilingFragment.OnFragmentInteractionListener {

    private int currentIndex;
    private List<io.swagger.client.model.SmartTransaction> relevant;
    private boolean done;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smartcheck);
        currentIndex = 0;
        done = false;
        relevant = SmartcheckUtils.getRelevantTransactions();

        /*
        ViewGroup v = (ViewGroup) findViewById(R.id.smartcheckLayout);
        Fade mFade = new Fade(Fade.IN);

        TransitionManager.beginDelayedTransition(v, mFade);
        */

        loadNextFragment(currentIndex);

		TextView cta = findViewById(R.id.cta);
        FontUtils.getInstance().applyYOMOFont(cta, FontUtils.Type.SEMI_BOLD);

		cta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                finish();
                if( done ) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3.eu-central-1.amazonaws.com/smartsteuer-symbioticon2017/index.html"));
                    startActivity(browserIntent);
                }
			}
		});
	}


    private void loadNextFragment(int transactionIndex) {
        setCTAText("Später weitermachen.");
        // Create new fragment and transaction
        Fragment newFragment = TransactionCategorizerFragment.newInstance(transactionIndex);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    private void loadFilingFragment() {
        // Create new fragment and transaction
        Fragment newFragment = FilingFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    private void showErrorText(ApiException e) {
		TextView error = findViewById(R.id.error_text);
		FontUtils.getInstance().applyYOMOFont(error);
		error.setVisibility(View.VISIBLE);
		error.setText(error.getText().toString() + "\n\n" + e.getResponseBody());
	}

    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("Fragment Interaction!");
    }

    public SmartTransaction getItem(int i) {
        return relevant.get(i);
    }

    public void navigateToNext() {

        currentIndex = currentIndex+1;

        if(currentIndex < relevant.size() ) {
            loadNextFragment(currentIndex);
        } else {
            System.out.println("### done -> finish move!");
            done = true;
            loadFilingFragment();
            setCTAText("Ich will mein Geld \uD83D\uDCB0 zurück!");
            SmartcheckUtils.transmitSmartTransactions(relevant);
        }
    }

    public void storeAnswer(boolean lastAnswer) {
        relevant.get(currentIndex).getAnswers().add(lastAnswer);
    }

    private void setCTAText(String text) {
        TextView button = findViewById(R.id.cta);
        if( null != button ) {
            button.setText(text);
        }
    }

}
