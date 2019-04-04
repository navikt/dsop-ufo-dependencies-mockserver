#!/bin/bash
docker ps -aq -f ancestor=penws-pensjonsinfo | xargs docker stop | xargs docker container rm | xargs docker image rm
docker build -t penws-pensjonsinfo .
docker run -p 9010:1090/tcp penws-pensjonsinfo
