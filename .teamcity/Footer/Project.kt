package Footer

import Footer.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Footer")
    name = "Footer"

    buildType(Footer_CommitStage)
    buildType(Footer_PullRequest)
    buildType(Footer_SonarQubeAnalysisMaster)

    params {
        param("project.fragment.path", "packages/fragment-footer")
        param("project.fragment.name", "GP.Kansas.Footer")
    }
    buildTypesOrder = arrayListOf(Footer_CommitStage, Footer_PullRequest, Footer_SonarQubeAnalysisMaster)

    subProject(Footer_ReleaseFragment.Project)
})
