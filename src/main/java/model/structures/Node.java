package model.structures;

import model.statistics.NodeStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Node {
    final static Logger logger = LoggerFactory.getLogger(Node.class);
    private final int residentsNumber;
    private int illnessCases;
    private int healthyResidents;
    private boolean isVaxDelivered;
    private final double infectingParameter;
    private final int id;
    private int vaccinated;
    private int deliveryTime;

    public Node(int id, int residentsNumber, double infectingParameter) {
        this.id = id;
        this.residentsNumber = residentsNumber;
        this.illnessCases = 0;
        this.healthyResidents = residentsNumber - illnessCases;
        this.infectingParameter = infectingParameter;
        this.isVaxDelivered = false;
        this.vaccinated = 0;
        this.deliveryTime = 0;
    }

    public Node(Node node) {
        residentsNumber = node.residentsNumber;
        illnessCases = node.illnessCases;
        healthyResidents = node.healthyResidents;
        isVaxDelivered = node.isVaxDelivered;
        infectingParameter = node.infectingParameter;
        id = node.id;
        vaccinated = node.vaccinated;
        deliveryTime = node.deliveryTime;
    }

    public int getId() {
        return id;
    }

    public int getIllnessCases() {
        return illnessCases;
    }

    public boolean getIsVaxDelivered() {
        return isVaxDelivered;
    }

    private int calculateIllnessCases(int minutes) {
        double theExponent = ((double) minutes) / 60;
        return (int) Math.min(Math.floor(Math.pow(infectingParameter, theExponent)), residentsNumber);
    }

    public void updateNodeStatistics(int minutes) {
        if (isVaxDelivered)
            return;

        illnessCases = calculateIllnessCases(minutes);
        healthyResidents = residentsNumber - illnessCases;
    }

    public int predictIllnessCases(int deliveryTime) {
        logger.debug("In node {} predicted number of ill is {}/{}", id, calculateIllnessCases(deliveryTime), residentsNumber);
        if(isVaxDelivered)
            return illnessCases;

        return calculateIllnessCases(deliveryTime);
    }

    public void deliverVaccines(int minutes) {
        if(minutes != 0)
            updateNodeStatistics(minutes);

        isVaxDelivered = true;
        vaccinated = healthyResidents;
        deliveryTime = minutes;
    }

    public NodeStatistics getNodeStatsAtTime(int minute)
    {
        int deliveryTime = this.deliveryTime;
        boolean isVaxDelivered = minute >= deliveryTime;
        int illnessCases = calculateIllnessCases(isVaxDelivered ? deliveryTime : minute);
        int healthyResidents = residentsNumber - illnessCases;
        int vaccinated = isVaxDelivered ? (residentsNumber - illnessCases) : 0;

        return new NodeStatistics(residentsNumber, illnessCases, healthyResidents, isVaxDelivered, id, vaccinated, deliveryTime, minute);
    }

    public int getDeliveryTime()
    {
        return deliveryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return residentsNumber == node.residentsNumber && illnessCases == node.illnessCases && healthyResidents == node.healthyResidents && isVaxDelivered == node.isVaxDelivered && infectingParameter == node.infectingParameter && id == node.id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(residentsNumber, illnessCases, healthyResidents, isVaxDelivered, infectingParameter, id);
    }
}
