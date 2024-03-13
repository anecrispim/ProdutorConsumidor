package classes;

import java.util.Queue;
import java.util.LinkedList;

public class ConsumidorProdutor {
	private Queue<Integer> buffer;
	private int limite;
	
	public ConsumidorProdutor(int limite) {
		this.buffer = new LinkedList<>();
		this.limite = limite;
	}
	
	public void produtor() {
		int elemento = 0;
		while (true) {
			synchronized (this) { // deve ser usado para evitar dead locks
				while (buffer.size() == limite) {
					try {
						wait();
					} catch (InterruptedException e){
						e.printStackTrace();
					}
	            }
				
				buffer.add(elemento);
				System.out.println("Elemento "+elemento+" produzido");
				elemento++;
				notify(); // envio de sinal IPC (Intercomunicacao de Threads)
			}
		}
	}
	
	public void consumidor() {
		int elemento;
		while (true) {
			synchronized (this) {
				while (buffer.isEmpty()) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				elemento = buffer.remove();
				System.out.println("Elemento "+elemento+" consumido");
				notify();
			}
		}
	}
}
