package YourOffers_ReleaseFragment

import YourOffers_ReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("YourOffers_ReleaseFragment")
    name = "Release Fragment"

    buildType(YourOffers_ReleaseFragment_ReleaseStage)
    buildType(YourOffers_ReleaseFragment_DeployToProduction)
    buildType(YourOffers_ReleaseFragment_DeployToBeta)
    buildType(YourOffers_ReleaseFragment_DeployToDev)

    params {
        param("project.octopus.name", "GP.Kansas.YourOffers")
        param("project.gpcdn.octopus.name", "Gp.Kansas.YourOffers_Gpcdn")
    }
    buildTypesOrder = arrayListOf(YourOffers_ReleaseFragment_ReleaseStage, YourOffers_ReleaseFragment_DeployToDev, YourOffers_ReleaseFragment_DeployToBeta, YourOffers_ReleaseFragment_DeployToProduction)
})
