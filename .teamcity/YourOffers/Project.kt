package YourOffers

import YourOffers.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("YourOffers")
    name = "Your offers"

    buildType(YourOffers_NewCiCommitStage)
    buildType(YourOffers_NewCiPullRequest)
    buildType(YourOffers_NewCiSonarQubeAnalysisMaster)

    params {
        param("project.fragment.path", "packages/fragment-youroffers")
        param("project.fragment.name", "GP.Kansas.YourOffers")
    }
    buildTypesOrder = arrayListOf(YourOffers_NewCiCommitStage, YourOffers_NewCiPullRequest, YourOffers_NewCiSonarQubeAnalysisMaster)

    subProject(YourOffers_ReleaseFragment.Project)
})
