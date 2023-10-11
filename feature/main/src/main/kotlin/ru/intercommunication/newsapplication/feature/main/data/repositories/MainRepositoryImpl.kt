package ru.intercommunication.newsapplication.feature.main.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.intercommunication.newsapplication.core.utils.RepositoryException
import ru.intercommunication.newsapplication.core.utils.RepositoryResult
import ru.intercommunication.newsapplication.core.utils.RepositorySuccess
import ru.intercommunication.newsapplication.feature.main.data.sources.api.ApiSource
import ru.intercommunication.newsapplication.feature.main.data.sources.local.DatabaseSource
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.main.domain.repositories.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val databaseSource: DatabaseSource,
    private val apiSource: ApiSource
) : MainRepository {


    override suspend fun updateStateFavorite(isFavorite: Boolean, id: Int) {
        withContext(Dispatchers.IO) {
            databaseSource.updateStateFavorite(isFavorite, id)
        }
    }

    override suspend fun loadScreen(): RepositoryResult<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching {
                apiSource.getNews()
            }.fold(
                onSuccess = {
                    databaseSource.saveNews(it.toMutableList())
                    RepositorySuccess(Unit)
                },
                onFailure = {
                    Log.d("tag_failed", "$it")
                    RepositoryException(it)
                }
            )
        }
    }

    override fun getNews(): Flow<List<ArticleModel>> = databaseSource.getNews()
}