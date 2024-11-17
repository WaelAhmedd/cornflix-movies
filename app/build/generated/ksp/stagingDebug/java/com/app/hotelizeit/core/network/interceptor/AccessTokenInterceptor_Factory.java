package com.app.hotelizeit.core.network.interceptor;

import com.app.hotelizeit.core.domain.SessionService;
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
public final class AccessTokenInterceptor_Factory implements Factory<AccessTokenInterceptor> {
  private final Provider<SessionService> sessionServiceProvider;

  public AccessTokenInterceptor_Factory(Provider<SessionService> sessionServiceProvider) {
    this.sessionServiceProvider = sessionServiceProvider;
  }

  @Override
  public AccessTokenInterceptor get() {
    return newInstance(sessionServiceProvider.get());
  }

  public static AccessTokenInterceptor_Factory create(
      Provider<SessionService> sessionServiceProvider) {
    return new AccessTokenInterceptor_Factory(sessionServiceProvider);
  }

  public static AccessTokenInterceptor newInstance(SessionService sessionService) {
    return new AccessTokenInterceptor(sessionService);
  }
}
