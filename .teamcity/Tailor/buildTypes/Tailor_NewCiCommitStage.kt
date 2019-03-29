package Tailor.buildTypes

import _Self.buildTypes.PdCommitStageNpmFragment
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

object Tailor_NewCiCommitStage : BuildType({
    templates(RelativeId("PdCommitStageNpmFragment"))
    name = "Commit Stage"

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
