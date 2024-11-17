package com.app.hotelizeit.features.reservations;

import com.app.hotelizeit.core.data.GetHotelDataUseCase;
import com.app.hotelizeit.core.data.GetHotelUrlUseCase;
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
public final class HotelReservationsViewModel_Factory implements Factory<HotelReservationsViewModel> {
  private final Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider;

  private final Provider<GetHotelUrlUseCase> getHotelUrlUseCaseProvider;

  public HotelReservationsViewModel_Factory(
      Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider,
      Provider<GetHotelUrlUseCase> getHotelUrlUseCaseProvider) {
    this.getHotelDataUseCaseProvider = getHotelDataUseCaseProvider;
    this.getHotelUrlUseCaseProvider = getHotelUrlUseCaseProvider;
  }

  @Override
  public HotelReservationsViewModel get() {
    return newInstance(getHotelDataUseCaseProvider.get(), getHotelUrlUseCaseProvider.get());
  }

  public static HotelReservationsViewModel_Factory create(
      Provider<GetHotelDataUseCase> getHotelDataUseCaseProvider,
      Provider<GetHotelUrlUseCase> getHotelUrlUseCaseProvider) {
    return new HotelReservationsViewModel_Factory(getHotelDataUseCaseProvider, getHotelUrlUseCaseProvider);
  }

  public static HotelReservationsViewModel newInstance(GetHotelDataUseCase getHotelDataUseCase,
      GetHotelUrlUseCase getHotelUrlUseCase) {
    return new HotelReservationsViewModel(getHotelDataUseCase, getHotelUrlUseCase);
  }
}
