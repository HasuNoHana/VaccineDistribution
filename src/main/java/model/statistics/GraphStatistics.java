package model.statistics;

public class GraphStatistics {
    private final int residentsNumber;
    private final int illnessCases;
    private final int healthyResidents;
    private final int vaccinated;
    private final int minute;

    public GraphStatistics(int residentsNumber, int illnessCases, int healthyResidents, int vaccinated, int minute) {
        this.residentsNumber = residentsNumber;
        this.illnessCases = illnessCases;
        this.healthyResidents = healthyResidents;
        this.vaccinated = vaccinated;
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

    public int getVaccinated() {
        return vaccinated;
    }

    public int getMinute() {
        return minute;
    }
}
