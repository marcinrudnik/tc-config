package DailySpecials_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object DailySpecials_ReleaseFragment_DeployToDev : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Dev"

    buildNumberPattern = "${DailySpecials_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Dev")
    }

    dependencies {
        snapshot(DailySpecials_ReleaseFragment_ReleaseStage) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
