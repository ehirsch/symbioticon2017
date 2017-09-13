package com.yomo.templateapp.providers;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.GiroTransaction;
import io.swagger.client.model.SmartTransaction;
import io.swagger.client.model.Transaction;

/**
 * Created by cdemon on 13.09.17.
 */
public class MockBackendProvider implements ISmartTransactionProvider {

    public List<SmartTransaction> getSmartcheckTransactions(List<Transaction> lst) {

        List<SmartTransaction> ret = new ArrayList<>();

        for(Transaction t : lst) {
            if(t instanceof GiroTransaction) {
                GiroTransaction g = (GiroTransaction) t;
                SmartTransaction smartTransaction = getSmartcheckTransaction(g);

                if(smartTransaction != null) {
                    ret.add(smartTransaction);
                }
            }
        }

        return ret;
    }


    private SmartTransaction getSmartcheckTransaction(GiroTransaction g) {

        if(g.getCreditor().toLowerCase().indexOf("telefonica") >= 0 ||
                g.getDebtor().toLowerCase().indexOf("telefonica") >= 0 ||
                g.getPurpose().toLowerCase().indexOf("telefonica") >= 0) {

            SmartTransaction t = new SmartTransaction(g);

            List<String> questions = new ArrayList<>();
            questions.add("Hast Du diesen Vertrag beruflich genutzt?");

            t.setQuestions(questions);

            return t;
        }

        return null;
    }

    public boolean transmitSmartTransactions(List<SmartTransaction> lst) {
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex) {

        }

        return true;
    }

}
