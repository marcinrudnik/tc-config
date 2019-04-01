package Tailor.buildTypes

import Tailor.vcsRoots.Tailor_GpKansasPullRequest
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

object Tailor_NewCiPullRequest : BuildType({
    templates(RelativeId("dfasfasfasf"))
    name = "Pull Request Stage"

    vcs {
        root(Tailor.vcsRoots.Tailor_GpKansasPullRequest, "+:%project.fragment.path% => .")
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
            vcsRootExtId = "${Tailor_GpKansasPullRequest.id}"
            provider = github {
                authType = token {
                    token = "credentialsJSON:d1e5ab10-b307-4bfb-ac83-048444b65c41"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
        commitStatusPublisher {
            id = "BUILD_EXT_252"
            vcsRootExtId = "${Tailor_GpKansasPullRequest.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:d1e5ab10-b307-4bfb-ac83-048444b65c41"
                }
            }
        }
    }
})
