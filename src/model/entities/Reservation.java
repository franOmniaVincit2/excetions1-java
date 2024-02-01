package model.entities;

/*
 * 2 - Na classe 'Reservation' vamos trocar o 
 * throw new IllegalArgumentException
 * pela nossa exceção personalizada throw new DomainException
 * 
 * 3 - Assim,, a classe Reservation vai reclamar para lançar uma exceção. 
 * Assim, quem tem que tratar exceção vai ser o meu programa principal "Program" no bloco catch()
 * Para resolver isto, vamos, na classe 'Resevation' propagar esta exceção
 * vamos propagar na assinatura do método 'no metodo "public void updateDates( Date checkIn, Date checkOut )  
 * ficando 'no metodo "public void updateDates( Date checkIn, Date checkOut ) "throws DomainException"'
 * 
 * 4 - E, na classe 'Program' capturaremos a exceção 'catch( DomainException e ){}'
 * 
 * Agora, o meu programa não está dando exceção nenhuma mais.
 * 
 * 5 - No exemplo, tinha um caso que a data já no construtor dava uma exceção, 
 *     no caso da de check-out fosse anterior a data de check-in. 
 *     Para resolver isto, já no construtor, eu vou lançar uma exceção.
 *     Para isto, vamos colocar o tratamento desta exceção no comecinho do construtor da
 *     clase reservation().
 *     Isto se chama PROGRAMAÇÃO DEFENSIVA (é uma boa prática):
 *     		if(!checkOut.after(checkIn)){ 
			throw new DomainException( "Check-out dates must be after check-in date");
		}
 *     
 *     declarando, inclusive, uma declaração 'throws DomainException' no construtor 'Reservation'
 *       
 *6 - E para terminar, na classe 'Reservation', posso na classe 'DomainException' 
 *    mudar o tipo de herança de 'extends Exception Exception' 
 *    para 'extends Exception RuntimeException'.
 *    Com isto, podemos, na classe 'Reservation':
 *     retirar 'throws DomainException'  do construtor e do método 'updateDates' 
 *     e agora a classe não vai reclamar nenhum erro.
 *     
 *     E, no programa principal 'Program' não vai mudar nada. vai funcionar sem nenhuma modificação 
 *     e vai continuar capturando as exceções normalmente, mesmo retirando o bloco catch()
 *     'catch( DomainException e ) '. A diferença é que agora, caso haja uma exceção que deveria ser 
 *     capturada pelo bloco 'catch( DomainException e )', o programa vai quebrar.
 *     
 * 7 - Obs: caso ocorra uma outra exception diferente, para que o meu programa não quebre, eu posso 
 *     criar, um outra catch() na classe principal "Program", mesmo com a classe DomainException 
 *     fazendo heranã da classe Exceptio: (public class DomainException extends Exception)
 *     
 * 		catch( RuntimeException e ) {
			System.out.println( "Unexpected error" );
		}   
		
		Então, a exceção tem a estrutura de herança e Upcastnig.
 * */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		if(!checkOut.after(checkIn)){ 
			throw new DomainException( "Check-out dates must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getroomNumber() {
		return roomNumber;
	}

	public void setroomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert( diff, TimeUnit.MILLISECONDS);	
	}
	
	public void updateDates( Date checkIn, Date checkOut ) throws DomainException{
		
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) { // caso a data de entrada(In) ou de saída(Out) for antes da data atual
			throw new DomainException( "Reservation dates for update must be future dates"); // esta exceção é utilizada quando os argumentos recebidos pelo método podem sr inválidos
		}
		if(!checkOut.after(checkIn)){ 
			throw new DomainException( "Check-out dates must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format( checkIn )
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights ";
				
	} // end of toString method
	
} // end of Reservation class
