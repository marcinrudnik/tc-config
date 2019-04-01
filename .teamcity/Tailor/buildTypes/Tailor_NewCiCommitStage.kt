package Tailor.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

object Tailor_NewCiCommitStage : BuildType({
    templates(AbsoluteId("PdCommitStageNpmFragment"))
    name = "Commit Stage"

    params {
        param("env.CLIENT_TRACING_URL", "#{sentry:url}")
        param("env.TRACKER_BUSINESS_URL", "#{tracker:buisnessurl}")
        param("env.FRAGMENT_DAILYSPECIALS", "#{fragment:dailyspecials}")
        param("env.TRACKER_CORE_URL", "#{tracker:coreurl}")
        param("env.FRAGMENT_FOOTER", "#{fragment:footer}")
        param("env.TRACKER_WSID_URL", "#{tracker:wsidUrl}")
        param("env.FRAGMENT_SEARCH", "#{fragment:search}")
        param("env.FRAGMENT_YOUROFFERS", "#{fragment:youroffers}")
        param("env.FRAGMENT_HEADER", "#{fragment:header}")
        param("env.FRAGMENT_RECOMMENDATIONS", "#{fragment:recommendations}")
        param("env.CLIENT_TRACING_ID", "#{sentry:id}")
    }

    vcs {
        root(_Self.vcsRoots.GpKansas, "+:%project.fragment.path% => .")
    }

    steps {
        script {
            name = "Authorize .npmrc"
            id = "RUNNER_2707"
            scriptContent = """
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/registry/:_authToken=%azure.npm.auth_token% >> .npmrc
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/:_authToken=%azure.npm.auth_token% >> .npmrc
            """.trimIndent()
        }
        stepsOrder = arrayListOf("RUNNER_2707", "RUNNER_3090", "RUNNER_479", "RUNNER_3075")
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            triggerRules = "+:%project.fragment.path%/**"
        }
    }
})
