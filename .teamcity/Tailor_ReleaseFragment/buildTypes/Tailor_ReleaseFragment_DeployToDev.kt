package Tailor_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Tailor_ReleaseFragment_DeployToDev : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Dev"

    buildNumberPattern = "${Tailor_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Dev")
    }

    dependencies {
        snapshot(Tailor_ReleaseFragment_ReleaseStage) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
