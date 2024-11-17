package com.app.hotelizeit.features.changepassword;

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
public final class ChangePasswordViewModel_Factory implements Factory<ChangePasswordViewModel> {
  @Override
  public ChangePasswordViewModel get() {
    return newInstance();
  }

  public static ChangePasswordViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ChangePasswordViewModel newInstance() {
    return new ChangePasswordViewModel();
  }

  private static final class InstanceHolder {
    private static final ChangePasswordViewModel_Factory INSTANCE = new ChangePasswordViewModel_Factory();
  }
}
