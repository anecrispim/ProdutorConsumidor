package classes;

public class Programa {

	public static void main(String[] args) {
		// limite de tamanho do buffer sera 4
		ConsumidorProdutor cp = new ConsumidorProdutor(4);
		
		// criando uma thread para o Produtor
		Thread produtor = new Thread(
			new Runnable() {
				public void run() {
					cp.produtor();
				}
			}
		);
		
		// criando uma thread para o Consumidor
		Thread consumidor = new Thread(
			new Runnable() {
				public void run() {
					cp.consumidor();
				}
			}
		);
		
		try {
			// iniciando threads
			produtor.start();
			consumidor.start();
			
			//Thread main abdica do controle e aguarda a execucao das threads
			produtor.join();
			consumidor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
