package me.kellymckinnon.daggerpractice.ui.main.profile;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.SessionManager;
import me.kellymckinnon.daggerpractice.models.User;
import me.kellymckinnon.daggerpractice.ui.auth.AuthResource;

public class ProfileViewModel extends ViewModel {

  private static final String TAG = "ProfileViewModel";

  private final SessionManager sessionManager;
  
  @Inject
  public ProfileViewModel(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
    Log.d(TAG, "ProfileViewModel: viewmodel is ready...");
  }

  public LiveData<AuthResource<User>> getAuthenticatedUser() {
    return sessionManager.getAuthUser();
  }
} 
