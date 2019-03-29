package Tailor_NewCiReleaseFragment.buildTypes

import _Self.buildTypes.PdDeployFrontProjectToOctopus
import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Tailor_NewCiReleaseFragment_DeployToProduction : BuildType({
    templates(PdDeployFrontProjectToOctopus)
    name = "Deploy to Production"

    buildNumberPattern = "${Tailor_NewCiReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Production")
    }

    dependencies {
        snapshot(Tailor_NewCiReleaseFragment_DeployToBeta) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
