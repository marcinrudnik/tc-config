package DailySpecials

import DailySpecials.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("DailySpecials")
    name = "Daily Specials"

    buildType(DailySpecials_SonarQubeAnalysisMaster)
    buildType(DailySpecials_PullRequest)
    buildType(DailySpecials_CommitStage)

    params {
        param("project.fragment.path", "packages/fragment-daily-specials")
        param("project.fragment.name", "GP.Kansas.DailySpecials")
    }
    buildTypesOrder = arrayListOf(DailySpecials_CommitStage, DailySpecials_PullRequest, DailySpecials_SonarQubeAnalysisMaster)

    subProject(DailySpecials_ReleaseFragment.Project)
})
