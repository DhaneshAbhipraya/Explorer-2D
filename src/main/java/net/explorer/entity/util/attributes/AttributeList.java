package net.explorer.entity.util.attributes;

import java.util.ArrayList;

public class AttributeList extends ArrayList<Attribute> {
    public double getValue(Attributes attr) {
        for (Attribute attribute : this) {
            if (attribute.getType().equals(attr)) {
                return attribute.getValue();
            }
        }
        return 0;
    }

    public void setValue(Attributes attr, double value) {
        for (Attribute attribute : this) {
            if (attribute.getType().equals(attr)) {
                attribute.setValue(value);
            }
        }
    }
}
