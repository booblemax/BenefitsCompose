package by.akella.benefits.data.datasource.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

interface RemoteDataApi {

    suspend fun getBenefits(): List<BenefitsResponse>
}

class RemoteDataApiImpl(
    private val db: FirebaseDatabase
) : RemoteDataApi {

    override suspend fun getBenefits(): List<BenefitsResponse> =
        suspendCancellableCoroutine { continuation ->
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
}