package Recommendations_ReleaseFragment.buildTypes

import _Self.vcsRoots.GpKansas
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.vcsLabeling

object Recommendations_ReleaseFragment_ReleaseStage : BuildType({
    templates(AbsoluteId("PdReleaseStageNpmFragment"))
    name = "Release Stage"

    params {
        param("env.LOGO_STORAGE_URL", "#{endpoint:logo_storage}")
        param("project.fragment.shortName", "recommendations")
        param("env.LINK_ACCOUNT", "#{link:account}")
        param("project.gpcdn.nuspec.path", """scripts\PackageRecommendationsGpcdn.nuspec""")
        param("project.package.name", "GP.Kansas.Recommendations")
        param("project.nuspec.path", """scripts\PackageRecommendations.nuspec""")
        param("env.LINK_LOGIN", "#{link:login}")
        param("env.LINK_HOMEPAGE", "#{link:homepage}")
        param("project.gpcdn.package.name", "GP.Kansas.Recommendations.Gpcdn")
        param("env.LINK_JOB_OFFERS", "#{link:job_offers}")
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
        dependency(Recommendations.buildTypes.Recommendations_CommitStage) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_139"
                buildRule = lastSuccessful()
                artifactRules = """
                    +:config => config
                    +:server => server
                    +:dist => dist
                    +:scripts => scripts
                    +:ecosystem.config.js
                    +:package.json
                    +:package-lock.json
                """.trimIndent()
            }
        }
    }
})
