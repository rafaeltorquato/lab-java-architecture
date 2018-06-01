#!/usr/bin/env bash

mvn clean install -Pjavaee,javaee-stubs-persistence
java -jar architecture-javaee-impl/javaee-delivery-ws/target/javaee-delivery-ws-swarm.jar
