package model.exceptions;

/*1 - Esta classe será serializable. Isto quer dizer que as classes serializable,
 * os objetos desta classe podem ser convertidos para byte, e assim poderem ser 
 * trafegados em redes, serem gravados em arquivos etc.
 * Em "DomainException" escolheremos 'Add default serial Version ID'
 * 
 * construiremos, também, um construtor que recebe uma String como argumento.
 * 
 * */

public class DomainException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DomainException( String msg ) {
		super( msg );
	}
	
}
