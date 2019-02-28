# Water-Quality-Portal

[![Build Status](https://travis-ci.org/NWQMC/WQP-WQX-Services.svg?branch=master)](https://travis-ci.org/NWQMC/WQP-WQX-Services)

Water Quality Portal data streaming service.

WORK NEEDED HERE
sample .yml files; sample .env files; info on database; 

docker network create --subnet=172.25.0.0/16 wqp

docker run -it -p 127.0.0.1:8080:8080 --env-file ./config.env --network wqp 0722a2973494


docker run -it --env-file ./.env --network wqp --ip 172.25.0.4 -v /home/drsteini/tools/git/wqp/WQP-WQX-Services/:/secrets -p 127.0.0.1:8083:8080 wqp

docker build . --tag wqp




docker run -it --env-file ./.env --network wqp --ip 172.25.0.6 -v /home/drsteini/tools/git/wqp/WQP-WQX-Services/secrets/common:/home/java/common/secrets -v /home/drsteini/tools/git/wqp/WQP-WQX-Services/secrets/app:/home/java/app/secrets -p 127.0.0.1:8083:8080 wqp

