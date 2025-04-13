package cl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class GameUI extends JFrame {
    private JLabel timerLabel, questionLabel, scoreLabel;
    private Timer timer;
    private int timeLeft = 30; // Thời gian đếm ngược
    private int currentQuestion = 1; // Câu hỏi hiện tại
    private int score = 0; // Điểm số người chơi
    private JButton[] answerButtons = new JButton[4];

    public GameUI() {
        setTitle("Ai Là Triệu Phú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(220, getHeight()));
        
        JPanel helpPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        helpPanel.setBackground(new Color(200, 230, 250));
        helpPanel.setPreferredSize(new Dimension(220, 60));

        JButton btn5050 = new JButton(new ImageIcon("ALTP/img.png"));
        JButton btnCall = new JButton(new ImageIcon("ALTP/img_1.png"));
        JButton btnAudience = new JButton(new ImageIcon("ALTP/img_2.png"));

        customizeButton(btn5050);
        customizeButton(btnCall);
        customizeButton(btnAudience);

        helpPanel.add(btn5050);
        helpPanel.add(btnCall);
        helpPanel.add(btnAudience);

        JPanel moneyPanel = new JPanel(new GridLayout(15, 1));
        moneyPanel.setBackground(new Color(200, 230, 250));

        String[] rewards = {
                "100.000", "200.000", "300.000", "500.000", "1.000.000",
                "2.000.000", "3.600.000", "6.000.000", "9.000.000", "15.000.000",
                "25.000.000", "35.000.000", "50.000.000", "80.000.000", "150.000.000"
        };

        for (int i = 14; i >= 0; i--) {
            JLabel moneyLabel = new JLabel((i + 1) + ": " + rewards[i] + " VNĐ", SwingConstants.CENTER);
            moneyLabel.setFont(new Font("Arial", Font.BOLD, 16));
            moneyLabel.setForeground(i % 5 == 4 ? Color.RED : Color.BLUE);
            moneyPanel.add(moneyLabel);
        }

        rightPanel.add(helpPanel, BorderLayout.NORTH);
        rightPanel.add(moneyPanel, BorderLayout.CENTER);

        // ====== Panel chứa câu hỏi và đáp án ======
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(getWidth(), 200));
        mainPanel.setBackground(new Color(30, 30, 80));

        JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionPanel.setBackground(new Color(30, 30, 80));

        questionLabel = new JLabel("Câu hỏi hiển thị tại đây", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setForeground(Color.WHITE);

        timerLabel = new JLabel("Thời gian: " + timeLeft + " giây", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setForeground(Color.RED);

        scoreLabel = new JLabel("Điểm: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(Color.YELLOW);

        questionPanel.add(questionLabel);
        questionPanel.add(timerLabel);
        questionPanel.add(scoreLabel);

        startTimer();

        JPanel answerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        answerPanel.setBackground(new Color(20, 20, 70));

        String[] answers = {"A: Đáp án A", "B: Đáp án B", "C: Đáp án C", "D: Đáp án D"};
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton(answers[i]);
            customizeAnswerButton(answerButtons[i]);
            answerButtons[i].addActionListener(new AnswerListener());
            answerPanel.add(answerButtons[i]);
        }

        mainPanel.add(questionPanel, BorderLayout.NORTH);
        mainPanel.add(answerPanel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(60, 60));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    private void customizeAnswerButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(50, 50, 200));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Thời gian: " + timeLeft + " giây");
                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Hết thời gian! Bạn thua cuộc.");
                }
            }
        });
        timer.start();
    }

    private class AnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            timer.stop();

            if (new Random().nextBoolean()) { // Giả lập đáp án đúng/sai
                clickedButton.setBackground(Color.GREEN);
                score++;
                scoreLabel.setText("Điểm: " + score);
                JOptionPane.showMessageDialog(null, "Chính xác! Chuyển sang câu tiếp theo.");
                nextQuestion();
            } else {
                clickedButton.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null, "Sai rồi! Trò chơi kết thúc.");
                System.exit(0);
            }
        }
    }

    private void nextQuestion() {
        currentQuestion++;
        questionLabel.setText("Câu hỏi " + currentQuestion + " hiển thị tại đây");
        timeLeft = 30;
        timerLabel.setText("Thời gian: " + timeLeft + " giây");
        startTimer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}
