package me.kellymckinnon.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.kellymckinnon.daggerpractice.di.ViewModelKey;
import me.kellymckinnon.daggerpractice.ui.auth.AuthViewModel;

@Module
public abstract class AuthViewModelsModule {

  @Binds
  @IntoMap
  @ViewModelKey(AuthViewModel.class)
  public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);

}
