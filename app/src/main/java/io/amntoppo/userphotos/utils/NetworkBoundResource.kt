package io.amntoppo.userphotos.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType>NetworkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchRequest: suspend(RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = {true}
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
}.flowOn(Dispatchers.IO)
    .catch {
        emit(Resource.Error(it, null))
    }