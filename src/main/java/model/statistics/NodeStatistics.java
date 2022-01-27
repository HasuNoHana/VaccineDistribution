package model.statistics;

public class NodeStatistics {
    private final int residentsNumber;
    private final int illnessCases;
    private final int healthyResidents;
    private final boolean isVaxDelivered;
    private final int nodeId;
    private final int vaccinated;
    private final int deliveryTime;
    private final int minute;

    public NodeStatistics(int residentsNumber, int illnessCases, int healthyResidents, boolean isVaxDelivered, int nodeId, int vaccinated, int deliveryTime, int minute) {
        this.residentsNumber = residentsNumber;
        this.illnessCases = illnessCases;
        this.healthyResidents = healthyResidents;
        this.isVaxDelivered = isVaxDelivered;
        this.nodeId = nodeId;
        this.vaccinated = vaccinated;
        this.deliveryTime = deliveryTime;
        this.minute = minute;
    }

    public int getResidentsNumber() {
        return residentsNumber;
    }

    public int getIllnessCases() {
        return illnessCases;
    }

    public int getHealthyResidents() {
        return healthyResidents;
    }

    public boolean isVaxDelivered() {
        return isVaxDelivered;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getVaccinated() {
        return vaccinated;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
