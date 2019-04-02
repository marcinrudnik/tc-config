package Recommendations.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

object Recommendations_PullRequestStage : BuildType({
    templates(AbsoluteId("PdPullRequestStageNpmFragment"))
    name = "Pull Request Stage"

    vcs {
        root(_Self.vcsRoots.GpKansas_PullRequests, "+:%project.fragment.path% => .")
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            branchFilter = """
                +:*
                -:<default>
            """.trimIndent()
        }
    }

    features {
        pullRequests {
            id = "BUILD_EXT_94"
            vcsRootExtId = "${_Self.vcsRoots.GpKansas_PullRequests.id}"
            provider = github {
                authType = token {
                    token = "credentialsJSON:c65bc6e3-d0a6-49ac-b1d3-6bdaf8202610"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
        commitStatusPublisher {
            id = "BUILD_EXT_252"
            vcsRootExtId = "${_Self.vcsRoots.GpKansas_PullRequests.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:c65bc6e3-d0a6-49ac-b1d3-6bdaf8202610"
                }
            }
        }
    }
})
