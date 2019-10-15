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
import com.hast.exchangerate.mvp.models.AlfabankApi;
import com.hast.exchangerate.mvp.models.AlfabankApiImpl;


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
    AlfabankApi alfabankApi;

    @Before
    public void setUp() throws Exception {
        alfabankApi = new AlfabankApiImpl();
    }

    @Test
    public void getTodaysListTest() throws Exception {
        TestObserver<String> observer = alfabankApi.getTodayList().test().await();
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
        assertTrue(answer.contains("<div class=\"currency-tab-block\" data-tab=\"1\">"));
        assertTrue(answer.contains("USD"));
        assertTrue(answer.contains("EUR"));
        assertTrue(answer.contains("RUB"));
        assertTrue(answer.contains("<span class=\"small-title\">Купівля</span>"));
        assertTrue(answer.contains("<span class=\"small-title\">Продаж</span>"));


    }
}