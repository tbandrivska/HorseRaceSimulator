package Part2;

public class CustomHorse extends Part1.Horse {
    private String name;
    private String breed;
    private String coatColour;
    private char symbol;
    private String saddleType;
    private String horseshoeType;
    private double confidence; // affects fall probability
    private double speed;     // affects movement frequency
    private double stamina;   // affects how far the horse can go before slowing

    public CustomHorse(String name, String breed, String coatColour, char symbol, String saddleType, String horseshoeType, double confidence) {
        this.name = name;
        this.breed = breed;
        this.coatColour = coatColour;
        this.symbol = symbol;
        this.saddleType = saddleType;
        this.horseshoeType = horseshoeType;
        this.confidence = confidence;
    }


public double getSpeed(){ return speed; }
    public double getStamina(){ return stamina; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getCoatColour() { return coatColour; }
    public char getSymbol() { return symbol; }
    public String getSaddleType() { return saddleType; }
    public String getHorseshoeType() { return horseshoeType; }
    public double getConfidence() { return confidence; }

    @Override
    public String toString() {
        return "Horse " + name + " (" + symbol + ") - " + breed + ", " + coatColour + ", Saddle: " + saddleType + ", Shoes: " + horseshoeType + ", Confidence: " + confidence;
    }
}
