# registrydemo
Spring microservice demo for PCF. Register application to service registry, access rest api of publisher app, access properties of config server, and hystrix sample with fallback method.

version: 2018.11.19-V0.1

#Concourse部署脚本
fly -t lite set-pipeline -c ci/pipeline.yml -p blue-green-pipeline -l ci/credentials.yml
