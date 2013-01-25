/**
 * Copyright (c) 2013, Ansgar Klein
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: 
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies, 
 * either expressed or implied, of the FreeBSD Project.
 */

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

class SwingWindow extends JFrame {
    private JLabel titleLabel;
    private JEditorPane textpane1;
    private JEditorPane textpane2;

    JCheckBox numericOnlyCheckBox;
    JCheckBox splitWordsCheckBox;
    JCheckBox intelligentMatchingCheckBox;

    private boolean numericOnly;
    private boolean splitWords;
    private boolean intelligentMatching;

    public SwingWindow() {
        this.setSize(600,400);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create editor pane 1
        textpane1 = new JEditorPane();
        textpane1.setText("The quick brown fox jumps over the lazy dog");
        textpane1.setForeground(Color.GRAY);
        textpane1.setBackground(Color.BLACK);
        textpane1.setSelectionColor(Color.WHITE);
        textpane1.setSelectedTextColor(Color.BLACK);
        JScrollPane scroll1 = new JScrollPane(textpane1);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //Create editor pane 2
        textpane2 = new JEditorPane();
        textpane2.setText("The quick brown fox jumps over the lazy dog");
        textpane2.setEditable(false);
        textpane2.setForeground(Color.GREEN);
        textpane2.setBackground(Color.BLACK);
        textpane2.setSelectionColor(Color.GREEN);
        textpane2.setSelectedTextColor(Color.BLACK);
        JScrollPane scroll2 = new JScrollPane(textpane2);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.add(scroll1);
        textPanel.add(Box.createRigidArea(new Dimension(10,0)));
        textPanel.add(scroll2);

        //Create check box
        numericOnlyCheckBox = new JCheckBox("Only numeric 1337");
        numericOnly = true;
        numericOnlyCheckBox.setSelected(true);
        numericOnlyCheckBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (((JCheckBox)e.getItemSelectable()).isSelected()) {
                        numericOnly = true;
                    } else {
                        numericOnly = false;
                    }

                }
            });

        splitWordsCheckBox = new JCheckBox("Split words");
        splitWords = false;
        splitWordsCheckBox.setSelected(false);
        splitWordsCheckBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (((JCheckBox)e.getItemSelectable()).isSelected()) {
                        splitWords = true;
                    } else {
                        splitWords = false;
                    }

                }
            });

        intelligentMatchingCheckBox = new JCheckBox("Intelligent matching");
        intelligentMatching = false;
        intelligentMatchingCheckBox.setSelected(false);
        intelligentMatchingCheckBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (((JCheckBox)e.getItemSelectable()).isSelected()) {
                        numericOnlyCheckBox.setEnabled(false);
                        intelligentMatching = true;
                    } else {
                        numericOnlyCheckBox.setEnabled(true);
                        intelligentMatching = false;
                    }

                }
            });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(numericOnlyCheckBox);
        buttonPanel.add(Box.createRigidArea(new Dimension(15,0)));
        buttonPanel.add(splitWordsCheckBox);
        buttonPanel.add(Box.createRigidArea(new Dimension(15,0)));
        buttonPanel.add(intelligentMatchingCheckBox);

        //Create acction button
        JButton button = new JButton("Leetify !");
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textpane2.setText(leetify(textpane1.getText()));
                }
            });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(button);
        bottomPanel.add(Box.createRigidArea(new Dimension(5,0)));

        //Put everything together
        Container mainPanel = this.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(textPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(bottomPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        this.setVisible(true);
    }

    private String leetify(String str) {
        str = str.toLowerCase();

        if (splitWords) {
            str = str.replace(" ", "\n");
        }

        if (intelligentMatching) {

            //hacker -> h4xX0r
            str = str.replace("hacker", "h4xX0r");

            //hacked -> h4cKed
            str = str.replace("hacked", "h4cKed");

            //noob -> n00b / b00n
            if (Math.random()<0.5) {
                str = str.replace(" noob ", " n00b ");
            } else {
                str = str.replace(" noob ", " b00n ");
            }

            //leet -> 1337
            str = str.replace(" leet ", " 1337 ");

            //leetspeak / leet speak -> 1337 5P34K
            str = str.replace(" leetspeak ", " 1337 5P34K ");
            str = str.replace(" leet speak ", " 1337 5P34K ");

            //skills -> skills
            str = str.replace(" skills ", " 5k!11Zz ");
            str = str.replace(" skilled ", " 5k!113D ");

            //suck -> 5uxX
            str = str.replace(" suck ", " 5uxX ");
            str = str.replace(" sucks ", " 5uxXz ");

            //-s --> -z
            str = str.replace("s ", "z ");

            //omg -> 0m9
            str = str.replace(" omg ", " 0m9 ");

            //at -> @
            str = str.replace(" at ", " @ ");

            //dude -> d00d
            str = str.replace("dude", "d00d");

            //cool -> kewl
            str = str.replace("cool", "kewl");

            //porn -> pr0n
            str = str.replace("porn", "pr0n");

            //the -> teh
            str = str.replace(" the ", " teh ");

            //you -> u
            str = str.replace(" you ", " u ");

            //are -> r
            str = str.replace(" are ", " r ");

            //why -> y
            str = str.replace(" why ", " y ");

            //nice -> n1
            str = str.replace(" nice ", " n1 ");

            //winner -> winnar
            str = str.replace(" winner ", " winnar ");

            return str;
        }

        if (Math.random()<0.5 && !numericOnly) {
            str = str.replace("a", "/-\\");
        } else {
            str = str.replace("a", "4");
        }

        if (Math.random()<0.5 && !numericOnly) {
            str = str.replace("b", "|3");
        } else {
            str = str.replace("b", "8");
        }

        if (Math.random()<0.5 && !numericOnly) {
            str = str.replace("e", "€");
        } else {
            str = str.replace("e", "3");            
        }

        str = str.replace("g", "9");

        if (Math.random()<0.5 && !numericOnly) {
            str = str.replace("i", "!");
        } else {
            str = str.replace("i", "1");
        }

        str = str.replace("o", "0");

        str = str.replace("s", "5");

        str = str.replace("t", "7");

        str = str.replace("z", "2");

        if (numericOnly) {
            return str;
        }

        str = str.replace("c", "(");

        str = str.replace("d", "|)");

        if (Math.random()<0.5) {
            str = str.replace("f", "|=");
        } else {
            str = str.replace("f", "|\"");
        }

        if (Math.random()<0.5) {
            str = str.replace("h", "|-|");
        } else {
            str = str.replace("h", "#");
        }

        str = str.replace("j", "_|");

        if (Math.random()<0.5) {
            str = str.replace("k", "|<");
        } else {
            str = str.replace("k", "|(");
        }

        str = str.replace("l", "|_");

        str = str.replace("m", "/\\/\\");

        if (Math.random()<0.5) {
            str = str.replace("n", "/\\/");
        } else {
            str = str.replace("n", "|\\|");
        }

        str = str.replace("p", "|°");

        str = str.replace("q", "0,");

        str = str.replace("r", "|2");

        str = str.replace("u", "|_|");

        str = str.replace("v", "\\/");

        if (Math.random()<0.5) {
            str = str.replace("w", "\\/\\/");
        } else {
            str = str.replace("w", "VV");
        }

        str = str.replace("x", "><");

        str = str.replace("y", "°/");

        return str;
    }
}
