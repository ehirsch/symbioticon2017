package com.yomo.templateapp.providers;

import com.yomo.templateapp.utils.StringUtils;

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

        if(g.getCreditor().toLowerCase().indexOf("telekom") >= 0) {

            SmartTransaction t = new SmartTransaction(g);

            List<String> questions = new ArrayList<>();
            questions.add("Nutzt Du Dein Smartphone für Deine Ausbildung?");

            t.setQuestions(questions);

            t.setCustomInfoText(
                    new StringBuilder("Du hast monatlich ")
                            .append(StringUtils.getAmountBigPart(Math.abs(g.getAmount().getValue())))
                            .append(StringUtils.getAmountSmallPart(g.getAmount().getValue()))
                            .append("€")
                            .append(" an die ").append(g.getCreditor()).append(" überwiesen.")
                            .toString()
            );

            return t;
        }
        else if(g.getCreditor().toLowerCase().indexOf("hofmann optik") >= 0) {

            SmartTransaction t = new SmartTransaction(g);

            List<String> questions = new ArrayList<>();
            questions.add("Hast Du für die Brille ein ärztliches Attest gehabt?");
            questions.add("Brauchst Du die Brille für Deinen Beruf z.B. als Schutz- oder Arbeitsplatzbrille?");

            t.setQuestions(questions);

            t.setCustomInfoText(
                    new StringBuilder("Du hast ")
                            .append(StringUtils.getAmountBigPart(Math.abs(g.getAmount().getValue())))
                            .append(StringUtils.getAmountSmallPart(g.getAmount().getValue()))
                            .append("€")
                            .append(" an ").append(g.getCreditor()).append(" überwiesen.")
                            .toString()
            );

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
