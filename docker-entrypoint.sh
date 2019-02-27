#!/usr/bin/env bash

set -e

./setup-environment.sh

echo WQP_DB_HOST=$WQP_DB_HOST
echo WQP_DB_PORT=$WQP_DB_PORT
#echo  $WQP_DB_NAME=wqp_db
#echo  $WQP_DB_READ_ONLY_USERNAME=wqp_user
#echo  $WQP_DB_READ_ONLY_PASSWORD=changeMe
#echo  $SWAGGER_SERVICES_LOOKUPS_URL=http://172.25.0.5:8080/qw_portal_services
#echo  $SWAGGER_SERVICES_CORE_URL=http://172.25.0.4:8080/wqp
#echo  $SERVER_PORT=8080
#echo  $SERVER_CONTEXT_PATH=/wqp
#echo  $ROOT_LOG_LEVEL=INFO
#echo  $SITE_URL_BASE=https://www.waterqualitydata.us
#echo  $CODES_SERVICE_URL=http://172.25.0.5:8080/qw_portal_services/codes
#echo  $CODES_TIMEOUT_MILLI=1000
#echo  $NLDI_TIMEOUT_MILLI=10000
#echo  $KML_STYLE_URL=https://www.waterqualitydata.us/kml/wqp_styles.kml
#echo  $MAX_RESULT_ROWS=20000000
#echo  $SWAGGER_DISPLAY_HOST=${WQP_SERVICES_IPV4}:8080
#echo  $SWAGGER_DISPLAY_PATH=/wqp
#echo  $SWAGGER_DISPLAY_PROTOCOL=http

java -jar app.jar $@