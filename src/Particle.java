
/**
 * @author nikla_000
 */
public class Particle
{
    // Particle's coordinates
    private float xCoord;
    private float yCoord;

    // Particle's properties
    private float width;
    private float height;

    /**
     * Constructs a new particle with xy coordinates and a given height and length
     * @param x coordinates of the particle
     * @param y coordinates particle
     * @param w Width of the particle
     * @param h height of the particle
     */
    public Particle (float x, float y, float w, float h)
    {
        xCoord = x;
        yCoord = y;
        width = w;
        height = h;
    }

    /**
     * Gets the current x coordinates
     * @return x coordinates to return
     */
    public float getXCoord ()
    {
        return xCoord;
    }

    /**
     * Gets the current y coordinates
     * @return y coordinates to return
     */
    public float getYCoord ()
    {
        return yCoord;
    }

    /**
     * Get the width of the particle
     * @return the width of the particle
     */
    public float getWidth ()
    {
        return width;
    }

    /**
     * Gets the height of the particle
     * @return the height of the particle
     */
    public float getHeight ()
    {
        return height;
    }

    /**
     * Move the particle1 in 1D
     * @param x direction
     */
    public void move1D (float x)
    {
        xCoord += x;
    }

    public Particle ()
    {
        xCoord = 200;
        yCoord = 200;
        width = 20;
        height = 20;
    }

    /**
     * Move the particle1 in 2D
     * @param x direction
     * @param y direction
     */
    public void move2D (float x, float y)
    {
        xCoord += x;
        yCoord += y;
    }
}