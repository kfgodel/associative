/**
 * 22/12/2013 18:26:29 Copyright (C) 2013 Darío L. García
 * 
 * <a rel="license" href="http://creativecommons.org/licenses/by/3.0/"><img
 * alt="Creative Commons License" style="border-width:0"
 * src="http://i.creativecommons.org/l/by/3.0/88x31.png" /></a><br />
 * <span xmlns:dct="http://purl.org/dc/terms/" href="http://purl.org/dc/dcmitype/Text"
 * property="dct:title" rel="dct:type">Software</span> by <span
 * xmlns:cc="http://creativecommons.org/ns#" property="cc:attributionName">Darío García</span> is
 * licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative
 * Commons Attribution 3.0 Unported License</a>.
 */
package ar.dgarcia.procesador.fraccionables.impl;

import ar.com.kfgodel.tostring.ImplementedWithStringer;
import ar.com.kfgodel.tostring.Stringer;
import ar.dgarcia.procesador.fraccionables.api.ResultadoIterativo;

/**
 * Esta clase representa el resultado utilizable por una tarea cuando aun no tiene valor final
 * 
 * @author D. García
 */
public class SinResultado<R> implements ResultadoIterativo<R> {

	private static final SinResultado<Object> instancia = new SinResultado<>();

	@SuppressWarnings("unchecked")
	public static <R> SinResultado<R> instancia() {
		return (SinResultado<R>) instancia;
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.ResultadoIterativo#quedanIteraciones()
	 */
	@Override
	public boolean quedanIteraciones() {
		return true;
	}

	/**
	 * @see ar.dgarcia.procesador.fraccionables.api.ResultadoIterativo#getValorFinal()
	 */
	@Override
	public R getValorFinal() throws IllegalStateException {
		throw new IllegalStateException("Este resultado aun no tiene valor final");
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	@ImplementedWithStringer
	public String toString() {
		return Stringer.representationOf(this);
	}
}
