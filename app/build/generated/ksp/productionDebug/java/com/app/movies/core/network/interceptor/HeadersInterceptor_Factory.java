package com.app.movies.core.network.interceptor;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class HeadersInterceptor_Factory implements Factory<HeadersInterceptor> {
  @Override
  public HeadersInterceptor get() {
    return newInstance();
  }

  public static HeadersInterceptor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HeadersInterceptor newInstance() {
    return new HeadersInterceptor();
  }

  private static final class InstanceHolder {
    private static final HeadersInterceptor_Factory INSTANCE = new HeadersInterceptor_Factory();
  }
}
