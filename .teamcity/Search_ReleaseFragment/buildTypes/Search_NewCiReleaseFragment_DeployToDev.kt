package Search_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Search_NewCiReleaseFragment_DeployToDev : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Dev"

    buildNumberPattern = "${Search_NewCiReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Dev")
    }

    dependencies {
        snapshot(Search_NewCiReleaseFragment_ReleaseStage) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
