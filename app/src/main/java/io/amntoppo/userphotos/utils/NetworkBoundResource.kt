package io.amntoppo.userphotos.utils

import kotlinx.coroutines.flow.*

inline fun <Result, Request>NetworkBoundResource(
    crossinline query: () -> Flow<Result>,
    crossinline fetch: suspend () -> Request,
    crossinline saveFetchRequest: suspend(Request) -> Unit,
    crossinline shouldFetch: (Result) -> Boolean = {true}
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchRequest(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, null) }
        }
    }
    else {
        query().map { Resource.Success(it)}
    }
    emitAll(flow)
}