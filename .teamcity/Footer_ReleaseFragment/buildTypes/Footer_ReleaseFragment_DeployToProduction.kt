package Footer_ReleaseFragment.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object Footer_ReleaseFragment_DeployToProduction : BuildType({
    templates(AbsoluteId("PdDeployFrontProjectToOctopus"))
    name = "Deploy to Production"

    buildNumberPattern = "${Footer_ReleaseFragment_ReleaseStage.depParamRefs.buildNumber}"

    params {
        param("environment.name", "Production")
    }

    dependencies {
        snapshot(Footer_ReleaseFragment_DeployToBeta) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
