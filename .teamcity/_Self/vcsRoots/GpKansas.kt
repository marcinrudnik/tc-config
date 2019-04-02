package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

object GpKansas : GitVcsRoot({
    name = "GP.Kansas_2"
    url = "git@github.com:GrupaPracuj/GP.Kansas.git"
    branchSpec = "refs/heads/*"
    authMethod = uploadedKey {
        uploadedKey = "GitHub-TeamCity"
        passphrase = "credentialsJSON:19f9d588-a7b9-4eb3-a6f1-40c756dcfd06"
    }
})
