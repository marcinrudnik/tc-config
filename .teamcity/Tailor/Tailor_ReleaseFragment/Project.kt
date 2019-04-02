package Tailor.Tailor_ReleaseFragment

import Tailor.Tailor_ReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Tailor/Tailor_ReleaseFragment")
    name = "Release Fragment"

    buildType(Tailor_ReleaseFragment_DeployToDev)
    buildType(Tailor_ReleaseFragment_DeployToBeta)
    buildType(Tailor_ReleaseFragment_ReleaseStage)
    buildType(Tailor_ReleaseFragment_DeployToProduction)

    params {
        param("project.octopus.name", "GP.Kansas")
        param("project.gpcdn.octopus.name", "Gp.Kansas_Gpcdn")
    }
    buildTypesOrder = arrayListOf(Tailor_ReleaseFragment_ReleaseStage, Tailor_ReleaseFragment_DeployToDev, Tailor_ReleaseFragment_DeployToBeta, Tailor_ReleaseFragment_DeployToProduction)
})
