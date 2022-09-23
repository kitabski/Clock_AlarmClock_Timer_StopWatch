package com.kit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyClock extends JPanel {
    private JLabel clockLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private JLabel timeZoneLabel = new JLabel();

    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeZoneFormat;

    private Font font = new Font (Font.MONOSPACED, 1, 20);
    private String clockString;
    private String dateString;
    private String timeZoneString;


    private Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            clockString = timeFormat.format(Calendar.getInstance().getTime());
            dateString = dateFormat.format(Calendar.getInstance().getTime());
            timeZoneString = timeZoneFormat.format(Calendar.getInstance().getTime());

            clockLabel.setText(clockString);
            dateLabel.setText(dateString);
            timeZoneLabel.setText("Week #" + timeZoneString);
        }
    });

    public MyClock () {
        this.setBounds(0,50,300,250);
        this.setLayout(null);

        timeFormat = new SimpleDateFormat("hh : mm : ss a");
        dateFormat = new SimpleDateFormat("dd . MM . YYYY");
        timeZoneFormat = new SimpleDateFormat("w || X");

        clockLabel.setBounds(20,20,250,50);
        clockLabel.setFont(font);
        clockLabel.setBorder(BorderFactory.createBevelBorder(1));
        clockLabel.setHorizontalAlignment(JTextField.CENTER);
        this.add(clockLabel);

        dateLabel.setBounds(20,80,250,50);
        dateLabel.setFont(font);
        dateLabel.setBorder(BorderFactory.createBevelBorder(1));
        dateLabel.setHorizontalAlignment(JTextField.CENTER);
        this.add(dateLabel);

        timeZoneLabel.setBounds(20,140,250,50);
        timeZoneLabel.setFont(font);
        timeZoneLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeZoneLabel.setHorizontalAlignment(JTextField.CENTER);
        this.add(timeZoneLabel);

        timer.start ();
    }
}
