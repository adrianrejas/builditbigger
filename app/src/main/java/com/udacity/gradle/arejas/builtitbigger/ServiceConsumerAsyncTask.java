package com.udacity.gradle.arejas.builtitbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;


public class ServiceConsumerAsyncTask extends AsyncTask<String, Void, String> {

    public static JokeApi jokeApiService = null;

    public String jokeApiEndpoint = null;

    private IServiceConsumerCallback serviceConsumerCallback;

    public ServiceConsumerAsyncTask(IServiceConsumerCallback serviceConsumerCallback) {
        this.serviceConsumerCallback = serviceConsumerCallback;
    }

    @Override
    protected String doInBackground(String... serviceEndpoints) {
        if (serviceEndpoints.length <= 0)  {
            return null;
        }
        String serviceEndpoint = serviceEndpoints[0];
        if (serviceEndpoint == null) {
            return null;
        }
        // Only do this the first time and every time the endpoint change
        if ((jokeApiService == null) || (jokeApiEndpoint == null) ||
                (!jokeApiEndpoint.equals(serviceEndpoint))) {
            jokeApiEndpoint = serviceEndpoint;
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(jokeApiEndpoint)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                                throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            jokeApiService = builder.build();
        }
        try {
            return jokeApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        serviceConsumerCallback.serviceConsumed(response);
    }
}
