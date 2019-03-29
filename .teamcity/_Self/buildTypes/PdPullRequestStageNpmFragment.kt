package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script

object PdPullRequestStageNpmFragment : Template({
    name = "PD Pull Request Stage NPM (fragment)"
    description = "Konfiguracja budowy i analizy projektu fragmentu NPM na Pull Request"

    allowExternalStatus = true
    artifactRules = """
        reports\client-bundle.html
        reports\server-bundle.html
    """.trimIndent()

    params {
        param("sonar.project.id", "%project.fragment.name%")
        param("env.TIMEOUT", "#{timeout}")
        param("sonar.project.name", "%project.fragment.name%")
    }

    vcs {
        buildDefaultBranch = false
        cleanCheckout = true
    }

    steps {
        script {
            name = "Authorize .npmrc"
            id = "RUNNER_3066"
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
            name = "Audit"
            id = "RUNNER_2675"
            type = "jonnyzzz.npm"
            param("npm_commands", "audit --only=prod")
        }
        step {
            name = "Syntax"
            id = "RUNNER_2878"
            type = "jonnyzzz.npm"
            param("npm_commands", """
                run eslint
                run stylelint
            """.trimIndent())
        }
        step {
            name = "Find duplicates"
            id = "RUNNER_3063"
            type = "jonnyzzz.npm"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            param("npm_commands", "run find-duplicates")
        }
        step {
            name = "Test"
            id = "RUNNER_499"
            type = "jonnyzzz.npm"
            param("npm_commands", "test")
        }
        step {
            name = "Build"
            id = "RUNNER_2677"
            type = "jonnyzzz.npm"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            param("npm_commands", "run build")
        }
        step {
            name = "SonarQube"
            id = "RUNNER_2678"
            type = "sonar-plugin"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            param("sonarProjectName", "%sonar.project.name%")
            param("additionalParameters", """
                -Dsonar.github.pullRequest=%teamcity.build.branch%
                -Dsonar.github.repository=%repository.name%
                -Dsonar.github.oauth=%sonar.github.oauth%
                -Dsonar.analysis.mode=preview
                -Dsonar.verbose=true
            """.trimIndent())
            param("sonarProjectKey", "%sonar.project.id%")
            param("sonarServer", "f5f1b64d-e183-49d1-ae9b-8822804d3bd5")
        }
    }

    features {
        feature {
            id = "BUILD_EXT_77"
            type = "teamcity.github.status"
            param("guthub_owner", "GrupaPracuj")
            param("guthub_authentication_type", "token")
            param("guthub_repo", "GP.Kansas")
            param("github_report_on", "on start and finish")
            param("secure:github_access_token", "credentialsJSON:d1e5ab10-b307-4bfb-ac83-048444b65c41")
            param("guthub_comments", "true")
        }
        pullRequests {
            id = "BUILD_EXT_94"
            provider = github {
                authType = token {
                    token = "credentialsJSON:d1e5ab10-b307-4bfb-ac83-048444b65c41"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
        commitStatusPublisher {
            id = "BUILD_EXT_252"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:d1e5ab10-b307-4bfb-ac83-048444b65c41"
                }
            }
        }
    }
})
