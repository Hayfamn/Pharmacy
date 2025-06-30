package vue;

import javax.swing.*;
import java.awt.*;
import modele.Utilisateur;

public class AdminDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    private Utilisateur admin;

    public AdminDashboard(Utilisateur admin) {
        this.admin = admin;
        setTitle("Tableau de bord - Administrateur");
        setSize(555, 397);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10)); 
        panel.setOpaque(false);

        ImageIcon backgroundIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/back.png");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(464, 389, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        setContentPane(backgroundLabel);
        getContentPane().setLayout(new BorderLayout());

        JButton btnProfil = new JButton("Profil");
        ImageIcon iconProfil = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/profil.png");
        Image scaledImage = iconProfil.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnProfil.setIcon(new ImageIcon(scaledImage));

        JButton btnGererPatients = new JButton("Gérer les patients");
        ImageIcon iconPatients = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/patients.png");
        Image imgPatients = iconPatients.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnGererPatients.setIcon(new ImageIcon(imgPatients));

        JButton btnGererMedicaments = new JButton("Gérer les médicaments");
        ImageIcon iconMedicaments = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/med.jpg");
        Image imgMedicaments = iconMedicaments.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnGererMedicaments.setIcon(new ImageIcon(imgMedicaments));

        JButton btnGererStock = new JButton("Gérer le stock d’un médicament");
        ImageIcon iconStock = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/stock.png");
        Image imgStock = iconStock.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnGererStock.setIcon(new ImageIcon(imgStock));

        JButton btnGererUtilisateurs = new JButton("Gérer les utilisateurs");
        ImageIcon iconUsers = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/users.png");
        Image imgUsers = iconUsers.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnGererUtilisateurs.setIcon(new ImageIcon(imgUsers));

        JButton btnDeconnexion = new JButton("Log Out");
        ImageIcon iconLogout = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/logout.png");
        Image imgLogout = iconLogout.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnDeconnexion.setIcon(new ImageIcon(imgLogout));

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnGererPatients);
        panel.add(btnGererMedicaments);
        panel.add(btnGererStock);
        panel.add(btnGererUtilisateurs);
        panel.add(btnProfil);
        panel.add(btnDeconnexion);

        getContentPane().add(panel, BorderLayout.CENTER);

        btnGererPatients.addActionListener(e -> new GestionPatient().setVisible(true));
        btnGererMedicaments.addActionListener(e -> new GestionMedicament().setVisible(true));
        btnGererStock.addActionListener(e -> new GestionStockMedicament().setVisible(true));
        btnProfil.addActionListener(e -> new ProfilFrame(admin).setVisible(true));

        btnGererUtilisateurs.addActionListener(e -> new GererUtilisateurFrame().setVisible(true));

        btnDeconnexion.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });
    }
}
