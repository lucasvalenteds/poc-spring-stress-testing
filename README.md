# POC: Spring Stress Testing

It demonstrates how to run stress test against REST API using Gatling Java DSL.

The goal is to develop a REST API with an endpoint that runs any CPU intensive algorithm, such as cryptography
algorithms, and to have a suite of tests that verify what amount of load the infrastructure serving the service can
handle.

The test orchestration is managed by Gatling with configuration obtained from the environment. The test suite
passes if the mean response time is below a threshold and if at least N percent of requests were responded with a given
status code. The test suite result is presented in the terminal and HTML report generated automatically.

## How to run

| Description      | Command                |
|:-----------------|:-----------------------|
| Run unit tests   | `./gradlew test`       |
| Run application  | `./gradlew bootRun`    |
| Run stress tests | `./gradlew gatlingRun` |

## Preview

Gatling Gradle plugin report:

```text
$ ./gradlew gatlingRun

> Task :gatlingRun
Simulation com.example.computation.ComputationSimulation started...

================================================================================
2022-07-09 19:02:20                                           5s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=20     KO=0     )
> cpuBound                                                 (OK=20     KO=0     )

---- CPU Bound Computation -----------------------------------------------------
[-----------------------                                                   ]  0%
          waiting: 104    / active: 46     / done: 0     
================================================================================


================================================================================
2022-07-09 19:02:25                                          10s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=45     KO=0     )
> cpuBound                                                 (OK=45     KO=0     )

---- CPU Bound Computation -----------------------------------------------------
[------------------------------------------------                          ]  0%
          waiting: 54     / active: 96     / done: 0     
================================================================================


================================================================================
2022-07-09 19:02:30                                          15s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=70     KO=0     )
> cpuBound                                                 (OK=70     KO=0     )

---- CPU Bound Computation -----------------------------------------------------
[------------------------------------------------------------------------- ]  0%
          waiting: 4      / active: 146    / done: 0     
================================================================================


================================================================================
2022-07-09 19:02:35                                          20s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=95     KO=0     )
> cpuBound                                                 (OK=95     KO=0     )

---- CPU Bound Computation -----------------------------------------------------
          active: 196    / done: 0     
================================================================================


================================================================================
2022-07-09 19:02:40                                          25s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=120    KO=0     )
> cpuBound                                                 (OK=120    KO=0     )

---- CPU Bound Computation -----------------------------------------------------
          active: 246    / done: 0     
================================================================================


================================================================================
2022-07-09 19:02:45                                          30s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=145    KO=0     )
> cpuBound                                                 (OK=145    KO=0     )

---- CPU Bound Computation -----------------------------------------------------
          active: 295    / done: 0     
================================================================================


================================================================================
2022-07-09 19:02:46                                          30s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=150    KO=0     )
> cpuBound                                                 (OK=150    KO=0     )

---- CPU Bound Computation -----------------------------------------------------
          active: 300    / done: 0     
================================================================================

Simulation com.example.computation.ComputationSimulation completed in 30 seconds
Parsing log file(s)...
Parsing log file(s) done
Generating reports...

================================================================================
---- Global Information --------------------------------------------------------
> request count                                        150 (OK=150    KO=0     )
> min response time                                   1011 (OK=1011   KO=-     )
> max response time                                   1389 (OK=1389   KO=-     )
> mean response time                                  1088 (OK=1088   KO=-     )
> std deviation                                         62 (OK=62     KO=-     )
> response time 50th percentile                       1073 (OK=1073   KO=-     )
> response time 75th percentile                       1105 (OK=1105   KO=-     )
> response time 95th percentile                       1214 (OK=1214   KO=-     )
> response time 99th percentile                       1303 (OK=1303   KO=-     )
> mean requests/sec                                  4.839 (OK=4.839  KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                             0 (  0%)
> t ≥ 800 ms <br> t < 1200 ms                          140 ( 93%)
> t ≥ 1200 ms                                           10 (  7%)
> failed                                                 0 (  0%)
================================================================================

Global: percentage of successful events is greater than or equal to 95.0 : true
Global: mean of response time is less than 5000.0 : true
```
