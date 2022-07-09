package com.example.computation;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.gatling.javaapi.core.Assertion;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

@SuppressWarnings("unused")
public class ComputationSimulation extends Simulation {

    private final Config configuration = ConfigFactory.load();

    private final HttpProtocolBuilder httpProtocol =
            http.baseUrl(configuration.getString("computations.serviceUrl"));

    private final ScenarioBuilder cpuBound =
            scenario("CPU Bound Computation").exec(
                    http("cpuBound")
                            .get(configuration.getString("computations.cpuBound.endpoint"))
                            .check(status().is(configuration.getInt("computations.cpuBound.successStatusCode")))
            );

    private final PopulationBuilder population =
            cpuBound.injectOpen(
                    constantUsersPerSec(configuration.getInt("computations.cpuBound.concurrentUsers"))
                            .during(Duration.ofSeconds(configuration.getInt("computations.cpuBound.duration")))
            );

    private final Assertion successRate =
            global().successfulRequests().percent().gte(configuration.getDouble("computations.cpuBound.successRate"));

    private final Assertion meanResponseTime =
            global().responseTime().mean().lt(configuration.getInt("computations.cpuBound.maximumMeanResponseTime"));

    {
        setUp(population)
                .assertions(successRate, meanResponseTime)
                .protocols(httpProtocol);
    }
}
