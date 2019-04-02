package DailySpecials_ReleaseFragment

import DailySpecials_ReleaseFragment.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("DailySpecials_ReleaseFragment")
    name = "Release Fragment"

    buildType(DailySpecials_ReleaseFragment_DeployToDev)
    buildType(DailySpecials_ReleaseFragment_DeployToProduction)
    buildType(DailySpecials_ReleaseFragment_DeployToBeta)
    buildType(DailySpecials_ReleaseFragment_ReleaseStage)

    params {
        param("project.octopus.name", "GP.Kansas.DailySpecials")
        param("project.gpcdn.octopus.name", "Gp.Kansas.DailySpecials_Gpcdn")
    }
    buildTypesOrder = arrayListOf(DailySpecials_ReleaseFragment_ReleaseStage, DailySpecials_ReleaseFragment_DeployToDev, DailySpecials_ReleaseFragment_DeployToBeta, DailySpecials_ReleaseFragment_DeployToProduction)
})
