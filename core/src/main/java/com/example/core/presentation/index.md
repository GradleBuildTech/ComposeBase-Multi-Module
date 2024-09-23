///🚀 MutableSharedFlow is a type of SharedFlow that allows you to emit values to the flow.
/// Example: ```
///val sharedFlow = MutableSharedFlow<Int>()
///sharedFlow.tryEmit(1)
///```
///```
///val sharedFlow = MutableSharedFlow<Int>()
///sharedFlow.emit(1)
///```
///```
///val sharedFlow = MutableSharedFlow<Int>()
///sharedFlow.emitAll(listOf(1, 2, 3))
///```
///```
///* Replay: is the number of values that will be replayed to new collectors.
///val sharedFlow = MutableSharedFlow<Int>(replay = 1)
///sharedFlow.emit(1)
///sharedFlow.emit(2)
///sharedFlow.collect {
///    println(it)
///}
/// -> Output: 2
///```
///```
///val sharedFlow = MutableSharedFlow<Int>(replay = 2)
///sharedFlow.emit(1)
///sharedFlow.emit(2)
///sharedFlow.collect {
///    println(it)
///}
/// -> Output: 1, 2
///```

///🚀 MutableStateFlow is a type of StateFlow that allows you to emit values to the flow.
/// Example: ```
///val stateFlow = MutableStateFlow(1)
///stateFlow.value = 2
///```
///```
///val stateFlow = MutableStateFlow(1)
///stateFlow.tryEmit(2)
///```
///```
///val stateFlow = MutableStateFlow(1)
///stateFlow.update { it + 1 }
///```
///```
