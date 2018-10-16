# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html). (Patch version X.Y.0 is implied if not specified.)

## [Unreleased]
###Added
- ActivityGroupURL to the activityAll dataProfile.
- resultPhysChem dataProfile.
- resultPrimary dataProfile.
- resultBroad dataProfile.
- added organization summary.
- added period of record data profile.

### Changed
- Station summary service returns only sites that have summary information.
- Fixed ActivityFileUrl, ActivityMetricURL, and ActivityAttachedBinaryObject logic errors.
- Refactored Summary Station mapper to use new format of station_sum table.
- Changed use of 'summarystation' to summaryMonitoringLocation.' 

## [0.15.0]
### Added
- Added station summary service

### Fixed
- Post bodies now correctly decode 'pCode' and 'bBox'
- Removed the use_hash hint for geospatial result queries

## [0.14.0]
### Added
- Converted project to Spring Boot 2.
- OAuth2 Security for Internal App.

## [0.13.0] - 2018-03-13
### Changed
- Fixed issue with inappropriate parameters being applied to retrieval.
- Refactor of tests in support of this bug fix.

## [0.12.0] - 2018-02-22
### Added
- Project Monitoring Location Weighting Download.

## [0.11.0] - 2018-02-16
### Added
- Project, Monitoring Location, Activity, and Result Attachement Downloads.
### Changed
- Ignore empty/null parameters.

## [0.10.0] - 2018-01-25
### Added
- Project Data Download
### Changed
- Parameter handling refactor.
- Dependency Updates.

## [0.9.0] - 2017-12-15
### Added
- This changelog file.

### Changed
- Updated dependencies.

## 0.8 - 2017-11-29
### Changed
- Migrated repository to NWQMC

[Unreleased]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.15.0...master
[0.15.0]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.14.0...wqp-0.15.0
[0.14.0]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.13.0...wqp-0.14.0
[0.13.0]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.12.0...wqp-0.13.0
[0.12.0]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.11.0...wqp-0.12.0
[0.11.0]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.10.0...wqp-0.11.0
[0.10.0]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.9.0...wqp-0.10.0
[0.9.0]: https://github.com/NWQMC/WQP-WQX-Services/compare/wqp-0.8...wqp-0.9.0
