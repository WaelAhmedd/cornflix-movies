package com.app.hotelizeit.core.network.di;

import android.content.Context;
import com.app.hotelizeit.core.network.interceptor.AccessTokenInterceptor;
import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class RetrofitModule_ProvidesRetrofitFactory implements Factory<Retrofit> {
  private final Provider<Context> contextProvider;

  private final Provider<AccessTokenInterceptor> accessTokenInterceptorProvider;

  private final Provider<Moshi> moshiProvider;

  public RetrofitModule_ProvidesRetrofitFactory(Provider<Context> contextProvider,
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<Moshi> moshiProvider) {
    this.contextProvider = contextProvider;
    this.accessTokenInterceptorProvider = accessTokenInterceptorProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public Retrofit get() {
    return providesRetrofit(contextProvider.get(), accessTokenInterceptorProvider.get(), moshiProvider.get());
  }

  public static RetrofitModule_ProvidesRetrofitFactory create(Provider<Context> contextProvider,
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<Moshi> moshiProvider) {
    return new RetrofitModule_ProvidesRetrofitFactory(contextProvider, accessTokenInterceptorProvider, moshiProvider);
  }

  public static Retrofit providesRetrofit(Context context,
      AccessTokenInterceptor accessTokenInterceptor, Moshi moshi) {
    return Preconditions.checkNotNullFromProvides(RetrofitModule.INSTANCE.providesRetrofit(context, accessTokenInterceptor, moshi));
  }
}
