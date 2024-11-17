package com.app.hotelizeit.core.data;

import com.app.hotelizeit.core.domain.ConfigDataSource;
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

  private final Provider<ConfigDataSource> configDataSourceProvider;

  public ConfigRepoImpl_Factory(Provider<SessionService> sessionServiceProvider,
      Provider<ConfigDataSource> configDataSourceProvider) {
    this.sessionServiceProvider = sessionServiceProvider;
    this.configDataSourceProvider = configDataSourceProvider;
  }

  @Override
  public ConfigRepoImpl get() {
    return newInstance(sessionServiceProvider.get(), configDataSourceProvider.get());
  }

  public static ConfigRepoImpl_Factory create(Provider<SessionService> sessionServiceProvider,
      Provider<ConfigDataSource> configDataSourceProvider) {
    return new ConfigRepoImpl_Factory(sessionServiceProvider, configDataSourceProvider);
  }

  public static ConfigRepoImpl newInstance(SessionService sessionService,
      ConfigDataSource configDataSource) {
    return new ConfigRepoImpl(sessionService, configDataSource);
  }
}
