package Header

import Header.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Header")
    name = "Header"

    buildType(Header_CommitStage)
    buildType(Header_PullRequest)
    buildType(Header_SonarQubeAnalysisMaster)

    params {
        param("project.fragment.path", "packages/fragment-header")
        param("project.fragment.name", "GP.Kansas.Header")
    }
    buildTypesOrder = arrayListOf(Header_CommitStage, Header_PullRequest, Header_SonarQubeAnalysisMaster)

    subProject(Header_ReleaseFragment.Project)
})
