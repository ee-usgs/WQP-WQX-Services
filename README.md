# Internet Of Water (IOW) Data Service
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/cacec87c86f341959d2da7f17b59c310)](https://app.codacy.com/app/usgs_wma_dev/WQP-WQX-Services?utm_source=github.com&utm_medium=referral&utm_content=NWQMC/WQP-WQX-Services&utm_campaign=Badge_Grade_Settings)
[![Build Status](https://travis-ci.org/NWQMC/WQP-WQX-Services.svg?branch=master)](https://travis-ci.org/NWQMC/WQP-WQX-Services)
[![codecov](https://codecov.io/gh/NWQMC/WQP-WQX-Services/branch/master/graph/badge.svg)](https://codecov.io/gh/NWQMC/WQP-WQX-Services)

Water Quality Portal data streaming service.

## Development
This is a Spring Boot project. All of the normal caveats relating to a Spring Boot application apply.

### Dependencies
This application retrieves information from a PostgreSQL database. Docker images for both automated testing (ci) and manual testing (demo) are available at https://hub.docker.com/r/usgswma/wqp_db.

Some of the query parameters are validated using the CODES_SERVICE. (They are identified with the @Lookup annotation in the FilterParameters class.) This can be run locally as a Docker container available by following the instructions at https://github.com/NWQMC/qw_portal_services.

### Environment Variables
This application has numerous environment variables to define the actual runtime environment. They can be defined in multiple locations depending on how you choose to run the application.

* Docker Compose: The substitution variables in docker-compose.yml should be defined in a .env file located in the project's root directory. The remaining variables need to be defined in .env files in the secrets/app/ and secrets/common/ directories. Samples of the later are included in the project.

* Command line or IDE: The variables found in the secrets/ samples should be placed into a single application.yml file located in the project's root directory. An addition application-it.yml file is also required in the same location to run unit tests via an IDE. It requires the environmentVariables listed in the **maven-failsafe-plugin** in this projects pom.xml file.

* Maven test execution: The pom contains all of the enviroment variable definitions needed. They are all generic values suitable for the automated testing and should not be used in any actual shared environments. ```mvn test``` will run just the unit tests, ```mvn verify``` will additionally run tests against the database. Running the tests in this way is completely self contained and no setup or local database is needed.

#### Definitions
* **CODES_SERVICE_URL** - URL of the service used to validate some of the query parameters. Be sure to include '/codes' suffix.
* **CODES_TIMEOUT_MILLI** - The timeout in milliseconds for calls to the codes service. This value is intentionally low as we want to fail fast if the codes service is not available. It is especially important when siting behind a WAF which breaks connections after (two minutes of) dead air.
* **KML_STYLE_URL** - URI of the styles WQP uses in KML output.
* **LOCAL_NETWORK_NAME** - The name of the local Docker Network you have created for using these images/containers.
* **MAX_RESULT_ROWS** - The maximum number of result rows for queries requesting sorted or xml output.
* **NLDI_TIMEOUT_MILLI** - The timeout in milliseconds for calls to the NLDI service. This value is intentionally low as we want to fail fast if the NLDI service is not available. It is especially important when siting behind a WAF which breaks connections after (two minutes of) dead air.
* **ROOT_LOG_LEVEL** - Logging level for the Java application.
* **SERVER_CONTEXT_PATH** - URL context for the application.
* **SERVER_PORT** - Port on which the application is run. Typically 8080 when running in a container. Can be any open port when using other run methods.
* **SITE_URL_BASE** - Several downloads contain URI's for related information. This variable is used to construct that URI.
* **SCHEMA_OWNER_USERNAME** - Role which will own the database objects. Only needed for automated testing.
* **SCHEMA_OWNER_PASSWORD** - Password for the **SCHEMA_OWNER_USERNAME** role. Only needed for automated testing.
* **DATABASE_ADDRESS** - Host name of the PosgreSQL Database.
* **DATABASE_NAME** - Name of the PostgreSQL Database used by the application.
* **DATABASE_PORT** - Port the PostgreSQL Database is listening on.
* **SCHEMA_NAME** - Database schema containing the database objects.
* **READ_ONLY_USERNAME** - The limited privilege role used by applications to access this schema.
* **READ_ONLY_PASSWORD** - Password for the **READ_ONLY_USERNAME** role.

## Docker
Included is a Docker Compose script to create a container including this project.

### Docker Network
A named Docker Network is required for local running of the container. Creating this network allows you to run all of the WQP locally in individual containers without having to maintain a massive Docker Compose script encompassing all of the required pieces. (It is also possible to run portions of the system locally against remote services.) The name of this network is provided by the __LOCAL_NETWORK_NAME__ environment variable. The following is a sample command for creating your own local network. In this example the name is wqp and the ip addresses will be 172.25.0.x

```
docker network create --subnet=172.25.0.0/16 wqp
```
