package me.kellymckinnon.daggerpractice.ui.auth;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.models.User;
import me.kellymckinnon.daggerpractice.network.auth.AuthApi;

public class AuthViewModel extends ViewModel {

  private static final String TAG = "AuthViewModel";

  private final AuthApi authApi;

  private MediatorLiveData<User> authUser = new MediatorLiveData<>();

  @Inject
  public AuthViewModel(AuthApi authApi) {
    this.authApi = authApi;
    Log.d(TAG, "AuthViewModel: viewmodel is working...");

  }

  public void authenticateWithId(String userId) {
    final LiveData<User> source = LiveDataReactiveStreams
        .fromPublisher(authApi.getUser(userId).subscribeOn(Schedulers.io()));

    authUser.addSource(source, new Observer<User>() {
      @Override
      public void onChanged(User user) {
        authUser.setValue(user);
        authUser.removeSource(source);
      }
    });
  }

  public LiveData<User> observeUser() {
    return authUser;
  }
}
