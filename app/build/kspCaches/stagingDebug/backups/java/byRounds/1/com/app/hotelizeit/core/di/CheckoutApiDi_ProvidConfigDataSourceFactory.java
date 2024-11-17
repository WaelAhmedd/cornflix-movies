package com.app.hotelizeit.core.di;

import com.app.hotelizeit.core.domain.ConfigDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class CheckoutApiDi_ProvidConfigDataSourceFactory implements Factory<ConfigDataSource> {
  private final Provider<Retrofit> retrofitProvider;

  public CheckoutApiDi_ProvidConfigDataSourceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ConfigDataSource get() {
    return providConfigDataSource(retrofitProvider.get());
  }

  public static CheckoutApiDi_ProvidConfigDataSourceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new CheckoutApiDi_ProvidConfigDataSourceFactory(retrofitProvider);
  }

  public static ConfigDataSource providConfigDataSource(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(CheckoutApiDi.INSTANCE.providConfigDataSource(retrofit));
  }
}
