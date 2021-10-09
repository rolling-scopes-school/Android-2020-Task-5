package com.rovenhook.rsshool2021_android_task_catapi

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.data.Repository
import com.rovenhook.rsshool2021_android_task_catapi.viewmodels.CatsViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ExampleUnitTest {
    //    val viewModelTest: CatsViewModel = CatsViewModel()
    var livedataTest: LiveData<List<CatsApiData>> =
        MutableLiveData<List<CatsApiData>>(arrayListOf(CatsApiData("id1", "url1")))

    @MockK
    val repositoryMock: Repository = mockk<Repository>()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun setup() {
        unmockkAll()
        clearAllMocks()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun callAllCatsAndZeroList() {
        val viewModelTest: CatsViewModel = CatsViewModel()

        livedataTest = viewModelTest.getAllCats()

        assertEquals(livedataTest, viewModelTest.items)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun callForMoreCatsExpandsArray() {
        val viewModelTest: CatsViewModel = CatsViewModel()

        coEvery {
            repositoryMock.getMoreCats(0)
        } answers {
            listOf<CatsApiData>(
                CatsApiData("sdf", "af"),
                CatsApiData("sdf", "af")
            )
        }

        viewModelTest.getMoreCats(repositoryMock)
        var size = 0
        runBlockingTest {
            size = repositoryMock.getMoreCats(0).size
        }

        assertEquals(2, size)
    }

    @After
    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}