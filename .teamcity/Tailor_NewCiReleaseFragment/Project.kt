package Tailor_NewCiReleaseFragment

import Tailor_NewCiReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Tailor_NewCiReleaseFragment")
    name = "Release Fragment"

    buildType(Tailor_NewCiReleaseFragment_DeployToDev)
    buildType(Tailor_NewCiReleaseFragment_DeployToBeta)
    buildType(Tailor_NewCiReleaseFragment_ReleaseStage)
    buildType(Tailor_NewCiReleaseFragment_DeployToProduction)

    params {
        param("project.octopus.name", "GP.Kansas")
        param("project.gpcdn.octopus.name", "Gp.Kansas_Gpcdn")
    }
    buildTypesOrder = arrayListOf(Tailor_NewCiReleaseFragment_ReleaseStage, Tailor_NewCiReleaseFragment_DeployToDev, Tailor_NewCiReleaseFragment_DeployToBeta, Tailor_NewCiReleaseFragment_DeployToProduction)
})
