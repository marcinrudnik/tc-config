import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.2"

project {
    description = "Strona główna"

    vcsRoot(GpKansas_PullRequests)
    vcsRoot(GpKansas)

    params {
        param("env.SERVICE_NAME", "#{service:name}")
        param("env.CDN", "#{service:cdnUrl}")
        param("repository.name", "GrupaPracuj/GP.Kansas")
    }

    features {
        feature {
            id = "PROJECT_EXT_16"
            type = "ReportTab"
            param("startPage", "client-bundle.html")
            param("title", "Client bundle")
            param("type", "BuildReportTab")
        }
        feature {
            id = "PROJECT_EXT_17"
            type = "ReportTab"
            param("startPage", "server-bundle.html")
            param("title", "Server bundle")
            param("type", "BuildReportTab")
        }
        feature {
            id = "PROJECT_EXT_67"
            type = "ReportTab"
            param("startPage", "jscpd-report.html")
            param("title", "Code duplicates")
            param("type", "BuildReportTab")
        }
    }
}

object GpKansas : GitVcsRoot({
    name = "GP.Kansas"
    url = "git@github.com:GrupaPracuj/GP.Kansas.git"
    branchSpec = "refs/heads/*"
    authMethod = uploadedKey {
        uploadedKey = "GitHub-TeamCity"
        passphrase = "credentialsJSON:19f9d588-a7b9-4eb3-a6f1-40c756dcfd06"
    }
})

object GpKansas_PullRequests : GitVcsRoot({
    name = "GP.Kansas_PullRequest"
    url = "git@github.com:GrupaPracuj/GP.Kansas.git"
    branch = "refs/heads/dev"
    branchSpec = "+:refs/pull/*/head"
    userForTags = "GrupaPracujTeamCity <TeamCity@pracuj.pl>"
    authMethod = uploadedKey {
        uploadedKey = "GitHub-TeamCity"
        passphrase = "credentialsJSON:19f9d588-a7b9-4eb3-a6f1-40c756dcfd06"
    }
})
