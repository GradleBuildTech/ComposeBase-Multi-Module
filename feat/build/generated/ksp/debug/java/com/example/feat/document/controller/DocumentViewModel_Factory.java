package com.example.feat.document.controller;

import com.example.core.navigation.NavigationService;
import com.example.domain.usecase.document.DocumentUseCase;
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
public final class DocumentViewModel_Factory implements Factory<DocumentViewModel> {
  private final Provider<DocumentUseCase> documentUseCaseProvider;

  private final Provider<NavigationService> navigationServiceProvider;

  public DocumentViewModel_Factory(Provider<DocumentUseCase> documentUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    this.documentUseCaseProvider = documentUseCaseProvider;
    this.navigationServiceProvider = navigationServiceProvider;
  }

  @Override
  public DocumentViewModel get() {
    return newInstance(documentUseCaseProvider.get(), navigationServiceProvider.get());
  }

  public static DocumentViewModel_Factory create(Provider<DocumentUseCase> documentUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    return new DocumentViewModel_Factory(documentUseCaseProvider, navigationServiceProvider);
  }

  public static DocumentViewModel newInstance(DocumentUseCase documentUseCase,
      NavigationService navigationService) {
    return new DocumentViewModel(documentUseCase, navigationService);
  }
}
