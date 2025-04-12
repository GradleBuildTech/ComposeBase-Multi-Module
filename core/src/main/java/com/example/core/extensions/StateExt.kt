package com.example.core.extensions

import android.widget.SearchView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/// [SearchView] extension function to get the query text change state flow.
/// This function creates a [StateFlow] that emits the current query text whenever it changes.
/// It sets a [SearchView.OnQueryTextListener] to listen for query text changes and updates the state flow accordingly.
/// The initial value of the state flow is an empty string.
/// How to use
/// ```kotlin
/// val queryTextFlow = searchView.getQueryTextChangeStateFlow()
//searchView.getQueryTextChangeStateFlow()
//.debounce(300)
//.filter { query ->
//    if (query.isEmpty()) {
//        textViewResult.text = ""
//        return@filter false
//    } else {
//        return@filter true
//    }
//}
//.distinctUntilChanged()
//.flatMapLatest { query ->
//    dataFromNetwork(query)
//        .catch {
//            emitAll(flowOf(""))
//        }
//}
//.flowOn(Dispatchers.Default)
//.collect { result ->
//    textViewResult.text = result
//}
/// ```

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query
}