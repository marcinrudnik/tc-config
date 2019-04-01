package Tailor

import Tailor.buildTypes.*
import Tailor.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Tailor")
    name = "Tailor"

    vcsRoot(Tailor_GpKansasPullRequest)

    buildType(Tailor_NewCiPullRequest)
    buildType(Tailor_NewCiCommitStage)
    buildType(Tailor_NewCiSonarQubeAnalysisMaster)

    params {
        param("project.fragment.path", "packages/home-page")
        param("project.fragment.name", "GP.Kansas")
    }
    buildTypesOrder = arrayListOf(Tailor_NewCiCommitStage, Tailor_NewCiPullRequest, Tailor_NewCiSonarQubeAnalysisMaster)
    /*subProject(Tailor_NewCiReleaseFragment.Project)*/
})
