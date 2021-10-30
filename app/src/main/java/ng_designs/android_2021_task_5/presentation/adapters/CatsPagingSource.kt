package ng_designs.android_2021_task_5.presentation.adapters

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ng_designs.android_2021_task_5.data.cats.remote.api.CatsApiImpl
import ng_designs.android_2021_task_5.domain.cat.models.Cats
import ng_designs.android_2021_task_5.domain.common.utils.toCats
import retrofit2.HttpException
import java.io.IOException

class CatsPagingSource(private val api: CatsApiImpl ): PagingSource<Int, Cats>() {

    override fun getRefreshKey(state: PagingState<Int, Cats>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cats> {
        return try {
            Log.i("PagingSource", params.key.toString())
            val nextPage: Int = params.key ?: 0
            val response = api.getImageThumbs("asc", 20, nextPage)


            val nextPageNumber = if(response.headers()["pagination-page"] != null) {
                (response.headers()["pagination-page"]!!.toInt()) + 1
            }else{ 1 }

            val prevPageNumber = if(response.headers()["pagination-page"]!!.toInt() > 0) {
                response.headers()["pagination-page"]!!.toInt()
            }else { null }

            val loadResData = response.body()!!.map { it.toCats() }

            LoadResult.Page(data = loadResData,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}
