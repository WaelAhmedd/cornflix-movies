package com.app.movies.core.network.di;

import com.app.movies.core.domain.SessionService;
import com.app.movies.core.network.interceptor.AccessTokenInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class RetrofitModule_ProvideAccessTokenInterceptorFactory implements Factory<AccessTokenInterceptor> {
  private final Provider<SessionService> sessionServiceProvider;

  public RetrofitModule_ProvideAccessTokenInterceptorFactory(
      Provider<SessionService> sessionServiceProvider) {
    this.sessionServiceProvider = sessionServiceProvider;
  }

  @Override
  public AccessTokenInterceptor get() {
    return provideAccessTokenInterceptor(sessionServiceProvider.get());
  }

  public static RetrofitModule_ProvideAccessTokenInterceptorFactory create(
      Provider<SessionService> sessionServiceProvider) {
    return new RetrofitModule_ProvideAccessTokenInterceptorFactory(sessionServiceProvider);
  }

  public static AccessTokenInterceptor provideAccessTokenInterceptor(
      SessionService sessionService) {
    return Preconditions.checkNotNullFromProvides(RetrofitModule.INSTANCE.provideAccessTokenInterceptor(sessionService));
  }
}
