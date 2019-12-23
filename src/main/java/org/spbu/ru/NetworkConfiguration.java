package org.spbu.ru;

public class NetworkConfiguration {

    double alpha;
    double probability;
    int maxNumberSteps;
    int maxDelay;

    NetworkConfiguration(double alpha, double probability, int maxNumberSteps, int maxDelay) {
        this.alpha = alpha;
        this.probability = probability;
        this.maxDelay = maxDelay;
        this.maxNumberSteps = maxNumberSteps;
    }
}