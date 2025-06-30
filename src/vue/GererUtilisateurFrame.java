package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import dao.UtilisateurDAO;
import modele.Utilisateur;

public class GererUtilisateurFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtNomUtilisateur, txtMotDePasse, txtType;
    private JTable utilisateursTable;
    private DefaultTableModel tableModel;
    private List<Utilisateur> utilisateurs;

    public GererUtilisateurFrame() {
        setTitle("Gestion des utilisateurs");
        setSize(446, 326);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        loadUtilisateursFromDB();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.setOpaque(false);

        ImageIcon backgroundIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/back.png");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(464, 389, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        setContentPane(backgroundLabel);
        setLayout(new BorderLayout());
        formPanel.add(new JLabel("Nom d'utilisateur :"));
        txtNomUtilisateur = new JTextField();
        formPanel.add(txtNomUtilisateur);

        formPanel.add(new JLabel("Mot de passe :"));
        txtMotDePasse = new JTextField();
        formPanel.add(txtMotDePasse);

        formPanel.add(new JLabel("Type :"));
        txtType = new JTextField();
        formPanel.add(txtType);

        JPanel buttonPanel = new JPanel(new FlowLayout());
 
        JButton btnModifier = new JButton("Modifier");
        btnModifier.setForeground(new Color(64, 128, 128));
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setForeground(new Color(255, 0, 0));
        JButton btnFermer = new JButton("Fermer");
        btnFermer.setForeground(new Color(64, 128, 128));


        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnFermer);

        String[] columnNames = {"ID", "Nom d'utilisateur", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        utilisateursTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(utilisateursTable);

        utilisateursTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = utilisateursTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < utilisateurs.size()) {
                Utilisateur u = utilisateurs.get(selectedRow);
                txtNomUtilisateur.setText(u.getNomUtilisateur());
                txtMotDePasse.setText(u.getMotDePasse());
                txtType.setText(u.getType());
            }
        });

        

        btnModifier.addActionListener(e -> {
            int selectedRow = utilisateursTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < utilisateurs.size()) {
                try {
                    Utilisateur u = utilisateurs.get(selectedRow);
                    u.setNomUtilisateur(txtNomUtilisateur.getText().trim());
                    u.setMotDePasse(txtMotDePasse.getText().trim());
                    u.setType(txtType.getText().trim());
                    new UtilisateurDAO().update(u);
                    loadUtilisateursFromDB();
                    clearFields();
                    utilisateursTable.clearSelection();
                } catch (Exception ex) {
                    showError(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur à modifier.");
            }
        });

        btnSupprimer.addActionListener(e -> {
            int selectedRow = utilisateursTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < utilisateurs.size()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        new UtilisateurDAO().delete(utilisateurs.get(selectedRow));
                        loadUtilisateursFromDB();
                        clearFields();
                    } catch (SQLException ex) {
                        showError(ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur à supprimer.");
            }
        });

        btnFermer.addActionListener(e -> dispose());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadUtilisateursFromDB() {
        try {
            utilisateurs = new UtilisateurDAO().getAll();
            tableModel.setRowCount(0);
            for (Utilisateur u : utilisateurs) {
                tableModel.addRow(new Object[]{u.getId(), u.getNomUtilisateur(), u.getType()});
            }
        } catch (SQLException e) {
            showError(e);
        }
    }

    private void clearFields() {
        txtNomUtilisateur.setText("");
        txtMotDePasse.setText("");
        txtType.setText("");
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
