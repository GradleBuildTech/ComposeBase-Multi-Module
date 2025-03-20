package com.example.core.network

import android.os.Looper
import android.os.Handler
import android.view.Choreographer
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.transformLatest

/**
 * Sharing is started (i.e., the upstream flow is collected) when the first subscriber appears
 * and is stopped when the last subscriber disappears.
 * However, the upstream flow is not cancelled when the last subscriber disappears,
 * but is retained until the next subscriber appears.
 * This is useful when the upstream flow is expensive to produce and there is a chance that it will be collected again soon.
 */

//Singleton object to share the
data object WhileSubscribedOrRetained : SharingStarted {
    ///Handler to post the frame callback in UI thread
    private val handler = Handler(Looper.getMainLooper())


    ///subscriptionCount: StateFlow<Int> - The number of subscribers to the shared flow.
    ///Returns Flow<SharingCommand> - A flow of commands that control the sharing of the upstream flow.
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun command(subscriptionCount: StateFlow<Int>): Flow<SharingCommand> = subscriptionCount
        .transformLatest { count ->
            if(count > 0) { ///If the count is greater than 0, emit the start command
                emit(SharingCommand.START)
            } else {
                val posted = CompletableDeferred<Unit>()
                Choreographer.getInstance().postFrameCallback { ///Wait for the next frame to emit the stop command
                    handler.postAtFrontOfQueue{
                        handler.post {
                            posted.complete(Unit)
                        }
                    }
                }
                posted.await()
                emit(SharingCommand.STOP)
            }

        }.dropWhile { it != SharingCommand.START }
        .distinctUntilChanged()

    override fun toString(): String = "WhileSubscribedOrRetained"

}