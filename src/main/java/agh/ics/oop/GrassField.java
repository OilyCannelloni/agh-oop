package agh.ics.oop;

import java.lang.Math;


public class GrassField extends AbstractWorldMap {
    private final Vector2d origin;
    private final int nGrassTiles;
    private final int grassGrowthBound;
    private final Vector2d grassGrowthBoundingBox;

    public GrassField(int nGrassTiles){
        super();
        this.origin = new Vector2d(0, 0);
        this.nGrassTiles = nGrassTiles;
        this.grassGrowthBound = (int) Math.floor(Math.sqrt(10*this.nGrassTiles)) + 1;
        this.grassGrowthBoundingBox = new Vector2d(this.grassGrowthBound, this.grassGrowthBound);
        this.generateGrass();
    }

    @Override
    protected boolean isWithinBounds(Vector2d position){
        return position.follows(this.origin);
    }

    public void growRandomGrass() {
        Vector2d regrowTile = this.getRandomEmptyTile(new Rect2D(this.origin, this.grassGrowthBoundingBox));
        IMapElement grass = new Grass(this, regrowTile);
        if (!this.placeElement(grass)) System.out.printf("Cannot place grass at %s!", regrowTile);
    }

    private void generateGrass() {
        int[] range = new int[this.grassGrowthBound*this.grassGrowthBound];
        for (int i = 0; i < this.grassGrowthBound*this.grassGrowthBound; i++) range[i] = i;
        shuffleArray(range, this.nGrassTiles);
        for (int i = 0; i < nGrassTiles; i++) {
            Vector2d tilePosition = new Vector2d(
                    range[i] % this.grassGrowthBound,
                    range[i] / this.grassGrowthBound
            );
            this.placeElement(new Grass(this, tilePosition), tilePosition);
        }
    }
}
