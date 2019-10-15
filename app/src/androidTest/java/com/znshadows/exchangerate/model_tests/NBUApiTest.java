package com.znshadows.exchangerate.model_tests;

/**
 * Created by kostya on 31.10.2017.
 */

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.hast.exchangerate.general.models.responces.NBUResponse;
import com.hast.exchangerate.general.models.responces.PrivateBankResponse;
import com.hast.exchangerate.mvp.models.NBUApi;
import com.hast.exchangerate.mvp.models.NBUApiImpl;
import com.hast.exchangerate.mvp.models.PrivateBankApi;
import com.hast.exchangerate.mvp.models.PrivateBankApiImpl;

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
public class NBUApiTest {
    NBUApi nbuApi;

    @Before
    public void setUp() throws Exception {
        nbuApi = new NBUApiImpl();
    }

    @Test
    public void getTodaysListTest() throws Exception {
        TestObserver<List<NBUResponse>> observer = nbuApi.getTodayList().test().await();
        //Thread.sleep(5000);
        observer.assertSubscribed();
        observer.assertNoErrors();
        observer.assertComplete();
        assertNotEquals(observer.getEvents().get(0), null);
        assertNotEquals(observer.getEvents().get(0).size(), 0);
        assertNotEquals(observer.getEvents().get(0).get(0), null);

        assertTrue(observer.getEvents().get(0).get(0) instanceof List);


        List<Object> answer = (List<Object>)observer.getEvents().get(0).get(0);
        assertNotEquals(answer, null);
        assertNotEquals(answer.size(), 0);
        assertTrue(answer.get(0) instanceof NBUResponse);
        assertNotEquals( ( (NBUResponse)answer.get(0)).getCode(), null);
        List<NBUResponse> responces = (List<NBUResponse>)observer.getEvents().get(0).get(0);

        assertNotEquals( responces.get(0).getCode(), null);
        boolean isUSDFound = false;
        for (NBUResponse item : responces) {
            isUSDFound = item.getCode().equals("USD");
            if (isUSDFound){
                break;
            }
        }
        assertTrue(isUSDFound);
    }
}