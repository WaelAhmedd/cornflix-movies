package com.app.hotelizeit.data;

import com.app.hotelizeit.data.models.OrderDetailsStrategyContext;
import com.app.hotelizeit.domain.OrderDataSource;
import com.app.hotelizeit.domain.RealtimeDataSource;
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
public final class OrderRepositoryImp_Factory implements Factory<OrderRepositoryImp> {
  private final Provider<OrderDataSource> orderDataSourceProvider;

  private final Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider;

  private final Provider<RealtimeDataSource> realTimeDataSourceProvider;

  public OrderRepositoryImp_Factory(Provider<OrderDataSource> orderDataSourceProvider,
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider,
      Provider<RealtimeDataSource> realTimeDataSourceProvider) {
    this.orderDataSourceProvider = orderDataSourceProvider;
    this.orderDetailsStrategyContextProvider = orderDetailsStrategyContextProvider;
    this.realTimeDataSourceProvider = realTimeDataSourceProvider;
  }

  @Override
  public OrderRepositoryImp get() {
    return newInstance(orderDataSourceProvider.get(), orderDetailsStrategyContextProvider.get(), realTimeDataSourceProvider.get());
  }

  public static OrderRepositoryImp_Factory create(Provider<OrderDataSource> orderDataSourceProvider,
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider,
      Provider<RealtimeDataSource> realTimeDataSourceProvider) {
    return new OrderRepositoryImp_Factory(orderDataSourceProvider, orderDetailsStrategyContextProvider, realTimeDataSourceProvider);
  }

  public static OrderRepositoryImp newInstance(OrderDataSource orderDataSource,
      OrderDetailsStrategyContext orderDetailsStrategyContext,
      RealtimeDataSource realTimeDataSource) {
    return new OrderRepositoryImp(orderDataSource, orderDetailsStrategyContext, realTimeDataSource);
  }
}
