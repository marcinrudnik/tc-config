package YourOffers.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

object YourOffers_NewCiSonarQubeAnalysisMaster : BuildType({
    templates(AbsoluteId("PdSonarQubeAnalysisNpmFragment"))
    name = "SonarQube Analysis (Master)"

    buildNumberPattern = "${YourOffers_NewCiCommitStage.depParamRefs.buildNumber}"

    vcs {
        root(_Self.vcsRoots.GpKansas, "+:%project.fragment.path% => .")
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_CUSTOM
            quietPeriod = 60
            branchFilter = "+:<default>"
        }
    }

    dependencies {
        snapshot(YourOffers_NewCiCommitStage) {
        }
    }
})
