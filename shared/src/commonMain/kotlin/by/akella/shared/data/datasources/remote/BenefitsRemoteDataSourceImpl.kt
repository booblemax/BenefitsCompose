package by.akella.shared.data.datasources.remote

import by.akella.shared.data.datasources.BenefitsRemoteDataSource
import by.akella.shared.domain.models.BenefitModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer

class BenefitsRemoteDataSourceImpl : BenefitsRemoteDataSource {

    override fun getBenefitList(): Flow<List<BenefitModel>> =
        Firebase.database.reference("benefits")
            .valueEvents
            .map {
                it.value(ListSerializer(BenefitModel.serializer()))
            }
}