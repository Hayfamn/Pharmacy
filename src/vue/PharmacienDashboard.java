package vue;

import javax.swing.*;
import java.awt.*;
import modele.Utilisateur;

public class PharmacienDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    private Utilisateur pharmacien;

    public PharmacienDashboard(Utilisateur pharmacien) {
        this.pharmacien = pharmacien;
        setTitle("Tableau de bord - Pharmacien");
        setSize(555, 397);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10)); 
        panel.setOpaque(false);

        ImageIcon backgroundIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/back.png");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(464, 389, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        setContentPane(backgroundLabel);
        getContentPane().setLayout(new BorderLayout());
        
        JButton btnVoirPatient = new JButton("Patients");
        ImageIcon iconPatient = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/patients.png");
        Image imgPatient = iconPatient.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnVoirPatient.setIcon(new ImageIcon(imgPatient));

        JButton btnVoirMedicaments = new JButton("Médicaments");
        ImageIcon iconMedicaments = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/med.jpg");
        Image imgMedicaments = iconMedicaments.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnVoirMedicaments.setIcon(new ImageIcon(imgMedicaments));

        JButton btnGérerOrdonnance = new JButton("Gérer une ordonnance");
        ImageIcon iconOrdonnance = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/ord.png");
        Image imgOrdonnance = iconOrdonnance.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnGérerOrdonnance.setIcon(new ImageIcon(imgOrdonnance));

        JButton btnProfil = new JButton("Profil");
        ImageIcon iconProfil = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/profil.png");
        Image scaledImage = iconProfil.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnProfil.setIcon(new ImageIcon(scaledImage));

        JButton btnDeconnexion = new JButton("Log Out");
        ImageIcon iconLogout = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/logout.png");
        Image imgLogout = iconLogout.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnDeconnexion.setIcon(new ImageIcon(imgLogout));

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnVoirPatient);
        panel.add(btnVoirMedicaments);
        panel.add(btnGérerOrdonnance);
        panel.add(btnProfil);
        panel.add(btnDeconnexion);

        getContentPane().add(panel, BorderLayout.CENTER);

        btnProfil.addActionListener(e -> new ProfilFrame(pharmacien).setVisible(true));
        btnVoirPatient.addActionListener(e -> new ListePatientsFrame().setVisible(true));
        btnVoirMedicaments.addActionListener(e -> new ListeMedicamentsFrame().setVisible(true));
        btnGérerOrdonnance.addActionListener(e -> {
			new GererOrdonnanceFrame().setVisible(true);
		});
        btnDeconnexion.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });
    }
}
