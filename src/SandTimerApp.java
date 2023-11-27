import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SandTimerApp extends JFrame {
    private SandTimerPanel sandTimerPanel;
    private Timer timer;

    public SandTimerApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Песочные часы");

        // Создание панели с изображением песочных часов
        sandTimerPanel = new SandTimerPanel();
        add(sandTimerPanel, BorderLayout.CENTER);

        // Создание кнопок "Старт" и "Стоп"
        JButton startButton = new JButton("Старт");
        JButton stopButton = new JButton("Стоп");

        // Обработчик события для кнопки "Старт"
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        // Обработчик события для кнопки "Стоп"
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        // Панель для размещения кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void startTimer() {
        if (timer == null || !timer.isRunning()) {
            // Запуск таймера с интервалом 100 миллисекунд
            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Выполнение задачи в отдельном потоке
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            sandTimerPanel.updateSandLevel();
                            sandTimerPanel.repaint();
                        }
                    });
                }
            });
            timer.start();
        }
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SandTimerApp app = new SandTimerApp();
                app.setVisible(true);
            }
        });
    }
}
