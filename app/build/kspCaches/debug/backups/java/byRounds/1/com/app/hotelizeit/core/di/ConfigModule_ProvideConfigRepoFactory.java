package com.app.hotelizeit.core.di;

import com.app.hotelizeit.core.domain.ConfigRepo;
import com.app.hotelizeit.core.domain.SessionService;
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
public final class ConfigModule_ProvideConfigRepoFactory implements Factory<ConfigRepo> {
  private final Provider<SessionService> sessionServiceProvider;

  public ConfigModule_ProvideConfigRepoFactory(Provider<SessionService> sessionServiceProvider) {
    this.sessionServiceProvider = sessionServiceProvider;
  }

  @Override
  public ConfigRepo get() {
    return provideConfigRepo(sessionServiceProvider.get());
  }

  public static ConfigModule_ProvideConfigRepoFactory create(
      Provider<SessionService> sessionServiceProvider) {
    return new ConfigModule_ProvideConfigRepoFactory(sessionServiceProvider);
  }

  public static ConfigRepo provideConfigRepo(SessionService sessionService) {
    return Preconditions.checkNotNullFromProvides(ConfigModule.INSTANCE.provideConfigRepo(sessionService));
  }
}
