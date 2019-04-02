package Header_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Header_ReleaseFragment_DeployToBeta : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Beta"

    buildNumberPattern = "${Header_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Beta")
    }

    dependencies {
        snapshot(Header_ReleaseFragment_DeployToDev) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
