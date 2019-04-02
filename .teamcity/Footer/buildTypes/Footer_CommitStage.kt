package Footer.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

object Footer_CommitStage : BuildType({
    templates(AbsoluteId("PdCommitStageNpmFragment"))
    name = "Commit Stage"

    vcs {
        root(_Self.vcsRoots.GpKansas, "+:%project.fragment.path% => .")
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            triggerRules = "+:%project.fragment.path%/**"
        }
    }
})
