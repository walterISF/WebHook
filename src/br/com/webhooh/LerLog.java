package br.com.webhooh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LerLog 
{
	public static void main(String[] args) 
	{
		try 
		{
			//Arquivo à ser lido pelo programa (O caminho especificado deverá ser de acordo com o local que o log estiver na maquina)
			BufferedReader br = new BufferedReader(new FileReader("G:/all/log.txt"));
		    
			//Listas de elementos
			List<Item> listaResquest = new ArrayList<Item>();
			List<Item> listaResponse = new ArrayList<Item>();
			
        	LerLog lerLog = new LerLog();
        	Item item = null;
        	String temp, temp2;
			String line = br.readLine();
			
		    while(line != null) 
		    {
		        int indexInicial = line.indexOf("request_to=\"");
		        int statusInicial = line.indexOf("response_status=\"");
		        
		        
		        if(indexInicial>=0)
		        {
		        	temp = line.substring(indexInicial+12);
		        	temp = temp.substring(0, temp.indexOf("\""));
		        	
		        	
		        	//Iteramos a lista de resultados (inicialmente vazia)
		        	int i; //Vamos usar o 'i'
		        	for(i = 0; i < listaResquest.size(); i++)
		        	{
		        		item = listaResquest.get(i); //Para cada item da lista
		        		
		        		//Verificamos se é o item que obtivemos
		        		if(item.getLink()!=null && item.getLink().equalsIgnoreCase(temp))
		        		{
		        			//Se sim, apenas incrementamos o valor
		        			item.setValor(item.getValor()+1);
		        			break; //Se encotramos o elemento na lista de resultados DEVEMOS interromper o FOR (afinal nao tem elementos repetidos na lista)
		        		}
					}
		        	if(i >= listaResquest.size()) //Se o 'i' for maior ou igual o tamanho da lista significa que o 'for' acima foi feito completo e nao encontrou o elemento (senao ele interrompia e saia no break)
		        	{
		        		//Adicionamos esse novo elemento
		        		listaResquest.add(lerLog.new Item(temp, 1));
		        	}
		        }
		     
		        
		        
		        
		        if(statusInicial>=0)
		        {
		        	temp = line.substring(statusInicial+17);
		        	temp = temp.substring(0, temp.indexOf("\""));

		        	
		        	//Iteramos a lista de resultados (inicialmente vazia)
		        	int i; //Vamos usar o 'i'
		        	for(i = 0; i < listaResponse.size(); i++)
		        	{
		        		item = listaResponse.get(i); //Para cada item da lista
		        		
		        		//Verificamos se é o item que obtivemos
		        		if(item.getLink()!=null && item.getLink().equalsIgnoreCase(temp))
		        		{
		        			//Se sim, apenas incrementamos o valor
		        			item.setValor(item.getValor()+1);
		        			break; //Se encotramos o elemento na lista de resultados DEVEMOS interromper o FOR (afinal nao tem elementos repetidos na lista)
		        		}
					}
		        	if(i >= listaResponse.size()) //Se o 'i' for maior ou igual o tamanho da lista significa que o 'for' acima foi feito completo e nao encontrou o elemento (senao ele interrompia e saia no break)
		        	{
		        		//Adicionamos esse novo elemento
		        		listaResponse.add(lerLog.new Item(temp, 1));
		        	}
		        }
		        
		        
		        line = br.readLine();
		    }
		    br.close();
		    
		    
		    
		  	//-------------- Ordenando resultado pelo valor (decrescentemente)
	        Collections.sort(listaResquest, new Comparator<Item>()
	        {
				@Override
				public int compare(Item o1, Item o2) 
				{
					return o2.getValor()-o1.getValor();
					//return o1.getValor()-o2.getValor();  se quiser ordenar crescentemente
				}
			});
	        //Nesse ponto a lista esta ordenada, so imprimi-la
	        for (int i = 0; i < 3; i++)
	        {
	        	item = listaResquest.get(i);
	        	System.out.println(item.getLink() + " - " + item.getValor());
			}
	        
	        System.out.println("\n");
		  	//-------------- Ordenando resultado pelo valor (decrescentemente)
	        Collections.sort(listaResponse, new Comparator<Item>()
	        {
				@Override
				public int compare(Item o1, Item o2) 
				{
					return o2.getValor()-o1.getValor();
					//return o1.getValor()-o2.getValor();  se quiser ordenar crescentemente
				}
			});
	        //Nesse ponto a lista esta ordenada, so imprimi-la
	        for (int i = 0; i < listaResponse.size(); i++)
	        {
	        	item = listaResponse.get(i);
				System.out.println(item.getLink() + " - " + item.getValor());
			}
		    
		    
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Inner Class gerada para tratar os valores encontrados na lista de array
	 * @author Walter
	 *
	 */
	class Item
	{
		private String link = null;
		private int valor = 0;
		
		public Item() {}
		public Item(String link, int valor) {
			this.link = link;
			this.valor = valor;
		}
		
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public int getValor() {
			return valor;
		}
		public void setValor(int valor) {
			this.valor = valor;
		}
	}
}
