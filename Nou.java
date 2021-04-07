import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Nou {

	private JFrame frame;
	private JTextField textFieldSuma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nou window = new Nou();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Nou() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSuma = new JLabel("Suma:");
		lblSuma.setBounds(39, 106, 46, 14);
		frame.getContentPane().add(lblSuma);
		
		textFieldSuma = new JTextField();
		textFieldSuma.setBounds(95, 103, 195, 20);
		frame.getContentPane().add(textFieldSuma);
		textFieldSuma.setColumns(10);
		
		JButton btnRetragere = new JButton("Retragere");
		btnRetragere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int suma=Integer.parseInt(textFieldSuma.getText());
				
			}
		});
		btnRetragere.setBounds(135, 198, 89, 23);
		frame.getContentPane().add(btnRetragere);
	}
}
