package Header_ReleaseFragment

import Header_ReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Header_ReleaseFragment")
    name = "Release Fragment"

    buildType(Header_ReleaseFragment_DeployToDev)
    buildType(Header_ReleaseFragment_DeployToBeta)
    buildType(Header_ReleaseFragment_ReleaseStage)
    buildType(Header_ReleaseFragment_DeployToProduction)

    params {
        param("project.octopus.name", "GP.Kansas.Header")
        param("project.gpcdn.octopus.name", "Gp.Kansas.Header_Gpcdn")
    }
    buildTypesOrder = arrayListOf(Header_ReleaseFragment_ReleaseStage, Header_ReleaseFragment_DeployToDev, Header_ReleaseFragment_DeployToBeta, Header_ReleaseFragment_DeployToProduction)
})
