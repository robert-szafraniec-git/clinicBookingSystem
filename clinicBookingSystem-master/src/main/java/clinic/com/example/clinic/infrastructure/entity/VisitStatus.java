package clinic.com.example.clinic.infrastructure.entity;

public enum VisitStatus {

    PLAN("planowana"),
    CONFIRMED("potwierdzona"),
    COMPLETED("zakończona"),
    NOT_TAKEN("nieaktualna");

    public String description_PL;


    VisitStatus(String description_PL) {
        this.description_PL = description_PL;
    }

    public String getDescription_PL() {
        return description_PL;
    }
}
