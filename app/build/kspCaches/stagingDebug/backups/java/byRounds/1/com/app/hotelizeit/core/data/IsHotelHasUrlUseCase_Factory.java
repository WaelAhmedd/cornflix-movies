package com.app.hotelizeit.core.data;

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
public final class IsHotelHasUrlUseCase_Factory implements Factory<IsHotelHasUrlUseCase> {
  private final Provider<ConfigRepo> configRepoProvider;

  public IsHotelHasUrlUseCase_Factory(Provider<ConfigRepo> configRepoProvider) {
    this.configRepoProvider = configRepoProvider;
  }

  @Override
  public IsHotelHasUrlUseCase get() {
    return newInstance(configRepoProvider.get());
  }

  public static IsHotelHasUrlUseCase_Factory create(Provider<ConfigRepo> configRepoProvider) {
    return new IsHotelHasUrlUseCase_Factory(configRepoProvider);
  }

  public static IsHotelHasUrlUseCase newInstance(ConfigRepo configRepo) {
    return new IsHotelHasUrlUseCase(configRepo);
  }
}
