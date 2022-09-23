package com.kit;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    private JPanel topPanel;
    private JPanel bottomPanel;

    private CardLayout cardLayout = new CardLayout();
    private Container container = getContentPane();

    private JPanel stopWatch = new StopWatch();
    private JPanel timer = new MyTimer();
    private JPanel clock = new MyClock();
    private JPanel alarmClock = new MyAlarmClock();

    private JButton clockButton;
    private JButton alarmClockButton;
    private JButton stopWatchButton;
    private JButton timerButton;

    private Font font= new Font(Font.MONOSPACED, 1, 12);

    public static void main(String[] args) {
	Main main = new Main();
    main.start();
    }

    private void start() {
        JFrame mainFrame = new JFrame("Clock");

        topPanel = new JPanel();
        topPanel.setBounds(0,0,300,50);
        topPanel.setLayout(null);
        mainFrame.add(topPanel);

        bottomPanel = new JPanel(cardLayout);
        bottomPanel.setBounds(0,50,300,250);
        bottomPanel.setLayout(cardLayout);
        bottomPanel.add(clock, "clock");
        bottomPanel.add(alarmClock, "alarmClock");
        bottomPanel.add(stopWatch, "stopWatch");
        bottomPanel.add(timer, "timer");
        mainFrame.add(bottomPanel);


        clockButton = new JButton("Clock");
        clockButton.setBounds(0,0,75,50);
        clockButton.setFont(font);
        clockButton.setFocusable(false);
        clockButton.addActionListener(e -> {
            cardLayout.show(bottomPanel, "clock");
        });
        topPanel.add(clockButton);

        alarmClockButton = new JButton("<html><center>Alarm<br />Clock</center></html>");
        alarmClockButton.setBounds(75,0,75,50);
        alarmClockButton.setFont(font);
        alarmClockButton.setFocusable(false);
        alarmClockButton.addActionListener(e -> {
            cardLayout.show(bottomPanel, "alarmClock");
        });
        topPanel.add(alarmClockButton);

        stopWatchButton = new JButton("<html><center>Stop<br />Watch</center></html>");
        stopWatchButton.setBounds(150,0,75,50);
        stopWatchButton.setFont(font);
        stopWatchButton.setFocusable(false);
        stopWatchButton.addActionListener(e -> {
            cardLayout.show(bottomPanel, "stopWatch");
        });
        topPanel.add(stopWatchButton);

        timerButton = new JButton("Timer");
        timerButton.setBounds(225,0,75,50);
        timerButton.setFont(font);
        timerButton.setFocusable(false);
        timerButton.addActionListener(e -> {
            cardLayout.show(bottomPanel, "timer");
        });
        topPanel.add(timerButton);

        mainFrame.setDefaultCloseOperation(3);
        mainFrame.setSize(315,300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }
}
