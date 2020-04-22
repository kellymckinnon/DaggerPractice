package me.kellymckinnon.daggerpractice.ui.auth;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.network.auth.AuthApi;

public class AuthViewModel extends ViewModel {

  private static final String TAG = "AuthViewModel";

  private final AuthApi authApi;

  @Inject
  public AuthViewModel(AuthApi authApi) {
    this.authApi = authApi;
    Log.d(TAG, "AuthViewModel: viewmodel is working...");
    
    if (this.authApi != null) {
      Log.d(TAG, "AuthViewModel: auth api is not null");
    }
  }
}
