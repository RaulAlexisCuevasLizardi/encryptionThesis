package photoEncryption;
import java.awt.EventQueue;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class PixelManipulation extends JFrame {

	private static Picture pic;
	private static PictureEncryptor encryptor = null;
	private JPanel contentPane;
	private JTextField newPhotoTitleTextField;
	@SuppressWarnings("unused")
	private JPanel photoPanel;
	private JLabel photoLabel;
	private PrintWriter writer;

	/**
	 * Launch the application.
	 * @throws PixelValueException 
	 */
	public static void main(String[] args) throws PixelValueException {
		pic = null;
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

		photoPanel = new JPanel();
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

		JButton browseButton = new JButton("Browse...");
		browseButton.setToolTipText("Search for a picture to encrypt in your file directory");
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
		exportButton.setToolTipText("Export your picture into a directory of your choosing");
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

		newPhotoTitleTextField = new JTextField();
		newPhotoTitleTextField.setToolTipText("New title");
		newPhotoTitleTextField.setBounds(139, 545, 309, 20);
		fullPanel.add(newPhotoTitleTextField);
		newPhotoTitleTextField.setColumns(10);

		JLabel newPhotoTitleLabel = new JLabel("Title of your new photo when exported...");
		newPhotoTitleLabel.setBounds(139, 518, 245, 14);
		fullPanel.add(newPhotoTitleLabel);

		JButton encryptAndDecryptButton = new JButton("Encrypt");
		encryptAndDecryptButton.setToolTipText("Encrypt your picture to make it more secure");
		encryptAndDecryptButton.setBounds(498, 518, 101, 23);
		fullPanel.add(encryptAndDecryptButton);

		/**
		 *Use this ButtonGroup to switch encryptButton to a decryptButton
		 */
		ButtonGroup buttonGroup = new ButtonGroup();

		JRadioButton encryptRadioButton = new JRadioButton("Encrypt");
		encryptRadioButton.setSelected(true);
		encryptRadioButton.setToolTipText("Switch to the window for encrypting");
		encryptRadioButton.setBounds(6, 6, 73, 23);
		fullPanel.add(encryptRadioButton);
		buttonGroup.add(encryptRadioButton);

		JRadioButton decryptRadioButton = new JRadioButton("Decrypt");
		decryptRadioButton.setToolTipText("Switch to the window for decrypting");
		decryptRadioButton.setBounds(81, 6, 109, 23);
		fullPanel.add(decryptRadioButton);
		buttonGroup.add(decryptRadioButton);

		JLabel modeLabel = new JLabel("Encryption mode");
		modeLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		modeLabel.setBounds(232, 10, 109, 14);
		fullPanel.add(modeLabel);

		/**
		 * make events down here
		 */
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(encryptRadioButton.isSelected()) {
					if(pic != null) {
						if(newPhotoTitleTextField.getText() != null) {
							pic.export(newPhotoTitleTextField.getText());
							System.out.println("Picture has been exported. With the name " + newPhotoTitleTextField.getText() + ".");
						}else {
							pic.export();
							System.out.println("Picture has been exported.");
						}
					}else {
						JOptionPane.showMessageDialog(null , "No chosen photo. Choose a photo before pressing the export button.");
					}
				}else if(decryptRadioButton.isSelected()) {
					if(encryptor != null) {
						if(newPhotoTitleTextField.getText() != null) {
							encryptor.ExportPicture(newPhotoTitleTextField.getText());
							System.out.println("Encrypted picture has been exported. With the name " + newPhotoTitleTextField.getText() + ".");
						}else {
							encryptor.ExportPicture();
							System.out.println("Encrypted picture has been exported.");
						}
					}else {
						JOptionPane.showMessageDialog(null, "No encrypted photo to export.");
					}
				}
			}
		});

		encryptAndDecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(encryptRadioButton.isSelected()) {
					if(pic != null) {
						if(encryptor != null) {
							try {
								encryptor.Encrypt();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}else {
							System.out.println("No picture to encrypt.");
						}
						pic.r();
						Image dimg = pic.getImage().getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(),
								Image.SCALE_SMOOTH);
						ImageIcon imageIcon = new ImageIcon(dimg);
						photoLabel.setText("");
						photoLabel.setIcon(imageIcon);
						System.out.println("Image encrypted.");
					}else {
						JOptionPane.showMessageDialog(null, "No chosen photo. Choose a photo before pressing the encrypt button.");
					}

				}else if(decryptRadioButton.isSelected()) {
					if(pic != null) {
						if(encryptor != null) {
							try {
								encryptor.Decrypt();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}else {
							System.out.println("No picture to decrypt.");
						}

						System.out.println("Image decrypted.");
					}else {
						JOptionPane.showMessageDialog(null, "No chosen photo. Choose a photo before pressing the encrypt button.");
					}
				}
			}
		});

		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);

				File file = null;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					System.out.println("Opening: " + file.getName() + ".\n File path + " + file.getPath());
					pic = new Picture(file.getPath());
					try {
						encryptor = new PictureEncryptor(pic);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					//System.out.println("Picture has: " + pic.P_ValueToString().length());
					//System.out.println(Arrays.toString(pic.P_ValueToString().getBytes()));
					System.out.println("Picture has: " + pic.getAmountOfPixels() + " pixels, " + pic.getAmountOfBytes() + " bytes, " + pic.getAmountOfBits() + " bits");
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

		encryptRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeLabel.setText("Encryption mode");
				encryptAndDecryptButton.setText("Encrypt");
				JOptionPane.showMessageDialog(null, "Switched to encryption mode.");
			}
		});

		decryptRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeLabel.setText("Decryption mode");
				encryptAndDecryptButton.setText("Decrypt");
				toEncryptLabel.setText("to decrypt");
				JOptionPane.showMessageDialog(null, "Switched to decryption mode.");
			}
		});
	}
}

