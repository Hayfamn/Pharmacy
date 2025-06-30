package vue;

import javax.swing.*;
import java.awt.*;
import dao.UtilisateurDAO;
import modele.Utilisateur;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, btnAjouterUtilisateur;

    public Login() {
        setTitle("Connexion - Gestion Pharmacie");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setForeground(new Color(64, 128, 128));
        title.setFont(new Font("Arial Black", Font.BOLD, 22));
        topPanel.add(title, BorderLayout.CENTER);

        backgroundPanel.add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel label = new JLabel("Username:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setForeground(new Color(64, 128, 128));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));

        JLabel label_1 = new JLabel("Password:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_1.setForeground(new Color(64, 128, 128));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(label, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(label_1, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        backgroundPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);

        loginButton = new JButton("Log In");
        loginButton.setForeground(new Color(64, 128, 128));
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        loginButton.setPreferredSize(new Dimension(100, 30));

        btnAjouterUtilisateur = new JButton("Sign In");
        btnAjouterUtilisateur.setForeground(new Color(64, 128, 128));
        btnAjouterUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAjouterUtilisateur.setPreferredSize(new Dimension(100, 30));

        JButton btnReset = new JButton("Reset");
        btnReset.setForeground(new Color(255, 0, 0));
        btnReset.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(loginButton);
        buttonPanel.add(btnAjouterUtilisateur);
        buttonPanel.add(btnReset);

        btnReset.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> login());
        btnAjouterUtilisateur.addActionListener(e -> new AjoutUtilisateur().setVisible(true));
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        Utilisateur user = UtilisateurDAO.authentifier(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Bienvenue " + user.getNomUtilisateur());
            String type = user.getType().trim().toLowerCase();
            dispose();

            switch (type) {
                case "admin":
                    new AdminDashboard(user).setVisible(true);
                    break;
                case "pharmacien":
                    new PharmacienDashboard(user).setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Type d'utilisateur inconnu : " + type, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Identifiants invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (UtilisateurDAO.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun utilisateur trouvé. Veuillez en créer un.");
                new AjoutUtilisateur().setVisible(true);
            } else {
                new Login().setVisible(true);
            }
        });
    }
}
