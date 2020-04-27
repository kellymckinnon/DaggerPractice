package me.kellymckinnon.daggerpractice.network.auth;

import io.reactivex.Flowable;
import me.kellymckinnon.daggerpractice.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

  @GET("users/{id}")
  Flowable<User> getUser(@Path("id") String id);
}
