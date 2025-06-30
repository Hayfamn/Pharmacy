package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.MedicamentDAO;
import modele.Medicament;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class GestionStockMedicament extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;
    private JTextField txtStock;
    private List<Medicament> medicaments;
    private int selectedIndex = -1;

    public GestionStockMedicament() {
        setTitle("Gérer le stock des médicaments");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        loadMedicaments();
    }

    private void initComponents() {
        model = new DefaultTableModel(new Object[]{"ID", "Nom", "Stock", "Prix", "Description"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelBas = new JPanel(new FlowLayout());
        txtStock = new JTextField(10);
        JButton btnModifierStock = new JButton("Mettre à jour le stock");
        btnModifierStock.setForeground(new Color(64, 128, 128));
        JButton btnFermer = new JButton("Fermer");
        btnFermer.setForeground(new Color(64, 128, 128));

        JLabel label = new JLabel("Nouveau stock :");
        label.setForeground(new Color(64, 128, 128));
        panelBas.add(label);
        panelBas.add(txtStock);
        panelBas.add(btnModifierStock);
        panelBas.add(btnFermer);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBas, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e -> {
            selectedIndex = table.getSelectedRow();
            if (selectedIndex != -1 && selectedIndex < medicaments.size()) {
                Medicament m = medicaments.get(selectedIndex);
                txtStock.setText(String.valueOf(m.getStock()));
            }
        });

        btnModifierStock.addActionListener(e -> {
            if (selectedIndex != -1 && selectedIndex < medicaments.size()) {
                try {
                    int newStock = Integer.parseInt(txtStock.getText().trim());
                    Medicament m = medicaments.get(selectedIndex);
                    m.setStock(newStock);
                    new MedicamentDAO().updateStock(m.getIdMed(), newStock);
                    loadMedicaments();
                    txtStock.setText("");
                    table.clearSelection();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Stock invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur DB : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFermer.addActionListener(e -> dispose());
    }

    private void loadMedicaments() {
        try {
            medicaments = new MedicamentDAO().getAll();
            model.setRowCount(0);
            for (Medicament m : medicaments) {
                model.addRow(new Object[]{
                        m.getIdMed(), m.getNomMed(), m.getStock(), m.getPrix(), m.getDescriptionMed()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
