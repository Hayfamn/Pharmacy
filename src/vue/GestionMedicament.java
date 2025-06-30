package vue;

import dao.MedicamentDAO;
import modele.Medicament;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class GestionMedicament extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtNom, txtPrix, txtStock, txtDescription;
    private DefaultTableModel tableModel;
    private JTable medicamentTable;
    private List<Medicament> medicaments;

    public GestionMedicament() {
        setTitle("Gestion des médicaments");
        setSize(609, 404);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        loadMedicamentsFromDB();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        
        formPanel.setOpaque(false);
        ImageIcon backgroundIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/back.png");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(464, 389, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        setContentPane(backgroundLabel);
        setLayout(new BorderLayout());
        
        formPanel.add(new JLabel("Nom :"));
        txtNom = new JTextField();
        formPanel.add(txtNom);

        formPanel.add(new JLabel("Prix :"));
        txtPrix = new JTextField();
        formPanel.add(txtPrix);

        formPanel.add(new JLabel("Stock :"));
        txtStock = new JTextField();
        formPanel.add(txtStock);

        formPanel.add(new JLabel("Description :"));
        txtDescription = new JTextField();
        formPanel.add(txtDescription);

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

        String[] columnNames = {"ID", "Nom", "Prix", "Stock", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
           
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        medicamentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(medicamentTable);

        btnAjouter.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String prixStr = txtPrix.getText().trim();
            String stockStr = txtStock.getText().trim();
            String description = txtDescription.getText().trim();

            if (!nom.isEmpty() && !prixStr.isEmpty() && !stockStr.isEmpty() && !description.isEmpty()) {
                try {
                    double prix = Double.parseDouble(prixStr);
                    int stock = Integer.parseInt(stockStr);

                    Medicament m = new Medicament(stock, nom, description, prix);
                    new MedicamentDAO().insert(m);
                    loadMedicamentsFromDB();
                    txtNom.setText("");
                    txtPrix.setText("");
                    txtStock.setText("");
                    txtDescription.setText("");
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            }
        });

        btnModifier.addActionListener(e -> {
            int selectedRow = medicamentTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < medicaments.size()) {
                String nom = txtNom.getText().trim();
                String prixStr = txtPrix.getText().trim();
                String stockStr = txtStock.getText().trim();
                String description = txtDescription.getText().trim();

                if (!nom.isEmpty() && !prixStr.isEmpty() && !stockStr.isEmpty() && !description.isEmpty()) {
                    try {
                        double prix = Double.parseDouble(prixStr);
                        int stock = Integer.parseInt(stockStr);
                        int id = medicaments.get(selectedRow).getIdMed();

                        Medicament med = new Medicament(id, nom, prix, stock, description);
                        new MedicamentDAO().update(med);
                        loadMedicamentsFromDB();
                        txtNom.setText("");
                        txtPrix.setText("");
                        txtStock.setText("");
                        txtDescription.setText("");
                        medicamentTable.clearSelection();
                    } catch (NumberFormatException | SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un médicament.");
            }
        });

        btnSupprimer.addActionListener(e -> {
            int selectedRow = medicamentTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < medicaments.size()) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Êtes-vous sûr de vouloir supprimer ce médicament ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Medicament medASupprimer = medicaments.get(selectedRow);
                        new MedicamentDAO().delete(medASupprimer);
                        loadMedicamentsFromDB();
                        txtNom.setText("");
                        txtPrix.setText("");
                        txtStock.setText("");
                        txtDescription.setText("");
                        medicamentTable.clearSelection();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un médicament à supprimer.");
            }
        });

        medicamentTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = medicamentTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < medicaments.size()) {
                Medicament m = medicaments.get(selectedRow);
                txtNom.setText(m.getNomMed());
                txtPrix.setText(String.valueOf(m.getPrix()));
                txtStock.setText(String.valueOf(m.getStock()));
                txtDescription.setText(m.getDescriptionMed());
            }
        });

        btnFermer.addActionListener(e -> dispose());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadMedicamentsFromDB() {
        try {
            medicaments = new MedicamentDAO().getAll();
            tableModel.setRowCount(0);
            for (Medicament m : medicaments) {
                tableModel.addRow(new Object[]{
                        m.getIdMed(),
                        m.getNomMed(),
                        m.getPrix(),
                        m.getStock(),
                        m.getDescriptionMed()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des médicaments : " + e.getMessage());
        }
    }
}
