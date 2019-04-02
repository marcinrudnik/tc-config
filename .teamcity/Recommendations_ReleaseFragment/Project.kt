package Recommendations_ReleaseFragment

import Recommendations_ReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Recommendations_ReleaseFragment")
    name = "Release Fragment"

    buildType(Recommendations_ReleaseFragment_DeployToDev)
    buildType(Recommendations_ReleaseFragment_DeployToProduction)
    buildType(Recommendations_ReleaseFragment_ReleaseStage)
    buildType(Recommendations_ReleaseFragment_DeployToBeta)

    params {
        param("project.octopus.name", "GP.Kansas.Recommendations")
        param("project.gpcdn.octopus.name", "Gp.Kansas.Recommendations_Gpcdn")
    }
    buildTypesOrder = arrayListOf(Recommendations_ReleaseFragment_ReleaseStage, Recommendations_ReleaseFragment_DeployToDev, Recommendations_ReleaseFragment_DeployToBeta, Recommendations_ReleaseFragment_DeployToProduction)
})
