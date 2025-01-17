package aplicacionFutbol;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// equipos
		int numEquipos = 6;
		// equipos - 1
		int numJornadas = 6 - 1 ;
	
		// equipos/2
		int numPartidosPorJornada = 6/2;
		
		// añadimos los equipos a un arraylist
        List<String> equipos = new ArrayList<>(); 
        
        // los equipos tienen nombres numerados "equipo5 vs equipo2"
		//for (int i = 1; i <= 6; i++) {
			//equipos.add("equipo" + i);
		//}
        
        // los equipos tienen nombres normales
        equipos.add("barça");
        equipos.add("real madrid");
        equipos.add("betis");
        equipos.add("rayo bayecano");
        equipos.add("athletic");
        equipos.add("osasuna");
        
        
        List<String[]> ida = new ArrayList<>();
        List<String[]> vuelta = new ArrayList<>();
        
        // **************************
        // PARTIDOS DE IDA
        // **************************
        // iteramos 5 veces (1 por jornada)
        for (int jornada = 0; jornada < numJornadas; jornada++) {
        	
            String[] partidos = new String[numPartidosPorJornada];
            // itermanos 3 veces (1 por partido)
            for (int partido = 0; partido < numPartidosPorJornada; partido++) {
            	
            	// obtenemos los INDICES de los equipos para luego sacar el nombre del arrayList Equipos
                int equipoLocal = (jornada + partido) % (numEquipos - 1);
                int equipoVisitante = (numEquipos - 1 - partido + jornada) % (numEquipos - 1);
                
                if (partido == 0) {
                    equipoVisitante = numEquipos - 1;
                }
                // generamos el string "equipoX vs EquipoY"
                partidos[partido] = equipos.get(equipoLocal) + " vs " + equipos.get(equipoVisitante);
            }
            // añadimos el string al arrayList 
            ida.add(partidos);
        }
        
        // **************************
        // PARTIDOS DE VUELTA
        // **************************
        
        // recorremos el arrayListr con los partidos de Ida
        for (String[] jornadaIda : ida) {
        	// creamos un array de strings 
            String[] partidosVuelta = new String[numPartidosPorJornada];
            
            // itermaos 5 veces (una por cada jornada de ida)
            for (int i = 0; i < jornadaIda.length; i++) {
            	// split parte el string por donde se le indique en este caso " vs "
                String[] equiposPartido = jornadaIda[i].split(" vs ");
                
                // invierte el orden ponoiendo primero el 1 luego el 0
                partidosVuelta[i] = equiposPartido[1] + " vs " + equiposPartido[0];
            }
            vuelta.add(partidosVuelta);
        }
        
        // Imprimir las jornadas
        System.out.println("Jornadas de IDA:");
        imprimirJornadas(ida);

        System.out.println("\n Jornadas de VUELTA:");
        imprimirJornadas(vuelta);
        
	}
        public static void imprimirJornadas(List<String[]> jornadas) {
            int numJornada = 1;
            for (String[] jornada : jornadas) {
                System.out.println("Jornada " + numJornada + ":");
                for (String partido : jornada) {
                    System.out.println(" - " + partido);
                }
                numJornada++;
            }
        }

	

}
