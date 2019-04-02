package Tailor_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Tailor_ReleaseFragment_DeployToProduction : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Production"

    buildNumberPattern = "${Tailor_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Production")
    }

    dependencies {
        snapshot(Tailor_ReleaseFragment_DeployToBeta) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
