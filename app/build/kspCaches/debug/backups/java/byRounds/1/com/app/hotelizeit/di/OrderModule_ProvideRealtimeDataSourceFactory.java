package com.app.hotelizeit.di;

import com.app.hotelizeit.data.models.OrderDetailsStrategyContext;
import com.app.hotelizeit.domain.RealtimeDataSource;
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
public final class OrderModule_ProvideRealtimeDataSourceFactory implements Factory<RealtimeDataSource> {
  private final Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider;

  public OrderModule_ProvideRealtimeDataSourceFactory(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    this.orderDetailsStrategyContextProvider = orderDetailsStrategyContextProvider;
  }

  @Override
  public RealtimeDataSource get() {
    return provideRealtimeDataSource(orderDetailsStrategyContextProvider.get());
  }

  public static OrderModule_ProvideRealtimeDataSourceFactory create(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    return new OrderModule_ProvideRealtimeDataSourceFactory(orderDetailsStrategyContextProvider);
  }

  public static RealtimeDataSource provideRealtimeDataSource(
      OrderDetailsStrategyContext orderDetailsStrategyContext) {
    return Preconditions.checkNotNullFromProvides(OrderModule.INSTANCE.provideRealtimeDataSource(orderDetailsStrategyContext));
  }
}
