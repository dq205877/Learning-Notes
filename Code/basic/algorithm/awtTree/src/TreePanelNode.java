import java.awt.*;
public class TreePanelNode extends TreeNode {
    private int x, y, width, height;
    private Object value;
    private TreePanelNode parent;
    public TreePanelNode (TreePanelNode parent, int x, int y, int width, int height, Object value) {
        super(parent);
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = width;
        this.height = height;
        this.value = value;
    }
    public void setLocation (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setSize (int width, int height) {
        this.width = width;
        this.height = height;
    }
    public Rectangle getBounds () {
        return new Rectangle(x, y, width, height);
    }
    public Object getValue () {
        return value;
    }
    public Point getLocation () {
        return new Point(x, y);
    }
    public int getWidth () {
        return width;
    }
    public int getHeight () {
        return height;
    }
    public void setValue (Object value) {
        this.value = value;
    }
    public TreePanelNode getParent () {
        return parent;
    }
}