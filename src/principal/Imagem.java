package principal;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagem {
	private BufferedImage imagem;
	private int linhas;
	private int colunas;
	
	public Imagem( File file ){
		BufferedImage imagemAuxiliar;
		Graphics2D g;
		
		imagemAuxiliar = null;
		imagem = null;
		linhas = 0;
		colunas = 0;
		
        try {
        	/*Recebendo Imagem*/
            imagemAuxiliar = ImageIO.read( file );
            
            /*Armazenando valores para linhas e colunas da imagem*/
            linhas = imagemAuxiliar.getHeight();
            colunas = imagemAuxiliar.getWidth();
            /**/
            
            imagem = new BufferedImage( colunas,
										linhas,
										BufferedImage.TYPE_BYTE_GRAY );
            
            /*Convertendo imagem colorida para a escala de cinza*/
            g = imagem.createGraphics();
	        g.drawRenderedImage(imagemAuxiliar, null);
	        g.dispose();
	        /**/
        }catch (IOException erro) {}
	}
	
	/*sets*/
	public void setImagem(BufferedImage imagem){
		this.imagem = imagem;
	}
	
	public void setLinhas(int linhas){
		this.linhas = linhas;
	}
	
	public void setColunas(int colunas){
		this.colunas = colunas;
	}
	/**/
	
	/*gets*/
	public BufferedImage getImagem(){
		return imagem;
	}
	
	public int getLinhas(){
		return linhas;
	}
	
	public int getColunas(){
		return colunas;
	}
	
	/*deep copy*/
	BufferedImage copiarImagem( BufferedImage bufferedImage ) {
		ColorModel cm;
		boolean isAlphaPremultiplied;
		WritableRaster raster;
		
		cm = bufferedImage.getColorModel();
		isAlphaPremultiplied = cm.isAlphaPremultiplied();
		raster = bufferedImage.copyData(null);
		
		return new BufferedImage( cm, raster, isAlphaPremultiplied, null );
	}
}
