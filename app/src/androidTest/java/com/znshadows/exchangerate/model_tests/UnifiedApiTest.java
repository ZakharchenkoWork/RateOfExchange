package com.znshadows.exchangerate.model_tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.hast.exchangerate.general.models.BANKS;
import com.hast.exchangerate.general.models.UnifiedBankResponse;
import com.hast.exchangerate.mvp.models.IUnifiedModel;
import com.hast.exchangerate.mvp.models.UnifiedModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by Konstantyn Zakharchenko on 15.10.2019.
 */
@RunWith(AndroidJUnit4.class)
public class UnifiedApiTest {

    IUnifiedModel model;

    @Before
    public void setUp() throws Exception {
        model = new UnifiedModel();
    }

    @Test
    public void allApiTest() throws Exception {
        for (int i = 0; i < BANKS.values().length; i++) {


            TestObserver<List<UnifiedBankResponse>> observer = model.getTodayList(BANKS.values()[i]).test().await();
            observer.assertSubscribed();
            observer.assertNoErrors();
            observer.assertComplete();

            assertNotEquals(observer.getEvents().get(0), null);
            assertNotEquals(observer.getEvents().get(0).size(), 0);
            assertNotEquals(observer.getEvents().get(0).get(0), null);

            assertTrue(observer.getEvents().get(0).get(0) instanceof List);
            assertTrue(observer.getEvents().get(0).get(0) instanceof List);

            List<Object> answer = (List<Object>) observer.getEvents().get(0).get(0);
            assertNotEquals(answer, null);
            assertNotEquals(answer.size(), 0);
            assertTrue(answer.get(0) instanceof UnifiedBankResponse);
            assertNotEquals(((UnifiedBankResponse) answer.get(0)).getCode(), null);
            List<UnifiedBankResponse> responces = (List<UnifiedBankResponse>) observer.getEvents().get(0).get(0);

            assertNotEquals(responces.get(0).getCode(), null);
            boolean isUSDFound = false;
            for (UnifiedBankResponse item : responces) {
                isUSDFound = item.getCode().equals("USD");
                if (isUSDFound) {
                    break;
                }
            }
            assertTrue(isUSDFound);
        }
    }
}
