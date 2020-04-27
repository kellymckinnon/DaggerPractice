package me.kellymckinnon.daggerpractice.ui.auth;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.SessionManager;
import me.kellymckinnon.daggerpractice.models.User;
import me.kellymckinnon.daggerpractice.network.auth.AuthApi;

public class AuthViewModel extends ViewModel {

  private static final String TAG = "AuthViewModel";

  private final AuthApi authApi;
  private final SessionManager sessionManager;

  @Inject
  public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
    this.authApi = authApi;
    this.sessionManager = sessionManager;
    Log.d(TAG, "AuthViewModel: viewmodel is working...");

  }

  public void authenticateWithId(String userId) {
    Log.d(TAG, "authenticateWithId: attempting to login");
    sessionManager.authenticateWithId(queryUserId(userId));
  }

  private LiveData<AuthResource<User>> queryUserId(String userId) {
    return LiveDataReactiveStreams
        .fromPublisher(authApi.getUser(userId).onErrorReturn(new Function<Throwable, User>() {
          @Override
          public User apply(Throwable throwable) throws Exception {
            User errorUser = new User();
            errorUser.setId(-1);
            return errorUser;
          }
        }).map(new Function<User, AuthResource<User>>() {
          @Override
          public AuthResource<User> apply(User user) throws Exception {
            if (user.getId() == -1) {
              return AuthResource.error("Could not authenticate", null);
            }
            return AuthResource.authenticated(user);
          }
        }).subscribeOn(Schedulers.io()));
  }

  public LiveData<AuthResource<User>> observeAuthState() {
    return sessionManager.getAuthUser();
  }
}
