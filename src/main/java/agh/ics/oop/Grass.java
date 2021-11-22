package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {
    private final GrassField map;

    public Grass(GrassField map, Vector2d position){
        super(position);
        this.priority = 1;
        this.map = map;
    }

    @Override
    public void onInteraction(IMapElement element){
        if (element instanceof Animal) this.map.growRandomGrass();
        this.map.removeElement(this.getPosition());
    }

    @Override
    public String toString(){
        return "*";
    }
}
