package Tailor_NewCiReleaseFragment.buildTypes

import _Self.buildTypes.PdDeployFrontProjectToOctopus
import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Tailor_NewCiReleaseFragment_DeployToBeta : BuildType({
    templates(PdDeployFrontProjectToOctopus))
    name = "Deploy to Beta"

    buildNumberPattern = "${Tailor_NewCiReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Beta")
    }

    dependencies {
        snapshot(Tailor_NewCiReleaseFragment_DeployToDev) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
