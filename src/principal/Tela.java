package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
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
	private JLabel lblImagemOriginal_1;
	private JLabel lblImagemFiltrada;

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

		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		setTitle("Processamento de Imagens - Marcos V. Siqueroli RA: 199563");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 576);
		this.setLocationRelativeTo(null);

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

		textField = new JTextField();
		textField.setText("127");
		textField.setBounds(95, 40, 133, 25);
		pnlImagens.add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(95, 11, 133, 20);
		pnlImagens.add(comboBox);

		JLabel lblLimiar = new JLabel("Limiar:");
		lblLimiar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLimiar.setBounds(13, 42, 99, 14);
		pnlImagens.add(lblLimiar);

		comboBox.addItem("Binarização");
		comboBox.addItem("Equalização");
		comboBox.addItem("Quantização");
		comboBox.addItem("Passa Alta");
		comboBox.addItem("Operador Roberts");

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Binarização")) {
					lblLimiar.setText("Limiar:");
					textField.enable();

				} else if (comboBox.getSelectedItem().toString().equals("Equalização")) {
					lblLimiar.setText("");
					textField.disable();
				} else if (comboBox.getSelectedItem().toString().equals("Quantização")) {
					lblLimiar.setText("Nº de Tons:");
					textField.enable();
				} else if (comboBox.getSelectedItem().toString().equals("Operador Roberts")) {
					lblLimiar.setText("");
					textField.disable();
				} else if (comboBox.getSelectedItem().toString().equals("Passa Alta")) {
					lblLimiar.setText("");
					textField.disable();
				}
			}
		});

		JButton btnNewButton = new JButton("Aplicar Filtro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (imagemFiltrada == null) {
					JOptionPane.showMessageDialog(null, "Importe uma imagem.");
				} else if (comboBox.getSelectedItem().toString().equals("Binarização")) {
					imagemFiltrada.setImagem(imagemOriginal.copiarImagem(imagemOriginal.getImagem()));
					Binarizacao(imagemFiltrada);
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
					lblImagemAlterada.repaint();
				} else if (comboBox.getSelectedItem().toString().equals("Equalização")) {
					imagemFiltrada.setImagem(imagemOriginal.copiarImagem(imagemOriginal.getImagem()));
					Equalizacao(imagemFiltrada, histogramaOriginal.getHistograma());
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
					lblImagemAlterada.repaint();
				} else if (comboBox.getSelectedItem().toString().equals("Quantização")) {
					try {
						imagemFiltrada.setImagem(imagemOriginal.copiarImagem(imagemOriginal.getImagem()));
						Quantizacao(imagemFiltrada);
						histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
						lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
						lblImagemAlterada.repaint();
					} catch (Exception e1) {
						imagemFiltrada.setImagem(imagemOriginal.copiarImagem(imagemOriginal.getImagem()));
						lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
						lblImagemAlterada.repaint();
					}
				} else if (comboBox.getSelectedItem().toString().equals("Passa Alta")) {
					System.out.println("Entrou");
					imagemFiltrada.setImagem(imagemOriginal.copiarImagem(imagemOriginal.getImagem()));
					PassaAlta(imagemFiltrada);
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
					lblImagemAlterada.repaint();
				} else if (comboBox.getSelectedItem().toString().equals("Operador Roberts")) {
					imagemFiltrada.setImagem(imagemOriginal.copiarImagem(imagemOriginal.getImagem()));
					OperadorRoberts(imagemFiltrada);
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
					lblImagemAlterada.repaint();
				}
			}
		});
		btnNewButton.setBounds(10, 91, 130, 23);
		pnlImagens.add(btnNewButton);

		btnImportarImagem = new JButton("Importar Imagem");
		btnImportarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("JPG, GIF, BMP, PNG", "jpg", "gif", "bmp", "png"));
				chooser.showOpenDialog(null);

				try {
					arquivo = chooser.getSelectedFile();

					imagemOriginal = new Imagem(arquivo);
					imagemFiltrada = new Imagem(arquivo);

					histogramaOriginal = new Histograma(imagemOriginal.getImagem());
					histogramaAlterado = new Histograma(imagemFiltrada.getImagem());

					lblImagemOriginal.setIcon(new ImageIcon(imagemOriginal.getImagem()));
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));

					lblImagemOriginal_1.setText("Imagem Original");
					lblImagemFiltrada.setText("Imagem Filtrada");

				} catch (Exception e) {

					if (e != null && e.getMessage() == null) {
						JOptionPane.showMessageDialog(null, "O arquivo não foi encontrado.");
					}
				}
			}
		});
		btnImportarImagem.setBounds(249, 11, 149, 23);
		pnlImagens.add(btnImportarImagem);

		btnNewButton_1 = new JButton("Resetar Imagem");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (imagemOriginal != null) {
					imagemFiltrada.setImagem(imagemOriginal.copiarImagem(imagemOriginal.getImagem()));
					lblImagemAlterada.setIcon(new ImageIcon(imagemFiltrada.getImagem()));
				} else {
					System.out.println("Clicoou!");
				}
			}
		});
		btnNewButton_1.setBounds(249, 40, 149, 23);
		pnlImagens.add(btnNewButton_1);

		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFiltro.setBounds(13, 12, 46, 14);
		pnlImagens.add(lblFiltro);

		lblImagemOriginal_1 = new JLabel("");
		lblImagemOriginal_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImagemOriginal_1.setBounds(134, 125, 115, 20);
		pnlImagens.add(lblImagemOriginal_1);

		lblImagemFiltrada = new JLabel("");
		lblImagemFiltrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImagemFiltrada.setBounds(499, 125, 115, 20);
		pnlImagens.add(lblImagemFiltrada);
	}

	public BufferedImage Quantizacao(Imagem imagem) {
		int tons;
		int tomAtual;
		RGB rgb;

		tons = 256 / (Integer.parseInt(textField.getText()) - 1);

		for (int j = 0; j < imagem.getColunas(); j++) {
			for (int i = 0; i < imagem.getLinhas(); i++) {
				rgb = new RGB(imagem.getImagem().getRGB(j, i));
				tomAtual = 0;

				while (rgb.getRed() > tomAtual) {
					tomAtual += tons;
				}

				if (tomAtual > 255) {
					imagem.getImagem().setRGB(j, i, new Color(255, 255, 255).getRGB());
				} else {
					imagem.getImagem().setRGB(j, i, new Color(tomAtual, tomAtual, tomAtual).getRGB());
				}
			}
		}

		return imagem.getImagem();
	}

	public BufferedImage Binarizacao(Imagem imagem) {
		int limiar;
		int tipoBinarizacao;
		RGB rgb;

		limiar = Integer.parseInt(textField.getText()) - 1;

		for (int j = 0; j < imagem.getColunas(); j++) {
			for (int i = 0; i < imagem.getLinhas(); i++) {
				rgb = new RGB(imagem.getImagem().getRGB(j, i));

				if (rgb.getRed() < limiar) {
					imagem.getImagem().setRGB(j, i, new Color(0, 0, 0).getRGB());
				} else {
					imagem.getImagem().setRGB(j, i, new Color(255, 255, 255).getRGB());
				}

			}
		}

		return imagem.getImagem();
	}

	public BufferedImage Equalizacao(Imagem imagem, int[] histograma) {
		int histogramaAux[];
		long soma;
		float fatorEscala;
		int valor;
		RGB rgb;

		histogramaAux = new int[256];
		soma = 0;
		fatorEscala = (float) (255.0 / (imagem.getColunas() * imagem.getLinhas()));
		valor = 0;

		for (int i = 0; i < histograma.length; i++) {
			soma += histograma[i];
			valor = (int) (soma * fatorEscala);

			if (valor > 255) {
				histogramaAux[i] = 255;
			} else {
				histogramaAux[i] = valor;
			}
		}

		for (int j = 0; j < imagem.getColunas(); j++) {
			for (int i = 0; i < imagem.getLinhas(); i++) {
				rgb = new RGB(imagem.getImagem().getRGB(j, i));
				imagem.getImagem().setRGB(j, i,
						new Color(histogramaAux[rgb.getRed()], histogramaAux[rgb.getRed()], histogramaAux[rgb.getRed()])
								.getRGB());
			}
		}

		return imagem.getImagem();
	}

	public BufferedImage AplicaMascara(BufferedImage bi, int tipo, float[] filtro) {
		Kernel kernel;
		BufferedImageOp bIOp;
		kernel = new Kernel(tipo, tipo, filtro);
		bIOp = new ConvolveOp(kernel);
		return bIOp.filter(bi, null);
	}

	public BufferedImage PassaAlta(Imagem imagem) {
		float[] passaAltaNormal = { -1, -1, -1, -1, 8, -1, -1, -1, -1 };
		imagem.setImagem(AplicaMascara(imagem.getImagem(), 3, passaAltaNormal));
		return imagem.getImagem();
	}

	public BufferedImage OperadorRoberts(Imagem imagem) {
		float[] operadorRoberts = { 0, 1, -1, 0 };
		imagem.setImagem(AplicaMascara(imagem.getImagem(), 2, operadorRoberts));
		return imagem.getImagem();
	}
}
