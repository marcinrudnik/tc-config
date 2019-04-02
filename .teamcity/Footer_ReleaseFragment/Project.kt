package Footer_ReleaseFragment

import Footer_ReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Footer_ReleaseFragment")
    name = "Release Fragment"

    buildType(Footer_ReleaseFragment_DeployToBeta)
    buildType(Footer_ReleaseFragment_DeployToProduction)
    buildType(Footer_ReleaseFragment_ReleaseStage)
    buildType(Footer_ReleaseFragment_DeployToDev)

    params {
        param("project.octopus.name", "GP.Kansas.Footer")
        param("project.gpcdn.octopus.name", "Gp.Kansas.Footer_Gpcdn")
    }
    buildTypesOrder = arrayListOf(Footer_ReleaseFragment_ReleaseStage, Footer_ReleaseFragment_DeployToDev, Footer_ReleaseFragment_DeployToBeta, Footer_ReleaseFragment_DeployToProduction)
})
