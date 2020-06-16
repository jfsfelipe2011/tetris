import java.awt.*;

public class Block {
    public Color color;
    public int shape = 0;
    public int x, y;
    public int spd = 2;
    public boolean collisionFloor = true;
    public boolean collision = false;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;

        int colorChance = Shape.random.nextInt(3);
        shape = Shape.random.nextInt(Shape.shapes.size());

        if (colorChance == 0) {
            color = Color.red;
        } else if (colorChance == 1) {
            color = Color.green;
        } else if (colorChance == 2) {
            color = Color.blue;
        }
    }

    public Block getCollision() {
        for (int i = 0; i < Game.blocks.size(); i++) {
            Block block = Game.blocks.get(i);

            if (block != this) {
                Rectangle me = new Rectangle(x, y, Shape.shapes.get(shape).width, Shape.shapes.get(shape).height);
                Rectangle other = new Rectangle(block.x, block.y, Shape.shapes.get(block.shape).width,
                        Shape.shapes.get(block.shape).height);

                if (me.intersects(other)) {
                    return block;
                }
            }
        }

        return null;
    }

    public void tick() {
        int hh = Shape.shapes.get(shape).height;

        if (collisionFloor) {
            Block collisor = getCollision();

            if (collisor == null) {
                y+=spd;
            } else {
                if (collisor.color == this.color) {
                    System.out.println("Ponto");

                    Game.player = new Player(0,0);
                    Game.blocks.remove(collisor);
                    Game.blocks.remove(this);

                    return;
                } else if (!collisor.collision || !collision) {
                    Game.player = new Player(0, 0);
                    Block blockClone = new Block(this.x, this.y);
                    blockClone.color = this.color;
                    blockClone.shape = this.shape;
                    blockClone.collision = true;
                    collisor.collision = true;
                    Game.blocks.add(blockClone);
                    Game.blocks.remove(this);
                }
            }
        }

        if (y + hh > 480) {
            y = 480 - hh;
            Block blockClone = new Block(this.x, this.y);
            blockClone.color = this.color;
            blockClone.shape = this.shape;
            blockClone.collisionFloor = false;
            Game.player = new Player(0, 0);
            Game.blocks.add(blockClone);
        }
    }

    public void render(Graphics g) {
        g.setColor(color);

        int ww = Shape.shapes.get(shape).width;
        int hh = Shape.shapes.get(shape).height;

        g.fillRect(x, y, ww, hh);
    }
}
