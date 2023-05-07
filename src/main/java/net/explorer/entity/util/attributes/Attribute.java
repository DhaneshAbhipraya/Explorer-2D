package net.explorer.entity.util.attributes;

public class Attribute {
    private double value;
    public Attribute(Attributes attribute) {
        this.value = 0;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
