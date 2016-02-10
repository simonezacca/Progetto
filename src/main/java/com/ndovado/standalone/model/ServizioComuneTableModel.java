package com.ndovado.standalone.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ndovado.dominio.servizi.ServizioComune;

public class ServizioComuneTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<ServizioComune> modelServizioComune;
	String[] headerTable = {"ID Servizio", "Nome Servizio"};

	public ServizioComuneTableModel(List<ServizioComune> nuovaLista) {
		// Richiama il costruttore della superclasse
		super();
		modelServizioComune = nuovaLista;
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return modelServizioComune.size();
	}

	public void setValueAt(Object value, int row, int col) {
		if (col==0) {
			Long newId = (Long) value;
			ServizioComune newSC = modelServizioComune.get(row);
			newSC.setId(newId);
			modelServizioComune.set(row, newSC);
		} else if (col==1) {
			String newName = (String) value;
			ServizioComune newSC = modelServizioComune.get(row);
			newSC.setNomeServizio(newName);
			modelServizioComune.set(row, newSC);
		}
		
	}

	public Object getValueAt(int row, int col) {
		if (col==0) {
			return ((ServizioComune) (modelServizioComune.get(row))).getId();
		} else if (col==1) {
			return ((ServizioComune) (modelServizioComune.get(row))).getNomeServizio();
		}
		return "";
	}
	
	@Override
	public String getColumnName(int index) {
		return headerTable[index];
	}
	
	public void setDataModel(List<ServizioComune> nuovaLista) {
		modelServizioComune = nuovaLista;
		this.fireTableDataChanged();
	}

}
