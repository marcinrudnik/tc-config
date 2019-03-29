package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object PdDeployFrontProjectToOctopus : Template({
    name = "PD Deploy Front project to (Octopus)"
    description = "Deploy istniejącego releasa dla aplikacji Frontowej przez Octopusa"

    allowExternalStatus = true
    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        param("env.NPM_AUTH_TOKEN", "%azure.npm.auth_token%")
        param("project.octopus.tenant.name", "")
    }

    steps {
        step {
            name = "Deploy GPCDN release"
            id = "RUNNER_650"
            type = "octopus.deploy.release"
            param("octopus_waitfordeployments", "true")
            param("octopus_version", "3.0+")
            param("octopus_host", "%octopus.api%")
            param("octopus_project_name", "%project.gpcdn.octopus.name%")
            param("octoups_tenants", "%project.octopus.tenant.name%")
            param("octopus_deployto", "%environment.name%")
            param("secure:octopus_apikey", "credentialsJSON:43a78390-c07c-4374-bc4b-268d78ebab59")
            param("octopus_releasenumber", "%build.number%")
        }
        step {
            name = "Deploy release"
            id = "RUNNER_2201"
            type = "octopus.deploy.release"
            param("octopus_waitfordeployments", "true")
            param("octopus_version", "3.0+")
            param("octopus_host", "%octopus.api%")
            param("octopus_project_name", "%project.octopus.name%")
            param("octoups_tenants", "%project.octopus.tenant.name%")
            param("octopus_deployto", "%environment.name%")
            param("secure:octopus_apikey", "credentialsJSON:43a78390-c07c-4374-bc4b-268d78ebab59")
            param("octopus_releasenumber", "%build.number%")
        }
    }
})
