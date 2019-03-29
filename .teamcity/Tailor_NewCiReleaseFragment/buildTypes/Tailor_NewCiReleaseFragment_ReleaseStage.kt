package Tailor_NewCiReleaseFragment.buildTypes

import _Self.buildTypes.PdReleaseStageNpmFragment
import _Self.vcsRoots.GpKansas
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.vcsLabeling

object Tailor_NewCiReleaseFragment_ReleaseStage : BuildType({
    templates(PdReleaseStageNpmFragment)
    name = "Release Stage"

    params {
        param("env.CLIENT_TRACING_URL", "#{sentry:url}")
        param("project.fragment.shortName", "home-page")
        param("env.TRACKER_BUSINESS_URL", "#{tracker:buisnessurl}")
        param("env.FRAGMENT_DAILYSPECIALS", "#{fragment:dailyspecials}")
        param("env.FRAGMENT_YOUROFFERS", "#{fragment:youroffers}")
        param("env.CLIENT_TRACING_ID", "#{sentry:id}")
        param("project.gpcdn.nuspec.path", """scripts\PackageGpcdn.nuspec""")
        param("project.package.name", "GP.Kansas")
        param("env.TRACKER_CORE_URL", "#{tracker:coreurl}")
        param("env.FRAGMENT_FOOTER", "#{fragment:footer}")
        param("env.TRACKER_WSID_URL", "#{tracker:wsidUrl}")
        param("env.FRAGMENT_SEARCH", "#{fragment:search}")
        param("project.nuspec.path", """scripts\Package.nuspec""")
        param("env.FRAGMENT_HEADER", "#{fragment:header}")
        param("env.FRAGMENT_RECOMMENDATIONS", "#{fragment:recommendations}")
        param("project.gpcdn.package.name", "GP.Kansas.Gpcdn")
    }

    vcs {
        root(_Self.vcsRoots.GpKansas, "+:%project.fragment.path% => .")
    }

    features {
        vcsLabeling {
            id = "BUILD_EXT_114"
            enabled = false
            vcsRootId = "${GpKansas.id}"
            labelingPattern = "%project.fragment.shortName%-%build.number%"
            successfulOnly = true
            branchFilter = "+:*"
        }
    }

    dependencies {
        snapshot(Tailor.buildTypes.Tailor_NewCiCommitStage) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
