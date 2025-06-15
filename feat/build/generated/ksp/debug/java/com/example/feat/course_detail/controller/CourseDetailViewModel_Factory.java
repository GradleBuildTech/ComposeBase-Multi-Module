package com.example.feat.course_detail.controller;

import com.example.core.navigation.NavigationService;
import com.example.domain.usecase.course_detail.CourseDetailUseCase;
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
public final class CourseDetailViewModel_Factory implements Factory<CourseDetailViewModel> {
  private final Provider<CourseDetailUseCase> courseDetailUseCaseProvider;

  private final Provider<NavigationService> navigationServiceProvider;

  public CourseDetailViewModel_Factory(Provider<CourseDetailUseCase> courseDetailUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    this.courseDetailUseCaseProvider = courseDetailUseCaseProvider;
    this.navigationServiceProvider = navigationServiceProvider;
  }

  @Override
  public CourseDetailViewModel get() {
    return newInstance(courseDetailUseCaseProvider.get(), navigationServiceProvider.get());
  }

  public static CourseDetailViewModel_Factory create(
      Provider<CourseDetailUseCase> courseDetailUseCaseProvider,
      Provider<NavigationService> navigationServiceProvider) {
    return new CourseDetailViewModel_Factory(courseDetailUseCaseProvider, navigationServiceProvider);
  }

  public static CourseDetailViewModel newInstance(CourseDetailUseCase courseDetailUseCase,
      NavigationService navigationService) {
    return new CourseDetailViewModel(courseDetailUseCase, navigationService);
  }
}
