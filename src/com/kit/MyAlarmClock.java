package com.kit;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MyAlarmClock extends JPanel {
    private JComboBox<ArrayList> apmBox;

    private JLabel hoursLabel = new JLabel("HH");
    private JLabel minutesLabel = new JLabel("MM");

    private JSpinner hoursSpinner, minutesSpinner;

    private JButton startAlarmButton = new JButton("Start");
    private JButton stopAlarmButton = new JButton("Stop");

    private Font font= new Font(Font.MONOSPACED, 1, 20);

    private Calendar currentTime = Calendar.getInstance();
    private Clip clip;

    private int setTime;
    private int curTime;

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date date = new Date();
            curTime = date.getHours() + date.getMinutes();

            if (setTime == curTime) {
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
                timer.stop();
            }
        }
    });

    public MyAlarmClock () {
        this.setBounds(0,50,300,250);
        this.setLayout(null);

        ArrayList<String> apm = new ArrayList<>(Arrays.asList("AM", "PM"));
        apmBox = new JComboBox(apm.toArray());
        apmBox.setBounds(20, 60, 50,30);
        apmBox.setFont(font);
        this.add(apmBox);

        hoursLabel.setBounds(120, 30, 50,30);
        hoursLabel.setFont(font);
        this.add(hoursLabel);

        SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 12, 1);
        hoursSpinner = new JSpinner();
        hoursSpinner.setEditor(new JSpinner.DefaultEditor(hoursSpinner));
        hoursSpinner.setBounds(110, 60, 50,30);
        hoursSpinner.setFont(font);
        hoursSpinner.setBorder(BorderFactory.createBevelBorder(1));
        hoursSpinner.setModel(hourModel);
        this.add(hoursSpinner);

        minutesLabel.setBounds(220, 30, 50,30);
        minutesLabel.setFont(font);
        this.add(minutesLabel);

        SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        minutesSpinner = new JSpinner();
        minutesSpinner.setEditor(new JSpinner.DefaultEditor(minutesSpinner));
        minutesSpinner.setBounds(210, 60, 50,30);
        minutesSpinner.setFont(font);
        minutesSpinner.setBorder(BorderFactory.createBevelBorder(1));
        minutesSpinner.setModel(minuteModel);
        this.add(minutesSpinner);

        startAlarmButton.setBounds(40, 130, 100, 50);
        startAlarmButton.setFont(font);
        startAlarmButton.setFocusable(false);
        startAlarmButton.addActionListener(e -> {
            startAlarmButton.setEnabled(false);
            if (apmBox.getSelectedItem().equals("PM"))
                setTime = (int) hoursSpinner.getValue() + (int) minutesSpinner.getValue()+12;
            setTime = (int) hoursSpinner.getValue() + (int) minutesSpinner.getValue();
            timer.start();
        });
        this.add(startAlarmButton);

        stopAlarmButton.setBounds(140, 130, 100, 50);
        stopAlarmButton.setFont(font);
        stopAlarmButton.setFocusable(false);
        stopAlarmButton.addActionListener(e -> {
            startAlarmButton.setEnabled(true);
            hoursSpinner.setValue(0);
            minutesSpinner.setValue(0);
        });
        this.add(stopAlarmButton);
    }
}
