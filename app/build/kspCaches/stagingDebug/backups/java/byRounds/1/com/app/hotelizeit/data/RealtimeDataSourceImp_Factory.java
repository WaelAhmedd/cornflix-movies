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
public final class RealtimeDataSourceImp_Factory implements Factory<RealtimeDataSourceImp> {
  private final Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider;

  public RealtimeDataSourceImp_Factory(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    this.orderDetailsStrategyContextProvider = orderDetailsStrategyContextProvider;
  }

  @Override
  public RealtimeDataSourceImp get() {
    return newInstance(orderDetailsStrategyContextProvider.get());
  }

  public static RealtimeDataSourceImp_Factory create(
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider) {
    return new RealtimeDataSourceImp_Factory(orderDetailsStrategyContextProvider);
  }

  public static RealtimeDataSourceImp newInstance(
      OrderDetailsStrategyContext orderDetailsStrategyContext) {
    return new RealtimeDataSourceImp(orderDetailsStrategyContext);
  }
}
