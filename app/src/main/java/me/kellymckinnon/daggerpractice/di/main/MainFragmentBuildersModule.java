package me.kellymckinnon.daggerpractice.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.kellymckinnon.daggerpractice.ui.main.posts.PostsFragment;
import me.kellymckinnon.daggerpractice.ui.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentBuildersModule {

  @ContributesAndroidInjector
  abstract ProfileFragment contributeProfileFragment();

  @ContributesAndroidInjector
  abstract PostsFragment contributePostsFragment();
}
