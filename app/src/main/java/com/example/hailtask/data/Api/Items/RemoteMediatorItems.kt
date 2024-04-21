package com.example.hailtask.data.Api.Items

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.data.model.itemss.RemoteKey
import com.example.hailtask.room.ItemDataBase
import com.example.hailtask.util.Constants


@OptIn(ExperimentalPagingApi::class)
class RemoteMediatorItems(
    private val initialPage:Int = 1,
    private val database: ItemDataBase,
    private val networkService:  ItemsInterface
) : RemoteMediator<Int, Item>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Item>): MediatorResult {
        return try {val page= when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1)?:initialPage
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

            val apiResponse = networkService.getItems(Constants.auth,Constants.apiKey, page,
                state.config.pageSize)
            if (apiResponse.isSuccessful) {
                val endOfPagination = apiResponse.body()?.data?.items?.isEmpty() ?: true
                if (endOfPagination) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                apiResponse.body()?.let {
                    if (loadType == LoadType.REFRESH) {
                        database.RemoteKeyDao().clearRemoteKeys()
                        database.itemDao().clearAll()
                    }

                    val prevKey = if (page == initialPage) null else page.minus(1)
                    val nextKey = if (endOfPagination) null else page.plus(1)
                    val keys = apiResponse.body()?.data?.items?.map { item ->
                        RemoteKey(
                            item.id,
                            prevKey ,
                            nextKey
                        )

                    }
                    if (keys != null) {
                        database.RemoteKeyDao().insertAll(keys)
                    }
                    database.itemDao().insert(it.data.items)
                }
                MediatorResult.Success(endOfPagination)

        } else {
            MediatorResult.Success(endOfPaginationReached = true)
        }


    } catch (e: Exception) {
        MediatorResult.Error(e)
    }

    }
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Item>): RemoteKey? {

        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                    database.RemoteKeyDao().remoteKeysRepoId(repo.id)
            }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Item>): RemoteKey? {

        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                database.RemoteKeyDao().remoteKeysRepoId(repo.id)
            }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Item>
    ): RemoteKey? {

        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                database.RemoteKeyDao().remoteKeysRepoId(repoId)
            }
        }
    }
}