package com.app.hotelizeit.data;

import com.app.hotelizeit.data.models.OrderDetailsStrategyContext;
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
public final class OrderDataSourceImp_Factory implements Factory<OrderDataSourceImp> {
  private final Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider;

  public OrderDataSourceImp_Factory(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    this.orderDetailsStrategyContextProvider = orderDetailsStrategyContextProvider;
  }

  @Override
  public OrderDataSourceImp get() {
    return newInstance(orderDetailsStrategyContextProvider.get());
  }

  public static OrderDataSourceImp_Factory create(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    return new OrderDataSourceImp_Factory(orderDetailsStrategyContextProvider);
  }

  public static OrderDataSourceImp newInstance(
      OrderDetailsStrategyContext orderDetailsStrategyContext) {
    return new OrderDataSourceImp(orderDetailsStrategyContext);
  }
}
