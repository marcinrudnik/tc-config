package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script

object PdReleaseStageNpmFragment : Template({
    name = "PD Release Stage NPM (fragment)"

    allowExternalStatus = true
    artifactRules = """
        +:nupkg
        +:reports\client-bundle.html
        +:reports\server-bundle.html
    """.trimIndent()

    params {
        param("project.versionfile.path", "version")
        param("env.GATEWAY", "#{endPoint:gateway}")
        param("env.NPM_AUTH_TOKEN", "%azure.npm.auth_token%")
        param("env.TIMEOUT", "#{timeout}")
    }

    vcs {
        cleanCheckout = true
    }

    steps {
        step {
            name = "Setup Version"
            id = "RUNNER_314"
            type = "SetupVersion"
            param("version.filename", "%project.versionfile.path%")
        }
        script {
            name = "Authorize .npmrc"
            id = "RUNNER_2712"
            enabled = false
            scriptContent = """
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/registry/:_authToken=%azure.npm.auth_token% >> .npmrc
                echo //gppracuj.pkgs.visualstudio.com/_packaging/gp/npm/:_authToken=%azure.npm.auth_token% >> .npmrc
            """.trimIndent()
        }
        step {
            name = "Install modules"
            id = "RUNNER_3090"
            type = "jonnyzzz.npm"
            enabled = false
            param("npm_commands", "install")
        }
        step {
            name = "Build client"
            id = "RUNNER_3075"
            type = "jonnyzzz.npm"
            enabled = false
            param("npm_commands", "run build")
        }
        step {
            name = "Test"
            id = "RUNNER_784"
            type = "jonnyzzz.npm"
            enabled = false
            param("npm_commands", "test")
        }
        step {
            name = "Create packages for Octopus"
            id = "RUNNER_3102"
            type = "jb.nuget.pack"
            param("nuget.pack.output.clean", "true")
            param("nuget.pack.specFile", """
                %project.nuspec.path%
                %project.gpcdn.nuspec.path%
            """.trimIndent())
            param("nuget.pack.output.directory", "nupkg")
            param("nuget.path", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("nuget.pack.as.artifact", "true")
            param("nuget.pack.version", "%build.number%")
        }
        step {
            name = "Publish packages to Octopus"
            id = "RUNNER_843"
            type = "octopus.push.package"
            param("octopus_host", "%octopus.api%")
            param("octopus_packagepaths", """
                nupkg/%project.gpcdn.package.name%.%build.number%.nupkg
                nupkg/%project.package.name%.%build.number%.nupkg
            """.trimIndent())
            param("secure:octopus_apikey", "credentialsJSON:43a78390-c07c-4374-bc4b-268d78ebab59")
        }
        step {
            name = "Create Octopus release for GPCDN"
            id = "RUNNER_844"
            type = "octopus.create.release"
            param("octopus_additionalcommandlinearguments", """--packageversion %build.number% --releaseNotes "Created by TeamCity from branch: %teamcity.build.branch%"""")
            param("octopus_version", "3.0+")
            param("octopus_host", "%octopus.api%")
            param("octopus_project_name", "%project.gpcdn.octopus.name%")
            param("secure:octopus_apikey", "credentialsJSON:43a78390-c07c-4374-bc4b-268d78ebab59")
            param("octopus_releasenumber", "%build.number%")
        }
        step {
            name = "Create Octopus release"
            id = "RUNNER_845"
            type = "octopus.create.release"
            param("octopus_additionalcommandlinearguments", """--packageversion %build.number% --releaseNotes "Created by TeamCity from branch: %teamcity.build.branch%"""")
            param("octopus_waitfordeployments", "true")
            param("octopus_version", "3.0+")
            param("octopus_host", "%octopus.api%")
            param("octopus_project_name", "%project.octopus.name%")
            param("secure:octopus_apikey", "credentialsJSON:43a78390-c07c-4374-bc4b-268d78ebab59")
            param("octopus_releasenumber", "%build.number%")
        }
    }
})
