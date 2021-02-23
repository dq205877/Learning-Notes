import javax.swing.*;
public class MainView extends JFrame {
    private TreePanel tp;
    public MainView () {
        super("TreePanel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        tp = new TreePanel();
        add(tp);
        setVisible(true);
    }

    /**
     * 按住 Alt 键拖动 Node 可以添加子节点。
     * 按住 Ctrl 键单击 node 可以多选。
     * 双击一个 node 然后可以从键盘输入 新的字符串，按回车结束
     * @param args
     */

    public static void main (String args[]) {
        new MainView();
    }
}