// Generated by Dagger (https://dagger.dev).
package com.example.liber.presentation.viewmodels;

import com.example.liber.common.firebase.FirebaseCommon;
import com.example.liber.presentation.fragments.maincontent.gamedetailfragment.DetailsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
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
public final class DetailsViewModel_Factory implements Factory<DetailsViewModel> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseCommon> firebaseCommonProvider;

  public DetailsViewModel_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider, Provider<FirebaseCommon> firebaseCommonProvider) {
    this.firestoreProvider = firestoreProvider;
    this.authProvider = authProvider;
    this.firebaseCommonProvider = firebaseCommonProvider;
  }

  @Override
  public DetailsViewModel get() {
    return newInstance(firestoreProvider.get(), authProvider.get(), firebaseCommonProvider.get());
  }

  public static DetailsViewModel_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider, Provider<FirebaseCommon> firebaseCommonProvider) {
    return new DetailsViewModel_Factory(firestoreProvider, authProvider, firebaseCommonProvider);
  }

  public static DetailsViewModel newInstance(FirebaseFirestore firestore, FirebaseAuth auth,
      FirebaseCommon firebaseCommon) {
    return new DetailsViewModel(firestore, auth, firebaseCommon);
  }
}