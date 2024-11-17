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
public final class GetHotelDataUseCase_Factory implements Factory<GetHotelDataUseCase> {
  private final Provider<ConfigRepo> configRepoProvider;

  public GetHotelDataUseCase_Factory(Provider<ConfigRepo> configRepoProvider) {
    this.configRepoProvider = configRepoProvider;
  }

  @Override
  public GetHotelDataUseCase get() {
    return newInstance(configRepoProvider.get());
  }

  public static GetHotelDataUseCase_Factory create(Provider<ConfigRepo> configRepoProvider) {
    return new GetHotelDataUseCase_Factory(configRepoProvider);
  }

  public static GetHotelDataUseCase newInstance(ConfigRepo configRepo) {
    return new GetHotelDataUseCase(configRepo);
  }
}
