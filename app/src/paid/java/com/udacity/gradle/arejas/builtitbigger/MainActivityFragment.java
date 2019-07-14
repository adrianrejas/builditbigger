package com.udacity.gradle.arejas.builtitbigger;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import org.udacity.android.arejas.jokedisplayer.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements IServiceConsumerCallback {

    private static final String DEFINED_ENDPOINT = "defined_endpoint";

    private EditText mEndpointEdit;
    private Button mButton;
    private ProgressBar mLoading;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Get references to layout elements
        mEndpointEdit = (EditText) root.findViewById(R.id.endpoint_text_edit);
        mButton = (Button) root.findViewById(R.id.get_joke);
        mLoading = (ProgressBar) root.findViewById(R.id.loading_joke);

        // Set initial visibility of button and loading spinner to button visible
        if (mButton != null) {
            mButton.setVisibility(View.VISIBLE);
        }
        if (mLoading != null) {
            mLoading.setVisibility(View.GONE);
        }

        // Save defined endpoint for future use
        if (mEndpointEdit != null) {
            try {
                String initialEndpoint = PreferenceManager.getDefaultSharedPreferences(getContext())
                        .getString(DEFINED_ENDPOINT, getString(R.string.default_service_endpoint));
                mEndpointEdit.setText(initialEndpoint);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // We add the onclick for the button programatically here in order to be able to make
        // elements of the fragment visible or invisible when clicked
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke() {
        String endpoint = null;
        try {
            // Get defined endpoint
            endpoint = mEndpointEdit.getText().toString();
            // Set visibility of button and loading spinner to loading visible
            if (mButton != null) {
                mButton.setVisibility(View.GONE);
            }
            if (mLoading != null) {
                mLoading.setVisibility(View.VISIBLE);
            }
            // Call async task to get a joke from defined endpoint
            new ServiceConsumerAsyncTask(this).execute(endpoint);
            // Save defined endpoint for future use
            PreferenceManager.getDefaultSharedPreferences(getContext())
                    .edit()
                    .putString(DEFINED_ENDPOINT, endpoint)
                    .commit();
        } catch (Exception e) {
            this.serviceConsumed(getString(R.string.endpoint_problem));
        }
    }

    @Override
    public void serviceConsumed(final String response) {
        try {
            // Set visibility of button and loading spinner to button visible
            if (mButton != null) {
                mButton.setVisibility(View.VISIBLE);
            }
            if (mLoading != null) {
                mLoading.setVisibility(View.GONE);
            }
            // Show the joke, good or null (nulls treated later
            launchJokeActivity(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void launchJokeActivity(String joke) {
        // Launch an actifity for showing the joke
        Intent activity = new Intent(getContext(), JokeActivity.class);
        activity.putExtra(JokeActivity.JOKE_EXTRA, joke);
        startActivity(activity);
    }

}