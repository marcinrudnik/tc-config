package Search_ReleaseFragment

import Search_ReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Search_ReleaseFragment")
    name = "Release Fragment"

    buildType(Search_NewCiReleaseFragment_DeployToProduction)
    buildType(Search_NewCiReleaseFragment_DeployToBeta)
    buildType(Search_NewCiReleaseFragment_ReleaseStage)
    buildType(Search_NewCiReleaseFragment_DeployToDev)

    params {
        param("project.octopus.name", "GP.Kansas.Search")
        param("project.gpcdn.octopus.name", "Gp.Kansas.Search_Gpcdn")
    }
    buildTypesOrder = arrayListOf(Search_NewCiReleaseFragment_ReleaseStage, Search_NewCiReleaseFragment_DeployToDev, Search_NewCiReleaseFragment_DeployToBeta, Search_NewCiReleaseFragment_DeployToProduction)
})
