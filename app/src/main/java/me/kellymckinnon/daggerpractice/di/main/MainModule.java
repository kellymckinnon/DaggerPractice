package me.kellymckinnon.daggerpractice.di.main;

import dagger.Module;
import dagger.Provides;
import me.kellymckinnon.daggerpractice.network.main.MainApi;
import me.kellymckinnon.daggerpractice.ui.main.posts.PostsRecyclerAdapter;
import retrofit2.Retrofit;

@Module
public class MainModule {

  @Provides
  static PostsRecyclerAdapter provideAdapter() {
    return new PostsRecyclerAdapter();
  }

  @Provides
  static MainApi provideMainApi(Retrofit retrofit) {
    return retrofit.create(MainApi.class);
  }

}
