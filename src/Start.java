import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class Start extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 639);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		MyButton btnNewButton = new MyButton("New button");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.setBackground(new Color(51, 102, 255));
		
		
		btnNewButton.setHoverBackgroundColor(new Color(3, 59, 90).brighter());
		btnNewButton.setPressedBackgroundColor(new Color(3, 59, 90));
		
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
			
		});
		btnNewButton.setBackground(new Color(51, 102, 255));
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
	
	//btnNewButton.setBorderPainted(false);

}
