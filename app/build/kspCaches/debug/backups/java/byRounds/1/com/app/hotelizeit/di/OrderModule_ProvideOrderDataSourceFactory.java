package com.app.hotelizeit.di;

import com.app.hotelizeit.data.models.OrderDetailsStrategyContext;
import com.app.hotelizeit.domain.OrderDataSource;
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
public final class OrderModule_ProvideOrderDataSourceFactory implements Factory<OrderDataSource> {
  private final Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider;

  public OrderModule_ProvideOrderDataSourceFactory(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    this.orderDetailsStrategyContextProvider = orderDetailsStrategyContextProvider;
  }

  @Override
  public OrderDataSource get() {
    return provideOrderDataSource(orderDetailsStrategyContextProvider.get());
  }

  public static OrderModule_ProvideOrderDataSourceFactory create(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    return new OrderModule_ProvideOrderDataSourceFactory(orderDetailsStrategyContextProvider);
  }

  public static OrderDataSource provideOrderDataSource(
      OrderDetailsStrategyContext orderDetailsStrategyContext) {
    return Preconditions.checkNotNullFromProvides(OrderModule.INSTANCE.provideOrderDataSource(orderDetailsStrategyContext));
  }
}
