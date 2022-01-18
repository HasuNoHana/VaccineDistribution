package model.structures;

public class Node {
    private int residentsNumber;
    private int illnessCases;
    private int healthyNumber;
    private boolean isVaccinated;
    private int parameter;
    private int illnessCasesStep;

    public Node(int residentsNumber, int parameter) {
        this.residentsNumber = residentsNumber;
        this.illnessCases = 0;
        this.healthyNumber = residentsNumber - illnessCases;
        this.parameter = parameter;
        this.isVaccinated = false;
        illnessCasesStep = 0;
        updateIllnessCases();
    }

    public void updateIllnessCases()
    {
        if(isVaccinated)
            return;

        illnessCases = Math.min((int) Math.pow(parameter, illnessCasesStep++), residentsNumber);
        healthyNumber = residentsNumber - illnessCases;
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

    public double getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }


}
