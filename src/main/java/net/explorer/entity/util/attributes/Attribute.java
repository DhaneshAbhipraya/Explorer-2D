package net.explorer.entity.util.attributes;

public class Attribute {
    private double value;
    private final Attributes type;
    public Attribute(Attributes attribute) {
        this.type = attribute;
        this.value = 0;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Attributes getType() {
        return type;
    }
}
