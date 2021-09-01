package by.akella.benefits.data

import by.akella.benefits.data.datasource.local.dao.BenefitsDao
import by.akella.benefits.data.datasource.local.toEntity
import by.akella.benefits.data.datasource.local.toModel
import by.akella.benefits.data.datasource.remote.RemoteDataApi
import by.akella.benefits.data.datasource.remote.toModel
import by.akella.benefits.domain.BenefitsRepository
import by.akella.benefits.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map

class BenefitsRepositoryImpl(
    private val remoteDataApi: RemoteDataApi,
    private val benefitsDao: BenefitsDao
) : BenefitsRepository {

    override fun saveBenefits(benefits: List<BenefitModel>) {
        benefitsDao.insert(*benefits.map { it.toEntity() }.toTypedArray())
    }

    override fun getBenefitList(force: Boolean): Flow<List<BenefitModel>> =
        if (force)
            remoteDataApi.getBenefits()
                .map { models ->
                    models.map { it.toModel() }.also { saveBenefits(it) }
                }
                .catch {
                    emitAll(
                        benefitsDao.getAll().map { models -> models.map { it.toModel() } }
                    )
                }
        else
            benefitsDao.getAll()
                .map { models -> models.map { it.toModel() } }

    override fun getBenefit(id: String): Flow<BenefitModel> =
            benefitsDao.getById(id).map { it.toModel() }
}