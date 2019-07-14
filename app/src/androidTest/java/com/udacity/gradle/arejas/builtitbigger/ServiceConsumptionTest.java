package com.udacity.gradle.arejas.builtitbigger;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ServiceConsumptionTest {
    @Test
    public void testDoInBackground() {
        try {
            final boolean[] result = {false};
            ServiceConsumerAsyncTask endpointAsyncTask =
                    new ServiceConsumerAsyncTask(new IServiceConsumerCallback() {

                        @Override
                        public void serviceConsumed(String response) {
                            if ((response != null) && (!response.isEmpty())) {
                                result[0] = true;
                            }
                        }
                    });
            endpointAsyncTask.execute("http://10.0.2.2:8080/_ah/api/");
            Thread.sleep(5000);
            assertTrue(result[0]);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
