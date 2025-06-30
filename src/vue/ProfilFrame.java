package vue;



import javax.swing.*;

import java.awt.*;

import modele.Utilisateur;

public class ProfilFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    public ProfilFrame(Utilisateur utilisateur) {
        setTitle("Profil de l'utilisateur");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
 
        ImageIcon icon = new ImageIcon("C:/Users/Star Info/Desktop/Mini projet Java/GestionPharmacie/src/vue/profil.png"); // mets le chemin correct ici
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

  
        JLabel iconLabel = new JLabel(resizedIcon);

      
        JLabel header = new JLabel("Détails du profil");
        header.setFont(new Font("Arial", Font.BOLD, 20));

   
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(iconLabel);
        topPanel.add(header);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Nom d'utilisateur :"));
        infoPanel.add(new JLabel(utilisateur.getNomUtilisateur()));
        infoPanel.add(new JLabel("Mot de passe :"));
        infoPanel.add(new JLabel(utilisateur.getMotDePasse()));
        infoPanel.add(new JLabel("Type d'utilisateur :"));
        infoPanel.add(new JLabel(utilisateur.getType()));
        panel.add(infoPanel, BorderLayout.CENTER);
        JButton btnFermer = new JButton("Fermer");
        btnFermer.setForeground(new Color(64, 128, 128));
        btnFermer.addActionListener(e -> dispose());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnFermer);
        panel.add(btnPanel, BorderLayout.SOUTH);
        setContentPane(panel);

    }

}