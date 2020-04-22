package me.kellymckinnon.daggerpractice.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import me.kellymckinnon.daggerpractice.viewmodels.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {

  @Binds
  public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);
}
