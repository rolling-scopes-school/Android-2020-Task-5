package com.rovenhook.rsshool2021_android_task_catapi

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
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class CatsViewModelTestSpek2 : Spek({
    @MockK
    val repositoryMock: Repository = mockk<Repository>()
    val mainThreadSurrogate = newSingleThreadContext("UI thread")

    group("CatsViewModel testing using Spek2") {
        beforeEachTest {
            unmockkAll()
            clearAllMocks()
            Dispatchers.setMain(mainThreadSurrogate)
        }

        test("call getAllCats results in zero size list") {
            var livedataTest: LiveData<List<CatsApiData>> =
                MutableLiveData<List<CatsApiData>>(arrayListOf(CatsApiData("id1", "url1")))
            val viewModelTest: CatsViewModel = CatsViewModel()

            livedataTest = viewModelTest.getAllCats()

            assertEquals(0, livedataTest.value?.size)
        }

        test("Call getMoreCats expands array") {
            val viewModelTest: CatsViewModel = CatsViewModel()

            coEvery {
                repositoryMock.getMoreCats(0)
            } answers {
                listOf<CatsApiData>(
                    CatsApiData("sdf", "af"),
                    CatsApiData("sdff", "affas")
                )
            }

            viewModelTest.getMoreCats(repositoryMock)
            var testSize = 0
            runBlockingTest {
                testSize = repositoryMock.getMoreCats(0).size
            }

            assertEquals(2, testSize)
        }

        afterEachTest {
            Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
            mainThreadSurrogate.close()
        }
    }
})
