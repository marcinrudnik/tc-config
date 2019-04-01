package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Tailor_NewCiCommitStage'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Tailor_NewCiCommitStage")) {
    expectSteps {
        script {
            name = "Authorize .npmrc"
            scriptContent = """
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/registry/:_authToken=%azure.npm.auth_token% >> .npmrc
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/:_authToken=%azure.npm.auth_token% >> .npmrc
            """.trimIndent()
        }
    }
    steps {
        check(stepsOrder == arrayListOf("RUNNER_2707", "RUNNER_3090", "RUNNER_479", "RUNNER_3075")) {
            "Unexpected build steps order: $stepsOrder"
        }
        stepsOrder = arrayListOf<String>()
    }
}
