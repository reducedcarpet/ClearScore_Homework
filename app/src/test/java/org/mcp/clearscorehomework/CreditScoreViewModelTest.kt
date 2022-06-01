package org.mcp.clearscorehomework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mcp.clearscorehomework.models.CoachingSummary
import org.mcp.clearscorehomework.models.CreditReportInfo
import org.mcp.clearscorehomework.models.CreditScoreModel
import org.mcp.clearscorehomework.viewmodels.CreditScoreViewModel
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations

// Yes there could be significantly more, better and of different types of testing, but alas I don't
// know mockito very well or roboelectric.
@RunWith(AndroidJUnit4::class)
class CreditScoreViewModelTest {

    @Mock
    private lateinit var creditScore: CreditScoreModel

    @Mock
    private lateinit var viewModel: CreditScoreViewModel

    @Mock
    private lateinit var isCreditScoreLiveData: LiveData<CreditScoreModel?>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = spy(CreditScoreViewModel(ApplicationProvider.getApplicationContext()))
        isCreditScoreLiveData = viewModel.creditScore
    }

    // Don't want to use the server when it could cost money.
    //@Test
    //fun `Verify fetching credit score works`() {
    //    assertNotNull(viewModel.fetchCreditScore())
    //}

    @Test
    fun `check livedata updates`() {
        assert(isCreditScoreLiveData.value == null)
        viewModel.creditScore.postValue(creditScoreInstance)
        assertNotNull(isCreditScoreLiveData.value)
        assert(isCreditScoreLiveData.value?.creditReportInfo?.score == creditScoreInstance.creditReportInfo.score)
    }
}

val creditScoreInstance =
    CreditScoreModel(
        "PASS",
        CreditReportInfo(
            514,
            4,
            "CS-SED-655426-708782",
            "MATCH",
            700,
            0,
            -1,
            false,
            1,
            true,
            44,
            1,
            0,
            13758,
            13758,
            30600,
            44,
            549,
            24682,
            24682,
            null,
            null,
            -327,
            9,
            0,
            4,
            "Excellent",
            9,
        ),
        "PASS",
        "INEXPERIENCED",
        CoachingSummary(
            false,
            true,
            0,
            0,
            true,
        ),
        null,
    )