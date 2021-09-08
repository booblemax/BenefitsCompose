package by.akella.benefits.data.datasource.remote.benefits

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resumeWithException

interface BenefitsRemoteApi {

    suspend fun getBenefits(): List<BenefitsResponse>
}

class BenefitsRemoteApiImpl(
    private val db: FirebaseDatabase
) : BenefitsRemoteApi {

    override suspend fun getBenefits(): List<BenefitsResponse> =
        suspendCancellableCoroutine { continuation ->
            db.reference.child("benefits")
                .get()
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