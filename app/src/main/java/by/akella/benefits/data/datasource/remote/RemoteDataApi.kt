package by.akella.benefits.data.datasource.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

interface RemoteDataApi {

    fun getBenefits(): Flow<List<BenefitsResponse>>
}

class RemoteDataApiImpl(
    private val db: FirebaseDatabase
) : RemoteDataApi {

    override fun getBenefits(): Flow<List<BenefitsResponse>> = flow {
        val result = suspendCancellableCoroutine<List<BenefitsResponse>> { continuation ->
            db.reference.child("benefits").get()
                .addOnSuccessListener { result ->
                    if (result.exists()) {
                        val benefits =
                            result.children.mapNotNull { it.getValue(BenefitsResponse::class.java) }
                        continuation.resume(benefits) { continuation.resumeWithException(it) }
                    }
                }
                .addOnFailureListener { fail -> continuation.resumeWithException(fail) }
        }
        emit(result)
    }
}