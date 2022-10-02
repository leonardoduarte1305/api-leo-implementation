pipeline {
    agent any
    tools {
        maven "maven-3"
        jdk "jdk-11"
    }
    environment {
        MAVEN = "mvn -B -Dstyle.color=always -Dmaven.test.redirectTestOutputToFile=false"
        MAJOR_VERSION = 1
        MAVEN_OPTS = "-Djansi.force=true -Xmx512m"
        SERVICE_NAME = "api-leo"
    }
    stages {
        stage("CI") {
            stages {
                stage("Setup") {
                    steps {
                        script {
                            echo "Environments:"
                        }
                        sh "printenv | sort"
                    }
                }
                stage("Init") {
                    steps {
                        sh "echo Stage Init"
                    }
                }
                stage("Build") {
                    steps {
                        sh "${MAVEN} clean package"
                    }
                }
                stage("Install Artifacts") {
                    steps {
                        sh "${MAVEN} install"
                    }
                }
                stage("Sonar Analysis"){
                    steps{
                        sh "${MAVEN} sonar:sonar -Dsonar.login=${SONAR_LOGIN}"
                    }
                }
            }
        }
        stage("CD") {
            stages {
                stage("Slack Message") {
                    steps {
                        slackSend(channel: "jenkins", message: "Here is the primary message")
                    }
                }
            }
        }
    }
}