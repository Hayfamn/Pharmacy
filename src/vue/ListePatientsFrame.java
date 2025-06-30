package vue;

import dao.PatientDAO;
import modele.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListePatientsFrame extends JFrame {

   
	private static final long serialVersionUID = 1L;

	public ListePatientsFrame() {
        setTitle("Liste des Patients et leurs Crédits");
        setSize(675, 458);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel background = new JLabel(new ImageIcon("src/vue/background.jpg"));
        setContentPane(background);
        background.setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon originalIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/patients.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(scaledImage);
        JLabel icon = new JLabel(imgIcon);
        JLabel title = new JLabel("Patients");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(0, 128, 128));
        headerPanel.add(icon);
        headerPanel.add(title);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        
        String[] columnNames = {"ID", "Nom", "Crédit"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        try {
            PatientDAO dao = new PatientDAO();
            List<Patient> patients = dao.getAll();

            for (Patient p : patients) {
                Object[] row = {p.getIdPat(), p.getNomPat(), p.getCredit()};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

       
        JButton btnRetour = new JButton("Retour");
        btnRetour.setForeground(new Color(128, 128, 128));
        btnRetour.setFont(new Font("Arial", Font.BOLD, 16));
        btnRetour.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnRetour);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }
}
