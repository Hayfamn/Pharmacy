package vue;

import dao.UtilisateurDAO;
import modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AjoutUtilisateur extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtNom;
    private JPasswordField txtMotDePasse;
    private JComboBox<String> comboType;
    private JButton btnAjouter, btnAnnuler;

    public AjoutUtilisateur() {
        setTitle("Add User");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        ImageIcon backgroundIcon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/back.png");
        Image backgroundImage = backgroundIcon.getImage();

        JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/logoPhar.png");
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(img));
        topPanel.add(iconLabel, BorderLayout.WEST);

        JLabel title = new JLabel("SIGN IN", SwingConstants.CENTER);
        title.setForeground(new Color(64, 128, 128));
        title.setFont(new Font("Arial Black", Font.BOLD, 22));
        topPanel.add(title, BorderLayout.CENTER);

        backgroundPanel.add(topPanel, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        txtNom = new JTextField(20);
        contentPanel.add(txtNom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        txtMotDePasse = new JPasswordField(20);
        contentPanel.add(txtMotDePasse, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        comboType = new JComboBox<>(new String[]{"Admin", "pharmacien"});
        contentPanel.add(comboType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setOpaque(false);

        btnAjouter = new JButton("Add User");
        btnAnnuler = new JButton("Exit");

        btnPanel.add(btnAjouter);
        btnPanel.add(btnAnnuler);

        contentPanel.add(btnPanel, gbc);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);

        btnAjouter.addActionListener(e -> ajouterUtilisateur());
        btnAnnuler.addActionListener(e -> dispose());
    }

    private void ajouterUtilisateur() {
        String nom = txtNom.getText().trim();
        String motDePasse = new String(txtMotDePasse.getPassword()).trim();
        String type = comboType.getSelectedItem().toString();

        if (nom.isEmpty() || motDePasse.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Utilisateur nouveau = new Utilisateur(nom, motDePasse, type);
        UtilisateurDAO dao = new UtilisateurDAO();

        try {
            dao.insert(nouveau);
            JOptionPane.showMessageDialog(this, "Utilisateur ajouté avec succès !");
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
}
