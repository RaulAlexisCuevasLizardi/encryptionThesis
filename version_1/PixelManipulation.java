import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class PixelManipulation extends JFrame {

	static BufferedImage img = null;
	static Picture pic;
	static ARGBPixel pixels [][];
	static File f = null;
	static Scanner kb;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 * @throws PixelValueException 
	 */
	public static void main(String[] args) throws PixelValueException {
		kb = new Scanner(System.in);
		pic = new Picture("C:\\Users\\841111795\\Desktop\\images.png");
		System.out.println(pic.P_ValueToString());
		PictureEncryptor c = new PictureEncryptor(pic);
		
		System.out.println("Enter the file name of the picture that will be encrypted.");
		String outputFilename = kb.nextLine();
		c.Encrypt();
		c.ExportPicture(outputFilename);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PixelManipulation frame = new PixelManipulation();
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
	public PixelManipulation() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Photo Encryption");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 609, 587);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Browse...");
		btnNewButton.setBounds(10, 518, 95, 47);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Search for a picture");
		lblNewLabel.setLabelFor(btnNewButton);
		lblNewLabel.setBounds(10, 479, 137, 23);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("to encrypt");
		lblNewLabel_1.setBounds(10, 498, 67, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Export ");
		btnNewButton_1.setBounds(498, 518, 101, 47);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 34, 589, 423);
		panel.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Export encrypted ");
		lblNewLabel_2.setBounds(508, 466, 87, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("       photo to new ");
		lblNewLabel_3.setBounds(508, 481, 101, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("                location");
		lblNewLabel_4.setBounds(508, 498, 87, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Photo that needs encryption may be dragged to the box below or you may use the browse button");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(10, 11, 575, 14);
		panel.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(139, 545, 309, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblWriteTheTitle = new JLabel("Title of your new photo when exported...");
		lblWriteTheTitle.setBounds(139, 518, 222, 14);
		panel.add(lblWriteTheTitle);
	}
}

