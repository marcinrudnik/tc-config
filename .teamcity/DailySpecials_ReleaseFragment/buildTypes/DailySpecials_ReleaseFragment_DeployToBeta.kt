package DailySpecials_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object DailySpecials_ReleaseFragment_DeployToBeta : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Beta"

    buildNumberPattern = "${DailySpecials_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Beta")
    }

    dependencies {
        snapshot(DailySpecials_ReleaseFragment_DeployToDev) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
