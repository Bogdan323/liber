// Generated by Dagger (https://dagger.dev).
package com.example.liber.presentation.viewmodels;

import android.content.SharedPreferences;

import com.example.liber.presentation.fragments.login.introductionfragment.IntroductionViewModel;
import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class IntroductionViewModel_Factory implements Factory<IntroductionViewModel> {
  private final Provider<SharedPreferences> sharedPreferencesProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public IntroductionViewModel_Factory(Provider<SharedPreferences> sharedPreferencesProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.sharedPreferencesProvider = sharedPreferencesProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public IntroductionViewModel get() {
    return newInstance(sharedPreferencesProvider.get(), firebaseAuthProvider.get());
  }

  public static IntroductionViewModel_Factory create(
      Provider<SharedPreferences> sharedPreferencesProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    return new IntroductionViewModel_Factory(sharedPreferencesProvider, firebaseAuthProvider);
  }

  public static IntroductionViewModel newInstance(SharedPreferences sharedPreferences,
      FirebaseAuth firebaseAuth) {
    return new IntroductionViewModel(sharedPreferences, firebaseAuth);
  }
}
