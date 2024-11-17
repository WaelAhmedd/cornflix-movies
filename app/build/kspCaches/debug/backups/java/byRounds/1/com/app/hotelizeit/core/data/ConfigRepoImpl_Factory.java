package com.app.hotelizeit.core.data;

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
public final class ConfigRepoImpl_Factory implements Factory<ConfigRepoImpl> {
  private final Provider<SessionService> sessionServiceProvider;

  public ConfigRepoImpl_Factory(Provider<SessionService> sessionServiceProvider) {
    this.sessionServiceProvider = sessionServiceProvider;
  }

  @Override
  public ConfigRepoImpl get() {
    return newInstance(sessionServiceProvider.get());
  }

  public static ConfigRepoImpl_Factory create(Provider<SessionService> sessionServiceProvider) {
    return new ConfigRepoImpl_Factory(sessionServiceProvider);
  }

  public static ConfigRepoImpl newInstance(SessionService sessionService) {
    return new ConfigRepoImpl(sessionService);
  }
}
