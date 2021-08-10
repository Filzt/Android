package com.codinginflow.imagesearchapp.data

import android.app.DownloadManager
import androidx.paging.PagingSource
import com.codinginflow.imagesearchapp.BuildConfig
import com.codinginflow.imagesearchapp.api.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1
class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
): PagingSource<Int, UnsplashPhoto>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
        val response = unsplashApi.searchPhotos(BuildConfig.UNSPLASH_ACCESS_KEY,query,position, params.loadSize)
        val photo = response.results
            LoadResult.Page(
                data = photo,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photo.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}