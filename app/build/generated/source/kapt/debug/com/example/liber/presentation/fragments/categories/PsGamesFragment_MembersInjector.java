// Generated by Dagger (https://dagger.dev).
package com.example.liber.presentation.fragments.categories;

import com.example.liber.presentation.fragments.maincontent.homefragment.categories.psgamesfragment.PsGamesFragment;
import com.google.firebase.firestore.FirebaseFirestore;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PsGamesFragment_MembersInjector implements MembersInjector<PsGamesFragment> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public PsGamesFragment_MembersInjector(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  public static MembersInjector<PsGamesFragment> create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new PsGamesFragment_MembersInjector(firestoreProvider);
  }

  @Override
  public void injectMembers(PsGamesFragment instance) {
    injectFirestore(instance, firestoreProvider.get());
  }

  @InjectedFieldSignature("com.example.liber.presentation.fragments.categories.PsGamesFragment.firestore")
  public static void injectFirestore(PsGamesFragment instance, FirebaseFirestore firestore) {
    instance.firestore = firestore;
  }
}
