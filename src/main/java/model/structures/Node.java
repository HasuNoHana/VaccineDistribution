package model.structures;

import java.util.Objects;

public class Node {
    private int residentsNumber;
    private int illnessCases;
    private int healthyNumber;
    private boolean isVaccinated;
    private int infectingParameter;
    private int id;

    public Node(int id, int residentsNumber, int infectingParameter) {
        this.id = id;
        this.residentsNumber = residentsNumber;
        this.illnessCases = 0;
        this.healthyNumber = residentsNumber - illnessCases;
        this.infectingParameter = infectingParameter;
        this.isVaccinated = false;
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

    public int getId()
    {
        return id;
    }

    public int getIllnessCases() {
        return illnessCases;
    }


    public boolean getIsVaccinated() {
        return isVaccinated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return residentsNumber == node.residentsNumber && illnessCases == node.illnessCases && healthyNumber == node.healthyNumber && isVaccinated == node.isVaccinated && infectingParameter == node.infectingParameter && id == node.id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(residentsNumber, illnessCases, healthyNumber, isVaccinated, infectingParameter, id);
    }
}
