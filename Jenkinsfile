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
        SONAR_PROJECT_KEY= "api-leo-Implementation"
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
                stage("Sonar Analysis") {
                    steps {
                        sh "${MAVEN} clean verify sonar:sonar" +
                                " -Dsonar.projectKey=${SONAR_PROJECT_KEY}" +
                                " -Dsonar.host.url=${SONAR_HOST_URL}" +
                                " -Dsonar.login=${SONAR_TOKEN}"
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
            }
        }
    }
    post("CD") {
        success {
            script {
                slackSend(
                        channel: "jenkins",
                        message: "Build #${BUILD_NUMBER} - The build finished Successfully. [${BUILD_URL}] :ok_hand:",
                )
            }
        }
        failure {
            script {
                slackSend(
                        channel: "jenkins",
                        message: "Build #${BUILD_NUMBER} - The build couldn't finish due something wrong. [${BUILD_URL}] cold_face:",
                )
            }
        }
        aborted {
            script {
                slackSend(
                        channel: "jenkins",
                        message: "Build #${BUILD_NUMBER} - The build was aborted. [${BUILD_URL}] :zipper_mouth_face:",
                )
            }
        }
    }
}