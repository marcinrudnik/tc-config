package YourOffers_ReleaseFragment.buildTypes

import _Self.vcsRoots.GpKansas
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.vcsLabeling

object YourOffers_ReleaseFragment_ReleaseStage : BuildType({
    templates(AbsoluteId("PdReleaseStageNpmFragment"))
    name = "Release Stage"

    params {
        param("env.LOGO_STORAGE_URL", "#{endpoint:logo_storage}")
        param("project.fragment.shortName", "your-offers")
        param("env.LINK_ACCOUNT", "#{link:account}")
        param("project.gpcdn.nuspec.path", """scripts\PackageYourOffersGpcdn.nuspec""")
        param("project.package.name", "GP.Kansas.YourOffers")
        param("project.nuspec.path", """scripts\PackageYourOffers.nuspec""")
        param("env.LINK_LOGIN", "#{link:login}")
        param("env.LINK_SAVED_OFFERS", "#{link:saved_offers}")
        param("env.LINK_HOMEPAGE", "#{link:homepage}")
        param("env.LINK_MY_APPLICATIONS", "#{link:my_applications}")
        param("project.gpcdn.package.name", "GP.Kansas.YourOffers.Gpcdn")
        param("env.LINK_JOB_OFFERS", "#{link:job_offers}")
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
        dependency(YourOffers.buildTypes.YourOffers_NewCiCommitStage) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_141"
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
