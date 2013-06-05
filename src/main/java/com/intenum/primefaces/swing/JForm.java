package com.intenum.primefaces.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import com.intenum.primefaces.ThemeUnpacker;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.looks.plastic.theme.Silver;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.FlowLayout;
import javax.swing.JCheckBox;

import org.apache.commons.io.filefilter.FileFilterUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.filechooser.*;

public class JForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtThemeName;
	private JTextField txtDestinationFolder;
	private JTextField txtThemeRollerZipFile;
	private JPanel content;
	private JPanel buttons;
	private JButton btnUncompress;
	private JButton btnClose;
	private JCheckBox chckbxUseCompressedCss;
	private JButton btnSelectDestinationFolder;
	private JCheckBox chckbxClearDestinationFolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JForm frame = new JForm();
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
	public JForm() {
		setTitle("Primefaces Themeroller Unpacker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 471, 291);
		content = new JPanel();
		content.setLayout(new BorderLayout());

		setContentPane(content);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		content.add(contentPane, BorderLayout.CENTER);

		contentPane
				.setLayout(new FormLayout(
						new com.jgoodies.forms.layout.ColumnSpec[] {
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_COLSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_COLSPEC,
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_COLSPEC,
								com.jgoodies.forms.layout.ColumnSpec
										.decodeSpecs("default:grow")[0],
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_COLSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_COLSPEC,
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_COLSPEC, },
						new com.jgoodies.forms.layout.RowSpec[] {
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.RELATED_GAP_ROWSPEC,
								com.jgoodies.forms.factories.FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblThemerollerZipFile = new JLabel("Themeroller Zip File");
		contentPane.add(lblThemerollerZipFile, "2, 4, right, default");

		txtThemeRollerZipFile = new JTextField();
		contentPane.add(txtThemeRollerZipFile, "4, 4, fill, default");
		txtThemeRollerZipFile.setColumns(10);

		JButton btnSelectZipFile = new JButton("Select");
		btnSelectZipFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter(
						"Zip File", "zip"));
				if (fileChooser.showDialog(null, "Open") == JFileChooser.APPROVE_OPTION) {
					txtThemeRollerZipFile.setText(fileChooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		contentPane.add(btnSelectZipFile, "6, 4");

		JLabel lblThemeName = new JLabel("Theme Name");
		contentPane.add(lblThemeName, "2, 6, right, default");

		txtThemeName = new JTextField();
		contentPane.add(txtThemeName, "4, 6, fill, default");
		txtThemeName.setColumns(10);

		JLabel lblDestinationFolder = new JLabel("Destination Folder");
		contentPane.add(lblDestinationFolder, "2, 8, right, default");

		txtDestinationFolder = new JTextField();
		contentPane.add(txtDestinationFolder, "4, 8, fill, default");
		txtDestinationFolder.setColumns(10);

		btnSelectDestinationFolder = new JButton("Select");
		btnSelectDestinationFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (fileChooser.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
					txtDestinationFolder.setText(fileChooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		contentPane.add(btnSelectDestinationFolder, "6, 8");

		chckbxUseCompressedCss = new JCheckBox("Use compressed css file");
		contentPane.add(chckbxUseCompressedCss, "4, 10");

		chckbxClearDestinationFolder = new JCheckBox("Clear destination folder");
		contentPane.add(chckbxClearDestinationFolder, "4, 12");

		buttons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		content.add(buttons, BorderLayout.SOUTH);

		btnUncompress = new JButton("Uncompress");
		btnUncompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemeUnpacker themeUnpacker = new ThemeUnpacker();
				themeUnpacker.setClearDestination(chckbxClearDestinationFolder
						.isSelected());
				themeUnpacker.setDestinationDirectory(txtDestinationFolder
						.getText());
				themeUnpacker.setThemeName(txtThemeName.getText());
				themeUnpacker.setUseCompressedCss(chckbxUseCompressedCss
						.isSelected());
				try {
					themeUnpacker.unpack(txtThemeRollerZipFile.getText());
					JOptionPane.showMessageDialog(null,
							"Theme folder has been created", getTitle(),
							JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString(), "Error",
							JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		buttons.add(btnUncompress);

		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttons.add(btnClose);
	}

}
