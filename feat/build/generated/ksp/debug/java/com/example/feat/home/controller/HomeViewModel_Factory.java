package com.example.feat.home.controller;

import com.example.domain.usecase.home.HomeUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<HomeUseCase> homeUseCaseProvider;

  public HomeViewModel_Factory(Provider<HomeUseCase> homeUseCaseProvider) {
    this.homeUseCaseProvider = homeUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(homeUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<HomeUseCase> homeUseCaseProvider) {
    return new HomeViewModel_Factory(homeUseCaseProvider);
  }

  public static HomeViewModel newInstance(HomeUseCase homeUseCase) {
    return new HomeViewModel(homeUseCase);
  }
}
