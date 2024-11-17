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
public final class GetHotelUrlUseCase_Factory implements Factory<GetHotelUrlUseCase> {
  private final Provider<ConfigRepo> configRepoProvider;

  public GetHotelUrlUseCase_Factory(Provider<ConfigRepo> configRepoProvider) {
    this.configRepoProvider = configRepoProvider;
  }

  @Override
  public GetHotelUrlUseCase get() {
    return newInstance(configRepoProvider.get());
  }

  public static GetHotelUrlUseCase_Factory create(Provider<ConfigRepo> configRepoProvider) {
    return new GetHotelUrlUseCase_Factory(configRepoProvider);
  }

  public static GetHotelUrlUseCase newInstance(ConfigRepo configRepo) {
    return new GetHotelUrlUseCase(configRepo);
  }
}
