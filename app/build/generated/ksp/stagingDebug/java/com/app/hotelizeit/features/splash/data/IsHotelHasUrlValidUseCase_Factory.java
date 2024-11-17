package com.app.hotelizeit.features.splash.data;

import com.app.hotelizeit.core.domain.ConfigRepo;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class IsHotelHasUrlValidUseCase_Factory implements Factory<IsHotelHasUrlValidUseCase> {
  private final Provider<ConfigRepo> configRepoProvider;

  public IsHotelHasUrlValidUseCase_Factory(Provider<ConfigRepo> configRepoProvider) {
    this.configRepoProvider = configRepoProvider;
  }

  @Override
  public IsHotelHasUrlValidUseCase get() {
    return newInstance(configRepoProvider.get());
  }

  public static IsHotelHasUrlValidUseCase_Factory create(Provider<ConfigRepo> configRepoProvider) {
    return new IsHotelHasUrlValidUseCase_Factory(configRepoProvider);
  }

  public static IsHotelHasUrlValidUseCase newInstance(ConfigRepo configRepo) {
    return new IsHotelHasUrlValidUseCase(configRepo);
  }
}
