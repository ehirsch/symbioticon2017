package com.yomo.templateapp.providers;

import java.util.List;

import io.swagger.client.model.SmartTransaction;
import io.swagger.client.model.Transaction;

/**
 * Created by cdemon on 13.09.17.
 */

public class SSTBackendProvider implements ISmartTransactionProvider {

    public List<SmartTransaction> getSmartcheckTransactions(List<Transaction> lst) {

        throw new RuntimeException("Not implmented...");
    }

    public boolean transmitSmartTransactions(List<SmartTransaction> lst) {

        throw new RuntimeException("Not implmented...");
    }
}
