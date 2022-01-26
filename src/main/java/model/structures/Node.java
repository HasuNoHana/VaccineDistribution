package model.structures;

import java.util.Objects;

public class Node {
    private final int residentsNumber;
    private int illnessCases;
    private int healthyResidents;
    private boolean isVaccinated;
    private final int infectingParameter;
    private final int id;
    private int vaccinated;
    private int deliveryTime;

    public Node(int id, int residentsNumber, int infectingParameter) {
        this.id = id;
        this.residentsNumber = residentsNumber;
        this.illnessCases = 0;
        this.healthyResidents = residentsNumber - illnessCases;
        this.infectingParameter = infectingParameter;
        this.isVaccinated = false;
        this.vaccinated = 0;
        this.deliveryTime = 0;
    }

    public Node(Node node) {
        residentsNumber = node.residentsNumber;
        illnessCases = node.illnessCases;
        healthyResidents = node.healthyResidents;
        isVaccinated = node.isVaccinated;
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

    public boolean getIsVaccinated() {
        return isVaccinated;
    }

    private int calculateIllnessCases(int minutes) {
        double theExponent = ((double) minutes) / 60;
        return (int) Math.min(Math.floor(Math.pow(infectingParameter, theExponent)), residentsNumber);
    }

    public void updateNodeStatistics(int minutes) {
        if (isVaccinated)
            return;

        illnessCases = calculateIllnessCases(minutes);
        healthyResidents = residentsNumber - illnessCases;
    }

    public int predictIllnessCases(int minutes) {
        return calculateIllnessCases(minutes);
    }

    public void deliverVaccines(int minutes) {
        updateNodeStatistics(minutes);
        isVaccinated = true;
        vaccinated = healthyResidents;
        deliveryTime = minutes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return residentsNumber == node.residentsNumber && illnessCases == node.illnessCases && healthyResidents == node.healthyResidents && isVaccinated == node.isVaccinated && infectingParameter == node.infectingParameter && id == node.id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(residentsNumber, illnessCases, healthyResidents, isVaccinated, infectingParameter, id);
    }
}
