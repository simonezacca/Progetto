package com.ndovado.standalone.frames;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.exceptions.servizi.CancellazioneServizioException;
import com.ndovado.standalone.controller.ServizioComuneController;
import com.ndovado.standalone.model.ServizioComuneTableModel;
import com.ndovado.tecservices.loggers.AppLogger;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ServicesFrame extends JFrame {

	/**
	 * 
	 */
	private static String titolo = "Pannello Gestione Servizi";
//	private JPanel panel = new JPanel();
	
	
	// pannello superiore
	JPanel psup = new JPanel();
	JLabel lblSup = new JLabel("Gestisci i servizi della piattaforma");

	// pannello centrale
	JPanel pcen = new JPanel(new BorderLayout());
		// tabella servizi
		private ServizioComuneController controller = new ServizioComuneController();
		private JTable tblServizi;
		private ServizioComuneTableModel sctmodel;
	
	// pannello inferiore
	JPanel pinf = new JPanel();
	JButton btnAddServizio = new JButton("Aggiungi");
	JButton btnEditServizio = new JButton("Modifica");
	JButton btnRemServizio = new JButton("Cancella");
	JButton btnUpdServizio = new JButton("Aggiorna elenco");

	
	private static final long serialVersionUID = 1L;
	
	private void initGUI() {
		// superiore
		psup.add(lblSup);
		
		// tabella centrale
		sctmodel = new ServizioComuneTableModel(controller.getElencoServiziComune());
		tblServizi = new JTable(sctmodel);
		tblServizi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tblServizi);
		scrollPane.setSize(788, 400);
		scrollPane.setLocation(6, 10);
		pcen.add(scrollPane);
		
		// pannello inferiore
		pinf.add(btnAddServizio);
		pinf.add(btnEditServizio);
		pinf.add(btnRemServizio);
		pinf.add(btnUpdServizio);
		
		
		
		// container di tutto
		Container cont = this.getContentPane();
		cont.add(psup, BorderLayout.NORTH);
		cont.add(pcen, BorderLayout.CENTER);
		cont.add(pinf, BorderLayout.SOUTH);
		
		// grafico a schermo la jframe
		this.pack();
		this.setLocationRelativeTo(null);
		this.setSize(800, 600);
		this.setVisible(true);
		
	} 
	
	private void initActionListenerButtons() {
		// aggancio action listener al bottone aggiorna
		btnUpdServizio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aggiornaModelTabellaServizi();
			}
		});
		
		// aggancio action listener al bottone aggiungi
		btnAddServizio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServizioComune newServizio =  aggiungiServizioDialog();
				if (newServizio.getNomeServizio().length()>0) {
					controller.salvaServizioComune(newServizio);
					aggiornaModelTabellaServizi();
				}				
			}
		});
		
		// aggancio action listener al bottone modifica
		btnEditServizio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServizioComune sToEdit = getCurrentSelectedServizio();
				sToEdit = modificaServizioDialog(sToEdit);
				controller.salvaServizioComune(sToEdit);
				aggiornaModelTabellaServizi();
				AppLogger.debug("Servizio modificato: "+sToEdit.getNomeServizio());
				
			}
		});
		
		// aggancio action listener al bottone modifica
		btnRemServizio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServizioComune sToRemove = getCurrentSelectedServizio();
				int risposta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il servizio: "+sToRemove.getNomeServizio(), "Elimina servizio", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (risposta==JOptionPane.OK_OPTION) {
					try {
						controller.rimuoviServizioComune(sToRemove);
					} catch (CancellazioneServizioException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
						AppLogger.error("Errore eliminazione servizio: "+e.getMessage());
					}
					aggiornaModelTabellaServizi();
				}
			}
		});
		
	}
	
	private ServizioComune getCurrentSelectedServizio() {
		String idServizioString = tblServizi.getValueAt(tblServizi.getSelectedRow(), 0).toString();
		Long idServizio = Long.parseLong(idServizioString);
		return controller.getServizioComuneById(idServizio);
	}
	
	private ServizioComune modificaServizioDialog(ServizioComune oldServizio) {
		String nomeServizio = JOptionPane.showInputDialog("Modifica il nome del nuovo servizio",oldServizio.getNomeServizio());
		oldServizio.setNomeServizio(nomeServizio);
		return oldServizio;
	}
	
	private ServizioComune aggiungiServizioDialog() {
		String nomeServizio = JOptionPane.showInputDialog("Inserisci il nome del nuovo servizio");
		ServizioComune newServizio = new ServizioComune(nomeServizio);
		return newServizio;
		
	}
	
	private void aggiornaModelTabellaServizi() {
		sctmodel.setDataModel(controller.getElencoServiziComune());
		AppLogger.debug("Lista servizi ricaricata da DB");
	}
	
	private void setSelectionEventTableServizi() {
		// disabilito i btn di modifica e cancella
		btnEditServizio.setEnabled(false);
		btnRemServizio.setEnabled(false);
		
		tblServizi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					btnEditServizio.setEnabled(true);
					btnRemServizio.setEnabled(true);
					
					String nomeServizioSelezionato = tblServizi.getValueAt(tblServizi.getSelectedRow(), 1).toString();
					AppLogger.debug("Servizio selezionato: "+nomeServizioSelezionato);
				}
			}
		});
		
	}

	public ServicesFrame() {
		super(titolo);
//		JFrame servicesFrame = new JFrame(titolo);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		initGUI();
		initActionListenerButtons();
		setSelectionEventTableServizi();
	}

}
