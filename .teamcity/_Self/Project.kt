package _Self

import _Self.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    description = "Strona główna"

    vcsRoot(GpKansas_PullRequests)
    vcsRoot(GpKansas)

    params {
        param("env.SERVICE_NAME", "#{service:name}")
        param("env.CDN", "#{service:cdnUrl}")
        param("repository.name", "GrupaPracuj/GP.Kansas")
    }

    features {
        feature {
            id = "PROJECT_EXT_16"
            type = "ReportTab"
            param("startPage", "client-bundle.html")
            param("title", "Client bundle")
            param("type", "BuildReportTab")
        }
        feature {
            id = "PROJECT_EXT_17"
            type = "ReportTab"
            param("startPage", "server-bundle.html")
            param("title", "Server bundle")
            param("type", "BuildReportTab")
        }
        feature {
            id = "PROJECT_EXT_67"
            type = "ReportTab"
            param("startPage", "jscpd-report.html")
            param("title", "Code duplicates")
            param("type", "BuildReportTab")
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Tailor"))

    subProject(Tailor.Project)
    subProject(YourOffers.Project)
    subProject(Recommendations.Project)
    subProject(Search.Project)
    subProject(DailySpecials.Project)
    subProject(Header.Project)
    subProject(Footer.Project)
})
