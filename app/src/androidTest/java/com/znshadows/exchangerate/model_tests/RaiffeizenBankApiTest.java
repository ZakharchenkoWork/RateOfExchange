package com.znshadows.exchangerate.model_tests;

/**
 * Created by kostya on 31.10.2017.
 */

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.hast.exchangerate.mvp.models.AlfabankApi;
import com.hast.exchangerate.mvp.models.AlfabankApiImpl;
import com.hast.exchangerate.mvp.models.RaiffeizenBankApi;
import com.hast.exchangerate.mvp.models.RaiffeizenBankImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Konstantyn Zakharchenko on 31.10.2017.
 */
@RunWith(AndroidJUnit4.class)
public class RaiffeizenBankApiTest {
    RaiffeizenBankApi raiffeizenBankApi;

    @Before
    public void setUp() throws Exception {
        raiffeizenBankApi = new RaiffeizenBankImpl();
    }

    @Test
    public void testRaiffeizenBank() throws Exception {
        TestObserver<String> observer = raiffeizenBankApi.getTodayList().test().await();
        //Thread.sleep(5000);
        observer.assertSubscribed();
        observer.assertNoErrors();
        observer.assertComplete();
        assertNotEquals(observer.getEvents().get(0), null);
        assertNotEquals(observer.getEvents().get(0).size(), 0);
        assertNotEquals(observer.getEvents().get(0).get(0), null);

        assertTrue(observer.getEvents().get(0).get(0) instanceof String);


        String answer = (String)observer.getEvents().get(0).get(0);
        assertNotEquals(answer, null);
        assertTrue(answer.contains("</th><th>Buy</th><th>Sell</th></tr><tr>"));
        assertTrue(answer.contains("USD"));
        assertTrue(answer.contains("Euro"));
        assertTrue(answer.contains("RUR"));



    }
}