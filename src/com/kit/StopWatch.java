package com.kit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopWatch extends JPanel {

    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton resetButton = new JButton("Reset");
    private JLabel timeLabel = new JLabel();
    private Font font= new Font(Font.MONOSPACED, 1, 20);

    private int elapsedTime = 0;
    private int milliseconds = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    private String millisecondsString = String.format("%01d", milliseconds);
    private String secondsString = String.format("%02d", seconds);
    private String minutesString = String.format("%02d", minutes);
    private String hoursString = String.format("%02d", hours);

    private Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime+=100;
            milliseconds = (elapsedTime/100)%10;
            seconds = (elapsedTime/1000)%60;
            minutes = (elapsedTime/60000)%60;
            hours = (elapsedTime/3600000)%24;

            millisecondsString = String.format("%01d", milliseconds);
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            hoursString = String.format("%02d", hours);

            timeLabel.setText(hoursString + " : " + minutesString + " : " + secondsString + "." + millisecondsString);

        }
    });

    public StopWatch () {

        this. setBounds(0,50,300,250);
        this.setLayout(null);

        timeLabel.setText(hoursString + " : " + minutesString + " : " + secondsString + "." + millisecondsString);
        timeLabel.setBounds(20,20,250,50);
        timeLabel.setFont(font);
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        this.add(timeLabel);

        startButton.setBounds(40, 80, 100, 50);
        startButton.setFont(font);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> timer.start());
        this.add(startButton);

        stopButton.setBounds(140, 80, 100, 50);
        stopButton.setFont(font);
        stopButton.setFocusable(false);
        stopButton.addActionListener(e -> timer.stop());
        this.add(stopButton);

        resetButton.setBounds(40, 130, 200, 50);
        resetButton.setFont(font);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> {
            timer.stop();
            elapsedTime=0;
            milliseconds=0;
            hours=0;
            minutes=0;
            seconds=0;
            millisecondsString = String.format("%01d", seconds);
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            hoursString = String.format("%02d", hours);
            timeLabel.setText(hoursString + " : " + minutesString + " : " + secondsString + "." + millisecondsString);
        });
        this.add(resetButton);
    }
}
