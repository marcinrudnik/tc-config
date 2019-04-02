package Footer_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Footer_ReleaseFragment_DeployToBeta : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Beta"

    buildNumberPattern = "${Footer_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Beta")
    }

    dependencies {
        snapshot(Footer_ReleaseFragment_DeployToDev) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
