package Header.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

object Header_SonarQubeAnalysisMaster : BuildType({
    templates(AbsoluteId("PdSonarQubeAnalysisNpmFragment"))
    name = "SonarQube Analysis (Master)"

    buildNumberPattern = "${Header_CommitStage.depParamRefs.buildNumber}"

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
        snapshot(Header_CommitStage) {
        }
    }
})
