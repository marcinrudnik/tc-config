package Recommendations

import Recommendations.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Recommendations")
    name = "Recommendations"

    buildType(Recommendations_PullRequestStage)
    buildType(Recommendations_SonarQubeAnalysisMaster)
    buildType(Recommendations_CommitStage)

    params {
        param("project.fragment.path", "packages/fragment-recommendations")
        param("project.fragment.name", "GP.Kansas.Recommendations")
    }
    buildTypesOrder = arrayListOf(Recommendations_CommitStage, Recommendations_PullRequestStage, Recommendations_SonarQubeAnalysisMaster)

    subProject(Recommendations_ReleaseFragment.Project)
})
