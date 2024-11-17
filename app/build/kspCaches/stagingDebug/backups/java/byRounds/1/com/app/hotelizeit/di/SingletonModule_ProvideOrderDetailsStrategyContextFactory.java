package com.app.hotelizeit.di;

import com.app.hotelizeit.data.models.OrderDetailsStrategyContext;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SingletonModule_ProvideOrderDetailsStrategyContextFactory implements Factory<OrderDetailsStrategyContext> {
  @Override
  public OrderDetailsStrategyContext get() {
    return provideOrderDetailsStrategyContext();
  }

  public static SingletonModule_ProvideOrderDetailsStrategyContextFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OrderDetailsStrategyContext provideOrderDetailsStrategyContext() {
    return Preconditions.checkNotNullFromProvides(SingletonModule.INSTANCE.provideOrderDetailsStrategyContext());
  }

  private static final class InstanceHolder {
    private static final SingletonModule_ProvideOrderDetailsStrategyContextFactory INSTANCE = new SingletonModule_ProvideOrderDetailsStrategyContextFactory();
  }
}
