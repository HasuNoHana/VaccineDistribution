package model.structures;

import java.util.Objects;

public class Node {
    private int residentsNumber;
    private int illnessCases;
    private int healthyNumber;
    private boolean isVaccinated;
    private int infectingParameter;
    private int id;
    private int visitIteration;

    public Node(int id, int residentsNumber, int infectingParameter) {
        this.residentsNumber = residentsNumber;
        this.illnessCases = 0;
        this.healthyNumber = residentsNumber - illnessCases;
        this.infectingParameter = infectingParameter;
        this.isVaccinated = false;
        updateIllnessCases(0);
    }

    public Node(Node node)
    {
        residentsNumber = node.residentsNumber;
        illnessCases = node.illnessCases;
        healthyNumber = node.healthyNumber;
        isVaccinated = node.isVaccinated;
        infectingParameter = node.infectingParameter;
        id = node.id;
    }

    public void updateIllnessCases(int iterationNumber)
    {
        if(isVaccinated)
            return;

        illnessCases = Math.min((int) Math.pow(infectingParameter, iterationNumber), residentsNumber);
        healthyNumber = residentsNumber - illnessCases;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public int getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(int residentsNumber) {
        this.residentsNumber = residentsNumber;
    }

    public int getIllnessCases() {
        return illnessCases;
    }

    public void setIllnessCases(int illnessCases) {
        this.illnessCases = illnessCases;
    }

    public int getIllnessCasesPrediction(int iterationNumber)
    {
        return Math.min((int) Math.pow(infectingParameter, iterationNumber), residentsNumber);
    }

    public int getHealthyNumber() {
        return healthyNumber;
    }

    public void setHealthyNumber(int healthyNumber) {
        this.healthyNumber = healthyNumber;
    }

    public boolean getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public double getInfectingParameter() {
        return infectingParameter;
    }

    public void setInfectingParameter(int infectingParameter) {
        this.infectingParameter = infectingParameter;
    }

    public int getVisitIteration() {
        return visitIteration;
    }

    public void visit(int visitIteration)
    {
        this.visitIteration = visitIteration;
        this.isVaccinated = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return residentsNumber == node.residentsNumber && illnessCases == node.illnessCases && healthyNumber == node.healthyNumber && isVaccinated == node.isVaccinated && infectingParameter == node.infectingParameter && id == node.id && visitIteration == node.visitIteration;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(residentsNumber, illnessCases, healthyNumber, isVaccinated, infectingParameter, id, visitIteration);
    }
}
