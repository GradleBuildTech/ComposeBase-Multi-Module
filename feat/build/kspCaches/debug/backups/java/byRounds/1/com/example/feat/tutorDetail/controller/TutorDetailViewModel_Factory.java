package com.example.feat.tutorDetail.controller;

import com.example.core.navigation.NavigationService;
import com.example.domain.usecase.tutorDetail.TutorDetailUseCase;
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
public final class TutorDetailViewModel_Factory implements Factory<TutorDetailViewModel> {
  private final Provider<TutorDetailUseCase> tutorDetailUseCaseProvider;

  private final Provider<NavigationService> navigationServiceProvider;

  public TutorDetailViewModel_Factory(Provider<TutorDetailUseCase> tutorDetailUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    this.tutorDetailUseCaseProvider = tutorDetailUseCaseProvider;
    this.navigationServiceProvider = navigationServiceProvider;
  }

  @Override
  public TutorDetailViewModel get() {
    return newInstance(tutorDetailUseCaseProvider.get(), navigationServiceProvider.get());
  }

  public static TutorDetailViewModel_Factory create(
      Provider<TutorDetailUseCase> tutorDetailUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    return new TutorDetailViewModel_Factory(tutorDetailUseCaseProvider, navigationServiceProvider);
  }

  public static TutorDetailViewModel newInstance(TutorDetailUseCase tutorDetailUseCase,
      NavigationService navigationService) {
    return new TutorDetailViewModel(tutorDetailUseCase, navigationService);
  }
}
