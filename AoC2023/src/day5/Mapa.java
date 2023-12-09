package day5;

public class Mapa {
	Long destino;
	Long source;
	Long rango;
	Rango destinoRango;
	Rango sourceRango;
	
	public Mapa() {
		destino = (long)0;
		source = (long)0;
		rango = (long)0;
	}
	
	public Mapa(Long destino, Long source, Long rango) {
		this.destino = destino;
		this.source = source;
		this.rango = rango;
		destinoRango = new Rango(destino, destino+rango);
		sourceRango = new Rango(source, source+rango);
	}
	
	public Mapa(String[] s) {
		this.destino = Long.parseLong(s[0]);
		this.source = Long.parseLong(s[1]);
		this.rango = Long.parseLong(s[2]);
		destinoRango = new Rango(destino, destino+rango);
		sourceRango = new Rango(source, source+rango);
	}
	
	public long rodea(Long comp) {
		if(comp<source+rango&&comp>=source) {
			return comp+(destino-source);
		}
		else return -1;
	}
	
	public long comparator(long delOtro) {
		return source-delOtro;
	}
	
	public String toString() {
		return sourceRango.toString()+" -> "+destinoRango.toString();
	}
	
	public Rango sourceADestino(Rango r) {
		long transf = destino-source;
		return new Rango(r.getMin()+transf,r.getMax()+transf);
	}
	
	public long getSource() {
		return source;
	}
	public Rango getDestinoRango() {
		return destinoRango;
	}
	public Rango getSourceRango() {
		return sourceRango;
	}
	
}
