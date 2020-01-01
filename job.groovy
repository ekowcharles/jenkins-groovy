def project = 'ekowcharles/jenkins-repo'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
  def branchName = it.name
  def jobName = "${project}-${branchName}".replaceAll('/','-')
  job(jobName) {
    definition {
      cpsScm {
        scm {
          git("git://github.com/${project}.git", branchName)
        }

        scriptPath("Jenkinsfile")
      }
    }
  }
}