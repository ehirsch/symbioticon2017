package com.yomo.templateapp.providers;

import java.util.List;

import io.swagger.client.model.GiroTransaction;
import io.swagger.client.model.SmartTransaction;
import io.swagger.client.model.Transaction;

/**
 * Created by cdemon on 13.09.17.
 */

public interface ISmartTransactionProvider {

    public List<SmartTransaction> getSmartcheckTransactions(List<Transaction> lst);

    public boolean transmitSmartTransactions(List<SmartTransaction> lst);
}
