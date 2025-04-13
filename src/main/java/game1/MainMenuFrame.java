package game1;

import Game.GameFrame;
import Game.LeaderboardFrame;
import Game.LoginFrame;
import Game.ProfileFrame;

import javax.swing.*;
import java.awt.*;

class GameMenuFrame extends JFrame {
    private String username;

    public GameMenuFrame(String username) {
        this.username = username;

        setTitle("AI LÀ TRIỆU PHÚ - MENU CHÍNH");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Nền màu
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0, 102, 204));
        add(mainPanel);

        // Header
        JLabel titleLabel = new JLabel("AI LÀ TRIỆU PHÚ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Các nút chính
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JButton playBtn = createButton("🎮 Bắt đầu chơi");
        JButton profileBtn = createButton("👤 Thông tin cá nhân");
        JButton leaderboardBtn = createButton("🏆 Bảng xếp hạng");

        centerPanel.add(playBtn);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(profileBtn);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(leaderboardBtn);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Góc trên phải - Tên người dùng + Đăng xuất
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.setOpaque(false);
        JLabel userLabel = new JLabel("👋 Xin chào, " + username + "  ");
        userLabel.setForeground(Color.WHITE);

        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setFocusPainted(false);

        topRightPanel.add(userLabel);
        topRightPanel.add(logoutBtn);

        mainPanel.add(topRightPanel, BorderLayout.SOUTH);

        // Sự kiện
        playBtn.addActionListener(e -> {
            dispose();
            new GameFrame(username);
        });

        profileBtn.addActionListener(e -> new ProfileFrame(username));
        leaderboardBtn.addActionListener(e -> new LeaderboardFrame());

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame(); // Quay về đăng nhập
        });

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(0, 51, 102));
        btn.setPreferredSize(new Dimension(250, 50));
        return btn;
    }

    public static void main(String[] args) {
        new GameMenuFrame("TungNguoiChoi");
    }
}
