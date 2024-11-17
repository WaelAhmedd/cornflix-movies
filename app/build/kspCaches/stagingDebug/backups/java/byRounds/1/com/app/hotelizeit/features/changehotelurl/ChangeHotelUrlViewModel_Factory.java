package com.app.hotelizeit.features.changehotelurl;

import com.app.hotelizeit.core.data.GetHotelDataUseCase;
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
public final class ChangeHotelUrlViewModel_Factory implements Factory<ChangeHotelUrlViewModel> {
  private final Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider;

  public ChangeHotelUrlViewModel_Factory(
      Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider) {
    this.getHotelDataUseCaseProvider = getHotelDataUseCaseProvider;
  }

  @Override
  public ChangeHotelUrlViewModel get() {
    return newInstance(getHotelDataUseCaseProvider.get());
  }

  public static ChangeHotelUrlViewModel_Factory create(
      Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider) {
    return new ChangeHotelUrlViewModel_Factory(getHotelDataUseCaseProvider);
  }

  public static ChangeHotelUrlViewModel newInstance(GetHotelDataUseCase getHotelDataUseCase) {
    return new ChangeHotelUrlViewModel(getHotelDataUseCase);
  }
}
