package Header_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Header_ReleaseFragment_DeployToDev : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Dev"

    buildNumberPattern = "${Header_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Dev")
    }

    dependencies {
        snapshot(Header_ReleaseFragment_ReleaseStage) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
