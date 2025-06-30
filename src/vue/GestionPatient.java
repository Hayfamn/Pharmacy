package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import dao.PatientDAO;
import modele.Patient;

public class GestionPatient extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtNom;
    private JTextField txtCredit;
    private JTable patientTable;
    private DefaultTableModel tableModel;
    private List<Patient> patients;

    public GestionPatient() {
        setTitle("Gestion des patients");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        loadPatientsFromDB();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(2, 2));
        formPanel.setOpaque(false);

        ImageIcon backgroundIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/back.png");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(464, 389, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        setContentPane(backgroundLabel);
        setLayout(new BorderLayout());
        
        formPanel.add(new JLabel("Nom :"));
        txtNom = new JTextField();
        formPanel.add(txtNom);
        formPanel.add(new JLabel("Crédit :"));
        txtCredit = new JTextField();
        formPanel.add(txtCredit);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setForeground(new Color(64, 128, 128));
        JButton btnModifier = new JButton("Modifier");
        btnModifier.setForeground(new Color(64, 128, 128));
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setForeground(new Color(255, 0, 0));
        JButton btnFermer = new JButton("Fermer");
        btnFermer.setForeground(new Color(64, 128, 128));

        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnFermer);

        
        String[] columnNames = {"ID", "Nom", "Crédit"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        patientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(patientTable);

        patientTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < patients.size()) {
                Patient p = patients.get(selectedRow);
                txtNom.setText(p.getNomPat());
                txtCredit.setText(String.valueOf(p.getCredit()));
            }
        });

        btnAjouter.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String creditStr = txtCredit.getText().trim();
            if (!nom.isEmpty() && !creditStr.isEmpty()) {
                try {
                    double credit = Double.parseDouble(creditStr);
                    Patient p = new Patient(nom, credit);
                    new PatientDAO().insert(p);
                    loadPatientsFromDB();
                    txtNom.setText("");
                    txtCredit.setText("");
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            }
        });

        btnModifier.addActionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < patients.size()) {
                String nom = txtNom.getText().trim();
                String creditStr = txtCredit.getText().trim();
                if (!nom.isEmpty() && !creditStr.isEmpty()) {
                    try {
                        double credit = Double.parseDouble(creditStr);
                        Patient patientModifie = patients.get(selectedRow);
                        patientModifie.setNomPat(nom);
                        patientModifie.setCredit(credit);
                        new PatientDAO().update(patientModifie);
                        loadPatientsFromDB();
                        txtNom.setText("");
                        txtCredit.setText("");
                        patientTable.clearSelection();
                    } catch (NumberFormatException | SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un patient.");
            }
        });

        btnSupprimer.addActionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < patients.size()) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Êtes-vous sûr de vouloir supprimer ce patient ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Patient patientASupprimer = patients.get(selectedRow);
                        new PatientDAO().delete(patientASupprimer);
                        loadPatientsFromDB();
                        txtNom.setText("");
                        txtCredit.setText("");
                        patientTable.clearSelection();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un patient à supprimer.");
            }
        });

        btnFermer.addActionListener(e -> dispose());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadPatientsFromDB() {
        try {
            patients = new PatientDAO().getAll();
            tableModel.setRowCount(0); 
            for (Patient p : patients) {
                tableModel.addRow(new Object[]{p.getIdPat(), p.getNomPat(), p.getCredit()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des patients : " + e.getMessage());
        }
    }
}
