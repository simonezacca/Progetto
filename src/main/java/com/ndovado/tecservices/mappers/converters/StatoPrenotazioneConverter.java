package com.ndovado.tecservices.mappers.converters;

import org.dozer.DozerConverter;
import com.ndovado.dominio.prenotazioni.statiprenotazione.AStatoPrenotazione;
import com.ndovado.dominio.prenotazioni.statiprenotazione.StatoConfermata;
import com.ndovado.dominio.prenotazioni.statiprenotazione.StatoEliminata;
import com.ndovado.dominio.prenotazioni.statiprenotazione.StatoPendente;
import com.ndovado.webapp.beans.prenotazioni.statiprenotazione.AStatoPrenotazioneBean;
import com.ndovado.webapp.beans.prenotazioni.statiprenotazione.StatoConfermataBean;
import com.ndovado.webapp.beans.prenotazioni.statiprenotazione.StatoEliminataBean;
import com.ndovado.webapp.beans.prenotazioni.statiprenotazione.StatoPendenteBean;

public class StatoPrenotazioneConverter extends DozerConverter<AStatoPrenotazione, AStatoPrenotazioneBean> {

	public StatoPrenotazioneConverter() {
	        super(AStatoPrenotazione.class, AStatoPrenotazioneBean.class);
	    }

	@Override
	public AStatoPrenotazioneBean convertTo(AStatoPrenotazione source, AStatoPrenotazioneBean destination) {
		if (source instanceof StatoConfermata) {
			return new StatoConfermataBean();
		} else if (source instanceof StatoPendente) {
			return new StatoPendenteBean();
		} else if (source instanceof StatoEliminata) {
			return new StatoEliminataBean();
		} else return null;
	}

	@Override
	public AStatoPrenotazione convertFrom(AStatoPrenotazioneBean source, AStatoPrenotazione destination) {
		if (source instanceof StatoConfermataBean) {
			return new StatoConfermata();
		} else if (source instanceof StatoPendenteBean) {
			return new StatoPendente();
		} else if (source instanceof StatoEliminataBean) {
			return new StatoEliminata();
		} else return null;
	}

	

}
