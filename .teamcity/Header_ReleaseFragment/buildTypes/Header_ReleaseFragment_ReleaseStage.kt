package Header_ReleaseFragment.buildTypes

import _Self.vcsRoots.GpKansas
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.vcsLabeling

object Header_ReleaseFragment_ReleaseStage : BuildType({
    templates(AbsoluteId("PdReleaseStageNpmFragment"))
    name = "Release Stage"

    params {
        param("project.fragment.shortName", "header")
        param("project.nuspec.path", """scripts\PackageHeader.nuspec""")
        param("project.gpcdn.package.name", "GP.Kansas.Header.Gpcdn")
        param("project.gpcdn.nuspec.path", """scripts\PackageHeaderGpcdn.nuspec""")
        param("project.package.name", "GP.Kansas.Header")
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
        dependency(Header.buildTypes.Header_CommitStage) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_94"
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
