package com.app.movies.core.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SessionServiceImpl_Factory implements Factory<SessionServiceImpl> {
  private final Provider<Context> contextProvider;

  public SessionServiceImpl_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SessionServiceImpl get() {
    return newInstance(contextProvider.get());
  }

  public static SessionServiceImpl_Factory create(Provider<Context> contextProvider) {
    return new SessionServiceImpl_Factory(contextProvider);
  }

  public static SessionServiceImpl newInstance(Context context) {
    return new SessionServiceImpl(context);
  }
}
