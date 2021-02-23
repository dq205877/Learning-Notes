import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
public class TreePanel extends JPanel {
    private Vector<TreePanelNode> nodes;
    private Vector<Integer> selectedNodes;
    private int tmpX, tmpY;
    private int editingNode = -1;
    private int addingParent = -2;
    private int mouseX, mouseY;
    public TreePanel () {
        nodes = new Vector<TreePanelNode>();
        selectedNodes = new Vector<Integer>();
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent me) {
                tmpX = me.getX();
                tmpY = me.getY();


                if (nodes.size() == 0) {
                    addingParent = -1;
                }
                int i = 0;
                for (; i<nodes.size(); i++) {
                    if (nodes.get(i).getBounds().contains(mouseX, mouseY)) {
                        if (me.isControlDown()) {
                            if (!selectedNodes.contains(i))
                                selectedNodes.add(i);
                            else
                                selectedNodes.removeElement(i);
                        } else if (!selectedNodes.contains(i)) {
                            selectedNodes = new Vector<Integer>();
                            selectedNodes.add(i);
                        }
                        if (me.getClickCount() == 2) {
                            nodes.get(i).setValue("");
                            editingNode = i;
                        }
                        break;
                    }
                }
                if (i == nodes.size())
                    if (!me.isControlDown())
                        selectedNodes = new Vector<Integer>();
                repaint();
            }
            public void mouseReleased (MouseEvent me) {
                addingParent = -2;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged (MouseEvent me) {
                mouseX = me.getX();
                mouseY = me.getY();
                int i = 0;
                for (; i<selectedNodes.size(); i++) {
                    if (nodes.get(selectedNodes.get(i)).getBounds().contains(mouseX, mouseY))
                        break;
                }
                if (i != selectedNodes.size()) {
                    if (me.isAltDown() && addingParent != -3) {
                        addingParent = selectedNodes.get(i);
                    } else {
                        for (i=0; i<selectedNodes.size(); i++) {
                            nodes.get(selectedNodes.get(i)).setLocation(nodes.get(selectedNodes.get(i)).getLocation().x + mouseX - tmpX,
                                    nodes.get(selectedNodes.get(i)).getLocation().y + mouseY - tmpY);
                        }
                        tmpX = mouseX;
                        tmpY = mouseY;
                    }
                }
                repaint();
            }
            public void mouseMoved (MouseEvent me) {
                mouseX = me.getX();
                mouseY = me.getY();
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyTyped (KeyEvent ke) {
                if (editingNode != -1) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER)
                        editingNode = -1;
                    else
                        nodes.get(editingNode).setValue(nodes.get(editingNode).getValue().toString()+ke.getKeyChar());
                    repaint();
                }
            }
        });
    }
    public void paint (Graphics g) {
        super.paint(g);
        FontMetrics fm = g.getFontMetrics();
        if (addingParent > -2) {
            String str = "node" + nodes.size();
            int width = fm.stringWidth(str);
            int height = fm.getHeight();
            TreePanelNode pnt = null;
            if (addingParent != -1)
                pnt = nodes.get(addingParent);
            nodes.add(new TreePanelNode(pnt, mouseX-width/2-10, mouseY-height/2-20, width+20, height+40, str));
            addingParent = -3;
            selectedNodes = new Vector<Integer>();
            selectedNodes.add(nodes.size()-1);
        }
        for (int i=0; i<nodes.size(); i++) {
            String str = nodes.get(i).getValue().toString();
            int width = fm.stringWidth(str);
            int height = fm.getHeight();
            nodes.get(i).setSize(width + 20, height + 40);
            if (selectedNodes.contains(i))
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);
            if (nodes.get(i).getParent() != null)
                g.drawLine(nodes.get(i).getLocation().x+nodes.get(i).getWidth()/2,
                        nodes.get(i).getLocation().y+nodes.get(i).getHeight()/2,
                        nodes.get(i).getParent().getLocation().x+nodes.get(i).getParent().getWidth()/2,
                        nodes.get(i).getParent().getLocation().y+nodes.get(i).getParent().getHeight()/2);
            g.drawString(str, nodes.get(i).getLocation().x + 10, nodes.get(i).getLocation().y + 20);
            g.drawRect(nodes.get(i).getLocation().x, nodes.get(i).getLocation().y, nodes.get(i).getWidth(), nodes.get(i).getHeight());
        }
        grabFocus();
    }
}