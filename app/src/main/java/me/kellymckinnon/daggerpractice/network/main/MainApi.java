package me.kellymckinnon.daggerpractice.network.main;

import io.reactivex.Flowable;
import java.util.List;
import me.kellymckinnon.daggerpractice.models.Post;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

  // posts?userId=1
  @GET("posts")
  Flowable<List<Post>> getPostsFromUser(@Query("userId") int id);

}
