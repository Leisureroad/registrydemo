# registrydemo
Spring microservice demo for PCF. Register application to service registry, access rest api of publisher app, access properties of config server, and hystrix sample with fallback method.

version: 2019.06.25-V0.3

#Concourse部署脚本
test:test
fly --target tutorial login --concourse-url http://127.0.0.1:8080 -u test -p test
fly --target tutorial sync
fly -t tutorial set-pipeline -c ci/pipeline.yml -p blue-green-pipeline -l ci/credentials.yml

#Concourse Samples
https://github.com/pivotalservices/concourse-pipeline-samples

#JFrog
docker pull docker.bintray.io/jfrog/artifactory-oss:latest
docker run --name artifactory -d -p 8081:8081 docker.bintray.io/jfrog/artifactory-oss:latest
admin:password
curl -uadmin:password -T registrydemo-v0.1.jar "http://localhost:8082/artifactory/myrepo/registrydemo-v0.1.jar"

Notes: use manifest.yml.bak, update jfrog ip address
