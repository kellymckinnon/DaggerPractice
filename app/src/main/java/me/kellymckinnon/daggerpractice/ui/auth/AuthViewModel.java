package me.kellymckinnon.daggerpractice.ui.auth;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.models.User;
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

    authApi.getUser(1).toObservable().subscribeOn(Schedulers.io()).subscribe(new Observer<User>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(User user) {
        Log.d(TAG, "onNext: " + user.getEmail());
      }

      @Override
      public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
      }

      @Override
      public void onComplete() {

      }
    });
  }
}
