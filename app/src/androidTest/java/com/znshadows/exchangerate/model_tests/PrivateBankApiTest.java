package com.znshadows.exchangerate.model_tests;

/**
 * Created by kostya on 31.10.2017.
 */

import android.support.test.runner.AndroidJUnit4;

import com.znshadows.exchangerate.general.models.responces.PrivateBankResponse;
import com.znshadows.exchangerate.mvp.models.PrivateBankApi;
import com.znshadows.exchangerate.mvp.models.PrivateBankApiImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Natali Zakharchenko on 31.10.2017.
 */
@RunWith(AndroidJUnit4.class)
public class PrivateBankApiTest {
    PrivateBankApi privateBankApi;

    @Before
    public void setUp() throws Exception {
        privateBankApi = new PrivateBankApiImpl();
    }

    @Test
    public void getTodaysListTest() throws Exception {
        TestObserver<List<PrivateBankResponse>> observer = privateBankApi.getTodayList(true, "", 4).test().await();

        observer.assertSubscribed();
        observer.assertNoErrors();
        observer.assertComplete();
        assertNotEquals(observer.getEvents().get(0), null);
        assertNotEquals(observer.getEvents().get(0).size(), 0);
        assertNotEquals(observer.getEvents().get(0).get(0), null);

        assertTrue(observer.getEvents().get(0).get(0) instanceof List);
        assertTrue(observer.getEvents().get(0).get(0) instanceof List);

        List<Object> answer = (List<Object>)observer.getEvents().get(0).get(0);
        assertNotEquals(answer, null);
        assertNotEquals(answer.size(), 0);
        assertTrue(answer.get(0) instanceof PrivateBankResponse);
        assertNotEquals( ( (PrivateBankResponse)answer.get(0)).getCode(), null);
        List<PrivateBankResponse> responces = (List<PrivateBankResponse>)observer.getEvents().get(0).get(0);

        assertNotEquals( responces.get(0).getCode(), null);
        boolean isUSDFound = false;
        for (PrivateBankResponse item : responces) {
            isUSDFound = item.getCode().equals("USD");
            if (isUSDFound){
                break;
            }
        }
        assertTrue(isUSDFound);
    }
}