public class Horse {
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence; // between 0.0 and 1.0

    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        setConfidence(horseConfidence); // uses setter for validation
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }


    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public boolean hasFallen() {
        return hasFallen;
    }

    public double getConfidence() {
        return confidence;
    }

    // === Mutator Methods (Setters) ===
    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }

    public void setConfidence(double newConfidence) {
        if (newConfidence < 0.0) {
            this.confidence = 0.0;
        } else if (newConfidence > 1.0) {
            this.confidence = 1.0;
        } else {
            this.confidence = newConfidence;
        }
    }

    public void moveForward() {
        if (!hasFallen) {
            distanceTravelled += 1;
        }
    }

    public void fall() {
        hasFallen = true;
    }

    public void goBackToStart() {
        distanceTravelled = 0;
        hasFallen = false;
    }
}

