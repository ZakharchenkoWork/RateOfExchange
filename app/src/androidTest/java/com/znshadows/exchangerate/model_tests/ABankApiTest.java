package com.znshadows.exchangerate.model_tests;

/**
 * Created by kostya on 31.10.2017.
 */

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.hast.exchangerate.general.models.responces.NBUResponse;
import com.hast.exchangerate.general.models.responces.abank.ABANKResponce;
import com.hast.exchangerate.general.models.responces.abank.Datum;
import com.hast.exchangerate.mvp.models.ABankApi;
import com.hast.exchangerate.mvp.models.ABankApiImpl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Konstantyn Zakharchenko on 31.10.2017.
 */
@RunWith(AndroidJUnit4.class)
public class ABankApiTest {
    ABankApi aBankApi;

    @Before
    public void setUp() throws Exception {
        aBankApi = new ABankApiImpl();
    }

    @Test
    public void testABank() throws Exception {
        TestObserver<ABANKResponce> observer = aBankApi.getTodayList().test().await();
        //Thread.sleep(5000);
        observer.assertSubscribed();
        observer.assertNoErrors();
        observer.assertComplete();
        assertNotEquals(observer.getEvents().get(0), null);
        assertNotEquals(observer.getEvents().get(0).size(), 0);
        assertNotEquals(observer.getEvents().get(0).get(0), null);

        assertTrue(observer.getEvents().get(0).get(0) instanceof ABANKResponce);
        assertTrue(observer.getEvents().get(0).get(0) instanceof ABANKResponce);

        ABANKResponce answer = (ABANKResponce)observer.getEvents().get(0).get(0);
        assertNotEquals(answer, null);
        assertNotEquals(answer.getData(), null);
        assertNotEquals(answer.getData().size(), 0);
        assertTrue(answer.getData().get(0) instanceof Datum);
        assertNotEquals( answer.getData().get(0), null);

        List<Datum> data = answer.getData();

        boolean isUSDFound = false;
        for (Datum item : data) {
            isUSDFound = item.getCcyA().equals("USD");
            if (isUSDFound){
                break;
            }
        }
        assertTrue(isUSDFound);
    }
}