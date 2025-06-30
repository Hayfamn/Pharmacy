package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import dao.OrdonnanceDAO;
import modele.Ordonnance;

public class GererOrdonnanceFrame extends JFrame {
private static final long serialVersionUID = 1L;


private JTextField txtDate, txtDescription, txtIdPatient;
private JTable ordonnanceTable;
private DefaultTableModel tableModel;
private List<Ordonnance> ordonnances;

public GererOrdonnanceFrame() {
    setTitle("Gestion des ordonnances");
    setSize(650, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    initComponents();
    loadOrdonnancesFromDB();
}

private void initComponents() {
    JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    formPanel.add(new JLabel("Date (yyyy-mm-dd) :"));
    txtDate = new JTextField();
    formPanel.add(txtDate);

    formPanel.add(new JLabel("Description :"));
    txtDescription = new JTextField();
    formPanel.add(txtDescription);

    formPanel.add(new JLabel("ID Patient :"));
    txtIdPatient = new JTextField();
    formPanel.add(txtIdPatient);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    JButton btnAjouter = new JButton("Ajouter");
    JButton btnModifier = new JButton("Modifier");
    JButton btnSupprimer = new JButton("Supprimer");
    JButton btnAssocierMedicament = new JButton("Associer Médicament");
    JButton btnFermer = new JButton("Fermer");

    btnAjouter.setForeground(new Color(64, 128, 128));
    btnModifier.setForeground(new Color(64, 128, 128));
    btnSupprimer.setForeground(Color.RED);
    btnAssocierMedicament.setForeground(new Color(102, 0, 102));
    btnFermer.setForeground(new Color(64, 128, 128));

    buttonPanel.add(btnAjouter);
    buttonPanel.add(btnModifier);
    buttonPanel.add(btnSupprimer);
    buttonPanel.add(btnAssocierMedicament);
    buttonPanel.add(btnFermer);

    String[] columnNames = {"ID", "Date", "Description", "ID Patient"};
    tableModel = new DefaultTableModel(columnNames, 0) {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    ordonnanceTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(ordonnanceTable);

    ordonnanceTable.getSelectionModel().addListSelectionListener(e -> {
        int selectedRow = ordonnanceTable.getSelectedRow();
        if (selectedRow != -1 && selectedRow < ordonnances.size()) {
            Ordonnance o = ordonnances.get(selectedRow);
            txtDate.setText(o.getDateOrd().toString());
            txtDescription.setText(o.getDescriptionOrd());
            txtIdPatient.setText(String.valueOf(o.getIdPat()));
        }
    });

    btnAjouter.addActionListener(e -> {
        try {
            Ordonnance o = new Ordonnance(
                java.sql.Date.valueOf(txtDate.getText().trim()),
                txtDescription.getText().trim(),
                Integer.parseInt(txtIdPatient.getText().trim())
            );
            new OrdonnanceDAO().insert(o);
            loadOrdonnancesFromDB();
            clearFields();
        } catch (Exception ex) {
            showError(ex);
        }
    });

    btnModifier.addActionListener(e -> {
        int selectedRow = ordonnanceTable.getSelectedRow();
        if (selectedRow != -1 && selectedRow < ordonnances.size()) {
            try {
                Ordonnance o = ordonnances.get(selectedRow);
                o.setDateOrd(java.sql.Date.valueOf(txtDate.getText().trim()));
                o.setDescriptionOrd(txtDescription.getText().trim());
                o.setIdPat(Integer.parseInt(txtIdPatient.getText().trim()));
                new OrdonnanceDAO().update(o);
                loadOrdonnancesFromDB();
                clearFields();
                ordonnanceTable.clearSelection();
            } catch (Exception ex) {
                showError(ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ordonnance à modifier.");
        }
    });

    btnSupprimer.addActionListener(e -> {
        int selectedRow = ordonnanceTable.getSelectedRow();
        if (selectedRow != -1 && selectedRow < ordonnances.size()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    new OrdonnanceDAO().delete(ordonnances.get(selectedRow));
                    loadOrdonnancesFromDB();
                    clearFields();
                } catch (SQLException ex) {
                    showError(ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ordonnance à supprimer.");
        }
    });

    btnAssocierMedicament.addActionListener(e -> {
        int selectedRow = ordonnanceTable.getSelectedRow();
        if (selectedRow != -1 && selectedRow < ordonnances.size()) {
            Ordonnance o = ordonnances.get(selectedRow);
            
            new AjouterMedicamentsOrdonnanceFrame(o.getIdOrd()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ordonnance.");
        }
    });

    btnFermer.addActionListener(e -> dispose());

    JLabel background = new JLabel(new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/back.png")); 
    setContentPane(background);
    background.setLayout(new BorderLayout(10, 10));
    getContentPane().add(formPanel, BorderLayout.NORTH);
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
}

private void loadOrdonnancesFromDB() {
    try {
        ordonnances = new OrdonnanceDAO().getAll();
        tableModel.setRowCount(0);
        for (Ordonnance o : ordonnances) {
            tableModel.addRow(new Object[]{o.getIdOrd(), o.getDateOrd(), o.getDescriptionOrd(), o.getIdPat()});
        }
    } catch (SQLException e) {
        showError(e);
    }
}

private void clearFields() {
    txtDate.setText("");
    txtDescription.setText("");
    txtIdPatient.setText("");
}

private void showError(Exception e) {
    JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
}


}
