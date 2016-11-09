package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TelaPrincipal extends JFrame {
	/*Componentes - Imagem*/
	private File arquivo;
	private Imagem imagemOriginal;
	private Imagem imagemAlterada;
	private Histograma histogramaOriginal;
	private Histograma histogramaAlterado;
	
	/*Componentes de tela*/
	private JPanel contentPane;
	private JPanel pnlImagens;
	private JPanel pnlImagemOriginal;
	private JPanel pnlImagemAlterada;
	private JLabel lblImagemOriginal;
	private JLabel lblImagemAlterada;
	private JMenuBar mnbMenu;
	private JMenu mnFiltros;
	private JMenu mnArquivo;
	private JMenuItem mniAbrir;
	private JMenuItem mniSair;
	private JSeparator separator;
	private JMenuItem mniBinarizacao;
	private JMenuItem mniQuantizacao;
	private JMenuItem mniEqualizacao;
	private JMenuItem mniPassaAltaNormal;
	private JMenuItem mniPassaAltaNorte;
	private JMenuItem mniPassaAltaLeste;
	private JMenuItem mniPassaAltaSul;
	private JMenuItem mniPassaAltaOeste;
	private JMenuItem mniPassaAltaNordeste;
	private JMenuItem mniPassaAltaSudeste;
	private JMenuItem mniPassaAltaSudoeste;
	private JMenuItem mniPassaAltaNoroeste;
	private JMenuItem mniPassaBaixa;
	private JMenuItem mniOperadorRoberts;
	private JMenuItem mniOperadorSobel;
	private JMenuItem mniOperadorPrewitt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {

		setTitle("Processamento Digital de Imagens");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 576);
		/**/
		
		/*Instancia��o do objeto da classe de a��es do menu*/
		AcoesMenu acoes = new AcoesMenu();
		/**/
		
		/*Inst�ncias dos componentes de imagem*/
		arquivo = null;
		imagemOriginal = null;
		imagemAlterada = null;
		histogramaOriginal = null;
		histogramaAlterado = null;
		/**/
		
		/*Inst�ncias dos componentes de tela*/
		contentPane = new JPanel();
		pnlImagens = new JPanel();
		pnlImagemOriginal = new JPanel();
		pnlImagemAlterada = new JPanel();
		lblImagemOriginal = new JLabel("");
		lblImagemAlterada = new JLabel("");
		mnbMenu = new JMenuBar();
		mnArquivo = new JMenu("Arquivo");
		mniAbrir = new JMenuItem("Abrir...");
		mniSair = new JMenuItem("Sair");
		mnFiltros = new JMenu("Filtros");
		mniBinarizacao = new JMenuItem("Binarização");
		mniQuantizacao = new JMenuItem("Quantização");
		mniEqualizacao = new JMenuItem("Equalização");
		mniPassaAltaNormal = new JMenuItem("Passa Alta Normal");
		mniPassaAltaNorte = new JMenuItem("Passa Alta Norte");
		mniPassaAltaLeste = new JMenuItem("Passa Alta Leste");
		mniPassaAltaSul = new JMenuItem("Passa Alta Sul");
		mniPassaAltaOeste = new JMenuItem("Passa Alta Oeste");
		mniPassaAltaNordeste = new JMenuItem("Passa Alta Nordeste");
		mniPassaAltaSudeste = new JMenuItem("Passa Alta Sudeste");
		mniPassaAltaSudoeste = new JMenuItem("Passa Alta Sudoeste");
		mniPassaAltaNoroeste = new JMenuItem("Passa Alta Noroeste");
		mniPassaBaixa = new JMenuItem("Passa Baixa");
		mniOperadorRoberts = new JMenuItem("Operador Roberts");
		mniOperadorSobel = new JMenuItem("Operador Sobel");
		mniOperadorPrewitt = new JMenuItem("Operador Prewitt");
		separator = new JSeparator();
		/**/
		
		/*Content Pane*/
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(mnbMenu, BorderLayout.NORTH);
		contentPane.add(pnlImagens, BorderLayout.CENTER);
		setContentPane(contentPane);
		/**/
		
		/*Panels*/
		pnlImagens.setLayout(new GridLayout(1, 2, 0, 0));
		pnlImagens.add(pnlImagemOriginal);
		pnlImagens.add(pnlImagemAlterada);
		pnlImagemOriginal.add(lblImagemOriginal);
		pnlImagemAlterada.add(lblImagemAlterada);
		/**/
		
		/*Menus*/
		//Arquivo
		mnArquivo.setMnemonic('A');
		mnArquivo.add(mniAbrir);
		mnArquivo.add(separator);
		mnArquivo.add(mniSair);
		//Filtros
		mnFiltros.setMnemonic('F');
		mnFiltros.add(mniBinarizacao);
		mnFiltros.add(mniQuantizacao);
		mnFiltros.add(mniEqualizacao);
		mnFiltros.add(mniPassaAltaNormal);
		mnFiltros.add(mniPassaAltaNorte);
		mnFiltros.add(mniPassaAltaLeste);
		mnFiltros.add(mniPassaAltaSul);
		mnFiltros.add(mniPassaAltaOeste);
		mnFiltros.add(mniPassaAltaNordeste);
		mnFiltros.add(mniPassaAltaSudeste);
		mnFiltros.add(mniPassaAltaSudoeste);
		mnFiltros.add(mniPassaAltaNoroeste);
		mnFiltros.add(mniPassaBaixa);
		mnFiltros.add(mniOperadorRoberts);
		mnFiltros.add(mniOperadorSobel);
		mnFiltros.add(mniOperadorPrewitt);
		/**/
		
		/*Menu Bar*/
		mnbMenu.add(mnArquivo);
		mnbMenu.add(mnFiltros);
		/**/
		
		/*ActionListeners*/
		mniAbrir.addActionListener(acoes);
		mniSair.addActionListener(acoes);
		mniBinarizacao.addActionListener(acoes);
		mniQuantizacao.addActionListener(acoes);
		mniEqualizacao.addActionListener(acoes);
		mniPassaAltaNormal.addActionListener(acoes);
		mniPassaAltaNorte.addActionListener(acoes);
		mniPassaAltaLeste.addActionListener(acoes);
		mniPassaAltaSul.addActionListener(acoes);
		mniPassaAltaOeste.addActionListener(acoes);
		mniPassaAltaNordeste.addActionListener(acoes);
		mniPassaAltaSudeste.addActionListener(acoes);
		mniPassaAltaSudoeste.addActionListener(acoes);
		mniPassaAltaNoroeste.addActionListener(acoes);
		mniPassaBaixa.addActionListener(acoes);
		mniOperadorRoberts.addActionListener(acoes);
		mniOperadorSobel.addActionListener(acoes);
		mniOperadorPrewitt.addActionListener(acoes);
		/**/
	}
	
	class AcoesMenu implements ActionListener{
		public void actionPerformed(ActionEvent evento){
			Object objJMenuItem = evento.getSource();
			Filtros filtros;
			
			filtros = new Filtros();
			
			/*Abrir imagem*/
			if( objJMenuItem.equals( mniAbrir ) ){
				/*Carregando a imagem como arquivo pelo JFileChooser*/
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter( new FileNameExtensionFilter( "JPG & GIF Images",
																	"jpg",
																	"gif") );
				int retorno = chooser.showOpenDialog(null);
				
				/*Caso n�o houver problemas no carregamento, o arquivo � instanciado
				 *e mostrado na janela*/
				if( retorno == JFileChooser.APPROVE_OPTION ){
					/*Instanciando as imagens*/
					arquivo = chooser.getSelectedFile();
					imagemOriginal = new Imagem( arquivo );
					imagemAlterada = new Imagem( arquivo );
					
					/*Instanciando o histograma da imagem original e alterada*/
					histogramaOriginal = new Histograma( imagemOriginal.getImagem() );
					histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
					
					lblImagemOriginal.setIcon( new ImageIcon( imagemOriginal.getImagem() ) );
					lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
					
				}
				/*Caso contr�rio, uma mensagem de erro � apresentada*/
				else{
					JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem.");
				}
			}
			/**/
			
			/*Sair do programa*/
			if( objJMenuItem.equals( mniSair ) ){
				for( Frame frame : Frame.getFrames() ){
                    WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                    frame.dispatchEvent(windowClosing);
                }
			}
			/**/
			
			/*Binariza��o*/
			if( objJMenuItem.equals( mniBinarizacao ) ){
				
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
		
				filtros.Binarizacao( imagemAlterada );
			
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
	
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Quantiza��o*/
			if( objJMenuItem.equals( mniQuantizacao ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.Quantizacao( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Equaliza��o*/
			if( objJMenuItem.equals( mniEqualizacao ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.Equalizacao( imagemAlterada, histogramaOriginal.getHistograma() );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Normal*/
			if( objJMenuItem.equals( mniPassaAltaNormal ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaNormal( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Norte*/
			if( objJMenuItem.equals( mniPassaAltaNorte ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaNorte( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Leste*/
			if( objJMenuItem.equals( mniPassaAltaLeste ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaLeste( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Sul*/
			if( objJMenuItem.equals( mniPassaAltaSul ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaSul( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Oeste*/
			if( objJMenuItem.equals( mniPassaAltaOeste ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaOeste( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Nordeste*/
			if( objJMenuItem.equals( mniPassaAltaNordeste ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaNordeste( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Sudeste*/
			if( objJMenuItem.equals( mniPassaAltaSudeste ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaSudeste( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Sudoeste*/
			if( objJMenuItem.equals( mniPassaAltaSudoeste ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaSudoeste( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Alta Noroeste*/
			if( objJMenuItem.equals( mniPassaAltaNoroeste ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaAltaNoroeste( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Passa Baixa*/
			if( objJMenuItem.equals( mniPassaBaixa ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.PassaBaixa( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Operador Roberts*/
			if( objJMenuItem.equals( mniOperadorRoberts ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.OperadorRoberts( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Operador Sobel*/
			if( objJMenuItem.equals( mniOperadorSobel ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.OperadorSobel( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
			
			/*Operador Prewitt*/
			if( objJMenuItem.equals( mniOperadorPrewitt ) ){
				/*Realizando uma c�pia profunda da imagem original*/
				imagemAlterada.setImagem( imagemOriginal.copiaProfunda( imagemOriginal.getImagem() ) );
				/**/
				
				/*Aplicando filtro*/
				filtros.OperadorPrewitt( imagemAlterada );
				/**/
				
				/*Atualizando o histograma*/
				histogramaAlterado = new Histograma( imagemAlterada.getImagem() );
				/**/
				
				/*Atualizando imagem na tela*/
				lblImagemAlterada.setIcon( new ImageIcon( imagemAlterada.getImagem() ) );
				lblImagemAlterada.repaint();
				/**/
			}
			/**/
		}
	}
}
