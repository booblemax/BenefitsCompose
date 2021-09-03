package by.akella.benefits.data

import by.akella.benefits.data.datasource.local.dao.BenefitsDao
import by.akella.benefits.data.datasource.local.toEntity
import by.akella.benefits.data.datasource.local.toModel
import by.akella.benefits.data.datasource.remote.RemoteDataApi
import by.akella.benefits.data.datasource.remote.toModel
import by.akella.benefits.domain.BenefitsRepository
import by.akella.benefits.domain.models.BenefitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber

class BenefitsRepositoryImpl(
    private val remoteDataApi: RemoteDataApi,
    private val benefitsDao: BenefitsDao
) : BenefitsRepository {

    override suspend fun saveBenefits(benefits: List<BenefitModel>) {
        benefitsDao.insert(*benefits.map { it.toEntity() }.toTypedArray())
    }

    override fun getBenefitList(force: Boolean): Flow<List<BenefitModel>> = flow {
        if (force) {
            try {
                val benefitsModels = remoteDataApi.getBenefits().map { it.toModel() }
                emit(benefitsModels)
                saveBenefits(benefitsModels)
            } catch (e: Exception) {
                emit(benefitsDao.getAll().map { it.toModel() })
            }
        } else benefitsDao.getAll().map { it.toModel() }
    }

    override fun getBenefit(id: String): Flow<BenefitModel> = flow {
        val model = benefitsDao.getById(id).toModel()
        emit(model)
    }
}