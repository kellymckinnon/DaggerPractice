package me.kellymckinnon.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.R;
import me.kellymckinnon.daggerpractice.models.User;
import me.kellymckinnon.daggerpractice.ui.auth.AuthResource;
import me.kellymckinnon.daggerpractice.viewmodels.ViewModelProviderFactory;

public class ProfileFragment extends DaggerFragment {

  private static final String TAG = "ProfileFragment";

  private ProfileViewModel viewModel;
  private TextView email, username, website;

  @Inject
  ViewModelProviderFactory providerFactory;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_profile, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    Log.d(TAG, "onViewCreated: profile fragment was created");
    viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);
    email = view.findViewById(R.id.email);
    username = view.findViewById(R.id.username);
    website = view.findViewById(R.id.website);
    subscribeObservers();
  }

  private void subscribeObservers() {
    LifecycleOwner lifeCycleOwner = getViewLifecycleOwner();

    // Necessary for fragments
    viewModel.getAuthenticatedUser().removeObservers(lifeCycleOwner);

    viewModel.getAuthenticatedUser().observe(lifeCycleOwner,
        new Observer<AuthResource<User>>() {
          @Override
          public void onChanged(AuthResource<User> userAuthResource) {
            if (userAuthResource == null) {
              return;
            }

            switch (userAuthResource.status) {
              case AUTHENTICATED:
                setUserDetails(userAuthResource.data);
                break;
              case ERROR:
                setErrorDetails(userAuthResource.message);
                break;
            }
          }
        });
  }

  private void setErrorDetails(String message) {
    email.setText(message);
    username.setText("error");
    website.setText("error");
  }

  private void setUserDetails(User data) {
    email.setText(data.getEmail());
    username.setText(data.getUsername());
    website.setText(data.getWebsite());
  }
}
