package me.kellymckinnon.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.kellymckinnon.daggerpractice.di.ViewModelKey;
import me.kellymckinnon.daggerpractice.ui.main.posts.PostsViewModel;
import me.kellymckinnon.daggerpractice.ui.main.profile.ProfileViewModel;

@Module
public abstract class MainViewModelsModule {

  @Binds
  @IntoMap
  @ViewModelKey(ProfileViewModel.class)
  public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

  @Binds
  @IntoMap
  @ViewModelKey(PostsViewModel.class)
  public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}
