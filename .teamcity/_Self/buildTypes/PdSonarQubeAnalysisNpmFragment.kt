package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script

object PdSonarQubeAnalysisNpmFragment : Template({
    name = "PD SonarQube Analysis NPM (fragment)"
    description = "Konfiguracja analizy SonarQube dla fragmentu NPM"

    allowExternalStatus = true

    params {
        param("sonar.project.id", "%project.fragment.name%")
        param("env.NPM_AUTH_TOKEN", "%azure.npm.auth_token%")
        param("sonar.project.name", "%project.fragment.name%")
    }

    vcs {
        cleanCheckout = true
    }

    steps {
        script {
            name = "Authorize .npmrc"
            id = "RUNNER_847"
            scriptContent = """
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/registry/:_authToken=%azure.npm.auth_token% >> .npmrc
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/:_authToken=%azure.npm.auth_token% >> .npmrc
            """.trimIndent()
        }
        step {
            name = "Install"
            id = "RUNNER_2674"
            type = "jonnyzzz.npm"
            param("npm_commands", "install")
        }
        step {
            name = "SonarQube"
            id = "RUNNER_2678"
            type = "sonar-plugin"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            param("sonarProjectName", "%sonar.project.name%")
            param("additionalParameters", "-Dsonar.verbose=true")
            param("sonarProjectKey", "%sonar.project.id%")
            param("sonarServer", "f5f1b64d-e183-49d1-ae9b-8822804d3bd5")
        }
    }
})
