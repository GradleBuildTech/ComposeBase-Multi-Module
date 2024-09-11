✨✨✨✨✨✨✨
///Explain all annotations in the core module
@Singleton // This annotation is used to mark a class as a singleton. This means that only one instance of the class will be created and shared across the application.
@BindsInstance // This annotation is used to bind an instance of an object to the component. This is useful when you want to pass an object to the component at the time of its creation.
@Component // This annotation is used to define a Dagger component. A component is a bridge between a module and the object that will use the dependencies provided by the module.
public interface CoreComponent {
    void inject(MainActivity mainActivity);
}
```

```markdown

@Binds // This annotation is used to bind an implementation to an interface. This is useful when you want to provide an implementation for an interface.
@Provides // This annotation is used to provide a dependency. This is useful when you want to provide a dependency that is not an interface.
