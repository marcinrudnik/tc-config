package DailySpecials_ReleaseFragment.buildTypes

import _Self.vcsRoots.GpKansas
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.vcsLabeling

object DailySpecials_ReleaseFragment_ReleaseStage : BuildType({
    templates(AbsoluteId("PdReleaseStageNpmFragment"))
    name = "Release Stage"

    params {
        param("project.fragment.shortName", "daily-specials")
        param("project.nuspec.path", """scripts\PackageDailySpecials.nuspec""")
        param("project.gpcdn.package.name", "GP.Kansas.DailySpecials.Gpcdn")
        param("project.gpcdn.nuspec.path", """scripts\PackageDailySpecialsGpcdn.nuspec""")
        param("project.package.name", "GP.Kansas.DailySpecials")
    }

    vcs {
        root(_Self.vcsRoots.GpKansas, "+:%project.fragment.path% => .")
    }

    features {
        vcsLabeling {
            id = "BUILD_EXT_114"
            enabled = false
            vcsRootId = "${GpKansas.id}"
            labelingPattern = "%project.fragment.shortName%-%build.number%"
            successfulOnly = true
            branchFilter = "+:*"
        }
    }

    dependencies {
        dependency(DailySpecials.buildTypes.DailySpecials_CommitStage) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_6"
                buildRule = lastSuccessful()
                artifactRules = """
                    +:config => config
                    +:server => server
                    +:dist => dist
                    +:scripts => scripts
                    +:ecosystem.config.js
                    +:package.json
                    +:package-lock.json
                """.trimIndent()
            }
        }
    }
})
