package principal;

import java.awt.image.BufferedImage;

public class Histograma {
	private int vetorHistograma[];
	
	public Histograma( BufferedImage imagem ){
		int linhas;
		int colunas;
		RGB rgb;
		
		linhas = imagem.getHeight();
		colunas = imagem.getWidth();		
		vetorHistograma = new int[ 256 ];
		
		for( int i = 0; i < 256; i++ ){
			vetorHistograma[ i ] = 0;
		}
		
		for( int j = 0; j < colunas; j++ ){
			for( int i = 0; i < linhas; i++ ){
				rgb = new RGB( imagem.getRGB( j, i ) );
				vetorHistograma[ rgb.getRed() ] += 1;
			}
		}		
	}

	public void setHistograma( int[] vetorHistograma ){
		this.vetorHistograma = vetorHistograma;
	}
	
	public int[] getHistograma(){
		return vetorHistograma;
	}
}
