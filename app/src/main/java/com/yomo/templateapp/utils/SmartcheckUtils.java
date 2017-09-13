package com.yomo.templateapp.utils;

import com.yomo.templateapp.providers.ISmartTransactionProvider;
import com.yomo.templateapp.providers.MockBackendProvider;
import com.yomo.templateapp.providers.SSTBackendProvider;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.SmartTransaction;
import io.swagger.client.model.Transaction;

/**
 * Created by cdemon on 13.09.17.
 */
public class SmartcheckUtils {

    private static List<Transaction> transactionList = new ArrayList<>();

    //TODO: call REST endpoint here to check entry and fetch questions
    //
    //private static ISmartTransactionProvider provider = new SSTBackendProvider();
    //
    //mocking entries here instead...
    //
    private static ISmartTransactionProvider provider = new MockBackendProvider();

    public static List<Transaction> getTransactionList() {
        return transactionList;
    }

    public static void setTransactionList(List<Transaction> transactionList) {
        SmartcheckUtils.transactionList = transactionList;
    }

    public static List<SmartTransaction> getRelevantTransactions() {

        return  provider.getSmartcheckTransactions(getTransactionList());
    }

    public static boolean transmitSmartTransactions(List<SmartTransaction> lst) {

        return provider.transmitSmartTransactions(lst);
    }

}
