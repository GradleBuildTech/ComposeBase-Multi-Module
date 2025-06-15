package com.example.feat.search.controller;

import com.example.core.navigation.NavigationService;
import com.example.domain.usecase.search.SearchUseCase;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SearchUseCase> searchUseCaseProvider;

  private final Provider<NavigationService> navigationServiceProvider;

  public SearchViewModel_Factory(Provider<SearchUseCase> searchUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    this.searchUseCaseProvider = searchUseCaseProvider;
    this.navigationServiceProvider = navigationServiceProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(searchUseCaseProvider.get(), navigationServiceProvider.get());
  }

  public static SearchViewModel_Factory create(Provider<SearchUseCase> searchUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    return new SearchViewModel_Factory(searchUseCaseProvider, navigationServiceProvider);
  }

  public static SearchViewModel newInstance(SearchUseCase searchUseCase,
      NavigationService navigationService) {
    return new SearchViewModel(searchUseCase, navigationService);
  }
}
