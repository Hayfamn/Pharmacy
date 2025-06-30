package vue;

import dao.MedicamentDAO;
import modele.Medicament;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListeMedicamentsFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;
    private JTable table;
    private JComboBox<String> comboNom;

    public ListeMedicamentsFrame() {
        setTitle("Liste des Médicaments");
        setSize(669, 471);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel background = new JLabel(new ImageIcon("src/vue/background.jpg")); 
        setContentPane(background);
        background.setLayout(new BorderLayout());


   
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon originalIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/medicaments.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(scaledImage);

        JLabel icon = new JLabel(imgIcon);
        JLabel title = new JLabel("Médicaments");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(0, 128, 128));

        headerPanel.add(icon);
        headerPanel.add(title);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblNom = new JLabel("Nom Médicament");
        lblNom.setFont(new Font("Arial", Font.BOLD, 18));
        lblNom.setForeground(new Color(128, 128, 128));

        comboNom = new JComboBox<>();
        remplirComboBox();

        JButton btnRechercher = new JButton("🔍 Rechercher");
        btnRechercher.addActionListener(e -> rechercherMedicament());

        searchPanel.add(lblNom);
        searchPanel.add(comboNom);
        searchPanel.add(btnRechercher);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        getContentPane().add(topPanel, BorderLayout.NORTH);


        String[] cols = {"ID", "NOM Med", "DESCRIPTION", "STOCK"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

 
        JPanel bottomPanel = new JPanel();

        JButton btnAfficherTous = new JButton("Afficher tous");
        btnAfficherTous.setFont(new Font("Arial", Font.BOLD, 16));
        btnAfficherTous.setForeground(new Color(0, 128, 128));
        btnAfficherTous.addActionListener(e -> afficherTous());

        JButton btnRetour = new JButton("Retour");
        btnRetour.setFont(new Font("Arial", Font.BOLD, 16));
        btnRetour.setForeground(Color.GRAY);
        btnRetour.addActionListener(e -> dispose());

        bottomPanel.add(btnAfficherTous);
        bottomPanel.add(btnRetour);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        afficherTous();
    }

    private void remplirComboBox() {
        try {
            MedicamentDAO dao = new MedicamentDAO();
            List<Medicament> liste = dao.getAll();
            for (Medicament m : liste) {
                comboNom.addItem(m.getNomMed());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur chargement noms : " + e.getMessage());
        }
    }

    private void rechercherMedicament() {
        String nomChoisi = (String) comboNom.getSelectedItem();
        try {
            MedicamentDAO dao = new MedicamentDAO();
            List<Medicament> resultats = dao.getByName(nomChoisi);
            model.setRowCount(0);
            for (Medicament m : resultats) {
                model.addRow(new Object[]{m.getIdMed(), m.getNomMed(), m.getDescriptionMed(), m.getStock()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur recherche : " + e.getMessage());
        }
    }

    private void afficherTous() {
        try {
            MedicamentDAO dao = new MedicamentDAO();
            List<Medicament> liste = dao.getAll();
            model.setRowCount(0);
            for (Medicament m : liste) {
                model.addRow(new Object[]{m.getIdMed(), m.getNomMed(), m.getDescriptionMed(), m.getStock()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur affichage : " + e.getMessage());
        }
    }
}
