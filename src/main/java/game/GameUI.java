package game;
/**
 * 
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import tictactoe.TTTView;

/**
* 
*/
public class GameUI extends JFrame{
    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;
    //private Player player1;
    //private Player player2;

    /** 
    * Constructor method
    */
    public GameUI(String title){
        // call the superclass constructor
        super();    
        // set the size, title and default close of the jframe
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(title);
        makeMenu();
        setJMenuBar(menuBar);
        gameContainer = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // make a new label to store messages
        
        add(gameContainer, BorderLayout.CENTER);
        add(makeButtonPanel(),BorderLayout.EAST);
        start();

    }

    private JPanel makeButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeXOButton());
        buttonPanel.add(makeNumTTTButton());
        return buttonPanel;
    }

    private JPanel startupMessage(){
        JPanel temp = new JPanel();
        temp.add(new JLabel("Time to play some TicTacToe games!"));
        return temp;

    }

    private JButton makeXOButton(){
        JButton button = new JButton("Play X and O");
        button.addActionListener(e->gameXO());
        return button;
    }

    private JButton makeNumTTTButton(){
        JButton button = new JButton("Play Numerical TicTacToe Game");
        button.addActionListener(e->numTTTGame());
        return button;
    }

    /** 
    * Add menu bar functionality
    */
    public void makeMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("A submenu");
        JMenuItem item = new JMenuItem("an item (e.g. save)");
        menu.add(item);
        menuBar.add(menu);
        item.addActionListener(e->saveSomething());
    }

    /** 
    * Start the GUI and display current GUI
    */
    public void start(){
        gameContainer.removeAll();
        gameContainer.add(startupMessage());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /**
    *   method to prompt for saving a file 
    */
    protected void saveSomething(){
        JOptionPane.showMessageDialog(null,"This should prompt for save files"); 
    }

    /** 
    * method to create X and O view and game
    */
    protected void gameXO(){
        gameContainer.removeAll();
        gameContainer.add(new TTTView(3,3,this,1));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /** 
    * Method to create numerical TicTacToe view and game
    */
    protected void numTTTGame(){
    gameContainer.removeAll();
    gameContainer.add(new TTTView(3,3,this,2));
    getContentPane().repaint();
    getContentPane().revalidate();
    pack(); 
    }

    
public static void main(String[] args){
    GameUI example = new GameUI("TicTacToe Games");
    example.setVisible(true);

}

} 
