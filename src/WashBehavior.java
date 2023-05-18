abstract class WashBehavior { // Strategy pattern implementation for intern wash behavior. Strategies Chemical, ElbowGrease, and Detailed

    public abstract void Wash(Vehicle v, Intern i, FNCD f); // vehicle input?
    protected int csChance; // clean to sparkling probability
    protected int cdChance; // clean to dirty probability
    protected int ccChance; // clean stays the same probability
    protected int dsChance; // dirty to sparkling probability
    protected int dcChance; // dirty to clean probability
    protected int ddChance; // dirty stays the same probability

    public int getCsChance() {
        return csChance;
    }

    public void setCsChance(int csChance) {
        this.csChance = csChance;
    }

    public int getCdChance() {
        return cdChance;
    }

    public void setCdChance(int cdChance) {
        this.cdChance = cdChance;
    }

    public int getCcChance() {
        return ccChance;
    }

    public void setCcChance(int ccChance) {
        this.ccChance = ccChance;
    }

    public int getDsChance() {
        return dsChance;
    }

    public void setDsChance(int dsChance) {
        this.dsChance = dsChance;
    }

    public int getDcChance() {
        return dcChance;
    }

    public void setDcChance(int dcChance) {
        this.dcChance = dcChance;
    }

    public int getDdChance() {
        return ddChance;
    }

    public void setDdChance(int ddChance) {
        this.ddChance = ddChance;
    }
}
