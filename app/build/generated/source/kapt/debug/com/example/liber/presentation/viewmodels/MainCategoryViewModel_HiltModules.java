package com.example.liber.presentation.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.liber.presentation.fragments.maincontent.homefragment.categories.maincategoryfragment.MainCategoryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap;
import dagger.hilt.codegen.OriginatingElement;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import java.lang.String;

@OriginatingElement(
    topLevelClass = MainCategoryViewModel.class
)
public final class MainCategoryViewModel_HiltModules {
  private MainCategoryViewModel_HiltModules() {
  }

  @Module
  @InstallIn(ViewModelComponent.class)
  public abstract static class BindsModule {
    private BindsModule() {
    }

    @Binds
    @IntoMap
    @StringKey("com.example.liber.presentation.fragments.maincontent.homefragment.categories.maincategoryfragment.MainCategoryViewModel")
    @HiltViewModelMap
    public abstract ViewModel binds(MainCategoryViewModel vm);
  }

  @Module
  @InstallIn(ActivityRetainedComponent.class)
  public static final class KeyModule {
    private KeyModule() {
    }

    @Provides
    @IntoSet
    @HiltViewModelMap.KeySet
    public static String provide() {
      return "com.example.liber.presentation.fragments.maincontent.homefragment.categories.maincategoryfragment.MainCategoryViewModel";
    }
  }
}
