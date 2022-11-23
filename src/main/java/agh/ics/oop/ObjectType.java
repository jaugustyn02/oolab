package agh.ics.oop;

public enum ObjectType implements Comparable<ObjectType>{
    Grass,
    Animal;
    private final static ObjectType[] values = values();

    public ObjectType getValue(){
        return values[this.ordinal()];
    }

    public String toString(){
        return switch (this){
            case Grass -> "Grass";
            case Animal -> "Animal";
        };
    }
}
