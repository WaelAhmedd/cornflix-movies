package com.app.hotelizeit.di;

import com.app.hotelizeit.data.models.OrderDetailsStrategyContext;
import com.app.hotelizeit.domain.OrderDataSource;
import com.app.hotelizeit.domain.OrderRepository;
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
public final class OrderModule_ProvideOrderRepositoryFactory implements Factory<OrderRepository> {
  private final Provider<OrderDataSource> orderDataSourceProvider;

  private final Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider;

  private final Provider<RealtimeDataSource> realtimeDataSourceProvider;

  public OrderModule_ProvideOrderRepositoryFactory(
      Provider<OrderDataSource> orderDataSourceProvider,
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider,
      Provider<RealtimeDataSource> realtimeDataSourceProvider) {
    this.orderDataSourceProvider = orderDataSourceProvider;
    this.orderDetailsStrategyContextProvider = orderDetailsStrategyContextProvider;
    this.realtimeDataSourceProvider = realtimeDataSourceProvider;
  }

  @Override
  public OrderRepository get() {
    return provideOrderRepository(orderDataSourceProvider.get(), orderDetailsStrategyContextProvider.get(), realtimeDataSourceProvider.get());
  }

  public static OrderModule_ProvideOrderRepositoryFactory create(
      Provider<OrderDataSource> orderDataSourceProvider,
      Provider<OrderDetailsStrategyContext> orderDetailsStrategyContextProvider,
      Provider<RealtimeDataSource> realtimeDataSourceProvider) {
    return new OrderModule_ProvideOrderRepositoryFactory(orderDataSourceProvider, orderDetailsStrategyContextProvider, realtimeDataSourceProvider);
  }

  public static OrderRepository provideOrderRepository(OrderDataSource orderDataSource,
      OrderDetailsStrategyContext orderDetailsStrategyContext,
      RealtimeDataSource realtimeDataSource) {
    return Preconditions.checkNotNullFromProvides(OrderModule.INSTANCE.provideOrderRepository(orderDataSource, orderDetailsStrategyContext, realtimeDataSource));
  }
}
