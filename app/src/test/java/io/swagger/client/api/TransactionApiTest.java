/*
 * YOMO-Service-Emulation with AHOI
 * [Data Privacy](/sandboxmanager/#/privacy)  [Terms of Service](/sandboxmanager/#/terms)  [Imprint](https://sparkassen-hub.com/impressum/)  &copy; 2017 Starfinanz - Ein Unternehmen der Finanz Informatik
 *
 * OpenAPI spec version: 2.1.0-yomo-emulator
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.Transaction;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for TransactionApi
 */
@Ignore
public class TransactionApiTest {

    private final TransactionApi api = new TransactionApi();

    
    /**
     * Get transaction
     *
     * Returns the transaction identified by **transactionId** in relationship with **accountId**.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getTransactionTest() throws ApiException {
        Long accessId = null;
        Long accountId = null;
        Long transactionId = null;
        Transaction response = api.getTransaction(accessId, accountId, transactionId);

        // TODO: test validations
    }
    
    /**
     * List transactions for account
     *
     * Retrieve all transactions for **accountId**.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listTransactionsTest() throws ApiException {
        Long accessId = null;
        Long accountId = null;
        Long maxAge = null;
        Integer limit = null;
        Integer offset = null;
        String from = null;
        String to = null;
        List<Transaction> response = api.listTransactions(accessId, accountId, maxAge, limit, offset, from, to);

        // TODO: test validations
    }
    
}
