package com.app.hotelizeit.features.splash;

import com.app.hotelizeit.core.data.GetHotelDataUseCase;
import com.app.hotelizeit.core.data.GetHotelUrlUseCase;
import com.app.hotelizeit.core.data.IsHotelHasUrlUseCase;
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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider;

  private final Provider<IsHotelHasUrlUseCase> isHotelHasUrlUseCaseProvider;

  private final Provider<GetHotelUrlUseCase> getHotelUrlUseCaseProvider;

  public SplashViewModel_Factory(Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider,
      Provider<IsHotelHasUrlUseCase> isHotelHasUrlUseCaseProvider,
      Provider<GetHotelUrlUseCase> getHotelUrlUseCaseProvider) {
    this.getHotelDataUseCaseProvider = getHotelDataUseCaseProvider;
    this.isHotelHasUrlUseCaseProvider = isHotelHasUrlUseCaseProvider;
    this.getHotelUrlUseCaseProvider = getHotelUrlUseCaseProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(getHotelDataUseCaseProvider.get(), isHotelHasUrlUseCaseProvider.get(), getHotelUrlUseCaseProvider.get());
  }

  public static SplashViewModel_Factory create(
      Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider,
      Provider<IsHotelHasUrlUseCase> isHotelHasUrlUseCaseProvider,
      Provider<GetHotelUrlUseCase> getHotelUrlUseCaseProvider) {
    return new SplashViewModel_Factory(getHotelDataUseCaseProvider, isHotelHasUrlUseCaseProvider, getHotelUrlUseCaseProvider);
  }

  public static SplashViewModel newInstance(GetHotelDataUseCase getHotelDataUseCase,
      IsHotelHasUrlUseCase isHotelHasUrlUseCase, GetHotelUrlUseCase getHotelUrlUseCase) {
    return new SplashViewModel(getHotelDataUseCase, isHotelHasUrlUseCase, getHotelUrlUseCase);
  }
}
