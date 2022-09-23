package com.kit;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer extends JPanel {
    private volatile int amountOfTime = 0;

    private final JLabel hoursLabel = new JLabel("Hours");
    private final JComboBox<ArrayList> hoursBox;
    private final JLabel minutesLabel = new JLabel("Minutes");
    private final JComboBox<ArrayList> minutesBox;
    private final JLabel secondsLabel = new JLabel("Seconds");
    private final JComboBox<ArrayList> secondsBox;

    private final JLabel timeRemainsLabel = new JLabel("Time remains:");
    private final JLabel timeLabel = new JLabel();

    private final JButton startButton = new JButton("Start");
    private final JButton resetButton = new JButton("Reset");
    private final Font font = new Font(Font.MONOSPACED, 1, 20);

    private Timer timer;
    private Clip clip;

    public MyTimer() {

        this.setBounds(0, 50, 300, 250);
        this.setLayout(null);

        hoursLabel.setBounds(20, 10, 100, 20);
        ArrayList<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String hour = String.valueOf(i);
            hours.add(hour);
        }
        hoursBox = new JComboBox(hours.toArray());
        hoursBox.setBounds(150, 10, 100, 20);
        hoursBox.addActionListener(e -> amountOfTime += Integer.parseInt(String.valueOf(hoursBox.getSelectedItem())) * 1000 * 60 * 60);
        this.add(hoursLabel);
        this.add(hoursBox);

        minutesLabel.setBounds(20, 35, 100, 20);
        ArrayList<String> minutes = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            String minute = String.valueOf(i);
            minutes.add(minute);
        }
        minutesBox = new JComboBox(minutes.toArray());
        minutesBox.setBounds(150, 35, 100, 20);
        minutesBox.addActionListener(e -> amountOfTime += Integer.parseInt(String.valueOf(minutesBox.getSelectedItem())) * 1000 * 60);
        this.add(minutesLabel);
        this.add(minutesBox);

        secondsLabel.setBounds(20, 60, 100, 20);
        ArrayList<String> seconds = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            String second = String.valueOf(i);
            seconds.add(second);
        }
        secondsBox = new JComboBox(seconds.toArray());
        secondsBox.setBounds(150, 60, 100, 20);
        secondsBox.addActionListener(e -> amountOfTime += Integer.parseInt(String.valueOf(secondsBox.getSelectedItem())) * 1000);
        this.add(secondsLabel);
        this.add(secondsBox);

        timeRemainsLabel.setBounds(20, 90, 100, 20);
        this.add(timeRemainsLabel);

        timeLabel.setBounds(150, 90, 100, 20);
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        this.add(timeLabel);

        startButton.setBounds(40, 130, 100, 50);
        startButton.setFont(font);
        startButton.setFocusable(false);
        startButton.addActionListener(e ->
        {
            startButton.setEnabled(false);
            timer = new Timer();
            TimerTask task1 = new TimerTask() {
                @Override
                public void run() {
                    File file = new File("C:\\JavaProjects\\MyProjects\\Beginner\\StopWatch\\src\\alarm.wav");
                    AudioInputStream inputStream;

                    try {
                        inputStream = AudioSystem.getAudioInputStream(file);
                        clip = AudioSystem.getClip();
                        clip.open(inputStream);
                        clip.start();
                    } catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                }
            };

            TimerTask task2 = new TimerTask() {
                @Override
                public void run() {
                    if (amountOfTime>0) {
                        String hoursString = String.format("%02d", amountOfTime/3600000);

                        String minutesString = String.format("%02d", amountOfTime/60000%60);

                        String secondsString = String.format("%02d", amountOfTime/1000%60);

                        timeLabel.setText(hoursString + " : " + minutesString + " : " + secondsString);
                        amountOfTime-=1000;
                    }
                    else timeLabel.setText("00 : 00 : 00");
                }
            };
            timer.schedule(task1, amountOfTime);
            timer.schedule(task2, 0, 1000);


        });
        amountOfTime = 0;
        this.add(startButton);

        resetButton.setBounds(140, 130, 100, 50);
        resetButton.setFont(font);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> {
            amountOfTime=0;
            startButton.setEnabled(true);
            timeLabel.setText("00 : 00 : 00");
            timer.cancel();
            hoursBox.setSelectedIndex(0);
            minutesBox.setSelectedIndex(0);
            secondsBox.setSelectedIndex(0);
            clip.stop();
        });
        this.add (resetButton);
    }
}
