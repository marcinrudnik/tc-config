package Search

import Search.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    id("Search")
    name = "Search"

    buildType(Search_SonarQubeAnalysisMaster)
    buildType(Search_PullRequest)
    buildType(Search_CommitStage)

    params {
        param("project.fragment.path", "packages/fragment-search")
        param("project.fragment.name", "GP.Kansas.Search")
    }
    buildTypesOrder = arrayListOf(Search_CommitStage, Search_PullRequest, Search_SonarQubeAnalysisMaster)

    subProject(Search_ReleaseFragment.Project)
})
