package com.app.hotelizeit.core.network.di;

import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ServicesModule_Companion_ProvideMoshiFactory implements Factory<Moshi> {
  @Override
  public Moshi get() {
    return provideMoshi();
  }

  public static ServicesModule_Companion_ProvideMoshiFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Moshi provideMoshi() {
    return Preconditions.checkNotNullFromProvides(ServicesModule.Companion.provideMoshi());
  }

  private static final class InstanceHolder {
    private static final ServicesModule_Companion_ProvideMoshiFactory INSTANCE = new ServicesModule_Companion_ProvideMoshiFactory();
  }
}
