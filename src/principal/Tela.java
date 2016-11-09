package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;

public class Tela extends JFrame {

	private File arquivo;

	private Imagem imagemOriginal;
	private Imagem imagemFiltrada;

	private Histograma histogramaOriginal;
	private Histograma histogramaAlterado;

	private JPanel contentPane;
	private JPanel pnlImagens;
	private JPanel pnlImagemOriginal;
	private JPanel pnlImagemAlterada;
	private JLabel lblImagemOriginal;
	private JLabel lblImagemAlterada;
	private JTextField textField;
	private JButton btnImportarImagem;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		setTitle("Processamento de Imagens - Marcos V. Siqueroli RA: 199563");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 576);
		this.setLocationRelativeTo(null);

		Filtros filtros = new Filtros();

		arquivo = null;
		imagemOriginal = null;
		imagemFiltrada = null;
		histogramaOriginal = null;
		histogramaAlterado = null;

		contentPane = new JPanel();
		pnlImagens = new JPanel();
		pnlImagemOriginal = new JPanel();
		pnlImagemOriginal.setBounds(0, 156, 350, 350);
		pnlImagemAlterada = new JPanel();
		pnlImagemAlterada.setBounds(360, 156, 350, 350);
		lblImagemOriginal = new JLabel("");
		lblImagemAlterada = new JLabel("");

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlImagens, BorderLayout.CENTER);
		setContentPane(contentPane);
		pnlImagens.setLayout(null);
		pnlImagens.add(pnlImagemOriginal);
		pnlImagens.add(pnlImagemAlterada);
		pnlImagemOriginal.add(lblImagemOriginal);
		pnlImagemAlterada.add(lblImagemAlterada);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 133, 20);
		pnlImagens.add(comboBox);

		comboBox.addItem("Binarização");
		comboBox.addItem("Equalização");
		comboBox.addItem("Quantização");

		JButton btnNewButton = new JButton("Aplicar Filtro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (imagemFiltrada == null) {
					JOptionPane.showMessageDialog(null, "Importe uma imagem.");
				} else if (comboBox.getSelectedItem().toString().equals("Binarização")) {
					imagemFiltrada.setImagem(imagemOriginal.copiaProfunda(imagemOriginal.getImagem()));
					filtros.Binarizacao(imagemFiltrada);
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
					lblImagemAlterada.repaint();
				} else if (comboBox.getSelectedItem().toString().equals("Equalização")) {
					imagemFiltrada.setImagem(imagemOriginal.copiaProfunda(imagemOriginal.getImagem()));
					filtros.Equalizacao(imagemFiltrada, histogramaOriginal.getHistograma());
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
					lblImagemAlterada.repaint();
				} else if (comboBox.getSelectedItem().toString().equals("Quantização")) {
					imagemFiltrada.setImagem(imagemOriginal.copiaProfunda(imagemOriginal.getImagem()));
					filtros.Quantizacao(imagemFiltrada);
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
					lblImagemAlterada.repaint();
				}
			}
		});
		btnNewButton.setBounds(15, 66, 130, 23);
		pnlImagens.add(btnNewButton);

		JLabel lblLimiar = new JLabel("Limiar:");
		lblLimiar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLimiar.setBounds(13, 42, 46, 14);
		pnlImagens.add(lblLimiar);

		textField = new JTextField();
		textField.setText("127");
		textField.setBounds(58, 40, 86, 20);
		pnlImagens.add(textField);
		textField.setColumns(10);

		btnImportarImagem = new JButton("Importar Imagem");
		btnImportarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("JPG, GIF, BMP", "jpg", "gif", "bmp"));
				chooser.showOpenDialog(null);

				try {
					arquivo = chooser.getSelectedFile();

					imagemOriginal = new Imagem(arquivo);
					imagemFiltrada = new Imagem(arquivo);

					histogramaOriginal = new Histograma(imagemOriginal.getImagem());
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());

					lblImagemOriginal.setIcon(new ImageIcon(imagemOriginal.getImagem()));
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));

				} catch (Exception e) {

					if (e != null && e.getMessage() == null) {
						JOptionPane.showMessageDialog(null, "O arquivo não foi encontrado.");
					}
				}
			}
		});
		btnImportarImagem.setBounds(153, 10, 115, 23);
		pnlImagens.add(btnImportarImagem);

		btnNewButton_1 = new JButton("Resetar Imagem");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (imagemFiltrada != null) {
					imagemFiltrada = imagemOriginal;
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
				}
			}
		});
		btnNewButton_1.setBounds(154, 39, 114, 23);
		pnlImagens.add(btnNewButton_1);
	}
}
