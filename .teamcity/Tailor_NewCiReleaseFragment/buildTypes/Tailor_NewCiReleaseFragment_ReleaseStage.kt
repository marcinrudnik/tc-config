package Tailor_NewCiReleaseFragment.buildTypes

import _Self.vcsRoots.GpKansas
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.vcsLabeling

object Tailor_NewCiReleaseFragment_ReleaseStage : BuildType({
    //templates(AbsoluteId("PdReleaseStageNpmFragment"))
    name = "Release Stage"

    params {
        param("project.fragment.shortName", "home-page")
        param("project.gpcdn.nuspec.path", """scripts\PackageGpcdn.nuspec""")
        param("project.package.name", "GP.Kansas")
        param("project.nuspec.path", """scripts\Package.nuspec""")
        param("project.gpcdn.package.name", "GP.Kansas.Gpcdn")
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
        dependency(Tailor.buildTypes.Tailor_NewCiCommitStage) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_135"
                artifactRules = """
                    +:dist => dist
                    +:scripts => scripts
                """.trimIndent()
            }
        }
    }
})
