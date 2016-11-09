package principal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Filtros {
	
	public BufferedImage Binarizacao( Imagem imagem ){
		int limiar;
		int tipoBinarizacao;
		RGB rgb;
		
		limiar = Integer.parseInt( JOptionPane.showInputDialog( "Qual o limiar?", "127" ) ) - 1;

		tipoBinarizacao = JOptionPane.showOptionDialog( new JFrame(),
														"Aplicar binarização comum ou inversa?",
														"Binarização",
														JOptionPane.YES_NO_OPTION,
														JOptionPane.QUESTION_MESSAGE,
														null,
														new Object[]{ "Comum", "Inversa" },
														null );
		
		for( int j = 0; j < imagem.getColunas(); j++ ){
			for( int i = 0; i < imagem.getLinhas(); i++ ){
				rgb = new RGB( imagem.getImagem().getRGB(j, i) );

				if( tipoBinarizacao == JOptionPane.YES_OPTION ){
					if( rgb.getRed() < limiar ){
						imagem.getImagem().setRGB( j,
												   i,
												   new Color(0, 0, 0).getRGB() );
					}
					else{
						imagem.getImagem().setRGB( j,
												   i,
												   new Color(255, 255, 255).getRGB() );
					}
				}
				
				else{
					if( rgb.getRed() < limiar ){
						imagem.getImagem().setRGB( j,
												   i,
												   new Color(255, 255, 255).getRGB() );
					}
					else{
						imagem.getImagem().setRGB( j,
												   i,
												   new Color(0, 0, 0).getRGB() );
					}
				}
			}
		}
		
		return imagem.getImagem();
	}
	
	public BufferedImage Quantizacao( Imagem imagem ){
		int tons;
		int tomAtual;
		RGB rgb;
		
		tons = 256 / ( Integer.parseInt( JOptionPane.showInputDialog( "Qual a quantidade de tons?" ) ) - 1 );
		
		for( int j = 0; j < imagem.getColunas(); j++ ){
			for( int i = 0; i < imagem.getLinhas(); i++ ){
				rgb = new RGB( imagem.getImagem().getRGB( j, i ) );
				tomAtual = 0;
		        
		        while( rgb.getRed() > tomAtual )
		        {
		        	tomAtual += tons;
		        }
		        
		        if(tomAtual > 255)
		        {
		        	imagem.getImagem().setRGB( j,
		        				   			   i,
		        				   			   new Color(255, 255, 255).getRGB() );
		        }
		        else
		        {
		        	imagem.getImagem().setRGB( j,
		        				   			   i,
		        				   			   new Color(tomAtual, tomAtual, tomAtual).getRGB() );
		        }
			}
		}
		
		return imagem.getImagem();
	}
	
	public BufferedImage Equalizacao( Imagem imagem, int[] histograma ){
		int histogramaAux[];
		long soma;
		float fatorEscala;
		int valor;
		RGB rgb;
		
		histogramaAux = new int[ 256 ];
		soma = 0;
		fatorEscala = ( float ) ( 255.0 / ( imagem.getColunas() * imagem.getLinhas() ) );
		valor = 0;
		
		for( int i = 0; i < histograma.length; i++ ){
			soma += histograma[ i ];
			valor = ( int )( soma * fatorEscala );
			
			if( valor > 255 ){
				histogramaAux[ i ] = 255;
			}
			else{
				histogramaAux[ i ] = valor;
			}
		}
		
		for( int j = 0; j < imagem.getColunas(); j++ ){
			for ( int i = 0; i < imagem.getLinhas(); i++ ){
				rgb = new RGB( imagem.getImagem().getRGB( j, i ) );
		        imagem.getImagem().setRGB( j, i, new Color(	histogramaAux[ rgb.getRed() ],
		    		   										histogramaAux[ rgb.getRed() ],
		    		   										histogramaAux[ rgb.getRed() ] ).getRGB() );
			}
		}
		/**/
		
		return imagem.getImagem();
	}
	
	public BufferedImage AplicaMascara( BufferedImage bi, int tipo, float[] filtro ){
		Kernel kernel;
		BufferedImageOp bIOp;		
		kernel = new Kernel( tipo, tipo, filtro );
		bIOp = new ConvolveOp( kernel );		
        return bIOp.filter( bi, null );
	}
	
	public BufferedImage PassaAltaNormal( Imagem imagem ){
		float[] passaAltaNormal = {-1, -1, -1, -1,  8, -1, -1, -1, -1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaNormal) );		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaNorte( Imagem imagem ){
		float[] passaAltaNorte = { 1,  1,  1,  1, -1,  1, -1, -1, -1};
		AplicaMascara( imagem.getImagem(), 3, passaAltaNorte);		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaLeste( Imagem imagem ){
		float[] passaAltaLeste = {-1,  1,  1, -1, -1,  1, -1,  1,  1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaLeste) );		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaSul( Imagem imagem ){
		float[] passaAltaSul = {-1, -1, -1,  1, -1,  1,  1,  1,  1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaSul) );
		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaOeste( Imagem imagem ){
		float[] passaAltaOeste = { 1,  1, -1,  1, -1, -1,  1,  1, -1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaOeste) );		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaNordeste( Imagem imagem ){
		float[] passaAltaNordeste = { 1,  1,  1, -1, -1,  1, -1, -1,  1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaNordeste) );		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaSudeste( Imagem imagem ){
		float[] passaAltaSudeste = {-1, -1,  1, -1, -1,  1,  1,  1,  1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaSudeste) );		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaSudoeste( Imagem imagem ){
		float[] passaAltaSudoeste = { 1, -1, -1,  1, -1, -1,  1,  1,  1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaSudoeste) );		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaAltaNoroeste( Imagem imagem ){
		float[] passaAltaNoroeste = { 1,  1,  1,  1, -1, -1,  1, -1, -1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaAltaNoroeste) );		
		return imagem.getImagem();
	}
	
	public BufferedImage PassaBaixa( Imagem imagem ){
		float[] passaBaixa = {1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f, 1f/9f};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, passaBaixa) );		
		return imagem.getImagem();
	}
	
	public BufferedImage OperadorRoberts( Imagem imagem ){
		float[] operadorRoberts = { 0,  1, -1,  0};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 2, operadorRoberts) );
		
		return imagem.getImagem();
	}
	
	public BufferedImage OperadorSobel( Imagem imagem ){
		float[] operadorSobel = { 1,  2,  1,  0,  0,  0, -1, -2, -1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, operadorSobel) );
		
		return imagem.getImagem();
	}
	
	public BufferedImage OperadorPrewitt( Imagem imagem ){
		float[] operadorPrewitt = {-1, -1, -1,  0,  0,  0,  1,  1,  1};
		imagem.setImagem( AplicaMascara( imagem.getImagem(), 3, operadorPrewitt) );
		
		return imagem.getImagem();
	}
}
