# Water-Quality-Portal
Water Quality Portal data streaming service.

WORK NEEDED HERE
sample .yml files; sample .env files; info on database; 

docker network create --subnet=172.25.0.0/16 wqp

docker run -it -p 127.0.0.1:8080:8080 --env-file ./config.env --network wqp 0722a2973494
