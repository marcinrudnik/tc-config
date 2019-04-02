package Search_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Search_NewCiReleaseFragment_DeployToBeta : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Beta"

    buildNumberPattern = "${Search_NewCiReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Beta")
    }

    dependencies {
        snapshot(Search_NewCiReleaseFragment_DeployToDev) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
