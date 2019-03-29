package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

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
