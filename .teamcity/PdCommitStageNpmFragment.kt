package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script

object PdCommitStageNpmFragment : Template({
    name = "PD Commit Stage NPM (fragment)"
    description = "Podstawowa konfiguracja commit stage'a dla fragmentu NPM"

    allowExternalStatus = true
    artifactRules = """
        reports\client-bundle.html
        reports\server-bundle.html
        dist => dist
        scripts => scripts
    """.trimIndent()

    params {
        param("env.NAME", "#{service:name}")
        param("env.GATEWAY", "#{endPoint:gateway}")
        param("env.NPM_AUTH_TOKEN", "%azure.npm.auth_token%")
        param("env.TIMEOUT", "#{timeout}")
    }

    vcs {
        cleanCheckout = true
    }

    steps {
        script {
            name = "Authorize .npmrc"
            id = "RUNNER_2707"
            scriptContent = """
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/registry/:_authToken=${'$'}{NPM_AUTH_TOKEN} >> .npmrc
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/:_authToken=${'$'}{NPM_AUTH_TOKEN} >> .npmrc
            """.trimIndent()
        }
        script {
            name = "Npm setup"
            id = "RUNNER_658"
            scriptContent = "npm set progress=false"
        }
        step {
            name = "Install modules"
            id = "RUNNER_3090"
            type = "jonnyzzz.npm"
            param("npm_commands", "install")
        }
        step {
            name = "Test"
            id = "RUNNER_479"
            type = "jonnyzzz.npm"
            param("npm_commands", "test")
        }
        step {
            name = "Build client"
            id = "RUNNER_3075"
            type = "jonnyzzz.npm"
            param("npm_commands", "run build")
        }
    }
})
