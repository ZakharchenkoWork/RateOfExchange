package com.znshadows.exchangerate.model_tests;

/**
 * Created by kostya on 31.10.2017.
 */

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.hast.exchangerate.mvp.models.AlfabankApi;
import com.hast.exchangerate.mvp.models.AlfabankApiImpl;
import com.hast.exchangerate.mvp.models.OtpBankApi;
import com.hast.exchangerate.mvp.models.OtpBankApiImpl;

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
public class OTPBankApiTest {
    OtpBankApi otpbankApi;

    @Before
    public void setUp() throws Exception {
        otpbankApi = new OtpBankApiImpl();
    }

    @Test
    public void testOTPBank() throws Exception {
        TestObserver<String> observer = otpbankApi.getTodayList().test().await();
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
        assertTrue(answer.contains("<td class=\"first_column\">"));
        String[] data = answer.split("<td class=\"first_column\">");
        data[3] = data[3].split("</tbody>")[0];
        assertTrue(data[1].contains("USD"));
        assertTrue(data[2].contains("EUR"));
        assertTrue(data[3].contains("CHF"));



    }
}