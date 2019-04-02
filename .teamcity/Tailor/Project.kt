package Tailor

import Tailor.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Tailor")
    name = "Tailor"

    buildType(Tailor_PullRequest)
    buildType(Tailor_CommitStage)
    buildType(Tailor_SonarQubeAnalysisMaster)

    params {
        param("project.fragment.path", "packages/home-page")
        param("project.fragment.name", "GP.Kansas")
    }
    buildTypesOrder = arrayListOf(Tailor_CommitStage, Tailor_PullRequest, Tailor_SonarQubeAnalysisMaster)
    subProject(Tailor.Tailor_ReleaseFragment.Project)
})
