package br.com.cotiinformatica.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	//método para converter um valor yyyy-MM-dd para java.util.Date
		public static Date convertDate(String date) {
			
			//Formato: yyyy-MM-dd => substring(indice, posicao)
			//índice:  0123456789
			//posição: 12345678910
			
			//capturar os valores da data no formato yyyy-MM-dd
			int ano = Integer.parseInt(date.substring(0, 4)); //ano
			int mes = Integer.parseInt(date.substring(5, 7)); //mes
			int dia = Integer.parseInt(date.substring(8, 10)); //dia
			
			Calendar result = new GregorianCalendar(ano, mes - 1, dia);
			return result.getTime();
		}
		
		//método para converter a hora HH:mm
		public static Date convertTime(String time) {
			
			int hora = Integer.parseInt(time.substring(0, 2));
			int minuto = Integer.parseInt(time.substring(3, 5));
					
			Calendar result = new GregorianCalendar();
			result.set(Calendar.HOUR, hora);
			result.set(Calendar.MINUTE, minuto);
			
			return result.getTime();
		}
}
