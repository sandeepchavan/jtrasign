/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtraffic.lib;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 * @date 08-01-2008
 */
public class Histograma<T> {
	private Hashtable<T, Integer> datos;
    private long total;

	public Histograma() {
		datos = new Hashtable<T, Integer>();
        total = 0;
	}

	/**
	 * Obtiene la probabilidad de un dato en el conjunto.
	 *
	 * @param dato
	 * @return la probabilidad del dato en el histograma, 0 si no existe.
	 */
	public double probabilidad(T dato) {
		double probabilidad = 0.0f;
		if (dato != null && datos.containsKey(dato)) {
			probabilidad = ((double) datos.get(dato).intValue())
					/ ((double) total);
		} else
			probabilidad = 0.0f;

		return probabilidad;
	}

	public void addDato(T dato) {
		if (datos.containsKey(dato)){
			datos.put(dato, new Integer(datos.get(dato).intValue() + 1));
            total ++;
        }
		else
			datos.put(dato, new Integer(1));
	}
}
