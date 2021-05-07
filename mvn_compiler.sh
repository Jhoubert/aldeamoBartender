#!/bin/bash
./mvnw versions:set -DnewVersion=0.1-PROTOTYPE
./mvnw package -Dhttps.protocols=TLSv1.2 -DskipTests
