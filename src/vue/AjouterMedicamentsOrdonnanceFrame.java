package vue;

import javax.swing.*;
import dao.LigneMedicamentDAO;
import dao.MedicamentDAO;
import modele.LigneMedicament;
import modele.Medicament;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AjouterMedicamentsOrdonnanceFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JComboBox<Medicament> medicamentComboBox;  
    private JTextField txtQuantite;
    private int idOrdonnance;

    private List<Medicament> medicamentList = new ArrayList<>();

    public AjouterMedicamentsOrdonnanceFrame(int idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
        setTitle("Ajouter Médicaments à l'Ordonnance");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        chargerMedicaments();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(245, 245, 255)); 

        JLabel lblMedicament = new JLabel("Médicament :");
        lblMedicament.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(lblMedicament);

        medicamentComboBox = new JComboBox<>();
        medicamentComboBox.setBackground(new Color(230, 240, 250));
        medicamentComboBox.setRenderer(new MedicamentListCellRenderer());
        panel.add(medicamentComboBox);

        JLabel lblQuantite = new JLabel("Quantité :");
        lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(lblQuantite);

        txtQuantite = new JTextField();
        panel.add(txtQuantite);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setBackground(new Color(204, 229, 255));
        btnAjouter.setForeground(new Color(40, 60, 90));
        btnAjouter.addActionListener(this::ajouterMedicament);

        panel.add(new JLabel());
        panel.add(btnAjouter);

        getContentPane().add(panel);
    }

    private void chargerMedicaments() {
        try {
            MedicamentDAO dao = new MedicamentDAO();
            medicamentList = dao.getAll();
            medicamentComboBox.removeAllItems();
            for (Medicament m : medicamentList) {
                medicamentComboBox.addItem(m); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des médicaments : " + e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ajouterMedicament(ActionEvent e) {
        try {
            Medicament med = (Medicament) medicamentComboBox.getSelectedItem();
            if (med == null) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un médicament.");
                return;
            }

            String quantiteStr = txtQuantite.getText().trim();
            if (quantiteStr.isEmpty() || !quantiteStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir une quantité numérique valide.");
                return;
            }

            int quantite = Integer.parseInt(quantiteStr);

            if (quantite > med.getStock()) {
                JOptionPane.showMessageDialog(this, "Quantité supérieure au stock disponible.");
                return;
            }

           
            LigneMedicament ligne = new LigneMedicament(idOrdonnance, med.getIdMed(), quantite);
            new LigneMedicamentDAO().insert(ligne);

            
            med.setStock(med.getStock() - quantite);
            new MedicamentDAO().update(med);

            JOptionPane.showMessageDialog(this, "Médicament ajouté avec succès !");
            dispose();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir une quantité valide.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static class MedicamentListCellRenderer extends DefaultListCellRenderer {
      
		private static final long serialVersionUID = 1L;

		@Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Medicament med) {
                setText("[" + med.getIdMed() + "] " + med.getNomMed() +
                        " | Prix: " + med.getPrix() + " DH | Stock: " + med.getStock() +
                        " | Desc: " + med.getDescriptionMed());
            }
            return c;
        }
    }
}
