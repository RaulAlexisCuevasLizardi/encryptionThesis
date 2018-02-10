import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PixelManipulation extends JFrame {

	static BufferedImage img = null;
	static Picture pic;
	static ARGBPixel pixels [][];
	static File f = null;
	static Scanner kb;
	private JPanel contentPane;
	private JTextField newPhotoTitleTextField;
	@SuppressWarnings("unused")
	private JLabel photoPanel;
	private JLabel photoLabel;

	/**
	 * Launch the application.
	 * @throws PixelValueException 
	 */
	public static void main(String[] args) throws PixelValueException {
		kb = new Scanner(System.in);
		pic = null;
		PictureEncryptor c = null;
		//		System.out.println("Enter the file name of the picture that will be encrypted.");
		//		String outputFilename = kb.nextLine();
		//		c.Encrypt();
		//		c.ExportPicture(outputFilename);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PixelManipulation frame = new PixelManipulation();
					frame.setVisible(true);
					System.out.println("Window loaded.");
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

		JPanel fullPanel = new JPanel();
		fullPanel.setBounds(0, 0, 609, 587);
		contentPane.add(fullPanel);
		fullPanel.setLayout(null);

		JPanel photoPanel = new JPanel();
		photoPanel.setBackground(Color.WHITE);
		photoPanel.setBounds(10, 36, 589, 432);
		fullPanel.add(photoPanel);
		photoPanel.setLayout(null);
		photoLabel = new JLabel();
		photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		photoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		photoLabel.setText("Your photo will be displayed here");
		photoLabel.setBounds(0, 0, 589, 432);
		photoPanel.add(photoLabel);
		
		photoLabel.setForeground(Color.BLACK);
		photoLabel.setBackground(Color.WHITE);
		
		JButton browseButton = new JButton("Browse...");
		
		browseButton.setBounds(10, 518, 95, 47);
		fullPanel.add(browseButton);

		JLabel searchForPictureLabel = new JLabel("Search for a picture");
		searchForPictureLabel.setLabelFor(browseButton);
		searchForPictureLabel.setBounds(10, 479, 137, 23);
		fullPanel.add(searchForPictureLabel);

		JLabel toEncryptLabel = new JLabel("to encrypt");
		toEncryptLabel.setBounds(10, 498, 67, 14);
		fullPanel.add(toEncryptLabel);
		

		JButton exportButton = new JButton("Export ");
		exportButton.setBounds(498, 542, 101, 23);
		fullPanel.add(exportButton);

		JLabel exportEncryptedLabel = new JLabel("Export encrypted ");
		exportEncryptedLabel.setBounds(498, 468, 101, 14);
		fullPanel.add(exportEncryptedLabel);

		JLabel photoToNewLabel = new JLabel("       photo to new ");
		photoToNewLabel.setBounds(498, 483, 101, 14);
		fullPanel.add(photoToNewLabel);

		JLabel locationLabel = new JLabel("                location");
		locationLabel.setBounds(498, 500, 101, 14);
		fullPanel.add(locationLabel);

		JLabel instructionsLabel = new JLabel("Photo that needs encryption may be dragged to the box below or you may use the browse button");
		instructionsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		instructionsLabel.setBounds(10, 11, 575, 14);
		fullPanel.add(instructionsLabel);

		newPhotoTitleTextField = new JTextField();
		newPhotoTitleTextField.setToolTipText("New title");
		newPhotoTitleTextField.setBounds(139, 545, 309, 20);
		fullPanel.add(newPhotoTitleTextField);
		newPhotoTitleTextField.setColumns(10);

		JLabel newPhotoTitleLabel = new JLabel("Title of your new photo when exported...");
		newPhotoTitleLabel.setBounds(139, 520, 245, 14);
		fullPanel.add(newPhotoTitleLabel);
		
		JButton encryptButton = new JButton("Encrypt");
		encryptButton.setBounds(498, 518, 101, 23);
		fullPanel.add(encryptButton);
		
		

		/**
		 * make events down here
		 */
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pic.export(newPhotoTitleTextField.getText());
			}
		});
		
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);

				File file = null;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					//This is where a real application would open the file.
					System.out.println("Opening: " + file.getName() + ".\n File path + " + file.getPath());
					pic = new Picture(file.getPath());
				} else {
					System.out.println("Open command cancelled by user.\n");

				}
			
				if(pic != null) {
					Image dimg = pic.getImage().getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(),
							Image.SCALE_SMOOTH);
					ImageIcon imageIcon = new ImageIcon(dimg);
					photoLabel.setText("");
					photoLabel.setIcon(imageIcon);
					System.out.println("Image added");
				}
			}
		});
	}
}

